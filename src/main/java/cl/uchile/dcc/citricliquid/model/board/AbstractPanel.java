package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.*;

import java.util.*;

import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:gonzalo.sobarzo@ug.uchile.cl">Gonzalo Sobarzo A.</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel {
  private final PanelType type;
  private final Set<AbstractPanel> nextPanels = new HashSet<>();
  private final List<Player> playersList = new ArrayList<>();
  private final int id;
  private IPanel up;
  private IPanel down;
  private IPanel right;
  private IPanel left;

  /**
   * Creates a new panel.
   *
   * @param type the type of the panel.
   * @param id id of the panel
   */
  public AbstractPanel(final PanelType type, final int id) {
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
   * Returns the id.
   */
  public int getId(){
    return id;
  }

  /**
   * Set's a panel above
   */
  public void setUp(IPanel panel){
    this.up = panel;
  }
  /**
   * Set's a panel Down
   */
  public void setDown(IPanel panel){
    this.down = panel;
  }

  /**
   * Set's a panel right
   */
  public void setRight(IPanel panel){
    this.right = panel;
  }

  /**
   * Set's a panel left
   */
  public void setLeft(IPanel panel){
    this.left = panel;
  }

  /**
   * Get's the panel above
   */
  public IPanel getUp(){
    return up;
  }
  /**
   * Get's the panel down
   */
  public IPanel getDown(){
    return down;
  }
  /**
   * Get's the panel right
   */
  public IPanel getRight(){
    return right;
  }
  /**
   * Get's the panel left
   */
  public IPanel getLeft(){
    return left;
  }


  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(final AbstractPanel panel) {
    if (this.getId() != panel.getId()) {
      nextPanels.add(panel);
    }
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
    return Objects.equals(nextPanels, that.nextPanels)
            && Objects.equals(playersList, that.playersList)
            && Objects.equals(type, that.type)
            && Objects.equals(left, that.left)
            && Objects.equals(right, that.right)
            && Objects.equals(up, that.up)
            && Objects.equals(down, that.down)
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

  /**
   * Removes a player from the panel
   * @param player
   */
  @Override
  public void removePlayer(Player player) {
    this.playersList.remove(player);
  }
}


