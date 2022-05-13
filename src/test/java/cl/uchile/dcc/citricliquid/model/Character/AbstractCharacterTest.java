package cl.uchile.dcc.citricliquid.model.Character;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
/**
 * Test for the common utilities of the Playable and non-playable Characters
 */
public class AbstractCharacterTest {
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
    /**
     * Constructor and equals tests
     */
    public void constructorTest() {
        final var expectedSuguri = getSuguri();
        Assertions.assertEquals(expectedSuguri, suguri);
        Assertions.assertEquals(suguri,suguri);
        Assertions.assertNotEquals(null,suguri);
        Assertions.assertNotEquals(suguri,new Object());
        Assertions.assertNotSame(suguri,getSuguri());
        Assertions.assertEquals(0,suguri.getStars());
        Assertions.assertEquals(0,suguri.getWins());

        final var expectedChicken = getChicken();
        Assertions.assertEquals(expectedChicken,chicken);
        Assertions.assertEquals(chicken,chicken);
        Assertions.assertEquals(null,chicken);
        Assertions.assertNotEquals(chicken,new Object());
        Assertions.assertNotSame(chicken,getChicken());
        Assertions.assertEquals(0,chicken.getStars());
        Assertions.assertEquals(0,chicken.getWins());

        final var expectedStoreManager = getStoremanager();
        Assertions.assertEquals(expectedStoreManager,storemanager);
        Assertions.assertEquals(storemanager,storemanager);
        Assertions.assertEquals(null,storemanager);
        Assertions.assertNotEquals(storemanager,new Object());
        Assertions.assertNotSame(storemanager,getStoremanager());
        Assertions.assertEquals(0,storemanager.getStars());
        Assertions.assertEquals(0,storemanager.getWins());
    }

    @Test
    public void hitPointsTest(){
        Assertions.assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
        suguri.setCurrentHp(2);
        Assertions.assertEquals(2,suguri.getCurrentHp());
        suguri.setCurrentHp(-1);
        Assertions.assertEquals(0,suguri.getCurrentHp());
        suguri.setCurrentHp(5);
        Assertions.assertEquals(4,suguri.getCurrentHp());

        Assertions.assertEquals(chicken.getMaxHp(), chicken.getCurrentHp());
        chicken.setCurrentHp(2);
        Assertions.assertEquals(2,chicken.getCurrentHp());
        chicken.setCurrentHp(-1);
        Assertions.assertEquals(0,chicken.getCurrentHp());
        chicken.setCurrentHp(7);
        Assertions.assertEquals(5,chicken.getCurrentHp());

        Assertions.assertEquals(storemanager.getMaxHp(), storemanager.getCurrentHp());
        storemanager.setCurrentHp(2);
        Assertions.assertEquals(2,storemanager.getCurrentHp());
        storemanager.setCurrentHp(-1);
        Assertions.assertEquals(0,storemanager.getCurrentHp());
        storemanager.setCurrentHp(10);
        Assertions.assertEquals(8,storemanager.getCurrentHp());
    }


    @RepeatedTest(100)
    public void hitPointsConsistencyTest() {
        final long testSeed = new Random().nextLong();
        final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHp() + 1)
                - 2 * suguri.getMaxHp();
        suguri.setCurrentHp(testHP);
        Assertions.assertTrue(0 <= suguri.getCurrentHp()
                        && suguri.getCurrentHp() <= suguri.getMaxHp(),
                suguri.getCurrentHp() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);
        final long testSeed2 = new Random().nextLong();
        final int testHP2 = new Random(testSeed2).nextInt(4 * storemanager.getMaxHp() + 1)
                - 2 * storemanager.getMaxHp();
        storemanager.setCurrentHp(testHP2);
        Assertions.assertTrue(0 <= storemanager.getCurrentHp()
                        && storemanager.getCurrentHp() <= storemanager.getMaxHp(),
                storemanager.getCurrentHp() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);
        final long testSeed3 = new Random().nextLong();
        final int testHP3 = new Random(testSeed3).nextInt(4 * chicken.getMaxHp() + 1)
                - 2 * chicken.getMaxHp();
        chicken.setCurrentHp(testHP3);
        Assertions.assertTrue(0 <= chicken.getCurrentHp()
                        && chicken.getCurrentHp() <= chicken.getMaxHp(),
                chicken.getCurrentHp() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);
    }
    @RepeatedTest(100)
    public void rollConsistencyTest() {
        final long testSeed = new Random().nextLong();
        suguri.setSeed(testSeed);
        final int roll = suguri.roll();
        Assertions.assertTrue(roll >= 1 && roll <= 6,
                roll + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed);
        final long testSeed2 = new Random().nextLong();
        storemanager.setSeed(testSeed2);
        final int roll2 = storemanager.roll();
        Assertions.assertTrue(roll2 >= 1 && roll2 <= 6,
                roll2 + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed2);
        final long testSeed3 = new Random().nextLong();
        chicken.setSeed(testSeed3);
        final int roll3 = chicken.roll();
        Assertions.assertTrue(roll3 >= 1 && 3 <= 6,
                roll3 + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed3);
    }

    // endregion


}
