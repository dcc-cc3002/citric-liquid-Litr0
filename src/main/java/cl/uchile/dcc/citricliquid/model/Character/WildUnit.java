package cl.uchile.dcc.citricliquid.model.Character;



public class WildUnit extends AbstractCharacter{
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
    public WildUnit(String name, int hp, final int atk,final int def, final int evd){
        super(name, hp, atk, def, evd);
    }

    @Override
    public void increaseStarsBy(ICharacter ICharacter) {
        ICharacter.increaseStarsByWildUnit(this);
    }

    @Override
    public void increaseStarsByPlayer(Player winner){
    }

    @Override
    /**
     * Increases Stars if a player is defeated by a Wild Unit
     */
    public void increaseStarsByWildUnit(WildUnit winner){
    }

    @Override
    public void increaseStarsByBossUnit(BossUnit winner) {

    }

    @Override
    public WildUnit copy(){
        return new WildUnit(this.getName(), this.getMaxHp(), this.getAtk(), this.getDef(), this.getEvd());
    }

}
