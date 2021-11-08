package fr.omega2028.dungeoncreator.commands;

import fr.omega2028.dungeoncreator.Dungeoncreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MobCustomCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        Dungeoncreator.getInstance().getCustomMobList().open(player);
        return true;
    }
}
