package fr.omega2028.dungeoncreator.data;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.*;

public class MobFactory {
    private Map mobFactory;
    private List items;

    public MobFactory() {
        mobFactory = new HashMap<Material, EntityType>();
        items = new ArrayList();
        items.add(Material.PIG_SPAWN_EGG);
        items.add(Material.SKELETON_SPAWN_EGG);
        items.add(Material.SPIDER_SPAWN_EGG);
        items.add(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
        items.add(Material.ZOMBIE_SPAWN_EGG);
        mobFactory.put(Material.PIG_SPAWN_EGG, EntityType.PIG);
        mobFactory.put(Material.SKELETON_SPAWN_EGG, EntityType.SKELETON);
        mobFactory.put(Material.SPIDER_SPAWN_EGG, EntityType.SPIDER);
        mobFactory.put(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, EntityType.ZOMBIFIED_PIGLIN);
        mobFactory.put(Material.ZOMBIE_SPAWN_EGG, EntityType.ZOMBIE);
    }

    public EntityType getEntityType(Material material) {
        return (EntityType) mobFactory.get(material);
    }

    public List getItems() {
        return items;
    }

    public int getSize() {
        return mobFactory.size();
    }
}
