package cl.uchile.dcc.citricliquid.view.States;

public class RecoveryPhase extends State{

    public RecoveryPhase(){
         this.CanStart = true;
         this.Battle = false;
         this.WaitingFight = false;
         this.WaitHome = false;
         this.CanFight = false;
         this.CanMove = false;
         this.CanEnd = false;
         this.K_O = true;
         this.ChoosePath = false ;
         this.StopAtHome = false;
    }

    @Override
    public String toString(){
        return "Recovery State";
    }

    @Override
    public void toStartState(){
        ChangeState(new StarState());
    }

}
