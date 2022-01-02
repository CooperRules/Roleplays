package org.roguemc.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventsClass implements Listener {
    private Plugin plugin = Roleplay.getPlugin(Roleplay.class);
    ChatRoom chatRoom = new ChatRoom();

    @EventHandler
    public void InvenClick(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        ClickType click = event.getClick();
        Inventory open = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();
        InventoryView view = event.getView();
        String checker = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gui-name"));
        if (view.getTitle().equals(checker)) {
            event.setCancelled(true);
            String itemsname = meta.getDisplayName();
            List<String> itemslore = meta.getLore();
            if(itemsname.equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("create-name")))){
                p.sendMessage("true");
                UUID plyruu = p.getUniqueId();
                chatRoom.createRoom(p.getDisplayName(), p.getName());
                }
            else {
                p.sendMessage("false");
            }

        }
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (chatRoom.isInRoom(p.getName())){
            e.setCancelled(true);
            p.sendMessage("hey ur in a room!");
            for (String pa : chatRoom.getRoomPlayers(p.getName()) ) {
                String msg = e.getMessage();
                Player playa = Bukkit.getPlayer(pa);
                playa.sendMessage(ChatColor.BLUE + p.getName() + ":" + " " + ChatColor.RED + msg);

            }
        }
        else {

        }
}}
