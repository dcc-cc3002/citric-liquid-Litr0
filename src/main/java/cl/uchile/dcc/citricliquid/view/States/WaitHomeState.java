package cl.uchile.dcc.citricliquid.view.States;

public class WaitHomeState extends State {
    public WaitHomeState(){
        this.CanStart = false;
        this.Battle = false;
        this.WaitingFight = false;
        this.WaitHome = true;
        this.CanFight = false;
        this.CanMove = false;
        this.CanEnd = false;
        this.K_O = false;
        this.ChoosePath = false ;
        this.StopAtHome = false;
    }

    @Override
    public void toCanMoveState(){
        ChangeState(new CanMoveState());
    }
    @Override
    public void toEndTurnState(){
        ChangeState(new EndTurnState());
    }
}
