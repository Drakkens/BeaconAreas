package com.drakkens.beaconAreas;

import com.drakkens.beaconAreas.commands.beaconCreation.NewBeaconAreaCommand;
import com.drakkens.beaconAreas.events.BeaconGUIBlocker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BeaconAreas extends JavaPlugin {
    public static ArrayList<Location> beaconLocations = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        NewBeaconAreaCommand newBeaconArea = new NewBeaconAreaCommand();
        Objects.requireNonNull(getCommand("setup")).setExecutor(newBeaconArea);
        Objects.requireNonNull(getCommand("setup")).setTabCompleter(newBeaconArea);

        File beaconYaml = new File(getDataFolder(), "beacons.yml");
        if (beaconYaml.exists()) {
            List<?> list = YamlConfiguration.loadConfiguration(beaconYaml).getList("beaconLocations");
            if (list != null) {
                beaconLocations.clear();
                for (Object obj : list) {
                    if (obj instanceof Location loc) {
                        beaconLocations.add(loc);
                    }
                }
            }
        }

        getServer().getPluginManager().registerEvents(new BeaconGUIBlocker(), this);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
//            for (BeaconRegion region : beaconRegions) {

        }, 20, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        FileConfiguration config = getConfig();
        config.set("beaconLocations", beaconLocations);
        try {
            config.save(getDataFolder() + "/beacons.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
