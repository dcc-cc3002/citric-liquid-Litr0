package cl.uchile.dcc.citricliquid.model.Character;

import cl.uchile.dcc.citricliquid.model.Character.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class PlayerTest {
  private final static String PLAYER_NAME = "Suguri";
  private Player suguri;
  private final static String WILD_NAME = "Chicken";
  private WildUnit chicken;
  private final static String WILD_NAME_2 = "Seagull";
  private WildUnit seagull;
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
  private WildUnit getSeagull(){
    return new WildUnit(WILD_NAME_2,3,1,-1,-1);
  }

  @Test
  public void normaClearTest() {
    suguri.normaClear();
    Assertions.assertEquals(2, suguri.getNormaLevel());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = getSuguri();
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedSuguri, actualSuguri);
  }

  @RepeatedTest(100)
  public void attackTest(){
    long seed = new Random().nextLong();
    int r = new Random(seed).nextInt(6)+1;
    suguri.setSeed(seed);
    int expectedattack = suguri.getAtk()+r;
    Assertions.assertEquals(expectedattack,suguri.attack());
  }

  @RepeatedTest(100)
  public void evadeTest(){
    long seed = new Random().nextLong();
    long seed2 = new Random().nextLong();
    int r = new Random(seed).nextInt(6)+1;
    suguri.setSeed(seed);
    storemanager.setSeed(seed2);
    int suguriHP = suguri.getCurrentHp();
    int atk = storemanager.attack();
    int evd = suguri.getEvd() + r;
    suguri.evade(atk);
    if (atk >= evd){
      int expectedHP = Math.max(0,(suguriHP-atk));
      Assertions.assertEquals(expectedHP,suguri.getCurrentHp());
    }
    else {
      Assertions.assertEquals(suguriHP,suguri.getCurrentHp());
    }
  }

  @RepeatedTest(100)
  public void defendTest(){
    long seed = new Random().nextLong();
    int r = new Random(seed).nextInt(6)+1;
    suguri.setSeed(seed);
    chicken.setSeed(seed);
    int atk = chicken.attack();
    suguri.defend(atk);
    int defend = Math.max(atk-suguri.getDef()-r,1);
    int expectedHP = Math.max(suguri.getCurrentHp()-defend,0);
    Assertions.assertEquals(expectedHP,suguri.getCurrentHp());
  }


  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    Assertions.assertEquals(expectedNorma, suguri.getNormaLevel(),
                            "Test failed with random seed: " + testSeed);
  }

  // endregion
}
