package cl.uchile.dcc.citricliquid.model.Character;

public interface ICharacter {
    void increaseWinsByPlayer(Player player);

    String getName();

    int getMaxHp();

    int getAtk();

    int getDef();

    int getEvd();

    int attack();

    void setSeed(long l);

    int getCurrentHp();

    void setCurrentHp(int newHp);

    void evade(int attack);

    void defend(int attack);

    ICharacter copy();

    void increaseStarsBy(ICharacter ICharacter);

    void increaseStarsByPlayer(Player player);

    void increaseStarsByWildUnit(WildUnit wildunit);

    void increaseStarsByBossUnit(BossUnit bossunit);
}
