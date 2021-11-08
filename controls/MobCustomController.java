package fr.omega2028.dungeoncreator.controls;

import fr.omega2028.dungeoncreator.Dungeoncreator;
import fr.omega2028.dungeoncreator.data.MobCustom;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MobCustomController {

    private int selectedMobIndex = -1;
    private MobCustom mobTmp;
    private MobCustom copyMob;
    private Player reopen;

    //Méthode pour réaliser des tests
    public void createMob(String name, double health, ItemStack head, ItemStack chestplate,
                          ItemStack leggins, ItemStack boots, ItemStack mainHand, ItemStack leftHand,
                          EntityType entityType){
        MobCustom mobCustom = new MobCustom(name, health, head, chestplate, leggins, boots, mainHand, leftHand,
                entityType);

        Dungeoncreator.getInstance().getMobCatalog().addCustomMob(mobCustom);
    }

    //Fait un apparaitre un CustomMob à l'emplacement du joueur
    public void spawnMob(int index, Player player){
        Dungeoncreator.getInstance().getMobCatalog().getCustomMob(index).spawnMobOnPlayerLocation(player);
    }

    //Crée un mob temporaire par défaut qui va être édité et sauvegardé lors de l'ajout d'un mob
    public void createNewTemporaryMob() {
        ItemStack air = new ItemStack(Material.AIR);
        mobTmp = new MobCustom("", 20, air, air, air, air, air, air, EntityType.ZOMBIE);
    }

    public void createCopyOfExistantMob() {
        if (selectedMobIndex == -1) {
            Bukkit.getLogger().info("Aucun mob sélectionné, impossible de faire une copie");
            return;
        }
        MobCustom selected = Dungeoncreator.getInstance().getMobCatalog().getCustomMob(selectedMobIndex);
        copyMob = new MobCustom(selected.getName(), selected.getHealth(), selected.getHead(), selected.getChestplate(),
                selected.getLeggins(), selected.getBoots(), selected.getMainHand(), selected.getLeftHand(),
                selected.getEntityType());
    }

    //Ajoute le nouveau mob au catalogue
    public void saveNewMob() {
        if (mobTmp == null) {
            Bukkit.getLogger().info("Impossible de sauvegarder, mobTmp est a NULL");
            return;
        }
        Dungeoncreator.getInstance().getMobCatalog().addCustomMob(mobTmp);
        mobTmp = null; //on oublie pas de reset la variable pour le prochain ajout
    }

    public void replaceExistantMob() {
        if (copyMob == null) {
            Bukkit.getLogger().info("Impossible de sauvegarder, copyMob est a NULL");
            return;
        }
        if (selectedMobIndex == -1) {
            Bukkit.getLogger().info("Impossible de remplacer le mob, aucun mob sélectionné.");
            return;
        }
        Dungeoncreator.getInstance().getMobCatalog().replaceCustomMob(selectedMobIndex, copyMob);
        copyMob = null; //on oublie pas de reset copyMob pour le prochain edit
        selectedMobIndex = -1;
    }

    public MobCustom getMobTmp() {
        return mobTmp;
    }

    public MobCustom getCopyMob() {
        return copyMob;
    }

    public Player getReopen() {
        return reopen;
    }

    public void setReopen(Player reopen) {
        this.reopen = reopen;
    }

    public int getSelectedMobIndex() {
        return selectedMobIndex;
    }

    public void setMobTmp(MobCustom mobTmp) {
        this.mobTmp = mobTmp;
    }

    public void setCopyMob(MobCustom copyMob) {
        this.copyMob = copyMob;
    }

    public void setSelectedMobIndex(int selectedMobIndex) {
        this.selectedMobIndex = selectedMobIndex;
    }
}
