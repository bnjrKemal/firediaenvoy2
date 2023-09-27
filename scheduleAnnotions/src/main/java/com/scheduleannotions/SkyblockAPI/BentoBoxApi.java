package com.scheduleannotions.SkyblockAPI;

import org.bukkit.Location;
import org.bukkit.World;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.GameModeAddon;

import java.util.UUID;

public class BentoBoxApi {

    public Location getIslandLocation(UUID uuid){
        BentoBox api = BentoBox.getInstance();
        for(GameModeAddon gamemodeAddon : api.getAddonsManager().getGameModeAddons()){
            World world = gamemodeAddon.getOverWorld();
            if(api.getIslands().getIsland(world, uuid) != null){
                return api.getIslands().getIsland(world, uuid).getSpawnPoint(World.Environment.NORMAL);
            }
        }
        return null;
    }
}
