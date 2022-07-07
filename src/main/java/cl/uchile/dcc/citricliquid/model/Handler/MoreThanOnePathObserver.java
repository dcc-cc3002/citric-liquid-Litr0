package cl.uchile.dcc.citricliquid.model.Handler;
import cl.uchile.dcc.citricliquid.view.Controller;


import java.beans.PropertyChangeEvent;

public class MoreThanOnePathObserver implements IHandler {
    private final Controller controller;

    public MoreThanOnePathObserver(final Controller controler){
        this.controller=controler;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onMoreThanOnePath((boolean) evt.getNewValue());
    }
}
