package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private HomePanel testHomePanel;
  private NeutralPanel testNeutralPanel;
  private NeutralPanel testNeutralPanel2;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel(PanelType.BONUS,1);
    testBossPanel = new BossPanel(PanelType.BOSS, 1);
    testDropPanel = new DropPanel(PanelType.DROP, 1);
    testEncounterPanel = new EncounterPanel(PanelType.ENCOUNTER,1);
    testHomePanel = new HomePanel(PanelType.HOME,1);
    testNeutralPanel = new NeutralPanel(PanelType.NEUTRAL,1);
    testNeutralPanel2 = new NeutralPanel(PanelType.NEUTRAL,2);
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    BonusPanel expectedBonusPanel = new BonusPanel(PanelType.BONUS, 1);
    BossPanel expectedBossPanel = new BossPanel(PanelType.BOSS, 1);
    DropPanel expectedDropPanel = new DropPanel(PanelType.DROP, 1);
    EncounterPanel expectedEncounterPanel = new EncounterPanel(PanelType.ENCOUNTER,1);
    HomePanel expectedHomePanel = new HomePanel(PanelType.HOME,1);
    NeutralPanel expectedNeutralPanel = new NeutralPanel(PanelType.NEUTRAL,1);
    NeutralPanel expectedNeutralPanel2 = new NeutralPanel(PanelType.NEUTRAL,2);
    assertEquals(expectedBonusPanel, testBonusPanel);
    assertEquals(expectedBossPanel, testBossPanel);
    assertEquals(expectedDropPanel, testDropPanel);
    assertEquals(expectedEncounterPanel, testEncounterPanel);
    assertEquals(expectedHomePanel, testHomePanel);
    assertEquals(expectedNeutralPanel, testNeutralPanel);
    assertEquals(expectedNeutralPanel2, testNeutralPanel2);
    testNeutralPanel.addNextPanel(testHomePanel);
    testNeutralPanel.addNextPanel(testBonusPanel);
    testNeutralPanel.addNextPanel(testBossPanel);
    testNeutralPanel.addNextPanel(testNeutralPanel2);
    testNeutralPanel.setUp(testHomePanel);
    testNeutralPanel.setDown(testBonusPanel);
    testNeutralPanel.setRight(testBossPanel);
    testNeutralPanel.setLeft(testNeutralPanel2);
    assertEquals(testHomePanel,testNeutralPanel.getUp());
    assertEquals(testBonusPanel,testNeutralPanel.getDown());
    assertEquals(testBossPanel,testNeutralPanel.getRight());
    assertEquals(testNeutralPanel2,testNeutralPanel.getLeft());

  }

  @Test
  public void PlayersListTest(){
    testNeutralPanel.addPlayer(suguri);
    assertNotEquals(testNeutralPanel2,testNeutralPanel);
    List<Player> expectedPlayerList = new ArrayList<>();
    expectedPlayerList.add(suguri);
    assertEquals(expectedPlayerList,testNeutralPanel.getPlayersList());
    testNeutralPanel.removePlayer(suguri);
    expectedPlayerList.remove(suguri);
    assertEquals(expectedPlayerList,testNeutralPanel.getPlayersList());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel(PanelType.NEUTRAL,3);
    final var expectedPanel2 = new NeutralPanel(PanelType.NEUTRAL,4);

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),
                 testNeutralPanel.getNextPanels());

  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    suguri.setCurrentHp(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHp());
    testHomePanel.setOwner(suguri);
    assertEquals(suguri,testHomePanel.getOwner());

  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
  // endregion
}