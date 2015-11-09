package mule.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import javafx.scene.paint.Color;
import mule.model.player.*;
import mule.model.resources.*;
import mule.model.town.Store;

/**
 * Created by Kyle on 11/6/2015.
 */
public class BuyMuleTest {
    private Store store;
    private Player player;

    @Before
    public void setUp() {
        store = new Store();
        player = new Human("Kyle", Color.BLUE);
    }

    @Test
    public void testBuyMule() {
        int expectedPlayerMule = 0;
        int expectedPlayerMoney = 600;
        int expectedStoreMule = 10;

        store.buyMule(player, new Mule());

        Assert.assertEquals("Player's mule should be 0", expectedPlayerMule, player.getResource(new Mule()));
        Assert.assertEquals("Player's money should be 600", expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Store's mule should be 10", expectedStoreMule, store.getResource(new Mule()));
    }

    @Test
    public void testBuyMule() {
        int expectedPlayerMule = 1;
        int expectedPlayerMoney = 600;
        int expectedStoreMule = 10;

        store.buyMule(player, new Mule());

        Assert.assertEquals("Player's mule should be 1", expectedPlayerMule, player.getResource(new Mule()));
        Assert.assertEquals("Player's money should be 600", expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Store's mule should be 15", expectedStoreMule, store.getResource(new Mule()));
    }

    @Test
    public void testBuyWithInsufficentFunds() {
        int expectedPlayerMoney = 10;
        int expectedPlayerMule = 1;
        int expectedStoreMule = 10;

        player.removeMoney(590);

        boolean expectedResult = store.buyMule(player, new Food());

        Assert.assertFalse("Should return false if can't buy", expectedResult);
        Assert.assertEquals("Player's money should not have changed",expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Player's food should not have changed", expectedPlayerMule, player.getResource(new Mule()));
        Assert.assertEquals("Store's food should not have changed", expectedStoreMule, store.getResource(new Mule()));
    }
}
