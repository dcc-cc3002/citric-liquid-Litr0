package cl.uchile.dcc.citricliquid.view.States;

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
    public void toChoosePathState(){
        ChangeState(new ChoosePathState());
    }

    @Override
    public void toWaitHomeState(){
        ChangeState(new WaitHomeState());
    }


}
