package org.roguemc.roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.roguemc.roleplay.Roleplay;

public class Help implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            for (String messages : Roleplay.plugin.getConfig().getStringList("help-menu")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
            }
        }
        return true;
    }
}
