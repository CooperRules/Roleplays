package org.roguemc.roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.roguemc.roleplay.ChatRoom;
import org.roguemc.roleplay.Roleplay;

import static org.bukkit.Bukkit.getPlayer;

public class Invite implements CommandExecutor {
    ChatRoom chatRoom = new ChatRoom();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = sender.getServer().getPlayer(sender.getName());
            if (args.length == 0) {
                new org.roguemc.roleplay.commands.Roleplay().opener(p);
            } else {
                if (args[0].equals("invite")) {
                    p.sendMessage("you used invite");
                    if (Roleplay.plugin.getChatRoom().isInRoom(p.getName())) {
                        Player target = null;
                        try {
                            target = getPlayer(args[1]);
                            if (!target.isOnline()) {
                                sender.sendMessage(ChatColor.RED + args[1] + " is not online!");
                                return true;
                            }
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + args[1] + " is not online!");
                            return true;
                        }
                        sender.sendMessage("You invited " + args[1] + " to your chatroom!");
                        String name = chatRoom.playerRooms.get(sender.getName());
                        target.sendMessage(sender.getName() + " has invited you to their chatroom named " + name + " to join, enter " + ChatColor.AQUA + "/chatroom join " + name);

                    } else {
                        p.sendMessage(ChatColor.RED + "You are not in a rp.");
                    }
                }
                if (args[0].equals("join")) {
                    if (args[1] != null) {
                        Roleplay.plugin.getChatRoom().joinRoom(args[1], p.getName());
                    }
                }
                if (args[0].equals("leave")) {
                    if (Roleplay.plugin.getChatRoom().isInRoom(p.getName())) {
                        Roleplay.plugin.getChatRoom().leaveRoom(p.getName());
                        String msger = Roleplay.plugin.getConfig().getString("leave-rp-msg");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msger));
                    }
                }
            }
        }


        return true;
    }
}
