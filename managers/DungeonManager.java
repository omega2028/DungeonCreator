package fr.omega2028.dungeoncreator.managers;

import fr.omega2028.dungeoncreator.data.DjRoom;
import fr.omega2028.dungeoncreator.data.Dungeon;
import fr.omega2028.dungeoncreator.data.MobCustom;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DungeonManager {
    private Map<String, Dungeon> dungeonCatalog;
    private String currentName;
    private int currentRoomNumber;

    public DungeonManager() {
        dungeonCatalog = new TreeMap<>();
    }

    public boolean createNewDongeon(String name) {
        if (dungeonCatalog.containsKey(name)) return false;
        Dungeon dungeon = new Dungeon(name);
        dungeonCatalog.put(name, dungeon);
        return true;
    }

    public boolean deleteDongeon(String name) {
        if (!dungeonCatalog.containsKey(name)) return false;
        dungeonCatalog.remove(name);
        return true;
    }

    public boolean containsDj(String name) {
        return dungeonCatalog.containsKey(name);
    }

    public void createNewRoom(String name) {
        dungeonCatalog.get(name).getRooms().add(new DjRoom());
    }

    public boolean deleteRoom(String name, int roomNumber){
        try{
            dungeonCatalog.get(name).getRooms().remove(roomNumber);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
        return true;
    }

    public int addMobInRoom(String name, int roomNumber, MobCustom mobCustom, Player player) {
        if (!dungeonCatalog.containsKey(name)) return 1;
        try {
            dungeonCatalog.get(name).getRooms().get(roomNumber);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException){
            return 2;
        }
        dungeonCatalog.get(name).getRooms().get(roomNumber).getEnemys().put(player.getLocation(), mobCustom);
        return 0;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public void setCurrentRoomNumber(int currentRoomNumber) {
        this.currentRoomNumber = currentRoomNumber;
    }

    public int getCurrentRoomNumber() {
        return currentRoomNumber;
    }
}
