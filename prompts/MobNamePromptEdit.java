package fr.omega2028.dungeoncreator.prompts;

import fr.omega2028.dungeoncreator.Dungeoncreator;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MobNamePromptEdit extends StringPrompt {
    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return "Name :";
    }

    @Override
    public @Nullable Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        Dungeoncreator.getInstance().getCustomMobController().getCopyMob().setName(input);
        Dungeoncreator.getInstance().getCustomMobEditGUI().open(Dungeoncreator.getInstance()
                .getCustomMobController().getReopen());
        Dungeoncreator.getInstance().getCustomMobController().setReopen(null);
        return null;
    }
}
