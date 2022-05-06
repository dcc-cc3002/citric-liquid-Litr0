package cl.uchile.dcc.citricliquid.model.Character;

import java.util.Random;

/**
 * This class represent the abstract Character of the game
 */
public class AbstractCharacter {
    private final Random random;
    private final String name;
    private final int maxHp;
    protected final int atk;
    protected int def;
    protected final int evd;
    protected int stars;
    protected int currentHp;
    protected int wins;


    /**
     * Creates a new character
     * @param name
     *         the character's name.
     * @param hp
     *         the initial (and max) hit points of the character.
     * @param atk
     *         the base damage the character does.
     * @param def
     *         the base defense of the character.
     * @param evd
     *         the base evasion of the character.
     */
    public AbstractCharacter(String name, int hp, int atk, int def, int evd){
        this.name = name;
        this.maxHp = currentHp = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        stars = 0;
        wins = 0;
        random = new Random();
    }

    /**
     * Return the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the character's Max Hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Return the character's atk points
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Return the character's def points
     */
    public int getDef() {
        return def;
    }

    /**
     * Return the character's evd points
     */
    public int getEvd() {
        return evd;
    }

    /**
     * Returns the character stars
     */
    public int getStars() {
        return stars;
    }

    /**
     * Returns the character Current Hp
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * Return the character's wins
     */
    public int getWins() {
        return wins;
    }
    /**
     * Sets the current character's hit points.
     *
     * <p>The character's hit points have a constraint to always be between 0 and maxHP, both
     * inclusive.
     */
    public void setCurrentHp(final int newHp) {
        this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
    }
    /**
     * Returns a uniformly distributed random value in [1, 6].
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }
    /**
     * Set's the seed for this player's random number generator.
     *
     * <p>The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

}
