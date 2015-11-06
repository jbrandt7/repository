package mule.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import javafx.scene.paint.Color;
import mule.model.player.*;
import mule.model.resources.*;
import mule.model.town.Store;

class BuyResourceTest {

    private Store store;
    private Player player;

    @Before
    public void setUp() {
        store = new Store();
        player = new Human("Harry", Color.BLUE);
    }

    @Test
    public void testBuyFood() {
        int expectedPlayerFood = 9;
        int expectedPlayerMoney = 570;
        int expectedStoreFood = 15;

        store.buyResource(player, new Food());

        Assert.assertEquals("Player's food should be 9", expectedPlayerFood, player.getResource(new Food()));
        Assert.assertEquals("Player's money should be 570", expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Store's food should be 15", expectedStoreFood, store.getResource(new Food()));
    }

    @Test
    public void testBuyEnergy() {
        int expectedPlayerEnergy = 5;
        int expectedPlayerMoney = 575;
        int expectedStoreEnergy = 15;

        store.buyResource(player, new Energy());

        Assert.assertEquals("Player's energy should be 5", expectedPlayerEnergy, player.getResource(new Energy()));
        Assert.assertEquals("Player's money should be 575", expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Store's energy should be 15", expectedStoreEnergy, store.getResource(new Energy()));
    }

    @Test
    public void testBuyWithInsufficentFunds() {
        int expectedPlayerMoney = 10;
        int expectedPlayerFood = 8;
        int expectedStoreFood = 16;

        player.removeMoney(590);

        boolean expectedResult = store.buyResource(player, new Food());

        Assert.assertFalse("Should return false if can't buy", expectedResult);
        Assert.assertEquals("Player's money should not have changed",expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Player's food should not have changed", expectedPlayerFood, player.getResource(new Food()));
        Assert.assertEquals("Store's food should not have changed", expectedStoreFood, store.getResource(new Food()));
    }

    @Test
    public void testBuyWithInsufficentInventory() {
        int expectedPlayerMoney = 120;
        int expectedPlayerFood = 24;
        int expectedStoreFood = 0;

        for (int i = 0; i < 16; i++) {
            store.buyResource(player, new Food());
        }

        boolean expectedResult = store.buyResource(player, new Food());

        Assert.assertFalse("Should return false if can't buy", expectedResult);
        Assert.assertEquals("Player's money should not have changed",expectedPlayerMoney, player.getMoney());
        Assert.assertEquals("Player's food should not have changed", expectedPlayerFood, player.getResource(new Food()));
        Assert.assertEquals("Store's food should not have changed", expectedStoreFood, store.getResource(new Food()));
    }

}
