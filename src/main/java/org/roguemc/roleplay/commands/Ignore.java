package org.roguemc.roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.roguemc.roleplay.Roleplay;

public class Ignore implements CommandExecutor {
    private final org.roguemc.roleplay.Roleplay plugin;

    public Ignore(Roleplay plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            if (sender.hasPermission("emotes.ignore")) {
                org.roguemc.roleplay.Roleplay.getPerms().playerRemove(p, "emotes.ignore");
                p.sendMessage(ChatColor.RED + "You are no longer ignoring emotes.");

            } else {
                org.roguemc.roleplay.Roleplay.getPerms().playerAdd(p, "emotes.ignore");
                p.sendMessage(ChatColor.RED + "You are now ignoring emotes.");
            }
        }
        return true;
    }
}
