package cl.uchile.dcc.citricliquid.view.States;

public class EndTurnState extends State {
    public EndTurnState(){
        this.CanStart = false;
        this.Battle = false;
        this.WaitingFight = false;
        this.WaitHome = false;
        this.CanFight = false;
        this.CanMove = false;
        this.CanEnd = true;
        this.K_O = false;
        this.ChoosePath = false ;
        this.StopAtHome = false;
    }

    @Override
    public void toStartState(){
        ChangeState(new StarState());
    }
}
