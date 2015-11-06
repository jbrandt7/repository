package mule.tests;

/**
 * Created by The Boat on 11/6/2015.
 */


import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import javafx.scene.paint.Color;
import mule.model.player.*;
import mule.model.resources.*;
import mule.model.map.*;
import javafx.scene.canvas.Canvas;
public class UpdateScoreTest {
    private Player p;

    @Before
    public void setUp() {
        p = new Human("Jay", Color.BLUE);
    }
    @Test
    public void testGetBagTotalCost() {
        p.removeResource(new Food(), 8);
        p.removeResource(new Energy(), 4);
        int expectedBagCost = 0;

        Assert.assertEquals(p.getBag().getTotalCost(), expectedBagCost);

        expectedBagCost = 1025;
        p.addResource(new Food(), 5);
        p.addResource(new Energy(), 5);
        p.addResource(new Smithore(), 5);
        p.addResource(new Crystite(), 5);

        Assert.assertEquals(p.getBag().getTotalCost(), expectedBagCost);

        expectedBagCost = 525;
        p.removeResource(new Crystite(), 5);
        Assert.assertEquals(p.getBag().getTotalCost(), expectedBagCost);

        expectedBagCost = 275;
        p.removeResource(new Smithore(), 5);
        Assert.assertEquals(p.getBag().getTotalCost(), expectedBagCost);

        expectedBagCost = 150;
        p.removeResource(new Energy(), 5);
        Assert.assertEquals(p.getBag().getTotalCost(), expectedBagCost);

        expectedBagCost = 0;
        p.removeResource(new Food(), 5);
        Assert.assertEquals(p.getBag().getTotalCost(), expectedBagCost);
    }
    @Test
    public void testMoneyEffectOnScore() {
        p = new Human("Jay", Color.BLUE);
        p.updateScore();
        int expectedScore = 940;
        Assert.assertEquals(p.getScore(), expectedScore);

        expectedScore = p.getScore() - p.getMoney();
        p.removeMoney(1001);
        p.updateScore();
        Assert.assertEquals(p.getScore(), expectedScore);

        expectedScore = expectedScore + 10;
        p.addMoney(10);
        p.updateScore();
        Assert.assertEquals(p.getScore(), expectedScore);

    }
    @Test
    public void testPlotScoreCount() {
        p = new Human("Jay", Color.BLUE);
        p.updateScore();
        p.addPlot(new RiverPlot(new Canvas(), 0, 0));
        int expectedScore = 300 + p.getScore();
        p.updateScore();
        Assert.assertEquals(p.getScore(), expectedScore);
    }
    @Test
    public void testUpdateScore() {
        p = new Human("Jay", Color.BLUE);
        p.updateScore();
        int expectedScore = 940;
        Assert.assertEquals(p.getScore(), expectedScore);

        p.addMoney(100);
        p.addPlot(new RiverPlot(new Canvas(), 20, 20));
        p.addResource(new Crystite(), 2);
        expectedScore = 1540;
        p.updateScore();
        Assert.assertEquals(p.getScore(), expectedScore);
    }



}
