package cl.uchile.dcc.citricliquid.model.Character;

import cl.uchile.dcc.citricliquid.model.board.HomePanel;
import cl.uchile.dcc.citricliquid.model.board.IPanel;

import java.util.List;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:gonzalo.sobarzo@ug.uchile.cl">Gonzalo Sobarzo A.</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends AbstractCharacter {
  private int normaLevel;
  private int wins;
  private ObjectiveNorma objectiveNorma;
  private IPanel homepanel;
  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player (final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
    this.objectiveNorma = ObjectiveNorma.STARS;
    wins = 0;
    this.homepanel = null;
  }

  /**
   * Returns the current objective
   * @return
   */
  public ObjectiveNorma getObjectiveNorma() {
    return objectiveNorma;
  }

  /**
   * sets a new objective
   * @param objectiveNorma
   */
  public void setObjectiveNorma(ObjectiveNorma objectiveNorma) {
    this.objectiveNorma = objectiveNorma;
  }

  /**
   * Return the character's wins
   */
  public int getWins() {
    return wins;
  }
  /**
   * Returns the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Returns the Home Panel
   */
  public IPanel getHomepanel(){
    return homepanel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }

  /**
   * Increases Stars to the Character that wins the battle
   */
  @Override
  public void increaseStarsBy(ICharacter ICharacter){
    ICharacter.increaseStarsByPlayer(this);
  }
  /**
   * Increases Stars if a player is defeated by a Player
   */
  @Override
  public void increaseStarsByPlayer(Player winner){
    winner.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
    this.reduceStarsBy((int) Math.floor(this.getStars()*0.5));
  }

  /**
   * Increases Stars if a player is defeated by a Wild Unit
   */
  @Override
  public void increaseStarsByWildUnit(WildUnit winner){
    winner.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
    this.reduceStarsBy((int) Math.floor(this.getStars()*0.5));
  }
  /**
   * Increases Stars if a player is defeated by a Boss Unit
   */
  @Override
  public void increaseStarsByBossUnit(BossUnit winner){
    winner.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
    this.reduceStarsBy((int) Math.floor(this.getStars()*0.5));
  }

  /**
   *compares two objects
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Player player)) {
      return false;
    }
    return getMaxHp() == player.getMaxHp()
           && getAtk() == player.getAtk()
           && getDef() == player.getDef()
           && getEvd() == player.getEvd()
           && getNormaLevel() == player.getNormaLevel()
           && getWins() == player.getWins()
           && getStars() == player.getStars()
           && getCurrentHp() == player.getCurrentHp()
           && getName().equals(player.getName())
           && getHomepanel() == player.getHomepanel();
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
  }


  /**
   * increases wins if a player beats a Character
   * @param ICharacter
   */
  public void increaseWinsBy(ICharacter ICharacter) {
    ICharacter.increaseWinsByPlayer(this);
  }

  /**
   * increases wins if a player beats a player
   * @param player
   */
  public void increaseWinsByPlayer(Player player){
    player.increaseWinsBy(2);
  }
  public void increaseWinsBy(int amount){
    this.wins += amount;
  }

  /**
   * checks if the player can level up the norma
   */
  public void normaCheck() {
    if(this.objectiveNorma == ObjectiveNorma.WINS){
      normaCheckWins();
    }
    if(this.objectiveNorma == ObjectiveNorma.STARS){
      normaCheckStars();
    }
  }

  /**
   * checks if the player can level up the norma by the stars objective
   */
  public void normaCheckStars(){
    List<Integer> starsObjective = List.of(10,30,70,120,200);
    int stars = this.getStars();
    if (starsObjective.get((this.normaLevel)-1) <= stars){
      normaClear();
    }
  }
  /**
   * checks if the player can level up the norma by the wins objective
   */
  public void normaCheckWins(){
    List<Integer> winsObjective = List.of(0,2,5,9,14);
    int wins = this.getWins();
    if (winsObjective.get((this.normaLevel)-1) <= wins){
      normaClear();
    }
  }

  /**
   * Sets the player's home panel
   * @param panel
   */
  public void SetHomePanel(HomePanel panel) {
    if (panel.owner == null) {
      this.homepanel = panel;
      panel.setOwner(this);
    }
  }
}
