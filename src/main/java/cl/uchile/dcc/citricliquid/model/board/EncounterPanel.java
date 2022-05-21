package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.jetbrains.annotations.NotNull;

public class EncounterPanel extends AbstractPanel{
    /**
     * Creates a new encounter panel.
     *
     * @param type the type of the panel.
     */
    public EncounterPanel(PanelType type) {
        super(type);
    }

    /**
     * creates a random wild unit
     * @param player
     */
    @Override
    public void activatedBy(@NotNull Player player) {
    }
}
