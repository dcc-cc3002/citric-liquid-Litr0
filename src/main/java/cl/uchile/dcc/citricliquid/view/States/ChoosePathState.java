package cl.uchile.dcc.citricliquid.view.States;

public class ChoosePathState extends State {
    public ChoosePathState(){
        this.CanStart = true;
        this.Battle = false;
        this.WaitingFight = false;
        this.WaitHome = false;
        this.CanFight = false;
        this.CanMove = false;
        this.CanEnd = false;
        this.K_O = false;
        this.ChoosePath = true ;
        this.StopAtHome = false;
    }

    @Override
    public void toCanMoveState(){
        ChangeState(new CanMoveState());
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
    public void toChoosePathState(){
        ChangeState(new ChoosePathState());
    }


}
