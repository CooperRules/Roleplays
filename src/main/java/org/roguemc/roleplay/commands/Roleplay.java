package org.roguemc.roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Roleplay implements Listener {
    public Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Roleplay");

    public void opener(Player sender) {
        if (sender instanceof Player) {
            Player p = sender.getPlayer();
            if (p.hasPermission("roleplay.rp")) {
                String configgetter = this.plugin.getConfig().getString("gui-name");
                Inventory inv = Bukkit.createInventory(p, 27, ChatColor.translateAlternateColorCodes('&', configgetter));
                String bookname = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("browse-name"));
                ItemStack browse = new ItemStack(Material.BOOK);
                ItemMeta meta = browse.getItemMeta();
                meta.setDisplayName(bookname);
                List<String> lore1 = new ArrayList<>();
                for (String all : this.plugin.getConfig().getStringList("browse-lore")) {
                    lore1.add(ChatColor.translateAlternateColorCodes('&', all));
                }
                meta.setLore(lore1);
                browse.setItemMeta(meta);
                inv.setItem(11, browse);

                String explainname = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("explanation-name"));
                ItemStack explain = new ItemStack(Material.NETHER_STAR);
                ItemMeta meta2 = explain.getItemMeta();
                meta2.setDisplayName(explainname);
                List<String> lore2 = new ArrayList<>();
                for (String all : this.plugin.getConfig().getStringList("explanation-lore")) {
                    lore2.add(ChatColor.translateAlternateColorCodes('&', all));
                }
                meta2.setLore(lore2);
                explain.setItemMeta(meta2);
                inv.setItem(13, explain);

                String createrp = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("create-name"));
                ItemStack create = new ItemStack(Material.ITEM_FRAME);
                ItemMeta meta3 = create.getItemMeta();
                meta3.setDisplayName(createrp);
                List<String> lore3 = new ArrayList<>();
                for (String all : this.plugin.getConfig().getStringList("create-lore")) {
                    lore3.add(ChatColor.translateAlternateColorCodes('&', all));
                }
                meta3.setLore(lore3);
                create.setItemMeta(meta3);
                inv.setItem(15, create);
                p.openInventory(inv);


            } else {
                String s = this.plugin.getConfig().getString("no-permission");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        } else {
            sender.sendMessage("You must be a player to execute this command.");
        }


    }

    public Plugin getPlugin() {
        return plugin;
    }

}
