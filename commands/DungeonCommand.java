package fr.omega2028.dungeoncreator.commands;

import fr.omega2028.dungeoncreator.Dungeoncreator;
import fr.omega2028.dungeoncreator.managers.DungeonManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DungeonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        //cmd: /dungeon create <nom>
        //cmd: /dungeon delete <nom> + confirmation
        //cmd: /dungeon <nom> addroom
        //cmd: /dungeon <nom> delroom <number>
        //cmd: /dungeon <nom> editroommobs <number> + open GUI pour choisir le mob
        //cmd: /dungeon <nom> editroomdoor <number> <pos1> <pos2>

        if (args.length < 2) return false;
        int roomNumber;
        switch (args[0]) {
            case "create":
                if (!(Dungeoncreator.getInstance().getDungeonManager().createNewDongeon(args[1]))) {
                    player.sendMessage(ChatColor.RED + "Le donjon existe déjà !");
                    return false;
                }
                break;
            case "delete":
                if (!(Dungeoncreator.getInstance().getDungeonManager().deleteDongeon(args[1]))) {
                    player.sendMessage(ChatColor.RED + "Le donjon n'existe pas !");
                    return false;
                }
                break;
            default:
                if (!(Dungeoncreator.getInstance().getDungeonManager().containsDj(args[0]))) {
                    player.sendMessage(ChatColor.RED + "Le donjon n'existe pas !");
                    return false;
                }
                try{
                    roomNumber = Integer.parseInt(args[2]);
                }
                catch (NumberFormatException numberFormatException) {
                    player.sendMessage(ChatColor.RED + "Il faut écrire un numéro de salle !");
                    return false;
                }
                switch (args[1]) {
                    case "addroom":
                        Dungeoncreator.getInstance().getDungeonManager().createNewRoom(args[0]);
                        break;
                    case "delroom":
                        if(!(Dungeoncreator.getInstance().getDungeonManager().deleteRoom(args[0], roomNumber))) {
                            player.sendMessage(ChatColor.RED + "La salle n'existe pas !");
                            return false;
                        }
                        break;
                    case "editroommobs":
                        Dungeoncreator.getInstance().getDungeonManager().setCurrentName(args[0]);
                        Dungeoncreator.getInstance().getDungeonManager().setCurrentRoomNumber(roomNumber);
                        Dungeoncreator.getInstance().getMobAviableListGUI().open(player);
                        break;
                    case "editroomdoor":
                        player.sendMessage("Pas encore implémentée");
                        break;
                    default:
                        break;
                }
                break;
        }
        return true;
    }
}
