package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.jetbrains.annotations.NotNull;

public class DropPanel extends AbstractPanel{
    /**
     * Creates a new drop panel.
     *
     * @param type the type of the panel.
     */
    public DropPanel(PanelType type, final int id) {
        super(type,id);
    }
    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    @Override
    public void activatedBy(@NotNull Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
