package com.drakkens.beaconAreas.commands.beaconCreation;

import com.drakkens.beaconAreas.commands.SubCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

import static com.drakkens.beaconAreas.BeaconAreas.beaconLocations;

public class NewBeaconAreaCommand extends SubCommand {
    public NewBeaconAreaCommand() {
        super("new",
                "Creates a new beacon area",
                "/beaconarea new <blockPosition> <beaconName>",
                "beaconareas.new");
    }

    //    https://www.spigotmc.org/wiki/create-a-simple-command/
//    https://stackoverflow.com/questions/68240438/set-command-arguments-type-in-minecraft-plugin-java
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (args.length < 4) {
            sender.sendMessage(this.usage);
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

        if (args.length == 2) {
            return List.of("beaconName");

        }

        return List.of();
    }
}
