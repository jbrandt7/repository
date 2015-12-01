package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;

public class DefaultMap extends Map {

    private static final long serialVersionUID = 42L;

    public DefaultMap(Canvas canvas) {
        super(canvas);

        List<Location> mountainList1 = new ArrayList<>();
        mountainList1.add(new Location(2,0));
        mountainList1.add(new Location(1,1));
        mountainList1.add(new Location(8,2));

        List<Location> mountainList2 = new ArrayList<>();
        mountainList2.add(new Location(1,3));
        mountainList2.add(new Location(6,3));
        mountainList2.add(new Location(2,4));
        mountainList2.add(new Location(7,4));

        List<Location> mountainList3 = new ArrayList<>();
        mountainList3.add(new Location(6,0));
        mountainList3.add(new Location(9,1));
        mountainList3.add(new Location(0,2));

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[0].length; j++) {
                Image image;

                if (isCenter(i, j)) {
                    plots[i][j] = new TownPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/town.jpg", false);
                } else if (isMiddle(i)) {
                    plots[i][j] = new RiverPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/river.jpg", false);
                } else if (mountainList1.contains(new Location(i,j))) {
                    plots[i][j] = new SmallMountainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/mountain1.jpg", false);
                } else if (mountainList2.contains(new Location(i,j))) {
                    plots[i][j] = new MediumMountainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/mountain2.jpg", false);
                } else if (mountainList3.contains(new Location(i,j))) {
                    plots[i][j] = new LargeMountainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/mountain3.jpg", false);
                } else {
                    plots[i][j] = new PlainPlot(parent, i * PLOT_SIZE, j * PLOT_SIZE);
                    image = new Image("mule/view/plain.jpg", false);
                }

                parent.getGraphicsContext2D().drawImage(image, PLOT_SIZE * i, PLOT_SIZE * j);
            }
        }

        parent.getGraphicsContext2D().setGlobalAlpha(1.0 / 2);
    }

}
