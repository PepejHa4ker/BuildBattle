package me.tomthedeveloper.buildbattle;

import me.tomthedeveloper.buildbattle.game.GameInstance;
import me.tomthedeveloper.buildbattle.permissions.PermStrings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tom on 27/07/2014.
 */
public class User {

    public static GameAPI plugin;
    private ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    private UUID uuid;
    private boolean spectator = false;
    private HashMap<String, Integer> ints = new HashMap<>();
    private HashMap<String, Object> objects = new HashMap<>();

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public Object getObject(String s) {
        if(objects.containsKey(s)) return objects.get(s);
        return null;
    }

    public void setObject(Object object, String s) {
        objects.put(s, object);
    }

    public GameInstance getGameInstance() {
        return plugin.getGameInstanceManager().getGameInstance(Bukkit.getPlayer(uuid));
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player toPlayer() {
        return Bukkit.getServer().getPlayer(uuid);
    }

    public boolean isSpectator() {
        return spectator;
    }

    public void setSpectator(boolean b) {
        spectator = b;
    }

    public int getInt(String s) {
        if(!ints.containsKey(s)) {
            ints.put(s, 0);
            return 0;
        } else if(ints.get(s) == null) {
            return 0;
        }

        return ints.get(s);
    }

    public void removeScoreboard() {
        this.toPlayer().setScoreboard(scoreboardManager.getNewScoreboard());
    }

    public void setInt(String s, int i) {
        ints.put(s, i);
    }

    public boolean isPremium() {
        if(this.toPlayer().hasPermission(PermStrings.getVIP()) || this.toPlayer().hasPermission(PermStrings.getMVP()) || this.toPlayer().hasPermission(PermStrings.getELITE())) {
            return true;
        } else {
            return false;
        }
    }

    public void addInt(String s, int i) {
        ints.put(s, getInt(s) + i);
    }

}