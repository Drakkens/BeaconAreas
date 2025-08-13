package com.drakkens.beaconAreas.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Beacon;

public class BeaconUtils {

    public static boolean isT4(Location beaconLocation) {
        if (beaconLocation.getBlock().getType() != Material.BEACON) return false;

        Beacon beacon = (Beacon) beaconLocation.getBlock().getState();
        return beacon.getTier() == 4;

    }

}
