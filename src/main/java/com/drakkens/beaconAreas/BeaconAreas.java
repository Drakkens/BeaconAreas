package com.drakkens.beaconAreas;

import com.drakkens.beaconAreas.commands.CommandSetup;
import com.drakkens.beaconAreas.events.BeaconGUIBlocker;
import com.drakkens.beaconAreas.models.BeaconEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
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
        CommandSetup setup = new CommandSetup();
        Objects.requireNonNull(getCommand("setup")).setExecutor(setup);
        Objects.requireNonNull(getCommand("setup")).setTabCompleter(setup);

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
            for (Location loc : beaconLocations) {
                BeaconEffect effect = new BeaconEffect();
                if (effect.isT4(loc)) {
//                    loc.getWorld().strikeLightningEffect(loc);
//                  ToDo: Apply Haste to Players in corresponding Region
                }

            }
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
