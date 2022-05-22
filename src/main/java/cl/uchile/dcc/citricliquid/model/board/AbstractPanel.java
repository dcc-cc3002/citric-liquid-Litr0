package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.*;

import java.util.*;

import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel {
  private final PanelType type;
  private final Set<AbstractPanel> nextPanels = new HashSet<>();
  private final List<Player> playersList = new ArrayList<>();
  private final int id;

  /**
   * Creates a new panel.
   *
   * @param type the type of the panel.
   * @param id id of the panel
   */
  public AbstractPanel(final PanelType type, final int id ) {
    this.type = type;
    this.id = id;
  }


  /**
   * Returns the type of this panel.
   */
  public PanelType getType() {
    return type;
  }

  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<AbstractPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * returns the list of players in the panel
   */
  public List<Player> getPlayersList() {
    return playersList;
  }
  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(final AbstractPanel panel) {
    nextPanels.add(panel);
  }

  /**
   * Executes the appropriate action to the player according to this panel's type.
   */
  public abstract void activatedBy(final @NotNull Player player);

  /**
   *Compares two objects
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPanel)) return false;
    AbstractPanel that = (AbstractPanel) o;
    return Objects.equals(nextPanels,that.nextPanels)
            && Objects.equals(playersList,that.playersList)
            && Objects.equals(type,that.type)
            && id == that.id;

  }

  @Override
  public int hashCode() {
    return Objects.hash(getType(), getNextPanels(), getPlayersList());
  }

  /**
   * adds a player to the panel
   * @param player
   */
  public void addPlayer(Player player){
    this.playersList.add(player);
  }

  @Override
  public void removePlayer(Player player) {
    this.playersList.remove(player);
  }
}
