package cl.uchile.dcc.citricliquid.model.Handler;
import cl.uchile.dcc.citricliquid.view.Controller;

import java.beans.PropertyChangeEvent;

public class AtHomePanelObserver implements IHandler {
    private final Controller controller;

    public AtHomePanelObserver(Controller controler){
        this.controller=controler;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onHomePanel((boolean) evt.getNewValue());
    }
}
