package org.roguemc.roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.roguemc.roleplay.Roleplay;

public class Reload implements CommandExecutor {
    private final Roleplay plugin;

    public Reload(Roleplay plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("emotes.reload")) {
            plugin.reloadConfig();
            plugin.start();
            sender.sendMessage(ChatColor.RED + "Emotes reloaded.");
        } else {
            sender.sendMessage("You don't have perms");
        }
        return true;
    }
}
