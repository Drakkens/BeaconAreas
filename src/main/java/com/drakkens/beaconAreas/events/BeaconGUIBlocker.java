package com.drakkens.beaconAreas.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class BeaconGUIBlocker implements Listener {
    
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;

        Block targetedBlock = player.getTargetBlock(null, 5);
        if (targetedBlock.getType() != Material.BEACON) return;

        Location blockPos = targetedBlock.getLocation();
//        if (blockPos in registeredBeacons) {
//          event.setCancelled(true);
//          player.sendMessage("This beacon is restricted");
//        }
    }

}
