package cl.uchile.dcc.citricliquid.view;

import cl.uchile.dcc.citricliquid.model.Character.*;
import cl.uchile.dcc.citricliquid.model.Handler.AtHomePanelObserver;
import cl.uchile.dcc.citricliquid.model.Handler.MoreThanOnePathObserver;
import cl.uchile.dcc.citricliquid.model.Handler.MoreThanOnePlayerObserver;
import cl.uchile.dcc.citricliquid.model.Handler.NormaObserver;
import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.view.States.InvalidMovementException;
import cl.uchile.dcc.citricliquid.view.States.InvalidTransitionException;
import cl.uchile.dcc.citricliquid.view.States.StarState;
import cl.uchile.dcc.citricliquid.view.States.State;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final List<Player> playersInGame = new ArrayList<>();
    private final List<IPanel> panelList = new ArrayList<>();
    int chapter;
    int roll_steps;
    private Player control;
    private Player nonePlayer = new Player("no winner yet",0,0,0,0);
    private AbstractCharacter rival = nonePlayer;
    private int turn;
    private int numberOfEnemies;
    private AbstractCharacter actual = nonePlayer;
    private final NormaObserver normaObserver = new NormaObserver(this);

    private Player champion = nonePlayer;
    private State state;
    private  final AtHomePanelObserver atHomePanelObservation = new AtHomePanelObserver(this);

    private final MoreThanOnePlayerObserver moreThanOnePlayerObserver =  new MoreThanOnePlayerObserver(this);

    private final MoreThanOnePathObserver moreThanOnePathObserver =  new MoreThanOnePathObserver(this);

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
     * gets the state in string
     */
    public String getState2(){
        return getState().toString();
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
     * gets the player's list.
     * @return player's in Game.
     */
    public List<Player> getPlayersInGame() {
        return playersInGame;
    }

    /**
     * set's the turn to be played.
     */
    public void setTurn(int newturn ){
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
    public void revive() throws InvalidTransitionException {
        int roll = roll();
        List<Integer> requirement = List.of(6, 5, 4, 3, 2, 1);
        int chapterRequirement = getChapter() - 1;
        if (roll >= requirement.get(chapterRequirement)){
            int max_HP = getControl().copy().getMaxHp();
            getControl().setCurrentHp(max_HP);
            state.toStartState();
        }
        else{
            state.toEndTurnState();
            tryToEndTurn();
        }
    }

    public void setChapter(int chapter) {
        this.chapter = Math.min(6, Math.max(1, chapter));
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
        HP.addPlayer(newpl);
        newpl.SetHomePanel(HP);
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
     * @param actual actual panel.
     * @param next next panel.
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

    /**
     * End's the turn.
     */
    public void endTurn(){
        if (turn % playersInGame.size() != 0){
            setRoll_steps(0);
            activatePanel(getControl(), getControl().getActualPanel());
            getControl().addNormaLevelObserver(normaObserver);
            setTurn(turn + 1);
        }
        else {
            chapter = Math.min(6, chapter + 1);
        }

    }

    /**
     * Activates the effect of the panel.
     * @param control player
     * @param actualPanel panel
     */
    private void activatePanel(Player control, @NotNull IPanel actualPanel) {
        actualPanel.activatedBy(control);
    }

    /**
     * using an observer to see if the norma level of a player has changed.
     * @param actualNormaLevel new norma of the player.
     */
    public void SeeIfChampion(int actualNormaLevel){
        if(actualNormaLevel == 6){
            champion = getControl();
        }
    }

    public void setObjectiveNorma(ObjectiveNorma newObjetive){
        getControl().setObjectiveNorma(newObjetive);
    }

 
    
    public void setActual(AbstractCharacter actual) {
        this.actual = actual;
    }

    public AbstractCharacter getActual() {
        return actual;
    }

    public boolean KO_status() {
        return getControl().KO_Status();
    }

    public void left() throws InvalidTransitionException{
        setRoll_steps(getRoll_steps() - 1);
        state.toCanMoveState();
        setCanMove(true);
        setPlayerPanel(getControl(), getControl().getActualPanel().getLeft());
        if (roll_steps == 0){
            stopMovement();
        }
    }

    public void right() throws InvalidTransitionException {
        setRoll_steps(getRoll_steps()-1);
        state.toCanMoveState();
        setCanMove(true);
        setPlayerPanel(getControl(),getControl().getActualPanel().getRight());
        if(roll_steps==0){
            stopMovement();
        }
    }

    public void up() throws InvalidTransitionException {
        setRoll_steps(getRoll_steps()-1);
        state.toCanMoveState();
        setCanMove(true);
        setPlayerPanel(getControl(),getControl().getActualPanel().getUp());
        if(roll_steps==0){
            stopMovement();
        }
    }

    public void down() throws InvalidTransitionException {
        setRoll_steps(getRoll_steps()-1);
        state.toCanMoveState();
        setCanMove(true);
        setPlayerPanel(getControl(),getControl().getActualPanel().getDown());
        if(roll_steps==0){
            stopMovement();
        }
    }



    private void stopMovement() throws InvalidTransitionException {
        state.toEndTurnState();
    }

    public void move() {
        getControl().increaseStarsBy((int) (Math.floor(getChapter()/5)+1)); //stars per turn
        control.addAtHomePanelObservation(atHomePanelObservation);
        control.addAmountOfPlayerObserver(moreThanOnePlayerObserver);
        control.addMoreTanOnePathObservation(moreThanOnePathObserver);

        roll_steps = roll();
        if (!getControl().getCanMove() && roll_steps > 0){
            try {
                state.toChoosePathState();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }

        if (roll_steps < 0){
            try {
                state.toEndTurnState();
            } catch (InvalidTransitionException e){
                e.printStackTrace();
            }
        }
        else{
            while (roll_steps > 0 && getControl().getCanMove()){
                IPanel newPanel = getControl().getActualPanel().getNextPanels().iterator().next();
                setPlayerPanel(getControl(), newPanel);
            }
        }
    }

    public void onHomePanel(boolean homepanel) {
        setCanMove(false);
        try {
            state.toWaitHomeState();
        } catch (InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    private void setCanMove(boolean canMove) {
        getControl().setCanMove(canMove);
    }


    public void onMoreThanOnePath(boolean newValue) {
        if(!newValue) {
            setCanMove(false);
            try {
                state.toChoosePathState();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }

    public void onMoreThanOnePlayer(boolean newValue) {
        if(!newValue) {
            setCanMove(false);
            List<Player> opponents = new ArrayList<>();
            opponents.addAll(getControl().getActualPanel().getPlayersList());
            opponents.remove(getControl());
            numberOfEnemies = opponents.size();
            setRival(opponents.get(0));
            try {
                state.toWaitingFightState(getControl(), getRival());
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRival(AbstractCharacter rival) {
        this.rival = rival;
    }

    public AbstractCharacter getRival() {
        return rival;
    }

    public int getRoll_steps() {
        return roll_steps;
    }

    public void tryToRevive() {
        try {
            state.revive();
        } catch (InvalidTransitionException | InvalidMovementException e) {
            e.printStackTrace();
        }

    }

    public void tryFirstMove() {
        try {
            state.firstMove();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }

    }

    public void tryToStart(){
        try {
            this.setActual(getControl());
            state.start();
        } catch (InvalidMovementException | InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    public void tryToMove(){
        try {
            state.move();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    public void tryToKeepMoving(){
        try {
            state.toCanMoveState();
            state.keepMoving();
        } catch (InvalidTransitionException | InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    public void tryToStayAtHome(){
        try {
            state.stayAtHome();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    public void tryToEvade(){
        try{
            state.evade();
        } catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    public void tryToDefend(){
        try {
            state.defend();
        } catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }
    public void tryToGoLeft(){
        try{
            state.left();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    public void tryToGoRight(){
        try{
            state.right();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }


    public void tryToGoUp(){
        try{
            state.up();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    public void tryToGoDown(){
        try{
            state.down();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    public void tryToEndTurn(){
        try {
            state.endTurn();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    public void tryToFight(){
        try {
            state.iAmGoingToFight();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    public void movePlayer() {
        if (!getControl().getCanMove() & roll_steps > 0){
            try {
                state.toChoosePathState();
            } catch (InvalidTransitionException e){
                e.printStackTrace();
            }
        }
        else {
            try{
                state.toEndTurnState();
            } catch (InvalidTransitionException e){
                e.printStackTrace();
            }
        }
    }

    public int attack(AbstractCharacter character){
        return character.attack();
    }

    public void evade(AbstractCharacter attack, AbstractCharacter evade) throws InvalidTransitionException {
        int atk = attack(attack);
        evade.evade(atk);
        afterEvent(attack, evade);
    }

    public void defend(AbstractCharacter attack, AbstractCharacter defend) throws InvalidTransitionException{
        int atk = attack(attack);
        defend.defend(atk);
        afterEvent(attack, defend);
    }

    public void afterEvent(AbstractCharacter attack, AbstractCharacter victim) throws InvalidTransitionException{
        if (victim.KO_Status()){
            setActual(getControl());
            setRival(nonePlayer);
            attack.increaseStarsBy(victim);
            if( attack instanceof Player){
                victim.increaseWinsByPlayer((Player) attack);
            }
        }
        else if (!victim.equals(getControl())){
            nextAttack(victim, attack);
        }
    }

    private void nextAttack(AbstractCharacter victim, AbstractCharacter attack) {
        try {
            state.toWaitingFightState(attack, victim);
        } catch (InvalidTransitionException e){
            e.printStackTrace();
        }
    }




}

