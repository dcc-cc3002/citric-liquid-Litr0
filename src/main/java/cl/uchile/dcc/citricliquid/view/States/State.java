package cl.uchile.dcc.citricliquid.view.States;


import cl.uchile.dcc.citricliquid.model.Character.AbstractCharacter;
import cl.uchile.dcc.citricliquid.view.Controller;

public class State {

    protected Controller controller;
    /**
     * booleans to check in what state we are in.
     */
    public boolean CanStart;
    public boolean Battle;
    public boolean WaitingFight;
    public boolean WaitHome;
    public boolean CanFight;
    public boolean CanMove;
    public boolean CanEnd;
    public boolean K_O;
    public boolean ChoosePath;
    public boolean StopAtHome;

    /**
     * getter's for the class.
     */
    public Controller getController() {
        return controller;
    }

    public boolean isCanStart() {
        return CanStart;
    }

    public boolean isBattle() {
        return Battle;
    }

    public boolean isWaitingFight() {
        return WaitingFight;
    }

    public boolean isWaitHome() {
        return WaitHome;
    }

    public boolean isCanFight() {
        return CanFight;
    }

    public boolean isCanMove() {
        return CanMove;
    }

    public boolean isCanEnd() {
        return CanEnd;
    }

    public boolean isK_O() {
        return K_O;
    }

    public boolean isChoosePath() {
        return ChoosePath;
    }

    public boolean isStopAtHome() {
        return StopAtHome;
    }

    /**
     * set's the controller(context).
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * changes the state of the player.
     * @param state
     */
    protected void ChangeState(State state){
        controller.SetState(state);
    }

    //Methods for each state, here they will do nothing and will be overridden in each state.

    /**
     * moves to the Recovery Phase.
     */
    public void toRecoveryPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());
    }

    /**
     * moves to the initial State for the player.
     */
    public void toStartState() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());

    }
    /**
     * moves to the choosing path state.
     */
    public void toChoosePathState() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());

    }
    /**
     * moves to the battle state.
     */
    public void toBattleState(AbstractCharacter attack, AbstractCharacter defend_evade) throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());
    }
    /**
     * moves to the waiting fight state.
     */
    public void toWaitingFightState(AbstractCharacter attack, AbstractCharacter defend_evade) throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());

    }

    /**
     * moves to waiting home state.
     */
    public void toWaitHomeState() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());
    }

    /**
     * moves to the state where it's move.
     */
    public void toCanMoveState() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());
    }

    /**
     * moves to End Turn State
     */
    public void toEndTurnState() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to this State from" + toString());
    }

    public void revive() throws InvalidMovementException, InvalidTransitionException {
        if(!isK_O()){
            throw new InvalidMovementException("Can't revive at" + toString());
        }
        controller.revive();
    }

    public void start() throws InvalidMovementException, InvalidTransitionException {
        if (isCanStart() && controller.KO_status()){
            toRecoveryPhase();
            controller.tryToRevive();
        }
        else if (!CanStart && !controller.KO_status()){
            throw  new InvalidMovementException ("at" + toString() + "you can't start");

        }
        else {
            toCanMoveState();
            controller.tryFirstMove();
        }
    }

    public void firstMove() throws InvalidMovementException {
        if (isCanMove()){
            controller.move();
        }
        else {
            throw new InvalidMovementException("Can't move at" + toString());
        }
    }

    public void move() throws  InvalidMovementException{
        if (!isCanMove()) {
            throw new InvalidMovementException("Can't move now");
        }
        controller.movePlayer();
    }

    public void keepMoving() throws InvalidMovementException, InvalidTransitionException {
        if(!isWaitHome() &&!isWaitingFight() && !isChoosePath()&&!isCanMove()){
            throw new InvalidMovementException("Can't keep moving at: "+toString());
        }

        controller.tryToMove();
    }


    public String toString(){
        return "State";
    }


    public void up() throws InvalidTransitionException, InvalidMovementException {
        throw new InvalidMovementException("Can't go up at" + toString());
    }

    public void down() throws InvalidTransitionException, InvalidMovementException {
        throw new InvalidMovementException("Can't go down at" + toString());
    }

    public void left() throws InvalidTransitionException, InvalidMovementException {
        throw new InvalidMovementException("Can't go left at" + toString());
    }

    public void right() throws InvalidTransitionException, InvalidMovementException {
        throw new InvalidMovementException("Can't go right at" + toString());
    }

    public void endTurn() throws InvalidMovementException{
        if (!isCanEnd()) {
            throw new InvalidMovementException("Can't end turn at" + toString());
        }
    }

    public void stayAtHome() throws InvalidMovementException{
        if (!isWaitHome()) {
            throw new InvalidMovementException("Can't be at home at" + toString());
        }
    }

    public void iAmGoingToFight() throws InvalidMovementException {
        if (controller.KO_status() | !isWaitingFight()){
            throw new InvalidMovementException("Can't fight at" + toString());
        }
    }

    public void attack() throws InvalidMovementException {
        if (!isBattle()) {
            throw new InvalidMovementException("Can't do this now");
        }
    }

    public void defend() throws InvalidMovementException, InvalidTransitionException{
        if (!isWaitingFight()){
            throw new InvalidMovementException("Can't do this now");
        }
    }

    public void evade() throws InvalidMovementException, InvalidTransitionException{
        if (!isWaitingFight()){
            throw new InvalidMovementException("Can't do this now");
        }
    }

}
