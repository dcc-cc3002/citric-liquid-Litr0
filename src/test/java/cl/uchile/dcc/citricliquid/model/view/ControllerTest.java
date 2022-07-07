package cl.uchile.dcc.citricliquid.model.view;

import cl.uchile.dcc.citricliquid.model.Character.BossUnit;
import cl.uchile.dcc.citricliquid.model.Character.ObjectiveNorma;
import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.Character.WildUnit;
import cl.uchile.dcc.citricliquid.model.board.*;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private Player suguriC;
    private final static String PLAYER_NAME_2 = "Marc";
    private Player marc;
    private Player marcC;
    private final static String PLAYER_NAME_3 = "Poppo";
    private Player poppo;
    private Player poppoC;
    private final static String PLAYER_NAME_4 = "Kai";
    private Player kai;
    private Player kaiC;
    private List<Player> playerList = new ArrayList<>();

    private List<IPanel> panelList = new ArrayList<>();
    private HomePanel HomePanelSuguri;
    private HomePanel HomePanelMarc;
    private HomePanel HomePanelPoppo;
    private HomePanel HomePanelKai;
    private NeutralPanel TestNeutralPanel;
    private DropPanel TestDropPanel;
    private BonusPanel TestBonusPanel;
    private EncounterPanel TestEncounterPanel; //needs to be upgraded to be tested
    private BossPanel TestBossPanel;//needs to be upgraded to be tested
    private Controller controller;
    private long seed;

    @BeforeEach
    public void SetUP(){
        seed = new Random().nextLong();
        controller = new Controller();

        suguri = new Player (PLAYER_NAME,4,1,-1,2);
        marc = new Player(PLAYER_NAME_2,4,1,1,-1);
        poppo = new Player(PLAYER_NAME_3,7,-1,-1,-1);
        kai = new Player(PLAYER_NAME_4,5,1,0,0);

        HomePanelSuguri = new HomePanel(PanelType.HOME, 70);
        HomePanelMarc = new HomePanel(PanelType.HOME, 71);
        HomePanelPoppo = new HomePanel(PanelType.HOME, 72);
        HomePanelKai = new HomePanel(PanelType.HOME, 73);

        suguriC = controller.addPlayer(PLAYER_NAME,4,1,-1,2, HomePanelSuguri);
        marcC = controller.addPlayer(PLAYER_NAME_2,4,1,1,-1, HomePanelMarc);
        poppoC = controller.addPlayer(PLAYER_NAME_3,7,-1,-1,-1, HomePanelPoppo);
        kaiC = controller.addPlayer(PLAYER_NAME_4,5,1,0,0, HomePanelKai);

        TestNeutralPanel = new NeutralPanel(PanelType.NEUTRAL, 74);
        TestBonusPanel = new BonusPanel(PanelType.BONUS, 75);
        TestDropPanel = new DropPanel(PanelType.DROP, 76);
        TestEncounterPanel = new EncounterPanel(PanelType.ENCOUNTER, 77);
        TestBossPanel = new BossPanel(PanelType.BOSS, 78);

        playerList.add(suguriC);
        playerList.add(marcC);
        playerList.add(poppoC);
        playerList.add(kaiC);
    }

    @Test
    public void addUnitsTest(){
        Assertions.assertEquals(suguri,suguriC);
        Assertions.assertEquals(marc,marcC);
        Assertions.assertEquals(poppo,poppoC);
        Assertions.assertEquals(kai,kaiC);

        BossUnit Shifu= controller.addBossUnit("Shifu_ROBOT",4,5,6,7);
        BossUnit expected= new BossUnit("Shifu_ROBOT",4,5,6,7);
        assertEquals(Shifu,expected);

        WildUnit chicken= controller.addWildUnit("chicken",4,5,6,7);
        WildUnit expectedchicken= new WildUnit("chicken",4,5,6,7);
        assertEquals(chicken,expectedchicken);
    }

    @Test
    public void addPanelTest(){
        HomePanel HP = new HomePanel(PanelType.HOME,20);
        HomePanel expectedHP = controller.addHomePanel(20);
        Assertions.assertEquals(HP,expectedHP);

        BonusPanel BP = new BonusPanel(PanelType.BONUS,21);
        BonusPanel expectedBP= controller.addBonusPanel(21);
        Assertions.assertEquals(BP,expectedBP);

        BossPanel BossP = new BossPanel(PanelType.BOSS,22);
        BossPanel expectedBossP= controller.addBossPanel(22);
        Assertions.assertEquals(BossP,expectedBossP);


        DropPanel DropP = new DropPanel(PanelType.DROP,24);
        DropPanel expectedDropP= controller.addDropPanel(24);
        Assertions.assertEquals(DropP, expectedDropP);

        EncounterPanel EP = new EncounterPanel(PanelType.ENCOUNTER,25);
        EncounterPanel expectedEP = controller.addEncounterPanel(25);
        Assertions.assertEquals(EP, expectedEP);

        NeutralPanel NP = new NeutralPanel(PanelType.NEUTRAL,26);
        NeutralPanel expectedNP = controller.addNeutralPanel(26);
        Assertions.assertEquals(NP,expectedNP);

    }

    @Test
    public void PlayerListTest(){
        Assertions.assertEquals(playerList,controller.getPlayersInGame());
    }

    @Test
    public void TurnChangeTest(){
        Assertions.assertEquals(suguri,controller.getControl());
        controller.endTurn();
        Assertions.assertEquals(marc,controller.getControl());
        controller.endTurn();
        Assertions.assertNotEquals(marc,controller.getControl());
        Assertions.assertEquals(poppo,controller.getControl());
        controller.endTurn();
        Assertions.assertEquals(kai,controller.getControl());

    }

    @Test
    public void NormaTest(){
        assertEquals(suguri.getObjectiveNorma(),suguriC.getObjectiveNorma());
        assertEquals(ObjectiveNorma.STARS,controller.getControl().getObjectiveNorma());
        controller.setObjectiveNorma(ObjectiveNorma.WINS);
        assertEquals(ObjectiveNorma.WINS,controller.getControl().getObjectiveNorma());
        controller.setObjectiveNorma(ObjectiveNorma.STARS);
        assertEquals(ObjectiveNorma.STARS,controller.getControl().getObjectiveNorma());
    }

    @RepeatedTest(150)
    public void MovingTest() throws InvalidMovementException, InvalidTransitionException {
        controller.getControl().getActualPanel().removePlayer(controller.getControl());
        controller.getControl().SetActualPanel(TestBonusPanel);
        controller.setNextPanel(HomePanelSuguri,TestBonusPanel);
        controller.setNextPanel(TestBonusPanel,TestNeutralPanel);
        controller.setNextPanel(TestBonusPanel,HomePanelSuguri);
        TestBonusPanel.setLeft(HomePanelSuguri);

        controller.tryToStart();
        controller.tryToGoLeft();
        Assertions.assertTrue(HomePanelSuguri.getPlayersList().contains(controller.getControl()));
        Assertions.assertFalse(TestBonusPanel.getPlayersList().contains(controller.getControl()));

        controller.endTurn();

        controller.setNextPanel(TestDropPanel, HomePanelMarc);
        controller.setNextPanel(TestDropPanel, HomePanelKai);
        Assertions.assertTrue(HomePanelMarc.getPlayersList().contains(controller.getControl()));



    }



}
