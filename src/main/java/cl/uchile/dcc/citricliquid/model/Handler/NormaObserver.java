package cl.uchile.dcc.citricliquid.model.Handler;

import cl.uchile.dcc.citricliquid.view.Controller;

import java.beans.PropertyChangeEvent;

/**
 * class to observe the norma level of the character's
 */
public class NormaObserver implements IHandler {
    private Controller controller;

    /**
     * constructor for this handler
     * @param controller
     */
    public NormaObserver(Controller controller){
        this.controller = controller;
    }

    /**
     * gets called when the norma level of a layer changes
     * @param changeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent changeEvent){
        controller.SeeIfChampion((int) changeEvent.getNewValue());
    }
}
