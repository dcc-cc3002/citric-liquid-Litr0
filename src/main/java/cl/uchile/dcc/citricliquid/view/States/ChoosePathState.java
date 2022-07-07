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
    public String toString(){
        return "Choose Path State";
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

    @Override
    public void left() throws InvalidTransitionException {
        controller.left();

    }
    @Override
    public void right() throws InvalidTransitionException {
        controller.right();

    }
    @Override
    public void up() throws InvalidTransitionException {
        controller.up();

    }
    @Override
    public void down() throws InvalidTransitionException {
        controller.down();

    }
}
