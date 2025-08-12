package com.drakkens.beaconAreas.models;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;

//ToDo: Refactor this into a utility class
public class BeaconEffect {
    public boolean isT4(Location beaconLocation) {
        Beacon beacon = (Beacon) beaconLocation.getBlock().getState();
        return beacon.getTier() == 4;

    }
}
