package fr.omega2028.dungeoncreator.providers;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import fr.omega2028.dungeoncreator.Dungeoncreator;
import fr.omega2028.dungeoncreator.data.MobCustom;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class MobCustomAviableListProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        Pagination pagination = contents.pagination();

        ClickableItem[] items = new ClickableItem[Dungeoncreator.getInstance().getMobCatalog().getLenght()];


        Material Egg;
        MobCustom mobCustom;

        for(int i = 0; i < Dungeoncreator.getInstance().getMobCatalog().getLenght(); i++) {
            int finalI = i;

            mobCustom = Dungeoncreator.getInstance().getMobCatalog().getCustomMob(i);
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
            itmEgg.setDisplayName(mobCustom.getName());
            itmEgg.setLore(List.of(""));
            itEgg.setItemMeta(itmEgg);

            MobCustom finalMobCustom = mobCustom;
            items[i] = ClickableItem.of(itEgg, action -> {
                int err = Dungeoncreator.getInstance().getDungeonManager().addMobInRoom(
                        Dungeoncreator.getInstance().getDungeonManager().getCurrentName(),
                        Dungeoncreator.getInstance().getDungeonManager().getCurrentRoomNumber(),
                        finalMobCustom,
                        player);
                switch (err){
                    case 0:
                        player.sendMessage(ChatColor.WHITE + "Mob ajouté !");
                        break;
                    case 1:
                        player.sendMessage(ChatColor.RED + "Le donjon n'existe pas !");
                        break;
                    case 2:
                        player.sendMessage(ChatColor.RED + "La salle n'existe pas !");
                        break;
                    default:
                        break;
                }
                player.getWorld().spawnParticle(Particle.BARRIER, player.getLocation(), 5);
                //Je remet l'indice de sélection a 0 pour la prochaine utilisation
                Dungeoncreator.getInstance().getDungeonManager().setCurrentName("");
                Dungeoncreator.getInstance().getDungeonManager().setCurrentRoomNumber(-1);
                Dungeoncreator.getInstance().getMobAviableListGUI().close(player);
            });
        }

        pagination.setItems(items);
        pagination.setItemsPerPage(36);

        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));


        contents.set(5, 3, ClickableItem.of(new ItemStack(Material.ARROW),
                e -> Dungeoncreator.getInstance().getCustomMobList().open(player, pagination.previous().getPage())));
        contents.set(5, 5, ClickableItem.of(new ItemStack(Material.ARROW),
                e -> Dungeoncreator.getInstance().getCustomMobList().open(player, pagination.next().getPage())));
        for (int slot : Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 46, 47, 49, 51, 52, 53)) {
            contents.set(slot / 9, slot % 9,
                    ClickableItem.empty(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
