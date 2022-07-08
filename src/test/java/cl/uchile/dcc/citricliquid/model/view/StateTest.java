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
import org.junit.jupiter.api.function.Executable;

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
        Assertions.assertThrows(InvalidTransitionException.class,() -> controller.getState().toEndTurnState());
        controller.getState().toStartState();
        Assertions.assertEquals("Start State", controller.getState2());
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

    @RepeatedTest(50)
    public void StartStateAndRecoveryTest() throws InvalidMovementException {
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel1);
        controller.setNextPanel(HomePanelSuguri, TestNeutralPanel2);
        HomePanelSuguri.setLeft(TestNeutralPanel1);
        HomePanelSuguri.setUp(TestNeutralPanel2);
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
        TestNeutralPanel2.setUp(TestBonusPanel); //square of panels

        Assertions.assertEquals(suguri, controller.getControl());
        Assertions.assertEquals("Start State", controller.getState2());
        controller.getControl().setCurrentHp(-10); //let's try the recovery phase
        Assertions.assertTrue(controller.KO_status());
        controller.tryToStart();

        controller.getControl().setSeed(seed);
        int roll = new Random(seed).nextInt(6) + 1;
        if (roll < (controller.getChapter())){
            Assertions.assertEquals("Start State", controller.getState2());
            Assertions.assertEquals(marc.getName(), controller.getControl().getName());
            Assertions.assertEquals(0, suguri.getCurrentHp());
        }
        else {
            Assertions.assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
            Assertions.assertEquals(suguri.getName(), controller.getControl().getName());
            Assertions.assertEquals("Start State", controller.getState2());
        }
    }







}
