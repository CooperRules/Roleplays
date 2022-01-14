package org.roguemc.roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.roguemc.roleplay.Roleplay;

public class SuffixClear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            if (p.hasPermission("datpixel.clearsuffixs")) {
                if (args.length != 0) {
                    String playername = args[0];
                    Player p2 = Bukkit.getPlayer(playername);
                    Roleplay.getChat().setPlayerSuffix(p2, "");
                }
            }
        }
        return true;
    }
}
