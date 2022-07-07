package cl.uchile.dcc.citricliquid.view.States;

import cl.uchile.dcc.citricliquid.model.Character.AbstractCharacter;

public class CanMoveState extends State {
    public CanMoveState(){
        this.CanStart = false;
        this.Battle = false;
        this.WaitingFight = false;
        this.WaitHome = false;
        this.CanFight = false;
        this.CanMove = true;
        this.CanEnd = false;
        this.K_O = false;
        this.ChoosePath = false ;
        this.StopAtHome = false;
    }

    @Override
    public String toString(){
        return "Can Move State";
    }

    @Override
    public void toChoosePathState(){
        ChangeState(new ChoosePathState());
    }

    @Override
    public void toWaitHomeState(){
        ChangeState(new WaitHomeState());
    }

    @Override
    public void toEndTurnState(){
        ChangeState(new EndTurnState());
    }

    @Override
    public void toWaitingFightState(AbstractCharacter attack, AbstractCharacter defend_evade){
        ChangeState(new WaitingFightState(attack, defend_evade));
    }
}
