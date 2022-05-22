package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.jetbrains.annotations.NotNull;

public class NeutralPanel extends AbstractPanel{
    /**
     * Creates a new Neutral Panel
     */
    public NeutralPanel(PanelType type, final int id) {
        super(type,id);
    }

    /**
     * neutral panel when activated does nothing
     * @param player
     */
    @Override
    public void activatedBy(final @NotNull Player player){
    }

}
