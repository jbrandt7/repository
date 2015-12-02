package mule.tests;

import mule.model.map.PlainPlot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import javafx.scene.paint.Color;
import mule.model.player.*;
import mule.model.resources.*;
import mule.model.map.Plot;
import javafx.scene.canvas.Canvas;

/**
 * Created by RyanC on 11/6/15.
 */
public class BuyLandTest {

    private Player player;
    private Player dude;
    private Plot plot;
    private Canvas canvas;

    @Before
    public void setUp() {
        player = new Human("Ryan", Color.BLUE);
        dude = new Human("Taker", Color.RED);
        canvas = new Canvas();
        plot = new PlainPlot(canvas, 2, 2 );
    }

    @Test
    public void testBuyLandGood() {
        int expectedPlayerMoney = 400;
        int expectedPlotCost = 300;
        boolean expectedResult = true;

        boolean actualResult = plot.buy(player);
        Assert.assertEquals("Should be 300 money", expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Should cost 300 money", expectedPlotCost, plot.getCost());
        Assert.assertEquals("Owner should be player", player, plot.getOwner());
        Assert.assertEquals("Should return true", expectedResult, actualResult);

    }

    public void testBuyLandBad() {

        player.removeMoney(400);

        int expectedPlayerMoney = 200;
        boolean expectedResult = false;
        boolean actualResult = plot.buy(player);


        Assert.assertEquals("Should return false", expectedResult, actualResult);
        Assert.assertEquals("Should stay at 200", expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Owner should not be player", null, plot.getOwner());
    }

    public void testBuyLandOwner() {
        plot.assignOwner(dude);

        int expectedPlayerMoney = 300;
        boolean expectedResult = false;
        boolean actualResult = plot.buy(player);

        Assert.assertEquals("Should return false", expectedResult, actualResult);
        Assert.assertEquals("Owner should be dude", dude, plot.getOwner());
    }
}
