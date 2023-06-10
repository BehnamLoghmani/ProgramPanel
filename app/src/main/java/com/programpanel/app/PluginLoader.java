package com.programpanel.app;

import com.programpanel.core.Plugin;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class PluginLoader {
    public List<Plugin> loadPlugins(String directoryPath) {
        final var plugins = new ArrayList<Plugin>();
        final var directory = getPluginsDirectory(directoryPath);
        final var jarFiles = getJarFilesInDirectory(directory);
        if (jarFiles == null) {
            return List.of();
        }
        for (final var jarFile : jarFiles) {
            try (final var jar = new JarFile(jarFile)) {
                final var url = getFileUrl(jarFile);
                final var classLoader = getClassLoader(url);
                final var entries = jar.entries();
                while (entries.hasMoreElements()) {
                    // Check entry file type
                    final var entry = entries.nextElement();
                    if (entry.isDirectory()) continue;
                    if (!isClassFile(entry)) continue;

                    // Check class name
                    final var className = resolveClassName(entry);
                    if (className.contains("module-info")) continue;
                    if (!className.endsWith("Plugin")) continue;

                    // Check class type
                    final var clazz = classLoader.loadClass(className);
                    if (clazz.isInterface()) continue;
                    if (!Plugin.class.isAssignableFrom(clazz)) continue;

                    // Load plugin
                    final var plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
                    plugin.onLoad();
                    plugins.add(plugin);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return plugins;
    }

    private File getPluginsDirectory(String directoryPath) {
        final var directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + directory.getAbsolutePath());
        }
        return directory;
    }

    private File[] getJarFilesInDirectory(File directory) {
        return directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
    }

    private URL getFileUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private URLClassLoader getClassLoader(URL url) {
        return new URLClassLoader(new URL[]{url}, Plugin.class.getClassLoader());
    }

    private boolean isClassFile(JarEntry entry) {
        return entry.getName().endsWith(".class");
    }

    private String resolveClassName(JarEntry entry) {
        return entry.getName().substring(0, entry.getName().length() - 6).replace('/', '.');
    }
}
