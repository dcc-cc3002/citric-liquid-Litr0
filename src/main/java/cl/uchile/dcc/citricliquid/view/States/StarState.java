package cl.uchile.dcc.citricliquid.view.States;

public class StarState extends State {

    public StarState(){
        this.CanStart = true;
        this.Battle = false;
        this.WaitingFight = false;
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
        return "Start State";
    }

    @Override
    public void toRecoveryPhase(){
        ChangeState(new RecoveryPhase());
    }

    @Override
    public void toCanMoveState(){
        ChangeState(new CanMoveState());
    }
}
