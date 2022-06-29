package cl.uchile.dcc.citricliquid.model.Handler;

import cl.uchile.dcc.citricliquid.view.Controller;

import java.beans.PropertyChangeEvent;

/**
 * class to observe the norma level of the character's
 */
public class NormaObserver {
    private Controller controller;

    /**
     * constructor for this handler
     * @param controller
     */
    public NormaObserver(Controller controller){
        this.controller = controller;
    }
    @Override
    public void propertyChange(PropertyChangeEvent changeEvent){
    }
}
