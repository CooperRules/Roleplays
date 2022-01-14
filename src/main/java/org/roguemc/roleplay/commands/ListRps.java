package org.roguemc.roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.roguemc.roleplay.ChatRoom;
import org.roguemc.roleplay.Roleplay;

import java.util.ArrayList;

public class ListRps {
    private final Roleplay plugin;
    ChatRoom chatRoom = new ChatRoom();

    public ListRps(Roleplay plugin) {
        this.plugin = plugin;
        this.chatRoom = plugin.getChatRoom();
    }

    public void lister(Player sender) {

        Player p = sender;
        String configgetter = this.plugin.getConfig().getString("list-rps-name");
        Inventory inv = Bukkit.createInventory(p, 45, ChatColor.translateAlternateColorCodes('&', configgetter));
        ArrayList<String> rplist = new ArrayList<>();
        for (Player a : Bukkit.getOnlinePlayers()) {
            if (plugin.getChatRoom().isInRoom(a.getName())) {
                if (!(rplist.contains(a.getName()))) {
                    rplist.add(a.getName());
                }

            }
        }
        for (String tester : rplist) {

        }
    }

}
