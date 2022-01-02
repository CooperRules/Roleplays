package org.roguemc.roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.roguemc.roleplay.ChatRoom;
import org.roguemc.roleplay.EventsClass;
import org.roguemc.roleplay.Roleplay;

public class Invite implements CommandExecutor {
    private Plugin plugin = org.roguemc.roleplay.Roleplay.getPlugin(Roleplay.class);
    ChatRoom chatRoom = new ChatRoom();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            if (chatRoom.isInRoom(p.getName())){
                p.sendMessage("you are in a room");
                if (args[0] != null) {
                    p.sendMessage("args0 not null");
                    String otherP = args[0];
                    Player p2 = Bukkit.getPlayer(otherP);
                    if (p2.isOnline()) {
                        p2.sendMessage(plugin.getConfig().getString("invited-to-rp"));
                        p.sendMessage(plugin.getConfig().getString("success-invited"));
                        chatRoom.joinRoom(p.getName(), p.getName());
                    }

                }
            }
            else {
                p.sendMessage("not in a room?");
            }
        }

        return true;
    }
}
