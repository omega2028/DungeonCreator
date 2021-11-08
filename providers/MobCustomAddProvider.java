package fr.omega2028.dungeoncreator.providers;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.omega2028.dungeoncreator.Dungeoncreator;
import fr.omega2028.dungeoncreator.data.MobCustom;
import fr.omega2028.dungeoncreator.prompts.MobLifePromptAdd;
import fr.omega2028.dungeoncreator.prompts.MobNamePromptAdd;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class MobCustomAddProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        //Si on a pas séléctionné de mob a modifier, on est donc dans le cas d'ajout, on crée donc un mob temporaire
        //pour sauvegarder les modifications lors de l'édition
        MobCustom mobCustom;
        if (Dungeoncreator.getInstance().getCustomMobController().getMobTmp() == null) {
            Dungeoncreator.getInstance().getCustomMobController().createNewTemporaryMob();
            mobCustom = Dungeoncreator.getInstance().getCustomMobController().getMobTmp();
        }
        else {
            mobCustom = Dungeoncreator.getInstance().getCustomMobController().getMobTmp();
        }


        //Décorations en verre
        for (int slot : Arrays.asList(0, 9, 18, 27, 36, 45, 8, 17, 26, 35, 44, 53)) {
            contents.set(slot / 9, slot % 9,
                    ClickableItem.empty(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }

        //Différents boutons du GUI
        ItemStack itLife = new ItemStack(Material.APPLE);
        ItemMeta itmLife = itLife.getItemMeta();
        itmLife.setDisplayName(ChatColor.RED + "Health");
        itmLife.setLore(List.of(ChatColor.WHITE + Double.toString(mobCustom.getHealth())));
        itLife.setItemMeta(itmLife);
        contents.set(1, 3, ClickableItem.of(itLife, action -> {
            Conversation conversation = Dungeoncreator.getInstance().getConversationFactory()
                    .withFirstPrompt(new MobLifePromptAdd()).withLocalEcho(true).buildConversation(player);
            Dungeoncreator.getInstance().getCustomMobController().setReopen(player);//Pour réouvrir a la fin de saisie
            Dungeoncreator.getInstance().getCustomMobAddGUI().close(player);//ne pas oublier de le réouvrir
            conversation.begin();
        }));

        ItemStack itName = new ItemStack(Material.NAME_TAG);
        ItemMeta itmName = itLife.getItemMeta();
        itmName.setDisplayName(ChatColor.WHITE + "Name");
        itmName.setLore(List.of(mobCustom.getName()));
        itName.setItemMeta(itmName);
        contents.set(1, 5, ClickableItem.of(itName, action -> {
            Conversation conversation = Dungeoncreator.getInstance().getConversationFactory()
                    .withFirstPrompt(new MobNamePromptAdd()).withLocalEcho(true).buildConversation(player);
            Dungeoncreator.getInstance().getCustomMobController().setReopen(player);
            Dungeoncreator.getInstance().getCustomMobAddGUI().close(player);
            conversation.begin();
        }));

        Material Egg;
        switch(mobCustom.getEntityType().name()){
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
        ItemMeta itmEgg = itEgg.getItemMeta();
        itmEgg.setDisplayName(ChatColor.WHITE + mobCustom.getEntityType().name());
        itmEgg.setLore(List.of(""));
        itEgg.setItemMeta(itmEgg);
        contents.set(0, 4, ClickableItem.of(itEgg, action -> {
            Dungeoncreator.getInstance().getMobAddSelectionGUI().open(player);
        }));

        ItemStack emptyhead = createEmpty("Helmet");
        ItemStack ithelmet;
        if (mobCustom.getHead().getType() == Material.AIR) ithelmet = emptyhead;
        else ithelmet = new ItemStack(mobCustom.getHead());
        contents.set(1, 4, ClickableItem.of(ithelmet, action -> {
            mobCustom.setHead(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
        }));

        ItemStack emptychest = createEmpty("Chestplate");
        ItemStack itchest;
        if (mobCustom.getChestplate().getType() == Material.AIR) itchest = emptychest;
        else itchest = new ItemStack(mobCustom.getChestplate());
        contents.set(2, 4, ClickableItem.of(itchest, action -> {
            mobCustom.setChestplate(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
        }));

        ItemStack emptyleggins = createEmpty("Leggins");
        ItemStack itleggins;
        if (mobCustom.getLeggins().getType() == Material.AIR) itleggins = emptyleggins;
        else itleggins = new ItemStack(mobCustom.getLeggins());
        contents.set(3, 4, ClickableItem.of(itleggins, action -> {
            mobCustom.setLeggins(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
        }));

        ItemStack emptyboots = createEmpty("Boots");
        ItemStack itboots;
        if (mobCustom.getBoots().getType() == Material.AIR) itboots = emptyboots;
        else itboots = new ItemStack(mobCustom.getBoots());
        contents.set(4, 4, ClickableItem.of(itboots, action -> {
            mobCustom.setBoots(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
        }));

        ItemStack emptymain = createEmpty("Main Hand");
        ItemStack itmain;
        if (mobCustom.getMainHand().getType() == Material.AIR) itmain = emptymain;
        else itmain = new ItemStack(mobCustom.getMainHand());
        contents.set(4, 5, ClickableItem.of(itmain, action -> {
            mobCustom.setMainHand(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
        }));

        ItemStack emptyleft = createEmpty("Left Hand");
        ItemStack itleft;
        if (mobCustom.getLeftHand().getType() == Material.AIR) itleft = emptyleft;
        else itleft = new ItemStack(mobCustom.getLeftHand());
        contents.set(4, 3, ClickableItem.of(itleft, action -> {
            mobCustom.setLeftHand(action.getCursor());
            Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
        }));




        ItemStack quit = new ItemStack(Material.RED_WOOL);
        ItemMeta quitM = quit.getItemMeta();
        String quitt = ChatColor.BOLD + "Quit";
        quitM.setDisplayName(ChatColor.RED + quitt);
        quitM.setLore(List.of(ChatColor.WHITE + "Go back to the mob lists",
                ChatColor.RED + "(no save)"));
        quit.setItemMeta(quitM);
        contents.set(5, 1, ClickableItem.of(quit, action -> {
            Dungeoncreator.getInstance().getCustomMobController().setMobTmp(null);
            //On delete le mob en cours d'édition car on veut pas le sauvegarder
            Dungeoncreator.getInstance().getCustomMobList().open(player);
        }));

        ItemStack save = new ItemStack(Material.GREEN_WOOL);
        ItemMeta saveM = save.getItemMeta();
        String savee = ChatColor.BOLD + "Save";
        saveM.setDisplayName(ChatColor.GREEN + savee);
        save.setItemMeta(saveM);
        contents.set(5, 7, ClickableItem.of(save, action -> {
            Dungeoncreator.getInstance().getCustomMobController().saveNewMob();
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
