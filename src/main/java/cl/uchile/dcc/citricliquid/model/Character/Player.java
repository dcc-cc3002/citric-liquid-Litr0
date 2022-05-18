package cl.uchile.dcc.citricliquid.model.Character;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends AbstractCharacter {
  private int normaLevel;
  private int wins;
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
    wins = 0;
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
           && getName().equals(player.getName());
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(name, maxHp, atk, def, evd);
  }


  /**
   * increases wins if a player beats a Character
   * @param ICharacter
   */
  public void increaseWinsBy(ICharacter ICharacter) {
    ICharacter.increaseWinsByPlayer(this);
  }
  public void increaseWinsByPlayer(Player player){
    player.increaseWinsBy(2);
  }
  public void increaseWinsBy(int amount){
    this.wins += amount;
  }
}
