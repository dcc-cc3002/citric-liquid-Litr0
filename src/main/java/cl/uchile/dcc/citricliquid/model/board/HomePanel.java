package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HomePanel extends AbstractPanel{
    public Player owner;

    /**
     * Creates a new home panel.
     *
     * @param type the type of the panel.
     */
    public HomePanel(PanelType type) {
        super(type);
        this.owner = null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if(this.owner == null) {
            this.owner = owner;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomePanel)) return false;
        if (!super.equals(o)) return false;
        HomePanel homePanel = (HomePanel) o;
        return Objects.equals(getOwner(), homePanel.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOwner());
    }

    /**
     * Restores a player's HP in 1 and does a norma check
     */
    @Override
    public void activatedBy(@NotNull Player player) {
        if (player.equals(this.getOwner()) == true ) {
            player.normaCheck();
            player.setCurrentHp(player.getCurrentHp() + 1);
        }
    }
}
