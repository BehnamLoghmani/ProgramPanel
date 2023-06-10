## Overview

The Plugin module is a fundamental component of ProgramPanel, facilitating easy extension and customization. The
plugins, compiled into JAR files and moved to `data/plugins`, are dynamically loaded by the ProgramPanel system at
runtime. While not necessary, the default plugin were included in this repository as it streamlines the development
process and facilitates efficient integration testing. No other modules should directly depend on this module.

## Plugin Metadata

* **Name**: The unique identifier of the plugin.
* **Author**: The individual or organization maintaining the plugin.
* **Description**: A concise explanation of the plugin's functionality.
* **Version**: The plugin's version, adhering to semantic versioning rules (major.minor.patch).

## Plugin Creation Guide

To create a plugin, follow the given guidelines:

1. Implement the `Plugin` interface or one of its sub-interfaces.
2. Compile the plugins into JARs.
3. Place the JAR files in the `data/plugins` directory.
4. Ensure that plugin files have a class name ending with the suffix `Plugin`.

Adhering to these guidelines will ensure the appropriate structure of your plugins, allowing them to be dynamically
incorporated into the system from the data/plugins directory.

### Plugin Interfaces

The following interfaces are provided for plugin development:

* `Plugin` - The base interface for all plugins.
* `DatabasePlugin` - A specific plugin interface for handling database connections and performing CRUD operations.

Each plugin interface can have multiple implementations. For instance, various DatabasePlugin implementations can be
designed, each connecting to a different database.

### Reference Implementation

The Plugin module includes default plugins that can serve as excellent reference points for developing new plugins.
Use these plugins to understand the structure and required functionalities when creating your plugins.
