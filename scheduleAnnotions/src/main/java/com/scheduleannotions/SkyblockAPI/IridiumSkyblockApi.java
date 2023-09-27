package com.scheduleannotions.SkyblockAPI;

import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.database.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class IridiumSkyblockApi {

    IridiumSkyblockAPI api = IridiumSkyblockAPI.getInstance();
    public Location getIslandLocation(UUID uuid){
        User player = api.getUser(Bukkit.getPlayer(uuid));
        if (player.getIsland().isPresent()){
            return player.getIsland().get().getHome();
        }
        return null;
    }
}

