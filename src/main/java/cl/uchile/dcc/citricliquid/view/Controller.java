package cl.uchile.dcc.citricliquid.view;

import cl.uchile.dcc.citricliquid.model.Character.ICharacter;
import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.board.IPanel;
import cl.uchile.dcc.citricliquid.view.States.State;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final List<Player> playersInGame = new ArrayList<>();
    private final List<IPanel> panelList = new ArrayList<>();
    int chapter;
    int roll_steps;
    private Player control;
    private Player nonePlayer = new Player("no winner yet",0,0,0,0);
    private ICharacter rival = nonePlayer;
    private int turns_played;
    private int numberOfEnemies;
    private ICharacter actual = nonePlayer;

    private Player champion = nonePlayer;
    private State state;

    public Controller(){
        turns_played = 1;
        chapter = 1;
        state = new State();

    }




}
