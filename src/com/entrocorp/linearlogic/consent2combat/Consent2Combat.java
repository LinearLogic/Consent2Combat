package com.entrocorp.linearlogic.consent2combat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Consent2Combat extends JavaPlugin {

    private final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + ChatColor.ITALIC + "C" +
            ChatColor.DARK_GRAY + "2" + ChatColor.DARK_RED + ChatColor.BOLD + ChatColor.ITALIC + "C" + ChatColor.GRAY + "] ";

    private boolean cancelOnDeath;
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

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player && !sender.hasPermission("consent2combat.reload")) {
                sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to reload the config.");
                return true;
            }
            reloadConfig();
            sender.sendMessage(prefix + ChatColor.GREEN + "Reloaded the config.");
            return true;
        }
        sender.sendMessage(prefix + "Running version " + ChatColor.LIGHT_PURPLE + getDescription().getVersion() +
                ChatColor.GRAY + " by LinearLogic");
        return true;
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        cancelOnDeath = getConfig().getBoolean("cancel-duels-on-death");
        verbose = getConfig().getBoolean("verbose");
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean cancelDuelsOnDeath() {
        return cancelOnDeath;
    }

    public boolean isVerbose() {
        return verbose;
    }
}
