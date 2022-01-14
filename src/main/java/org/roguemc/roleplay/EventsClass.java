package org.roguemc.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.List;

public class EventsClass implements Listener {
    private final Roleplay plugin;

    ChatRoom chatRoom = new ChatRoom();

    public EventsClass(Roleplay plugin) {
        this.plugin = plugin;
        this.chatRoom = plugin.getChatRoom();
    }
    @EventHandler
    public void format(AsyncPlayerChatEvent event) {
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        String prefix = ChatColor.translateAlternateColorCodes('&', Roleplay.getChat().getPlayerPrefix(event.getPlayer()));
        String suffix = ChatColor.translateAlternateColorCodes('&', Roleplay.getChat().getPlayerSuffix(event.getPlayer()));
        suffix = " " + suffix;
        if (Roleplay.getChat().getPlayerSuffix(event.getPlayer()) !=  "") {
            event.setFormat(prefix + event.getPlayer().getDisplayName() + suffix + " §7: " + "§r%2$s");
        }
        if (Roleplay.getChat().getPlayerSuffix(event.getPlayer()) ==  "") {
            event.setFormat(prefix + event.getPlayer().getDisplayName()  + " §7: " + "§r%2$s");
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (Roleplay.getPerms().playerInGroup(e.getPlayer(), "adminrank")){
            String playername = e.getPlayer().getName();
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(ChatColor.AQUA + "" +ChatColor.BOLD + "* ADMIN" + ChatColor.RESET + "" + ChatColor.YELLOW + "" + ChatColor.BOLD + " " + playername + " has joined the game!");
            Bukkit.broadcastMessage(" ");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player victim = (Player) event.getEntity();
        for (AttributeModifier modifier : victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getModifiers())
            victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).removeModifier(modifier);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) //when a player quits the game
    {
        Player player = e.getPlayer();
        plugin.getChatRoom().leaveRoom(player.getName());
    }

    @EventHandler
    public void InvenClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        String checker = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gui-name"));
        if (view.getTitle().equals(checker)) {
            Player p = (Player) event.getWhoClicked();
            ClickType click = event.getClick();
            Inventory open = event.getClickedInventory();
            ItemStack item = event.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            event.setCancelled(true);
            String itemsname = meta.getDisplayName();
            List<String> itemslore = meta.getLore();
            if (itemsname.equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("create-name")))) {
                int out = plugin.getChatRoom().createRoom(p.getName(), p.getName());
                p.sendMessage(ChatColor.AQUA + "created");

            }

        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (plugin.getChatRoom().isInRoom(p.getName())) {
            e.setCancelled(true);
            for (String pa : plugin.getChatRoom().getRoomPlayers(p.getName())) {
                String msg = e.getMessage();
                Player playa = Bukkit.getPlayer(pa);
                playa.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "RP" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " " + p.getName() + " : " + ChatColor.RESET + msg);

            }

        } else {

        }
    }

    @EventHandler
    public void onChat2(AsyncPlayerChatEvent e) {
        for (Iterator<Player> it = e.getRecipients().iterator(); it.hasNext(); ) {
            Player player = it.next();
            if (plugin.getChatRoom().isInRoom(player.getName())) {
                it.remove();
            }
        }

    }


}
