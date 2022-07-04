package cl.uchile.dcc.citricliquid.model.view;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.board.BonusPanel;
import cl.uchile.dcc.citricliquid.model.board.DropPanel;
import cl.uchile.dcc.citricliquid.model.board.HomePanel;
import cl.uchile.dcc.citricliquid.model.board.NeutralPanel;
import cl.uchile.dcc.citricliquid.view.Controller;
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

        playerList.add(suguri);
        playerList.add(marc);
        playerList.add(poppo);
        playerList.add(kai);

        TestNeutralPanel1 = controller.addNeutralPanel(29);
        TestNeutralPanel2 = controller.addNeutralPanel(30);
        TestNeutralPanel3 = controller.addNeutralPanel(31);
        TestNeutralPanel4 = controller.addNeutralPanel(32);
        TestNeutralPanel5 = controller.addNeutralPanel(33);

        TestDropPanel = controller.addDropPanel(34);

        TestBonusPanel = controller.addBonusPanel(35);

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
    public void StartStateAndRecoveryTest(){
        controller.setNextPanel(HomePanelSuguri,TestNeutralPanel1);
        controller.setNextPanel(TestNeutralPanel1,HomePanelSuguri);
        controller.setNextPanel(HomePanelSuguri,TestNeutralPanel2);
        controller.setNextPanel(TestNeutralPanel2,HomePanelSuguri);
        HomePanelSuguri.setDown(TestNeutralPanel1);
        HomePanelSuguri.setRight(TestNeutralPanel2);
        TestNeutralPanel1.setUp(HomePanelSuguri);
        TestNeutralPanel2.setLeft(HomePanelSuguri);
        controller.setNextPanel(TestNeutralPanel1,TestBonusPanel);
        controller.setNextPanel(TestBonusPanel,TestNeutralPanel1);
        controller.setNextPanel(TestNeutralPanel2,TestBonusPanel);
        controller.setNextPanel(TestBonusPanel,TestNeutralPanel2);
        TestNeutralPanel1.setRight(TestBonusPanel);
        TestBonusPanel.setLeft(TestNeutralPanel1);
        TestNeutralPanel2.setDown(TestBonusPanel);
        TestBonusPanel.setUp(TestNeutralPanel2);

        Assertions.assertEquals(controller.getControl(),suguri);
    }




}
