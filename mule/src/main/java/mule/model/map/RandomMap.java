package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.util.Random;

public class RandomMap extends Map {

    private static final long serialVersionUID = 42L;

    public RandomMap(Canvas canvas) {
        super(canvas);

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[0].length; j++) {
                Image image;

                if (isCenter(i, j)) {
                    plots[i][j] = new TownPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/town.jpg", false);
                } else if (isMiddle(i)) {
                    plots[i][j] = new RiverPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/river.jpg", false);
                } else {
                    int rand = new Random().nextInt() & 1;
                    if (rand == 0) {
                        plots[i][j] = new PlainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                        image = new Image("mule/view/plain.jpg", false);
                    } else {
                        plots[i][j] = new MountainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                        image = new Image("mule/view/mountain.jpg", false);
                    }

                }
                parent.getGraphicsContext2D().drawImage(image, PLOT_SIZE * i, PLOT_SIZE * j);
            }
        }
        parent.getGraphicsContext2D().setGlobalAlpha(1.0 / 2);
    }

}
