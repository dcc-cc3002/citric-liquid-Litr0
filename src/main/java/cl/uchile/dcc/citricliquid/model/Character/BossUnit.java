package cl.uchile.dcc.citricliquid.model.Character;

public class BossUnit extends AbstractCharacter {
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
    public BossUnit(String name, int hp, final int atk,final int def, final int evd){
        super(name, hp, atk, def, evd);
    }
    /**
     * Increases Stars to the Character that wins the battle
     */
    @Override
    public void increaseStarsBy(ICharacter ICharacter) {
        ICharacter.increaseStarsByBossUnit(this);
    }
    /**
     * Increases Stars if a Boss Unit is defeated by a Player
     */
    @Override
    public void increaseStarsByPlayer(Player winner) {
        winner.increaseStarsBy(this.getStars());
        this.reduceStarsBy(this.getStars());

    }
    /**
     * Increases Stars if a Boss Unit is defeated by a Wild Unit (this function needs to exist but in the game it won't be used,
     * because battles are between Players or a Player v/s an NPC (Boss or Wild Unit), so it won't do anything)
     */
    @Override
    public void increaseStarsByWildUnit(WildUnit wildunit) {

    }
    /**
     * Increases Stars if a Boss Unit is defeated by a Boss Unit (this function needs to exist but in the game it won't be used,
     * because battles are between Players or a Player v/s an NPC (Boss or Wild Unit), so it won't do anything)
     */
    @Override
    public void increaseStarsByBossUnit(BossUnit winner) {
    }

    /**
     * increases a player wins if it's beats a Boss Unit
     * @param player
     */
    @Override
    public void increaseWinsByPlayer(Player player) {
        player.increaseWinsBy(3);
    }

    @Override
    public BossUnit copy(){
        return new BossUnit(this.getName(), this.getMaxHp(), this.getAtk(), this.getDef(), this.getEvd());
    }
}
