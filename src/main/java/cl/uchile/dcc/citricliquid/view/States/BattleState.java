package cl.uchile.dcc.citricliquid.view.States;

import cl.uchile.dcc.citricliquid.model.Character.AbstractCharacter;

public class BattleState extends State {
    AbstractCharacter attack;
    AbstractCharacter defend_evade;

    public BattleState(AbstractCharacter attack, AbstractCharacter defend_evade) {
        this.CanStart = false;
        this.Battle = false;
        this.WaitingFight = false;
        this.WaitHome = false;
        this.CanFight = true;
        this.CanMove = false;
        this.CanEnd = false;
        this.K_O = false;
        this.ChoosePath = false ;
        this.StopAtHome = false;
        this.attack = attack;
        this.defend_evade = defend_evade;
    }
    @Override
    public String toString(){
        return "Battle State";
    }

    @Override
    public void toWaitingFightState(AbstractCharacter attack, AbstractCharacter defend_evade){
        ChangeState(new WaitingFightState(attack, defend_evade));
        controller.setActual(defend_evade);
    }

    @Override
    public void attack(){
        toWaitingFightState(attack, defend_evade);
    }
}
