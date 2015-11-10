package mule.model.town;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class Town implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private Store[][] stores;

    private static final int TOWN_WIDTH = 2;
    private static final int TOWN_HEIGHT = 3;
    public static final double STORE_WIDTH = 337.5;
    public static final double STORE_HEIGHT = 125;

    public Town(Canvas canvas) {
        Canvas parent = canvas;

        Image image = new Image("mule/view/townmap.jpg", false);
        stores = new Store[TOWN_WIDTH][TOWN_HEIGHT];

        stores[0][0] = new Store();

        stores[0][2] = new LandStore();

        stores[1][0] = new AssayStore();

        stores[1][2] = new Pub();

        parent.getGraphicsContext2D().drawImage(image, 0, 0);

    }

    public final Store getStore() {
        return stores[0][0];
    }

    public final Pub getPub() {
        return (Pub) stores[1][2];
    }

}
