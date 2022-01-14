package org.roguemc.roleplay;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler extends BukkitCommand {
    private final Plugin plugin;

    public CommandHandler(final String name) {
        super(name);
        this.plugin = Bukkit.getPluginManager().getPlugin("Roleplay");
        this.description = "Preform command";
        this.usageMessage = "/" + name + " <player>";
        this.setPermission("emotes." + name);
        this.setAliases(new ArrayList());
    }

    public Particle getParticle(final String Type) {
        Particle p = null;
        if (Type.equalsIgnoreCase("BARRIER")) {
            p = Particle.BLOCK_CRACK;
        }
        if (Type.equalsIgnoreCase("BLOCK_CRACK")) {
            p = Particle.BLOCK_CRACK;
        }
        if (Type.equalsIgnoreCase("BLOCK_DUST")) {
            p = Particle.BLOCK_DUST;
        }
        if (Type.equalsIgnoreCase("CLOUD")) {
            p = Particle.CLOUD;
        }
        if (Type.equalsIgnoreCase("CRIT")) {
            p = Particle.CRIT;
        }
        if (Type.equalsIgnoreCase("CRIT_MAGIC")) {
            p = Particle.CRIT_MAGIC;
        }
        if (Type.equalsIgnoreCase("DAMAGE_INDICATOR")) {
            p = Particle.DAMAGE_INDICATOR;
        }
        if (Type.equalsIgnoreCase("DRAGON_BREATH")) {
            p = Particle.DRAGON_BREATH;
        }
        if (Type.equalsIgnoreCase("DRIP_LAVA")) {
            p = Particle.DRIP_LAVA;
        }
        if (Type.equalsIgnoreCase("DRIP_WATER")) {
            p = Particle.DRIP_WATER;
        }
        if (Type.equalsIgnoreCase("ENCHANTMENT_TABLE")) {
            p = Particle.ENCHANTMENT_TABLE;
        }
        if (Type.equalsIgnoreCase("END_ROD")) {
            p = Particle.END_ROD;
        }
        if (Type.equalsIgnoreCase("EXPLOSION_HUGE")) {
            p = Particle.EXPLOSION_HUGE;
        }
        if (Type.equalsIgnoreCase("EXPLOSION_LARGE")) {
            p = Particle.EXPLOSION_LARGE;
        }
        if (Type.equalsIgnoreCase("EXPLOSION_NORMAL")) {
            p = Particle.EXPLOSION_NORMAL;
        }
        if (Type.equalsIgnoreCase("FALLING_DUST")) {
            p = Particle.FALLING_DUST;
        }
        if (Type.equalsIgnoreCase("FIREWORKS_SPARK")) {
            p = Particle.FIREWORKS_SPARK;
        }
        if (Type.equalsIgnoreCase("FLAME")) {
            p = Particle.FLAME;
        }
        if (Type.equalsIgnoreCase("FOOTSTEP")) {
            p = Particle.LAVA;
        }
        if (Type.equalsIgnoreCase("HEART")) {
            p = Particle.HEART;
        }
        if (Type.equalsIgnoreCase("ITEM_CRACK")) {
            p = Particle.ITEM_CRACK;
        }
        if (Type.equalsIgnoreCase("ITEM_TAKE")) {
            p = Particle.HEART;
        }
        if (Type.equalsIgnoreCase("LAVA")) {
            p = Particle.LAVA;
        }
        if (Type.equalsIgnoreCase("MOB_APPEARANCE")) {
            p = Particle.MOB_APPEARANCE;
        }
        if (Type.equalsIgnoreCase("NOTE")) {
            p = Particle.NOTE;
        }
        if (Type.equalsIgnoreCase("PORTAL")) {
            p = Particle.PORTAL;
        }
        if (Type.equalsIgnoreCase("REDSTONE")) {
            p = Particle.REDSTONE;
        }
        if (Type.equalsIgnoreCase("SLIME")) {
            p = Particle.SLIME;
        }
        if (Type.equalsIgnoreCase("SMOKE_LARGE")) {
            p = Particle.SMOKE_LARGE;
        }
        if (Type.equalsIgnoreCase("SMOKE_NORMAL")) {
            p = Particle.SMOKE_NORMAL;
        }
        if (Type.equalsIgnoreCase("SNOW_SHOVEL")) {
            p = Particle.SNOW_SHOVEL;
        }
        if (Type.equalsIgnoreCase("SNOWBALL")) {
            p = Particle.SNOWBALL;
        }
        if (Type.equalsIgnoreCase("SPELL")) {
            p = Particle.SPELL;
        }
        if (Type.equalsIgnoreCase("SPELL_INSTANT")) {
            p = Particle.SPELL_INSTANT;
        }
        if (Type.equalsIgnoreCase("SPELL_MOB")) {
            p = Particle.SPELL_MOB;
        }
        if (Type.equalsIgnoreCase("SPELL_MOB_AMBIENT")) {
            p = Particle.SPELL_MOB_AMBIENT;
        }
        if (Type.equalsIgnoreCase("SPELL_WITCH")) {
            p = Particle.SPELL_WITCH;
        }
        if (Type.equalsIgnoreCase("SUSPENDED")) {
            p = Particle.SUSPENDED;
        }
        if (Type.equalsIgnoreCase("SUSPENDED_DEPTH")) {
            p = Particle.SUSPENDED_DEPTH;
        }
        if (Type.equalsIgnoreCase("SWEEP_ATTACK")) {
            p = Particle.SWEEP_ATTACK;
        }
        if (Type.equalsIgnoreCase("TOWN_AURA")) {
            p = Particle.TOWN_AURA;
        }
        if (Type.equalsIgnoreCase("VILLAGER_ANGRY")) {
            p = Particle.VILLAGER_ANGRY;
        }
        if (Type.equalsIgnoreCase("VILLAGER_HAPPY")) {
            p = Particle.VILLAGER_HAPPY;
        }
        if (Type.equalsIgnoreCase("WATER_BUBBLE")) {
            p = Particle.WATER_BUBBLE;
        }
        if (Type.equalsIgnoreCase("WATER_DROP")) {
            p = Particle.WATER_DROP;
        }
        if (Type.equalsIgnoreCase("WATER_SPLASH")) {
            p = Particle.WATER_SPLASH;
        }
        if (Type.equalsIgnoreCase("WATER_WAKE")) {
            p = Particle.WATER_WAKE;
        }
        return p;
    }

    public void sendParticle(final Player p, final Player reciving, final String particle) {
        final Location p2 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2.0, p.getLocation().getZ());
        final Location p3 = new Location(reciving.getWorld(), reciving.getLocation().getX(), reciving.getLocation().getY() + 2.0, reciving.getLocation().getZ());
        reciving.spawnParticle(this.getParticle(particle), p2, 5);
        p.spawnParticle(this.getParticle(particle), p3, 5);
        final List<Entity> entitylist = p.getNearbyEntities(20.0, 20.0, 20.0);
        for (int t = 0; t < entitylist.size(); ++t) {
            if (entitylist.get(t).getType() == EntityType.PLAYER) {
                final Player near = (Player) entitylist.get(t);
                near.spawnParticle(this.getParticle(particle), p2, 5);
            }
        }
        final List<Entity> entitylist2 = reciving.getNearbyEntities(20.0, 20.0, 20.0);
        for (int t2 = 0; t2 < entitylist2.size(); ++t2) {
            if (entitylist2.get(t2).getType() == EntityType.PLAYER) {
                final Player near2 = (Player) entitylist2.get(t2);
                near2.spawnParticle(this.getParticle(particle), p3, 5);
            }
        }
    }

    public boolean execute(final CommandSender sender, final String cmd, final String[] args) {
        final Player p = (Player) sender;
        for (final String key : Roleplay.plugin.getConfig().getConfigurationSection("Emotes").getKeys(false)) {
            if (cmd.equalsIgnoreCase(key)) {
                if (p.hasPermission("emotes." + key)) {
                    if (args.length > 0) {
                        final Player reciving = Bukkit.getPlayer(args[0]);
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                            if (!(reciving.hasPermission("emotes.ignore"))) {
                                final String particle = Roleplay.plugin.getConfig().getString("Emotes." + key + ".particle");
                                final String sound = Roleplay.plugin.getConfig().getString("Emotes." + key + ".sound");
                                if (!particle.equalsIgnoreCase("false")) {
                                    this.sendParticle(p, reciving, particle);
                                }
                                if (!sound.equalsIgnoreCase("false")) {
                                    p.playSound(p.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                                    reciving.playSound(reciving.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                                }
                                for (String messages : Roleplay.plugin.getConfig().getStringList("Emotes." + key + ".tomessages")) {
                                    messages = messages.replace("%player%", reciving.getDisplayName());
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
                                }
                                for (String messages : Roleplay.plugin.getConfig().getStringList("Emotes." + key + ".frommessages")) {
                                    messages = messages.replace("%player%", p.getDisplayName());
                                    reciving.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
                                }
                                for (String messages : Roleplay.plugin.getConfig().getStringList("Emotes." + key + ".broadcastmsgs")) {
                                    messages = messages.replace("%player%", reciving.getDisplayName());
                                    messages = messages.replace("%target%", p.getDisplayName());
                                    for (Player player : Bukkit.getOnlinePlayers()) {

                                        if (!(player == p | player == reciving)) {
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
                                        }
                                    }


                                }
                            } else {
                                p.sendMessage("Sorry but " + args[0] + " has ignored all emotes.");
                            }
                        } else {
                            p.sendMessage(args[0] + " is not online");
                        }
                    } else {
                        p.sendMessage("You must type a players name");
                    }
                } else {
                    p.sendMessage("Sorry but you don't have permission for this Emote");
                }
            }
        }
        return false;
    }
}


