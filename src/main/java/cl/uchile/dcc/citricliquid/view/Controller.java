package cl.uchile.dcc.citricliquid.view;

import cl.uchile.dcc.citricliquid.model.Character.BossUnit;
import cl.uchile.dcc.citricliquid.model.Character.ICharacter;
import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.Character.WildUnit;
import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.view.States.StarState;
import cl.uchile.dcc.citricliquid.view.States.State;
import org.jetbrains.annotations.NotNull;

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
    private int turn;
    private int numberOfEnemies;
    private ICharacter actual = nonePlayer;

    private Player champion = nonePlayer;
    private State state;

    public Controller(){
        turn= 1;
        chapter = 1;
        state = new State();
        SetState(new StarState()); //always start with a start state
    }

    /**
     * set's the state of the controller.
     * @param state
     */
    public void SetState(State state) {
        this.state = state;
        state.setController(this);
    }

    /**
     * Getter for the State.
     */
    public State getState(){
        return state;
    }

    /**
     * gets the player of the controller.
     * @return control
     */
    public Player getControl() {
        control = playersInGame.get((turn - 1) % playersInGame.size());
        return control;
    }

    /**
     * get's the player's list.
     * @return player's in Game.
     */
    public List<Player> getPlayersInGame() {
        return playersInGame;
    }

    /**
     * set's the turn to be played.
     */
    public void setTurns_played(int newturn ){
        this.turn = newturn;
        actual = getControl();
    }

    /**
     * set's de steps that the player can do.
     * @param roll_steps
     */
    public void setRoll_steps(int roll_steps) {
        this.roll_steps = roll_steps;
    }

    /**
     * gets the roll of the player.
     * @return roll.
     */
    public int roll(){
        int roll = getControl().roll();
        return roll;
    }

    /**
     * tries to revive the player following the rules of the game.
     */
    public void revive() {
        int roll = roll();
        if (roll <= chapter){
            state.toEndTurnState();
        }
        else{
            getControl().setCurrentHp(getControl().copy().getMaxHp());
            state.toStartState();
        }
    }

    /**
     * adds a new HomePanel to the game
     * @param i
     * @return new Home Panel
     */
    public HomePanel addHomePanel(int i) {
        HomePanel newhp = new HomePanel(PanelType.HOME,i);
        panelList.add(newhp);
        return newhp;
    }

    /**
     * add a player to the game
     * @param playerName
     * @param hp
     * @param atk
     * @param def
     * @param evd
     * @param HP
     * @return new player
     */
    public Player addPlayer(String playerName, int hp, int atk, int def, int evd, @NotNull HomePanel HP) {
        Player newpl = new Player(playerName, hp, atk, def, evd);
        playersInGame.add(newpl);
        newpl.SetHomePanel(HP);
        HP.addPlayer(newpl);
        newpl.SetActualPanel(HP);
        return newpl;
    }

    /**
     * adds a neutral panel
     * @param i
     * @return New Neutral Panel
     */
    public NeutralPanel addNeutralPanel(int i) {
        NeutralPanel newnp = new NeutralPanel(PanelType.NEUTRAL,i);
        panelList.add(newnp);
        return newnp;
    }

    /**
     * adds a Drop Panel
     * @param i
     * @return New Drop Panel
     */
    public DropPanel addDropPanel(int i) {
        DropPanel newdp = new DropPanel(PanelType.DROP,i);
        panelList.add(newdp);
        return newdp;
    }

    public BonusPanel addBonusPanel(int i){
        BonusPanel newbp = new BonusPanel(PanelType.BONUS,i);
        panelList.add(newbp);
        return newbp;
    }

    /**
     * set's a panel next to another.
     * @param actual
     * @param next
     */
    public void setNextPanel(@NotNull AbstractPanel actual, AbstractPanel next) {
        actual.addNextPanel(next);
    }

    /**
     * changes the actual panel of the player
     * @param player
     * @param panel
     */
    public void setPlayerPanel(Player player, IPanel panel){
        player.getActualPanel().removePlayer(player);
        panel.addPlayer(player);
        player.SetActualPanel(panel);
    }

    /**
     * getter for the champion of the game.
     * @return champion.
     */
    public Player getChampion() {
        return champion;
    }

    /**
     * getter for the actual chapter.
     * @return chapter.
     */
    public int getChapter() {
        return chapter;
    }


    /**
     * functions to add a boss unit or a Wild Unit.
     * @param name
     * @param hp
     * @param atk
     * @param def
     * @param evd
     * @return Boss Unit or Wild Unit
     */
    public BossUnit addBossUnit(String name, int hp, int atk, int def, int evd) {
        return new BossUnit(name, hp, atk, def, evd);
    }

    public WildUnit addWildUnit(String name, int hp, int atk, int def, int evd) {
        return new WildUnit(name, hp, atk, def, evd);
    }

    public EncounterPanel addEncounterPanel(int i) {
        EncounterPanel newep = new EncounterPanel(PanelType.ENCOUNTER,i);
        panelList.add(newep);
        return newep;
    }

    public BossPanel addBossPanel(int i) {
        BossPanel newbp = new BossPanel(PanelType.BOSS,i);
        panelList.add(newbp);
        return newbp;
    }
}
