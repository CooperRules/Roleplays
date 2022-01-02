package org.roguemc.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Roleplay extends JavaPlugin implements Listener {
    public Plugin rp = getServer().getPluginManager().getPlugin("Roleplay");
    private static Roleplay instance;
    ChatRoom room;

    public ChatRoom getRoom() {
        return room;
    }

    public void setRoom(ChatRoom room) {
        this.room = room;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        instance = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("rp").setExecutor(new org.roguemc.roleplay.commands.Roleplay());
        this.getCommand("rpinvite").setExecutor(new org.roguemc.roleplay.commands.Invite());
        getServer().getPluginManager().registerEvents(new EventsClass(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    }

