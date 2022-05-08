package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.Character.Player;
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

    @BeforeEach
    public void setUp() {
        suguri = getSuguri();
    }
    private Player getSuguri(){ //
        return new Player(PLAYER_NAME, 4, 1, -1, 2);
    }


    @Test
    /**
     * Constructor and equals tests (wild unit and boss unit test missing)
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
    }

    @RepeatedTest(100)
    public void hitPointsConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHp() + 1)
                - 2 * suguri.getMaxHp();
        suguri.setCurrentHp(testHP);
        Assertions.assertTrue(0 <= suguri.getCurrentHp()
                        && suguri.getCurrentHp() <= suguri.getMaxHp(),
                suguri.getCurrentHp() + "is not a valid HP value"
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
    }
    // endregion


}
