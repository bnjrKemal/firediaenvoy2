package com.scheduleannotions;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class SARunnable extends BukkitRunnable {

    @Override
    public void run() {

        for(Long lng : ScheduleAnnotations.times){
            if (Math.abs(new Date().getTime() - lng) <= 1000){
                ScheduleAnnotations.main.EnvoyEvent();
                break;
            }
        }
    }
}
