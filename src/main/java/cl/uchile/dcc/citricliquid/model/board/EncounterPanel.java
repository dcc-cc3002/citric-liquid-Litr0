package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Character.Player;
import cl.uchile.dcc.citricliquid.model.Character.WildUnit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EncounterPanel extends AbstractPanel{
    /**
     * Creates a new encounter panel.
     *
     * @param type the type of the panel.
     */
    public EncounterPanel(PanelType type, final int id) {
        super(type,id);
    }

    /**
     * creates a random wild unit and starts a battle
     * @param player
     */
    @Override
    public void activatedBy(@NotNull Player player) {
        Random random = new Random();
        int mini_roll = random.nextInt(3)+1;
        List<WildUnit> possibleEnemies = new ArrayList<>();
        possibleEnemies.add(new WildUnit("Chicken",3,-1,-1,1));
        possibleEnemies.add(new WildUnit("Robo Ball",3,-1,1,-1));
        possibleEnemies.add(new WildUnit("Seagull",3,1,-1,-1));
        WildUnit enemy = possibleEnemies.get(mini_roll);
        System.out.println("Wild Unit Battle activated");
        //player.battle(enemy); battle function still not implemented, maybe next time have an independent Wild Unit battle function same with the Boss Unit

    }
}
