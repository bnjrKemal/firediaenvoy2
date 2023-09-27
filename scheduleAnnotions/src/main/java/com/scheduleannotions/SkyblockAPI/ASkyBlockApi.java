package com.scheduleannotions.SkyblockAPI;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import org.bukkit.Location;

import java.util.UUID;

public class ASkyBlockApi {

    public Location getIslandLocation(UUID uuid){
        if (ASkyBlockAPI.getInstance().hasIsland(uuid)){
            return ASkyBlockAPI.getInstance().getHomeLocation(uuid);
        }
        return null;
    }

}
