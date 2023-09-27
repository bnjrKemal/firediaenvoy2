package com.scheduleannotions;

import com.scheduleannotions.SkyblockAPI.*;
import com.scheduleannotions.license.License;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.*;
import java.util.*;
import java.util.logging.Level;


public class ScheduleAnnotations extends JavaPlugin {

    public static List<Long> times;
    Month month;
    int monthDay;
    static String format;
    static Object api;
    public String prefix;

    static ScheduleAnnotations main;

    @Override
    public void onEnable() {
        main = this;
        //license();
        saveDefaultConfig();
        if(!api()){
            this.setEnabled(false);
            Bukkit.getLogger().log(Level.WARNING, "You must to have any skyblock plugin or PlaceHolderAPI plugin. This plugin has disabled!");
            return;
        }
        prefix = getConfig().getString("prefix");
        loadingTimes();
        new SARunnable().runTaskTimer(this, 0,0);
        getCommand("firediaenvoy").setExecutor(new SACommand(this));
    }

    private void license() {
        try {
            new License("https://firedia.com/licence.php", "55");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean api(){

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)
            return false;
        new PAPI().register();

        if(Bukkit.getPluginManager().getPlugin("ASkyBlock") != null){
            api = new ASkyBlockApi();
            return true;
        }
        if(Bukkit.getPluginManager().getPlugin("SuperiorSkyblock2") != null){
            api = new SuperiorSkyblockApi();
            return true;
        }
        if(Bukkit.getPluginManager().getPlugin("FabledSkyblock") != null){
            api = new FabledSkyBlockApi();
            return true;
        }
        if(Bukkit.getPluginManager().getPlugin("IridiumSkyblock") != null){
            api = new IridiumSkyblockApi();
            return true;
        }
        if(Bukkit.getPluginManager().getPlugin("BentoBox") != null){
            api = new BentoBoxApi();
            return true;
        }

        return false;
    }

    public void loadingTimes() {
        format = getConfig().getString("format");
        LocalDateTime l = LocalDateTime.now();
        times = new ArrayList<>();
        LocalDateTime ld;

        //if(getConfig().getConfigurationSection("times") == null) return;

        for(String str : getConfig().getStringList("times")){
            //try{
                String[] options = str.split(",");

                if(options[0].equalsIgnoreCase("*")) month = l.getMonth();
                if(options[0].equalsIgnoreCase(l.getMonth().toString())) month = Month.of(Integer.parseInt(options[0]));
                if(options[1].equalsIgnoreCase("*")) monthDay = LocalDateTime.now().getDayOfMonth();
                if(options[1].equalsIgnoreCase(l.getDayOfMonth() + "")) monthDay = Integer.parseInt(options[1]);

                int hour = Integer.parseInt(options[2]);
                int minute = Integer.parseInt(options[3]);

                ld = LocalDateTime.of(l.getYear(), month, monthDay, hour, minute);
            //}catch(Exception e){
            //    continue;
            //}
            times.add((ZonedDateTime.of(ld, ZoneId.systemDefault())).toInstant().toEpochMilli());
            System.out.println("ggg ================================ " + (ZonedDateTime.of(ld, ZoneId.systemDefault())).toInstant().toEpochMilli());
       }
        System.out.println("nexttimes ================================ " + times);
        Collections.sort(times);
    }

    public static long getNextTime(){
        for(Long lng : times)
            if(System.currentTimeMillis() < lng)
                return lng;
        return 0L;
    }

    public static String remainingTime(){

        if(getNextTime() == 0L)
            return "null";

        long differenceTime = getNextTime() - System.currentTimeMillis();

        long second = (differenceTime / 1000) % 60;
        long minute = (second/60) % 60;
        long hour = (second/3600) % 24;
        long day = (second/86400) % 30;
        long month = (second/2592000)/12;

        format = format
                .replace("{month}", month + "")
                .replace("{day}", day + "")
                .replace("{hour}", hour + "")
                .replace("{minute}", minute + "")
                .replace("{second}", second + "");

        return format;
    }

    public void EnvoyEvent() {

        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(getConfig().getString("started-event").replace("{prefix}", prefix)));

        for (Location loc : getAllLocation()) {

            if (loc == null) continue;

            loc.getBlock().setType(Material.TRAPPED_CHEST);

            Block block = loc.getBlock();
            Chest chest = (Chest) block.getState();

            for (Object item : getConfig().getStringList("items"))
                chest.getInventory().addItem((ItemStack) item);

        }
    }

    public static List<Location> getAllLocation(){
        List<Location> list = new ArrayList<>();
        for(Player player : Bukkit.getOnlinePlayers()){
            if (api instanceof ASkyBlockApi)
                list.add(((ASkyBlockApi) api).getIslandLocation(player.getUniqueId()));
            if (api instanceof SuperiorSkyblockApi)
                list.add(((SuperiorSkyblockApi) api).getIslandLocation(player.getUniqueId()));
            if (api instanceof FabledSkyBlockApi)
                list.add(((FabledSkyBlockApi)api).getIslandLocation(player.getUniqueId()));
            if (api instanceof IridiumSkyblockApi)
                list.add(((IridiumSkyblockApi) api).getIslandLocation(player.getUniqueId()));
            if (api instanceof BentoBoxApi)
                list.add(((BentoBoxApi)api).getIslandLocation(player.getUniqueId()));
        }
        return list;
    }

}
