package com.scheduleannotions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PAPI extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "firediaenvoy";
    }

    @Override
    public String getAuthor() {
        return "bnjrKemal";
    }

    @Override
    public String getVersion() {
        return "3.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        return ScheduleAnnotations.remainingTime();
    }

}