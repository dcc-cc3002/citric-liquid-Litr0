package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.List;

/**
 * Interface for the panel class where are the common functions between the panels.
 */
public interface IPanel {
    /**
     * function go get the panels that are next to the active one.
     */
    Set<AbstractPanel> getNextPanels();

    /**
     * returns the type to of the panel.
     */
    PanelType getType();

    /**
     * returns the list of players in the panel.
     */
    List<Player> getPlayersList();

    /**
     * adds a panel next to the other.
     * @param panel
     */
    void addNextPanel(final AbstractPanel panel);

    /**
     * function to activate the panel.
     * @param player
     */
    void activatedBy(final @NotNull Player player);

    /**
     * adds a player to the list of players in the panel.
     * @param player
     */
    void addPlayer(Player player);

    /**
     * removes a player from the list of players.
     * @param player
     */
    void removePlayer(Player player);
}
