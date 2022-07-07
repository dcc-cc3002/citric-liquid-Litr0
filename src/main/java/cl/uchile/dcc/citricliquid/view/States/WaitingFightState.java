package cl.uchile.dcc.citricliquid.view.States;

import cl.uchile.dcc.citricliquid.model.Character.AbstractCharacter;

public class WaitingFightState extends State {
    AbstractCharacter attack;
    AbstractCharacter defend_evade;

    public WaitingFightState(AbstractCharacter attack, AbstractCharacter defend_evade) {
        this.attack = attack;
        this.defend_evade = defend_evade;
        this.CanStart = false;
        this.Battle = false;
        this.WaitingFight = true;
        this.WaitHome = false;
        this.CanFight = false;
        this.CanMove = false;
        this.CanEnd = false;
        this.K_O = false;
        this.ChoosePath = false ;
        this.StopAtHome = false;
    }

    @Override
    public String toString(){
        return "Waiting Fight State";
    }

    @Override
    public void toCanMoveState(){
        ChangeState(new CanMoveState());
    }

    @Override
    public void toEndTurnState(){
        ChangeState(new EndTurnState());
    }

    @Override
    public void toBattleState(AbstractCharacter attack, AbstractCharacter defend_evade){
        ChangeState(new BattleState(attack, defend_evade));
    }

    @Override
    public void toWaitingFightState(AbstractCharacter attack, AbstractCharacter defend_evade){
        ChangeState(new WaitingFightState(attack,defend_evade));
        controller.setActual(defend_evade);
    }

    @Override
    public void iAmGoingToFight() throws InvalidMovementException{
        toBattleState(attack, defend_evade);
        controller.setRoll_steps(0);
        controller.setActual(defend_evade);
        controller.getState().attack();
    }

    @Override
    public void evade() throws InvalidTransitionException {
        controller.evade(attack, defend_evade);
    }

    @Override
    public void defend() throws InvalidTransitionException{
        controller.defend(attack, defend_evade);
    }


}
