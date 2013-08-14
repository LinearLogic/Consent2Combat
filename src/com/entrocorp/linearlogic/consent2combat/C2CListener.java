package com.entrocorp.linearlogic.consent2combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class C2CListener implements Listener {

    private Consent2Combat plugin;
    private List<Pair<Player, Player>> duelers;
    private HashMap<Player, HashSet<Player>> pending;

    public C2CListener(Consent2Combat instance) {
        plugin = instance;
        duelers = new ArrayList<Pair<Player, Player>>();
        pending = new HashMap<Player, HashSet<Player>>();
    }

    @EventHandler
    public void onHandshake(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player))
            return;
        Player clicked = (Player) event.getRightClicked();
        if (pending.containsKey(event.getPlayer())) {
            HashSet<Player> requesters = pending.get(event.getPlayer());
            if (requesters.remove(clicked)) { // Handshake complete
                duelers.add(getAlphabetizedPair(event.getPlayer(), clicked));
                event.getPlayer().sendMessage("You are now dueling with " + ChatColor.RED + clicked.getName() + "!");
                clicked.sendMessage("You are now dueling with " + ChatColor.RED + event.getPlayer().getName() + "!");
                if (plugin.isVerbose())
                    plugin.getLogger().info(event.getPlayer().getName() + " is now dueling with " + clicked.getName());
                if (requesters.size() == 0)
                    pending.remove(event.getPlayer());
                return;
            }
        }
        if (!pending.containsKey(clicked))
            pending.put(clicked, new HashSet<Player>());
        if (pending.get(clicked).add(event.getPlayer()))
            clicked.sendMessage(ChatColor.RED + event.getPlayer().getName() + ChatColor.GRAY + " wants to duel. " +
                    "Right-click the player to accept.");
    }

    private Pair<Player, Player> getAlphabetizedPair(Player p1, Player p2) {
        if (p1 == null || p2 == null || p1 == p2)
            return null;
        return p1.getName().compareTo(p2.getName()) < 0 ? new Pair<Player, Player>(p1, p2) : new Pair<Player, Player>(p2, p1);
    }
}
