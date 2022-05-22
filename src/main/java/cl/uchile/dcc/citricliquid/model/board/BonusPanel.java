package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.jetbrains.annotations.NotNull;

public class BonusPanel extends AbstractPanel{
    /**
     * Creates a new bonus panel.
     *
     * @param type the type of the panel.
     */
    public BonusPanel(PanelType type, final int id) {
        super(type,id);
    }
    /**
     * Increases the player's star count by the D6 roll multiplied by the maximum between the player's
     * norma level and three.
     */
    @Override
    public void activatedBy(@NotNull Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }
}
