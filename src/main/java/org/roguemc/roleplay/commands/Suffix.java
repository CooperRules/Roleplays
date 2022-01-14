package org.roguemc.roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.roguemc.roleplay.Roleplay;

public class Suffix implements CommandExecutor {
    private final Roleplay plugin;

    public Suffix(Roleplay plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            if (p.hasPermission("datpixel.suffix")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "Please provide your suffix!");
                }
                if (!(args.length == 0)) {
                    String suffixtoset = args[0];
                    if (suffixtoset.contains("&1")) {
                        p.sendMessage(ChatColor.RED + "You cannot use dark blue in your suffix!");
                    }
                    if (suffixtoset.contains("&0")) {
                        p.sendMessage(ChatColor.RED + "You cannot use black in your suffix!");
                    }
                    if (suffixtoset.contains("admin")) {
                        p.sendMessage(ChatColor.RED + "Please do not impersonate staff.");
                    }
                    if (suffixtoset == "off") {
                        Roleplay.getChat().setPlayerSuffix(p, "");
                    } else {
                        String newsuffix = ChatColor.translateAlternateColorCodes('&', suffixtoset);
                        Roleplay.getChat().setPlayerSuffix(p, " " + newsuffix);
                    }
                }
            }
        }
        return true;
    }
}
