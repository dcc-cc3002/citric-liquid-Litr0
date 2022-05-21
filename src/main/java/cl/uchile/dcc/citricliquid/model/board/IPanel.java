package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.List;

public interface IPanel {
    Set<AbstractPanel> getNextPanels();

    PanelType getType();

    List<Player> getPlayersList();

    void addNextPanel(final AbstractPanel panel);

    void activatedBy(final @NotNull Player player);

    void addPlayer(Player player);

    void removePlayer(Player player);
}
