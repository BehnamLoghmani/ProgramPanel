package com.programpanel.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PluginLoaderTest {
    @Test
    public void testLoadDefaultPlugins() {
        final var pluginLoader = new PluginLoader();
        final var plugins = pluginLoader.loadPlugins("../data/plugins");
        assertNotNull(plugins);
        assertEquals(1, plugins.size());
    }
}
