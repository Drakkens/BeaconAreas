package com.drakkens.beaconAreas.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CommandSetup implements CommandExecutor {
//    https://www.spigotmc.org/wiki/create-a-simple-command/
//    https://stackoverflow.com/questions/68240438/set-command-arguments-type-in-minecraft-plugin-java
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        return false;
        //ToDo: Setup command.
        //Args: Location
        //Check if block is beacon.
        //Add to beacon list.



    }
}
