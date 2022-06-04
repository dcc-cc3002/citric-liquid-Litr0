package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.Character.WildUnit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossPanel extends AbstractPanel{
    /**
     * Creates a new boss panel.
     *
     * @param type the type of the panel.
     */
    public BossPanel(PanelType type, final int id) {
        super(type,id);
    }

    /**
     * creates a random boss Unit if the norma level of any player is <= 4, if not generates a random wild unit.
     * @param player
     */
    @Override
    public void activatedBy(@NotNull Player player) {
        System.out.println("Boss Battle activated");
    }
}
