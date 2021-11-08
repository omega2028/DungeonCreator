package fr.omega2028.dungeoncreator.providers;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.omega2028.dungeoncreator.Dungeoncreator;
import fr.omega2028.dungeoncreator.data.MobCustom;
import fr.omega2028.dungeoncreator.prompts.MobLifePromptEdit;
import fr.omega2028.dungeoncreator.prompts.MobNamePromptEdit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class MobCustomEditProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        MobCustom copyMob;
        if (Dungeoncreator.getInstance().getCustomMobController().getCopyMob() == null) {
            Dungeoncreator.getInstance().getCustomMobController().createCopyOfExistantMob();
        }
        copyMob = Dungeoncreator.getInstance().getCustomMobController().getCopyMob();


        for (int slot : Arrays.asList(0, 9, 18, 27, 36, 45, 8, 17, 26, 35, 44, 53)) {
            contents.set(slot / 9, slot % 9,
                    ClickableItem.empty(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }

        //Différents boutons du GUI
        ItemStack itLife = new ItemStack(Material.APPLE);
        ItemMeta itmLife = itLife.getItemMeta();
        itmLife.setDisplayName(ChatColor.RED + "Health");
        itmLife.setLore(List.of(ChatColor.WHITE + Double.toString(copyMob.getHealth())));
        itLife.setItemMeta(itmLife);
        contents.set(1, 3, ClickableItem.of(itLife, action -> {
            Conversation conversation = Dungeoncreator.getInstance().getConversationFactory()
                    .withFirstPrompt(new MobLifePromptEdit()).withLocalEcho(true).buildConversation(player);
            Dungeoncreator.getInstance().getCustomMobController().setReopen(player);//Pour réouvrir a la fin de saisie
            Dungeoncreator.getInstance().getCustomMobEditGUI().close(player);//ne pas oublier de le réouvrir
            conversation.begin();
        }));

        ItemStack itName = new ItemStack(Material.NAME_TAG);
        ItemMeta itmName = itLife.getItemMeta();
        itmName.setDisplayName(ChatColor.WHITE + "Name");
        itmName.setLore(List.of(copyMob.getName()));
        itName.setItemMeta(itmName);
        contents.set(1, 5, ClickableItem.of(itName, action -> {
            Conversation conversation = Dungeoncreator.getInstance().getConversationFactory()
                    .withFirstPrompt(new MobNamePromptEdit()).withLocalEcho(true).buildConversation(player);
            Dungeoncreator.getInstance().getCustomMobController().setReopen(player);
            Dungeoncreator.getInstance().getCustomMobAddGUI().close(player);
            conversation.begin();
        }));

        Material Egg;
        switch(copyMob.getEntityType().name()){
            case "ZOMBIE":
                Egg = Material.ZOMBIE_SPAWN_EGG;
                break;
            case "SKELETON":
                Egg = Material.SKELETON_SPAWN_EGG;
                break;
            case "SPIDER":
                Egg = Material.SPIDER_SPAWN_EGG;
                break;
            case "ZOMBIFIED_PIGLIN":
                Egg = Material.ZOMBIFIED_PIGLIN_SPAWN_EGG;
                break;
            default:
                Egg = Material.PIG_SPAWN_EGG;
                break;
        }
        ItemStack itEgg = new ItemStack(Egg);
        ItemMeta itmEgg = itLife.getItemMeta();
        itmEgg.setDisplayName(ChatColor.WHITE + copyMob.getEntityType().name());
        itmEgg.setLore(List.of(""));
        itEgg.setItemMeta(itmEgg);
        contents.set(0, 4, ClickableItem.of(itEgg, action -> {
            Dungeoncreator.getInstance().getMobEditSelectionGUI().open(player);
        }));

        ItemStack emptyhead = createEmpty("Helmet");
        ItemStack ithelmet;
        if (copyMob.getHead().getType() == Material.AIR) ithelmet = emptyhead;
        else ithelmet = new ItemStack(copyMob.getHead());
        contents.set(1, 4, ClickableItem.of(ithelmet, action -> {
            copyMob.setHead(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobEditGUI().open(player);
        }));

        ItemStack emptychest = createEmpty("Chestplate");
        ItemStack itchest;
        if (copyMob.getChestplate().getType() == Material.AIR) itchest = emptychest;
        else itchest = new ItemStack(copyMob.getChestplate());
        contents.set(2, 4, ClickableItem.of(itchest, action -> {
            copyMob.setChestplate(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobEditGUI().open(player);
        }));

        ItemStack emptyleggins = createEmpty("Leggins");
        ItemStack itleggins;
        if (copyMob.getLeggins().getType() == Material.AIR) itleggins = emptyleggins;
        else itleggins = new ItemStack(copyMob.getLeggins());
        contents.set(3, 4, ClickableItem.of(itleggins, action -> {
            copyMob.setLeggins(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobEditGUI().open(player);
        }));

        ItemStack emptyboots = createEmpty("Boots");
        ItemStack itboots;
        if (copyMob.getBoots().getType() == Material.AIR) itboots = emptyboots;
        else itboots = new ItemStack(copyMob.getBoots());
        contents.set(4, 4, ClickableItem.of(itboots, action -> {
            copyMob.setBoots(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobEditGUI().open(player);
        }));

        ItemStack emptymain = createEmpty("Main Hand");
        ItemStack itmain;
        if (copyMob.getMainHand().getType() == Material.AIR) itmain = emptymain;
        else itmain = new ItemStack(copyMob.getMainHand());
        contents.set(4, 5, ClickableItem.of(itmain, action -> {
            copyMob.setMainHand(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobEditGUI().open(player);
        }));

        ItemStack emptyleft = createEmpty("Left Hand");
        ItemStack itleft;
        if (copyMob.getLeftHand().getType() == Material.AIR) itleft = emptyleft;
        else itleft = new ItemStack(copyMob.getLeftHand());
        contents.set(4, 3, ClickableItem.of(itleft, action -> {
            copyMob.setLeftHand(action.getCursor());
            player.getInventory().addItem(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobEditGUI().open(player);
        }));

        ItemStack quit = new ItemStack(Material.RED_WOOL);
        ItemMeta quitM = quit.getItemMeta();
        String quitt = ChatColor.BOLD + "Quit";
        quitM.setDisplayName(ChatColor.RED + quitt);
        quitM.setLore(List.of(ChatColor.WHITE + "Go back to the mob lists",
                ChatColor.RED + "(no save)"));
        quit.setItemMeta(quitM);
        contents.set(5, 1, ClickableItem.of(quit, action -> {
            Dungeoncreator.getInstance().getCustomMobController().setSelectedMobIndex(-1);
            Dungeoncreator.getInstance().getCustomMobController().setCopyMob(null);
            //On retire la séléction puisqu'on veut pas la sauvegarder
            Dungeoncreator.getInstance().getCustomMobList().open(player);
        }));

        ItemStack save = new ItemStack(Material.GREEN_WOOL);
        ItemMeta saveM = save.getItemMeta();
        String savee = ChatColor.BOLD + "Save";
        saveM.setDisplayName(ChatColor.GREEN + savee);
        save.setItemMeta(saveM);
        contents.set(5, 7, ClickableItem.of(save, action -> {
            Dungeoncreator.getInstance().getCustomMobController().replaceExistantMob();
            Dungeoncreator.getInstance().getCustomMobList().open(player);
        }));
    }

    private static ItemStack createEmpty(String name){
        ItemStack empty = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = empty.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + name);
        itemMeta.setLore(List.of(""));
        empty.setItemMeta(itemMeta);
        return empty;
    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
