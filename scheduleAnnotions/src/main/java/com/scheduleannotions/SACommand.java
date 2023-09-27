package com.scheduleannotions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SACommand implements CommandExecutor {

    ScheduleAnnotations scheduleAnnotations;

    public SACommand(ScheduleAnnotations instance) {
        scheduleAnnotations = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            scheduleAnnotations.reloadConfig();
            scheduleAnnotations.prefix = scheduleAnnotations.getConfig().getString("prefix");
            scheduleAnnotations.loadingTimes();
            sender.sendMessage(scheduleAnnotations.getConfig().getString("reload").replace("{prefix}", scheduleAnnotations.prefix));
            return false;
        }

        Player player = (Player) sender;

        scheduleAnnotations.getConfig().set("items.", player.getInventory().getContents());

        scheduleAnnotations.saveConfig();
        player.sendMessage(scheduleAnnotations.getConfig().getString("reload-rewards").replace("{prefix}", scheduleAnnotations.prefix));

        return false;
    }
}
