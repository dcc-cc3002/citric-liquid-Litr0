package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HomePanel extends AbstractPanel{

    /**
     * Creates a new home panel.
     *
     * @param type the type of the panel.
     */
    public HomePanel(PanelType type, final int id) {
        super(type, id);
    }

    /**
     * Restores a player's HP in 1 and does a norma check
     */
    @Override
    public void activatedBy(@NotNull Player player) {
        if (!player.KO_Status()){
            player.setCurrentHp(player.getCurrentHp() + 1);
        }
        player.normaCheck();
    }
}
