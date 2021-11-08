package fr.omega2028.dungeoncreator.data;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DjRoom {
    private Map<Location, MobCustom> enemys;
    private int count = 0;
    private List<LivingEntity> alive;
    private List<Location> door;

    public DjRoom() {
        enemys = new HashMap<Location, MobCustom>();
        alive = new ArrayList<LivingEntity>();
    }

    public Map<Location, MobCustom> getEnemys() {
        return enemys;
    }

    public int getCount() {
        return count;
    }

    public List<LivingEntity> getAlive() {
        return alive;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Location> getDoor() {
        return door;
    }
}
