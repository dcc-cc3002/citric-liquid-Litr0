package cl.uchile.dcc.citricliquid.view.States;


import cl.uchile.dcc.citricliquid.view.Controller;

public class State {

    protected Controller controller; //later to do the actions
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
    public void toRecoveryPhase(){
    }

    /**
     * moves to the initial State for the player.
     */
    public void toStartState(){

    }
    /**
     * moves to the choosing path state.
     */
    public void toChoosePathState(){

    }
    /**
     * moves to the battle state.
     */
    public void toBattleState(){

    }
    /**
     * moves to the waiting fight state.
     */
    public void toWaitingFightState(){

    }

    /**
     * moves to waiting home state.
     */
    public void toWaitHomeState(){

    }

    /**
     * moves to the state where it's move.
     */
    public void toCanMoveState(){

    }

    /**
     * moves to End Turn State
     */
    public void toEndTurnState(){

    }

    public void revive(){
        if(isK_O()){
           controller.revive();
        }
    }

    public void start(){
        if (CanStart && controller.KO_status()){
            toRecoveryPhase();
            controller.revive();
        }
        else if (!CanStart && !controller.KO_status()){

        }
        else {
            toCanMoveState();
            controller.getState().firstMove();
        }
    }

    private void firstMove() {
        if (CanMove){
            controller.move();
        }
    }


}
