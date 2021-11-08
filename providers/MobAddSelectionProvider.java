package fr.omega2028.dungeoncreator.providers;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import fr.omega2028.dungeoncreator.Dungeoncreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class MobAddSelectionProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        Pagination pagination = contents.pagination();

        ClickableItem[] items = new ClickableItem[Dungeoncreator.getInstance().getMobFactory().getSize()];

        for (int i = 0; i < Dungeoncreator.getInstance().getMobFactory().getSize() ; i++) {
            int finalI = i;
            items[i] = ClickableItem.of(new ItemStack((Material) Dungeoncreator.getInstance().getMobFactory().getItems().get(i)),
                    action -> {
                        Dungeoncreator.getInstance().getCustomMobController().getMobTmp().setEntityType(
                                Dungeoncreator.getInstance().getMobFactory().getEntityType(
                                        (Material) Dungeoncreator.getInstance().getMobFactory().getItems().get(finalI)
                                )
                        );
                        Dungeoncreator.getInstance().getCustomMobAddGUI().open(player);
                    });
        }

        pagination.setItems(items);
        pagination.setItemsPerPage(36);

        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));

        contents.set(5, 3, ClickableItem.of(new ItemStack(Material.ARROW),
                e -> Dungeoncreator.getInstance().getCustomMobList().open(player, pagination.previous().getPage())));
        contents.set(5, 5, ClickableItem.of(new ItemStack(Material.ARROW),
                e -> Dungeoncreator.getInstance().getCustomMobList().open(player, pagination.next().getPage())));
        for (int slot : Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 46, 47, 49, 51, 52)) {
            contents.set(slot / 9, slot % 9,
                    ClickableItem.empty(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
