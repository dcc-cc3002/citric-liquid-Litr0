package cl.uchile.dcc.citricliquid.model.Handler;


import cl.uchile.dcc.citricliquid.view.Controller;

import java.beans.PropertyChangeEvent;
public class MoreThanOnePlayerObserver implements IHandler {
    private final Controller controller;

    public MoreThanOnePlayerObserver(Controller controler){
        this.controller=controler;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onMoreThanOnePlayer((boolean) evt.getNewValue());
    }
}
