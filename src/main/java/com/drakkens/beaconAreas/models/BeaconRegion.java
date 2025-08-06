package com.drakkens.beaconAreas.models;

import org.bukkit.Location;
import org.bukkit.World;

public class BeaconRegion {
    Location minLoc;
    Location maxLoc;


    public BeaconRegion(Location minLoc, Location maxLoc) {
        this.minLoc = minLoc;
        this.maxLoc = maxLoc;

    }

    public BeaconRegion(World world, double x1, double z1, double x2, double z2) {
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minZ = Math.min(z1, z2);
        double maxZ = Math.max(z1, z2);

        this.minLoc = new Location(world, minX, 0.0, minZ);
        this.maxLoc = new Location(world, maxX, 256, maxZ);
    }

    public boolean contains(Location location) {
        if (location.getX() < minLoc.getX() || location.getX() > maxLoc.getX()) return false;
        if (location.getZ() < minLoc.getZ() || location.getZ() > maxLoc.getZ()) return false;
        return true;

    }

}
