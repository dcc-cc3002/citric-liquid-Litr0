package cl.uchile.dcc.citricliquid.model.Character;

/**
 * Interface for the character class where are the common functions between the 3 types
 * of character.
 */
public interface ICharacter {
    /**
     * increases the wins of a player depending on what the player beats.
     * @param player
     */
    void increaseWinsByPlayer(Player player);

    /**
     * returns the name of the character.
     */
    String getName();

    /**
     * returns the max hp of the character.
     */
    int getMaxHp();

    /**
     * returns the attack of the character.
     */
    int getAtk();

    /**
     * returns the defense.
     */
    int getDef();

    /**
     * returns the evade points of the character.
     */
    int getEvd();

    /**
     *attack function.
     */
    int attack();

    /**
     * Set's the seed for this character's random number generator.
     * @param l
     */
    void setSeed(long l);

    /**
     * returns the current hp of the character.
     * @return
     */
    int getCurrentHp();

    /**
     * changes the hp of a character.
     * @param newHp
     */
    void setCurrentHp(int newHp);

    /**
     * evades an attack.
     * @param attack
     */
    void evade(int attack);

    /**
     * defends an attack
     * @param attack
     */
    void defend(int attack);

    /**
     * copies a character.
     */
    ICharacter copy();

    /**
     * Increases the stars depending on what is beaten
     * @param ICharacter
     */
    void increaseStarsBy(ICharacter ICharacter);

    /**
     * increases a player's stars depending on what is beaten
     * @param player
     */
    void increaseStarsByPlayer(Player player);
    /**
     * increases a Wild Unit's stars depending on what is beaten
     * @param wildunit
     */
    void increaseStarsByWildUnit(WildUnit wildunit);
    /**
     * increases a Boss Unit's stars depending on what is beaten
     * @param bossunit
     */
    void increaseStarsByBossUnit(BossUnit bossunit);
}
