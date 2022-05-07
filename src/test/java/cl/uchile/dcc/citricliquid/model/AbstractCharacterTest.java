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
    public void constructorTest() {
        final var expectedSuguri = getSuguri();
        Assertions.assertEquals(expectedSuguri, suguri);
        Assertions.assertNotEquals(null,suguri);
        Assertions.assertNotEquals(suguri,new Object());
        Assertions.assertNotSame(suguri,getSuguri());
        Assertions.assertEquals(0,suguri.getStars());
        Assertions.assertEquals(0,suguri.getWins());
    }


}
