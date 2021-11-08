package fr.omega2028.dungeoncreator.prompts;

import fr.omega2028.dungeoncreator.Dungeoncreator;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MobLifePromptEdit extends StringPrompt {
    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return "Pv :";
    }

    @Override
    public @Nullable Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        Double pv;
        try {
            pv = Double.parseDouble(input);
        }
        catch (NumberFormatException numberFormatException) {
            context.getForWhom().sendRawMessage("Pv must be a NUMBER !");
            return new MobLifePromptEdit();//On redemande
        }
        Dungeoncreator.getInstance().getCustomMobController().getCopyMob().setHealth(pv);
        Dungeoncreator.getInstance().getCustomMobEditGUI().open(Dungeoncreator.getInstance()
                .getCustomMobController().getReopen());
        //Pas très propre mais pas trop le choix
        Dungeoncreator.getInstance().getCustomMobController().setReopen(null);
        //On retire le joueur après lui avoir réouvert le bon inventaire
        return null;
    }
}
