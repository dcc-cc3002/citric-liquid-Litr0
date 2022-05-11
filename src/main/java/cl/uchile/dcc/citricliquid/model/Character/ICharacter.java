package cl.uchile.dcc.citricliquid.model.Character;

public interface ICharacter {
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



}
