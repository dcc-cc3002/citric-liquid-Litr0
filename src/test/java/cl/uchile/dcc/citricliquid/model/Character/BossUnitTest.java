package cl.uchile.dcc.citricliquid.model.Character;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

class BossUnitTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String WILD_NAME = "Chicken";
    private WildUnit chicken;
    private final static String BOSS_NAME = "Store Manager";
    private BossUnit storemanager;

    @BeforeEach
    public void setUp() {
        suguri = getSuguri();
        chicken = getChicken();
        storemanager = getStoremanager();
    }
    private Player getSuguri(){
        return new Player(PLAYER_NAME, 4, 1, -1, 2);
    }
    private WildUnit getChicken(){
        return new WildUnit(WILD_NAME, 5, 3, -1, 1);
    }
    private BossUnit getStoremanager(){
        return new BossUnit(BOSS_NAME,8,3,2,-1);
    }

    @Test
    public void copyTest() {
        final var expectedStoremanager = getStoremanager();
        final var actualStoremanager = storemanager.copy();
        // Checks that the copied player have the same parameters as the original
        Assertions.assertEquals(expectedStoremanager, actualStoremanager);
        // Checks that the copied player doesn't reference the same object
        Assertions.assertNotSame(expectedStoremanager, actualStoremanager);
    }
    @RepeatedTest(100)
    public void attackTest(){
        long seed = new Random().nextLong();
        int r = new Random(seed).nextInt(6)+1;
        storemanager.setSeed(seed);
        int expectedattack = storemanager.getAtk() + r;
        Assertions.assertEquals(expectedattack,storemanager.attack());
    }
    @RepeatedTest(100)
    public void defendTest(){
        long seed = new Random().nextLong();
        int r = new Random(seed).nextInt(6)+1;
        suguri.setSeed(seed);
        storemanager.setSeed(seed);
        int atk = suguri.attack();
        int defend = Math.max(atk-storemanager.getDef()-r,1);
        int expectedHP = Math.max(storemanager.getCurrentHp()-defend,0);
        storemanager.defend(atk);
        Assertions.assertEquals(expectedHP,storemanager.getCurrentHp());
    }
    @RepeatedTest(100)
    public void evadeTest(){
        long seed = new Random().nextLong();
        long seed2 = new Random().nextLong();
        int r = new Random(seed).nextInt(6)+1;
        storemanager.setSeed(seed);
        chicken.setSeed(seed2);
        int storemanagerHP = storemanager.getCurrentHp();
        int atk = chicken.attack();
        int evd = storemanager.getEvd() + r;
        storemanager.evade(atk);
        if (atk >= evd){
            int expectedHP = Math.max(0,(storemanagerHP-atk));
            Assertions.assertEquals(expectedHP,storemanager.getCurrentHp());
        }
        else {
            Assertions.assertEquals(storemanagerHP,storemanager.getCurrentHp());
        }
    }
    @Test
    public void increaseStarsByTest(){
        suguri.increaseStarsBy(5);
        storemanager.increaseStarsBy(7);

        int StoreManagerStars = storemanager.getStars();
        int SuguriStars = suguri.getStars();
        suguri.increaseStarsBy(storemanager);
        int expectedStoreManagerStars = 0;
        int ExpectedSuguriStars = StoreManagerStars + SuguriStars;
        Assertions.assertEquals(expectedStoreManagerStars,storemanager.getStars());
        Assertions.assertEquals(ExpectedSuguriStars,suguri.getStars());
    }

}