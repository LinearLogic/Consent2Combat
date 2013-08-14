package com.entrocorp.linearlogic.consent2combat;

import org.bukkit.plugin.java.JavaPlugin;

public class Consent2Combat extends JavaPlugin {

    private boolean verbose;

    @Override
    public void onEnable() {
        getLogger().info("Enabled. En garde!");
    }

    @Override
    public void onDisable() {

    }

    public boolean isVerbose() {
        return verbose;
    }
}
