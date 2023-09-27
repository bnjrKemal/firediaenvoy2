package com.scheduleannotions.SkyblockAPI;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import org.bukkit.Location;

import java.util.UUID;

public class SuperiorSkyblockApi {

    public Location getIslandLocation(UUID uuid){
        if (SuperiorSkyblockAPI.getSuperiorSkyblock().getPlayers().getSuperiorPlayer(uuid).hasIsland()){
            return SuperiorSkyblockAPI.getSuperiorSkyblock().getPlayers().getSuperiorPlayer(uuid).getLocation();
        }
        return null;
    }

}
