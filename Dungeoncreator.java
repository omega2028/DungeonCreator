package fr.omega2028.dungeoncreator;

import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInventory;
import fr.omega2028.dungeoncreator.commands.MobCustomCommand;
import fr.omega2028.dungeoncreator.controls.MobCustomController;
import fr.omega2028.dungeoncreator.data.MobCustom;
import fr.omega2028.dungeoncreator.data.MobCatalog;
import fr.omega2028.dungeoncreator.data.MobFactory;
import fr.omega2028.dungeoncreator.managers.DungeonManager;
import fr.omega2028.dungeoncreator.providers.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dungeoncreator extends JavaPlugin {

    private static Dungeoncreator instance;
    private InventoryManager inventoryManager;
    private SmartInventory customMobList;
    private SmartInventory customMobAddGUI;
    private SmartInventory customMobEditGUI;
    private SmartInventory mobAddSelectionGUI;
    private SmartInventory mobEditSelectionGUI;
    private SmartInventory mobAviableListGUI;
    private MobCatalog mobCatalog;
    private MobCustomController mobCustomController;
    private ConversationFactory conversationFactory;
    private MobFactory mobFactory;
    private DungeonManager dungeonManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Mise en place de SmartInvs
        inventoryManager = new InventoryManager(this);
        inventoryManager.init();

        // Build des invs
        customMobList = SmartInventory.builder()
                .manager(inventoryManager)
                .size(6, 9)
                .title(ChatColor.GRAY + "Custom mob list :")
                .provider(new MobCustomListProvider())
                .build();
        customMobAddGUI = SmartInventory.builder()
                .manager(inventoryManager)
                .size(6, 9)
                .title(ChatColor.GRAY + "New custom mob edition :")
                .closeable(false)
                .provider(new MobCustomAddProvider())
                .build();
        customMobEditGUI = SmartInventory.builder()
                .manager(inventoryManager)
                .size(6, 9)
                .closeable(false)
                .title(ChatColor.GRAY + "Existant custom mob edition :")
                .provider(new MobCustomEditProvider())
                .build();
        mobAddSelectionGUI = SmartInventory.builder()
                .manager(inventoryManager)
                .size(6, 9)
                .closeable(false)
                .title(ChatColor.GRAY + "Select a mob :")
                .provider(new MobAddSelectionProvider())
                .build();
        mobEditSelectionGUI = SmartInventory.builder()
                .manager(inventoryManager)
                .size(6, 9)
                .closeable(false)
                .title(ChatColor.GRAY + "Select a mob :")
                .provider(new MobEditSelectionProvider())
                .build();
        mobAviableListGUI = SmartInventory.builder()
                .manager(inventoryManager)
                .size(6, 9)
                .title(ChatColor.GRAY + "Select a mob :")
                .provider(new MobCustomAviableListProvider())
                .closeable(false)
                .build();

        mobCatalog = new MobCatalog();
        mobFactory = new MobFactory();
        mobCustomController = new MobCustomController();
        conversationFactory = new ConversationFactory(Dungeoncreator.getInstance());
        dungeonManager = new DungeonManager();

        ItemStack air = new ItemStack(Material.AIR);
        mobCatalog.addCustomMob(new MobCustom("Test", 50, new ItemStack(Material.DIAMOND_HELMET),
                air, air, air, air, air, EntityType.ZOMBIE));
        mobCatalog.addCustomMob(new MobCustom("Test", 50, new ItemStack(Material.IRON_HELMET),
                air, air, air, new ItemStack(Material.STONE), air, EntityType.ZOMBIE));
        mobCatalog.addCustomMob(new MobCustom("Test", 50, new ItemStack(Material.LEATHER_HELMET),
                air, air, air, air, air, EntityType.ZOMBIE));

        this.getCommand("custommob").setExecutor(new MobCustomCommand());
        this.getCommand("cm").setExecutor(new MobCustomCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Dungeoncreator getInstance() {
        return instance;
    }

    public SmartInventory getMobAddSelectionGUI() {
        return mobAddSelectionGUI;
    }

    public SmartInventory getCustomMobList() {
        return customMobList;
    }

    public SmartInventory getMobEditSelectionGUI() {
        return mobEditSelectionGUI;
    }

    public SmartInventory getMobAviableListGUI() {
        return mobAviableListGUI;
    }

    public DungeonManager getDungeonManager() {
        return dungeonManager;
    }

    public SmartInventory getCustomMobEditGUI() {
        return customMobEditGUI;
    }

    public ConversationFactory getConversationFactory() {
        return conversationFactory;
    }

    public MobFactory getMobFactory() {
        return mobFactory;
    }

    public MobCatalog getMobCatalog() {
        return mobCatalog;
    }

    public MobCustomController getCustomMobController() {
        return mobCustomController;
    }

    public SmartInventory getCustomMobAddGUI() {
        return customMobAddGUI;
    }
}
