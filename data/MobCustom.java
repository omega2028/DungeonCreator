package fr.omega2028.dungeoncreator.data;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class MobCustom {
    private EntityType entityType;
    private String name;
    private double health;
    private ItemStack head;
    private ItemStack chestplate;
    private ItemStack leggins;
    private ItemStack boots;
    private ItemStack mainHand;
    private ItemStack leftHand;




    public MobCustom(String name, double health, ItemStack head, ItemStack chestplate,
                     ItemStack leggins, ItemStack boots, ItemStack mainHand, ItemStack leftHand,
                     EntityType entityType){
       this.name = name;
       this.health = health;
       this.head = head;
       this.chestplate = chestplate;
       this.leggins = leggins;
       this.boots = boots;
       this.mainHand = mainHand;
       this.leftHand = leftHand;
       this.entityType = entityType;
    }

    //Précondition : les Materials sont != NULL
    public void spawnMobOnPlayerLocation(Player player){
        LivingEntity mob = (LivingEntity)player.getWorld().spawnEntity(player.getLocation(), entityType);
        if (!name.equals("")) {
            mob.setCustomName(name);
            mob.setCustomNameVisible(true);
        }
        mob.setMaxHealth(health);
        mob.setHealth(health);
        EntityEquipment entityEquipment = mob.getEquipment();
        if (entityEquipment == null) return;
        if (head != null) entityEquipment.setHelmet(head);
        if (chestplate != null) entityEquipment.setChestplate(chestplate);
        if (leggins != null) entityEquipment.setLeggings(leggins);
        if (boots != null) entityEquipment.setBoots(boots);
        if (mainHand != null) entityEquipment.setItemInMainHand(mainHand);
        if (leftHand != null) entityEquipment.setItemInOffHand(leftHand);

        player.sendMessage((ChatColor.BOLD + (ChatColor.YELLOW + "Dungeon Creator : "))
                + ChatColor.WHITE + "Custom mob spawned");
    }

    public LivingEntity spawnMob(Location location) {
        LivingEntity mob = (LivingEntity)location.getWorld().spawnEntity(location, entityType);
        if (!name.equals("")) {
            mob.setCustomName(name);
            mob.setCustomNameVisible(true);
        }
        mob.setMaxHealth(health);
        mob.setHealth(health);
        EntityEquipment entityEquipment = mob.getEquipment();
        if (entityEquipment == null) return null;
        if (head != null) entityEquipment.setHelmet(head);
        if (chestplate != null) entityEquipment.setChestplate(chestplate);
        if (leggins != null) entityEquipment.setLeggings(leggins);
        if (boots != null) entityEquipment.setBoots(boots);
        if (mainHand != null) entityEquipment.setItemInMainHand(mainHand);
        if (leftHand != null) entityEquipment.setItemInOffHand(leftHand);
        return mob;
    }

    public double getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public ItemStack getHead() {
        return head;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggins() {
        return leggins;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getMainHand() {
        return mainHand;
    }

    public ItemStack getLeftHand() {
        return leftHand;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setHead(ItemStack head) {
        this.head = head;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setLeggins(ItemStack leggins) {
        this.leggins = leggins;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public void setMainHand(ItemStack mainHand) {
        this.mainHand = mainHand;
    }

    public void setLeftHand(ItemStack leftHand) {
        this.leftHand = leftHand;
    }
}
