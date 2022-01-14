package org.roguemc.roleplay;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.roguemc.roleplay.commands.*;

import java.lang.reflect.Field;

public final class Roleplay extends JavaPlugin implements Listener {
    public static Roleplay plugin;
    private static ChatRoom chatRoom;
    private static Permission perms;
    private static Chat chat;
    public Plugin rp = getServer().getPluginManager().getPlugin("Roleplay");

    public static Permission getPerms() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        plugin = this;
        chatRoom = new ChatRoom();
        setupChat();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("roleplay").setExecutor(new Invite());
        this.getCommand("rp").setExecutor(new Invite());
        this.getCommand("noemotes").setExecutor(new Ignore());
        this.getCommand("emotes").setExecutor(new Help());
        this.getCommand("emotesreload").setExecutor(new Reload());
        this.getCommand("suffix").setExecutor(new Suffix(this));
        this.getCommand("suffixclear").setExecutor(new SuffixClear());

        getServer().getPluginManager().registerEvents(new EventsClass(this), this);
        this.start();
        this.setupPermissions();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void start() {
        Field bukkitCommandMap = null;
        try {
            bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = null;
        try {
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        for (final String key : this.getConfig().getConfigurationSection("Emotes").getKeys(false)) {
            commandMap.register(key, new CommandHandler(key));
        }
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
}

