package org.roguemc.roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.roguemc.roleplay.Roleplay;

public class Reload implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("emotes.reload")) {
            Roleplay.plugin.reloadConfig();
            Roleplay.plugin.start();
            sender.sendMessage(ChatColor.RED + "Emotes reloaded.");
        } else {
            sender.sendMessage("You don't have perms");
        }
        return true;
    }
}
