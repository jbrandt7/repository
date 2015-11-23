package mule.model.player;

import javafx.scene.paint.Color;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * Gets the correct image filename for a player
 * bases on a player's color
*/
public class PlayerImageNames {

    private final static Map<Color, String> fileNames;

    static {
        Map<Color, String> map = new HashMap<>();
        map.put(Color.BLUE, "mule/view/person_blue.png");
        map.put(Color.RED, "mule/view/person_red.png");
        map.put(Color.YELLOW, "mule/view/person_yellow.png");
        map.put(Color.PINK, "mule/view/person_pink.png");
        fileNames = Collections.unmodifiableMap(map);
    }

    public static String getFileName(Player p) {
        return fileNames.get(p.getColor());
    }

}
