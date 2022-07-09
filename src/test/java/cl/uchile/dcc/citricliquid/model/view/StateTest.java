package cl.uchile.dcc.citricliquid.model.view;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.board.BonusPanel;
import cl.uchile.dcc.citricliquid.model.board.DropPanel;
import cl.uchile.dcc.citricliquid.model.board.HomePanel;
import cl.uchile.dcc.citricliquid.model.board.NeutralPanel;
import cl.uchile.dcc.citricliquid.view.Controller;
import cl.uchile.dcc.citricliquid.view.States.InvalidMovementException;
import cl.uchile.dcc.citricliquid.view.States.InvalidTransitionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StateTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String PLAYER_NAME_2 = "Marc";
    private Player marc;
    private final static String PLAYER_NAME_3 = "Poppo";
    private Player poppo;
    private final static String PLAYER_NAME_4 = "Kai";
    private Player kai;
    private List<Player> playerList = new ArrayList<>();
    private HomePanel HomePanelSuguri;
    private HomePanel HomePanelMarc;
    private HomePanel HomePanelPoppo;
    private HomePanel HomePanelKai;
    private NeutralPanel TestNeutralPanel1;
    private NeutralPanel TestNeutralPanel2;
    private NeutralPanel TestNeutralPanel3;
    private NeutralPanel TestNeutralPanel4;
    private NeutralPanel TestNeutralPanel5;
    private DropPanel TestDropPanel;
    private BonusPanel TestBonusPanel;
    private long seed;
    private Controller controller;

    @BeforeEach
    public void SetUP(){
        seed = new Random().nextLong();
        controller = new Controller();
        HomePanelKai = controller.addHomePanel(25);
        HomePanelMarc = controller.addHomePanel(26);
        HomePanelPoppo = controller.addHomePanel(27);
        HomePanelSuguri = controller.addHomePanel(28);

        suguri = controller.addPlayer(PLAYER_NAME,4,1,-1,2, HomePanelSuguri);
        marc = controller.addPlayer(PLAYER_NAME_2,4,1,1,-1, HomePanelMarc);
        poppo = controller.addPlayer(PLAYER_NAME_3,7,-1,-1,-1, HomePanelPoppo);
        kai = controller.addPlayer(PLAYER_NAME_4,5,1,0,0, HomePanelKai);


        TestDropPanel = controller.addDropPanel(34);

        TestBonusPanel = controller.addBonusPanel(35);

        TestNeutralPanel1 = controller.addNeutralPanel(29);
        TestNeutralPanel2 = controller.addNeutralPanel(30);
        TestNeutralPanel3 = controller.addNeutralPanel(31);
        TestNeutralPanel4 = controller.addNeutralPanel(32);
        TestNeutralPanel5 = controller.addNeutralPanel(33);

        playerList.add(suguri);
        playerList.add(marc);
        playerList.add(poppo);
        playerList.add(kai);


    }

    @Test
    public void LocationTest(){ //works also as a test for SetActualPanel
        Assertions.assertTrue(HomePanelSuguri.getPlayersList().contains(suguri));
        Assertions.assertFalse(HomePanelSuguri.getPlayersList().contains(marc));
        Assertions.assertTrue(HomePanelMarc.getPlayersList().contains(marc));
        Assertions.assertTrue(HomePanelPoppo.getPlayersList().contains(poppo));
        Assertions.assertTrue(HomePanelKai.getPlayersList().contains(kai));
    }


    @Test
    public void MovingThroughStatesTest() throws InvalidTransitionException {
        Assertions.assertEquals("Start State", controller.getState2());
        controller.getState().toRecoveryPhase();
        Assertions.assertEquals("Recovery State", controller.getState2());
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toChoosePathState());
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toBattleState(suguri, marc));
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toWaitingFightState(suguri, marc));
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toWaitHomeState());
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toCanMoveState());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().attack());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().defend());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().evade());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().stayAtHome());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().endTurn());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().right());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().keepMoving());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().move());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().firstMove());
        controller.getState().toStartState();
        Assertions.assertEquals("Start State", controller.getState2());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().revive());
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toEndTurnState());
        Assertions.assertThrows(InvalidMovementException.class, () -> controller.getState().iAmGoingToFight());
        controller.getState().toCanMoveState();
        Assertions.assertNotEquals("Start State", controller.getState2());
        Assertions.assertEquals("Can Move State", controller.getState2());
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toRecoveryPhase());
        controller.getState().toChoosePathState();
        Assertions.assertEquals("Choose Path State", controller.getState2());
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toStartState());
        controller.getState().toWaitHomeState();
        Assertions.assertEquals("Wait Home State", controller.getState2());
        controller.getState().toCanMoveState();
        Assertions.assertEquals("Can Move State", controller.getState2());
        controller.getState().toWaitingFightState(suguri, marc);
        Assertions.assertEquals("Waiting Fight State", controller.getState2());
        controller.getState().toBattleState(suguri, marc);
        Assertions.assertEquals("Battle State", controller.getState2());
        controller.getState().toWaitingFightState(suguri, marc);
        controller.getState().toEndTurnState();
        Assertions.assertEquals("End Turn State", controller.getState2());
        controller.getState().toStartState();
    }

    @RepeatedTest(75)
    public void StartStateAndRecoveryTest() throws InvalidMovementException {

        Assertions.assertEquals(suguri, controller.getControl());
        Assertions.assertEquals("Start State", controller.getState2());
        controller.getControl().setCurrentHp(0); //let's try the recovery phase
        Assertions.assertTrue(controller.KO_status());


        controller.getControl().setSeed(seed);
        controller.tryToStart();
        int roll = new Random(seed).nextInt(6) + 1;
        List<Integer> requirement = List.of(6, 5, 4, 3, 2, 1);
        int chapterRequirement = (controller.getChapter()) - 1;
        if (roll >= requirement.get(chapterRequirement)){
            Assertions.assertEquals(suguri.getName(), controller.getControl().getName());
            Assertions.assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
            Assertions.assertEquals("Start State", controller.getState2());
        }
        else {
            Assertions.assertEquals(0, suguri.getCurrentHp());
            Assertions.assertEquals("Start State", controller.getState2());
            Assertions.assertEquals(marc.getName(), controller.getControl().getName());
        }
    }

    @RepeatedTest(75)
    public void atHomeTestAndSomethingElse(){
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel1);
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel2);
        HomePanelSuguri.setUp(TestNeutralPanel1);
        HomePanelSuguri.setLeft(TestNeutralPanel2);
        controller.setNextPanel(TestNeutralPanel1, HomePanelSuguri);
        controller.setNextPanel(TestNeutralPanel1, TestBonusPanel);
        TestNeutralPanel1.setLeft(TestBonusPanel);
        TestNeutralPanel1.setDown(HomePanelSuguri);
        controller.setNextPanel(TestBonusPanel, TestNeutralPanel1);
        controller.setNextPanel(TestBonusPanel, TestNeutralPanel2);
        TestBonusPanel.setRight(TestNeutralPanel1);
        TestBonusPanel.setDown(TestNeutralPanel2);
        controller.setNextPanel(TestNeutralPanel2, TestBonusPanel);
        controller.setNextPanel(TestNeutralPanel2, HomePanelSuguri);
        TestNeutralPanel2.setRight(HomePanelSuguri);
        TestNeutralPanel2.setUp(TestBonusPanel);

        Assertions.assertEquals("Start State", controller.getState2());
        Assertions.assertEquals(suguri, controller.getControl());
        controller.getControl().setCurrentHp(controller.getControl().getMaxHp() - 2);
        controller.getControl().setSeed(seed);
        controller.tryToStart();
        int roll = new Random(seed).nextInt(6) + 1;

        Assertions.assertEquals("Choose Path State", controller.getState2());
        Assertions.assertTrue(HomePanelSuguri.getPlayersList().contains(suguri));

        controller.tryToGoLeft();
        Assertions.assertTrue(TestNeutralPanel2.getPlayersList().contains(suguri));

        if (roll > 2){
            Assertions.assertEquals("Choose Path State", controller.getState2());
            controller.tryToGoRight(); //goes back home.
            Assertions.assertTrue(HomePanelSuguri.getPlayersList().contains(suguri));
            Assertions.assertEquals("Wait Home State", controller.getState2());
            controller.tryToStayAtHome(); //wants to stay at home
            Assertions.assertTrue(HomePanelSuguri.getPlayersList().contains(suguri));
            Assertions.assertEquals(0, controller.getRoll_steps());
            Assertions.assertEquals(1, suguri.getStars());
            Assertions.assertEquals(3, suguri.getCurrentHp());
            Assertions.assertEquals(marc, controller.getControl());
        }
    }

    @RepeatedTest(75)
    public void KeepMovement(){
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel1);
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel2);
        HomePanelSuguri.setUp(TestNeutralPanel1);
        HomePanelSuguri.setLeft(TestNeutralPanel2);
        controller.setNextPanel(TestNeutralPanel1, HomePanelSuguri);
        controller.setNextPanel(TestNeutralPanel1, TestBonusPanel);
        TestNeutralPanel1.setLeft(TestBonusPanel);
        TestNeutralPanel1.setDown(HomePanelSuguri);
        controller.setNextPanel(TestBonusPanel, TestNeutralPanel1);
        controller.setNextPanel(TestBonusPanel, TestNeutralPanel2);
        TestBonusPanel.setRight(TestNeutralPanel1);
        TestBonusPanel.setDown(TestNeutralPanel2);
        controller.setNextPanel(TestNeutralPanel2, TestBonusPanel);
        controller.setNextPanel(TestNeutralPanel2, HomePanelSuguri);
        TestNeutralPanel2.setRight(HomePanelSuguri);
        TestNeutralPanel2.setUp(TestBonusPanel);

        controller.getControl().setSeed(seed);
        int roll = new Random(seed).nextInt(6) + 1;
        controller.setPlayerPanel(suguri,TestNeutralPanel1);
        Assertions.assertEquals(suguri, controller.getControl());
        Assertions.assertEquals("Start State", controller.getState2());
        suguri.setCurrentHp(suguri.getMaxHp() - 2);
        Assertions.assertEquals(2, suguri.getCurrentHp());
        Assertions.assertNotEquals(suguri.getMaxHp(), suguri.getCurrentHp());

        controller.tryToStart();
        controller.tryToGoDown();
        Assertions.assertTrue(HomePanelSuguri.getPlayersList().contains(suguri));

        if (roll == 1){
            Assertions.assertEquals("End Turn State", controller.getState2());
        }
        if (roll == 2){
            controller.tryToKeepMoving();
            Assertions.assertEquals("Choose Path State", controller.getState2());
            controller.tryToGoLeft();
            Assertions.assertTrue(TestNeutralPanel2.getPlayersList().contains(suguri));
            Assertions.assertEquals("End Turn State", controller.getState2());
        }
        if (roll > 2){
            controller.tryToKeepMoving();
            Assertions.assertEquals("Choose Path State", controller.getState2());
            controller.tryToGoLeft();
            Assertions.assertTrue(TestNeutralPanel2.getPlayersList().contains(suguri));
            Assertions.assertEquals("Choose Path State", controller.getState2());
        }
    }

    @RepeatedTest(20)
    public void BattleTest(){
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel3);
        controller.setNextPanel(HomePanelSuguri, TestDropPanel);
        HomePanelSuguri.setUp(TestNeutralPanel3);
        HomePanelSuguri.setRight(TestDropPanel);
        controller.setNextPanel(TestDropPanel, HomePanelSuguri);
        controller.setNextPanel(TestDropPanel, TestNeutralPanel3);
        TestDropPanel.setLeft(HomePanelSuguri);
        TestDropPanel.setUp(TestNeutralPanel3);
        controller.setNextPanel(TestNeutralPanel3, HomePanelSuguri);
        controller.setNextPanel(TestNeutralPanel3, TestDropPanel);
        TestNeutralPanel3.setLeft(HomePanelSuguri);
        TestNeutralPanel3.setRight(TestDropPanel);
        controller.setPlayerPanel(marc, TestNeutralPanel3);

        controller.getControl().setSeed(seed);
        int roll = new Random(seed).nextInt(6) + 1;
        controller.tryToStart();
        controller.tryToGoUp();

        if (roll == 1){
            Assertions.assertTrue(TestNeutralPanel3.getPlayersList().contains(suguri));
            Assertions.assertEquals("End Turn State",controller.getState2());
        }
        else{
            Assertions.assertEquals("Waiting Fight State", controller.getState2());
            Assertions.assertTrue(TestNeutralPanel3.getPlayersList().contains(suguri));
            Assertions.assertTrue(TestNeutralPanel3.getPlayersList().contains(marc));
            controller.tryToFight();
            Assertions.assertEquals(suguri.getName(), controller.getControl().getName());
            Assertions.assertEquals(marc.getName(), controller.getRival().getName());
            Assertions.assertEquals(4, marc.getCurrentHp());
            controller.getControl().setSeed(seed);
            int ATK = new Random(seed).nextInt(6) + 1;
            controller.getRival().setSeed(seed);
            int EVD = new Random(seed).nextInt(6) + 1;
            controller.tryToEvade();
            if (ATK + suguri.getAtk() > EVD + marc.getEvd()){
                Assertions.assertEquals(Math.max(0, 4 - ATK - suguri.getAtk()),marc.getCurrentHp());
                if (!controller.getRival().KO_Status()){
                    Assertions.assertEquals("Waiting Fight State", controller.getState2());
                }
            }
            else {
                Assertions.assertEquals("Waiting Fight State", controller.getState2());
                Assertions.assertEquals(marc.getMaxHp(), marc.getCurrentHp());
            }
        }
    }












}
