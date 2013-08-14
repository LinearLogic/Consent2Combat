package com.entrocorp.linearlogic.consent2combat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Consent2Combat extends JavaPlugin {

    private final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + ChatColor.ITALIC + "C" +
            ChatColor.DARK_GRAY + "2" + ChatColor.DARK_RED + ChatColor.BOLD + ChatColor.ITALIC + "C" + ChatColor.GRAY + "] ";
    private boolean verbose;

    @Override
    public void onEnable() {
        getLogger().info("Loading: config");
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("Loading: listener");
        getServer().getPluginManager().registerEvents(new C2CListener(this), this);
        getLogger().info("Enabled. En garde!");
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        verbose = getConfig().getBoolean("verbose");
    }

    public String getPrefix() {
        return prefix;
    }
    public boolean isVerbose() {
        return verbose;
    }
}
