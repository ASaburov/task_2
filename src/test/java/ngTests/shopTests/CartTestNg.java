package ngTests.shopTests;

import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

public class CartTestNg {

    //DISABLED
    @Test(enabled=false, groups = "cartRealItemTests")
    void addRealItem() {
        Cart testCart = new Cart ("testTaxCart");
        double priceOfRealItem = 10.0;
        RealItem realItem = new RealItem();
        realItem.setPrice(priceOfRealItem);
        testCart.addRealItem(realItem);
        //assertion checks that totalPrice of the testCart != null
        assertNotNull(testCart.getTotalPrice());
        //assertion checks that TAX is included into calculation of the total price
        assertEquals(priceOfRealItem + (priceOfRealItem * 0.2), testCart.getTotalPrice());
    }

    @Test(groups = "cartVirtualItemTests")
    void addVirtualItem() {
        Cart testCart = new Cart ("testTaxCart");
        double priceOfVirtualItem = 12.2;
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setPrice(priceOfVirtualItem);
        testCart.addVirtualItem(virtualItem);
        //assertion checks that totalPrice of the testCart != null
        assertNotNull(testCart.getTotalPrice());
        //assertion checks that TAX is included into calculation of the total price
        assertEquals(priceOfVirtualItem + (priceOfVirtualItem * 0.2), testCart.getTotalPrice());
    }

}