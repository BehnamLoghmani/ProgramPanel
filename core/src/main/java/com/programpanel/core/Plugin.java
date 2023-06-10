package com.programpanel.core;

public interface Plugin {
    void onLoad();

    void onUnload();

    void onEnable();

    void onDisable();
}
