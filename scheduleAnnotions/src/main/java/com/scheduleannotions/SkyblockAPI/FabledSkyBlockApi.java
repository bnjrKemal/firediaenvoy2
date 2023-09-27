package com.scheduleannotions.SkyblockAPI;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.island.IslandEnvironment;
import com.songoda.skyblock.island.IslandWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class FabledSkyBlockApi {

    public Location getIslandLocation(UUID uuid){

        for(World world : Bukkit.getWorlds()){
            if(SkyBlockAPI.getImplementation().getWorldManager().isIslandWorld(world)){
                if(SkyBlockAPI.getIslandManager().getIslandByUUID(uuid).isLoaded()){
                    IslandWorld islandWorld = SkyBlockAPI.getImplementation().getWorldManager().getIslandWorld(world);
                    return SkyBlockAPI.getImplementation().getIslandManager().getIslandByUUID(uuid).getIslandLocation(islandWorld, IslandEnvironment.Island).getLocation();
                }
            }
        }
        return null;
    }

}
