package fr.omega2028.dungeoncreator.managers;

import fr.omega2028.dungeoncreator.Dungeoncreator;
import fr.omega2028.dungeoncreator.data.DjRoom;
import fr.omega2028.dungeoncreator.data.MobCustom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomsManager {
    private Map<Integer, Integer> taskcache;


    public RoomsManager(){
        taskcache = new HashMap<>();
    }

    /**
     * Spawn et gestion de la mort des ennemis
     * @param djSalle
     */
    public void spawnEnemys(DjRoom djSalle) {
        List<String> ensId = new ArrayList<String>();
        int roomId;
        djSalle.getEnemys().forEach((loc, mob) -> {
            MobCustom mobtmp = (MobCustom) mob;
            Location loctmp = (Location) loc;
            LivingEntity tmp = mobtmp.spawnMob(loctmp);
            djSalle.getAlive().add(tmp);
            ensId.add(tmp.getUniqueId().toString());
        });
        roomId = ensId.hashCode();
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Dungeoncreator.getInstance(), () -> {
            for ( int i = 0; i < djSalle.getAlive().size() ; i++) {
                if ( djSalle.getAlive().get(i).isDead()) djSalle.getAlive().remove(i);
                if ( djSalle.getAlive().size() == 0) {
                    //ouvrir la salle
                    Bukkit.getScheduler().cancelTask(taskcache.get(roomId));
                }
            }
        }, 0L, 2L);

        taskcache.put(roomId,task);
    }


}

