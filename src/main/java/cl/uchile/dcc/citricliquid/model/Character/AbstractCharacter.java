package cl.uchile.dcc.citricliquid.model.Character;

import java.util.Objects;
import java.util.Random;

/**
 * This class represent the abstract Character of the game
 */
public abstract class AbstractCharacter implements ICharacter {
    private final Random random;
    private final String name;
    protected int maxHp;
    protected final int atk;
    protected int def;
    protected final int evd;
    protected int stars;
    protected int currentHp;


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
     * Set's the seed for this character's random number generator.
     *
     * <p>The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * the character attack's
     * attack decided by a roll of the dice
     */
    public  int attack(){
        return (this.roll()+this.getAtk());
    }

    /**
     * executed if character chooses to avoid the attack
     * @param attack
     */
    public void evade(int attack) {
        int evd = (this.getEvd() + this.roll());
        int HP = this.getCurrentHp();
        if (attack >= evd){
            int actual = Math.max(0,(HP-attack));
            this.setCurrentHp(actual);
        }
    }

    /**
     * executed if character chooses to defend the attack
     * @param attack
     */
    public void defend(int attack) {
        int defense = (this.getDef() + this.roll());
        int atk = Math.max((attack-defense),1);
        this.setCurrentHp(this.getCurrentHp()-atk);
    }

    /**
     * increases stars of a character post battle
     * @param ICharacter
     */
    public abstract void increaseStarsBy(ICharacter ICharacter);

    /**
     * Increases this player's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Reduces this player's star count by a given amount.
     *
     * <p>The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Equals: compares two objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractCharacter that)) {
            return false;
        }
        return getMaxHp() == that.getMaxHp()
                && getAtk() == that.getAtk()
                && getDef() == that.getDef()
                && getEvd() == that.getEvd()
                && getStars() == that.getStars()
                && getCurrentHp() == that.getCurrentHp()
                && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(random, getName(), getMaxHp(), getAtk(), getDef(), getEvd(), getStars(), getCurrentHp());
    }

    public abstract ICharacter copy();
}
