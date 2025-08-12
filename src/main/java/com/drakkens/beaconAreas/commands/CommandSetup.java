package com.drakkens.beaconAreas.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.List;

import static com.drakkens.beaconAreas.BeaconAreas.beaconLocations;

public class CommandSetup implements CommandExecutor, TabCompleter {
    //    https://www.spigotmc.org/wiki/create-a-simple-command/
//    https://stackoverflow.com/questions/68240438/set-command-arguments-type-in-minecraft-plugin-java
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (args.length < 3)  {
            sender.sendMessage("/setup <blockPosition>");
            return false;
        }

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);

        Location targetLocation = new Location(player.getWorld(), x, y, z);
        if (targetLocation.getBlock().getType() != Material.BEACON) {
            sender.sendMessage("Target block is not a beacon");
            return false;
        }

        if (beaconLocations.contains(targetLocation)) {
            sender.sendMessage("Target block is already configured");
        }

        beaconLocations.add(targetLocation);
        sender.sendMessage("Added beacon location");

        return true;

    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            Block target = player.getTargetBlockExact(5);

            if (target != null) {
                Location loc = target.getLocation();
                String coords = loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
                return List.of(coords);
            }

        }

        return List.of();
    }
}
