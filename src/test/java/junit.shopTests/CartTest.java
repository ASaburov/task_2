package shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class CartTest {

    @Test
    @DisplayName("Checking the addRealItem() method: ensure that Tax is included")
    void addRealItem() {
        Cart testCart = new Cart ("testTaxCart");
        double priceOfRealItem = 10.0;
        RealItem realItem = new RealItem();
        realItem.setPrice(priceOfRealItem);
        testCart.addRealItem(realItem);
        // GROUPED ASSERTION
        assertAll("realItem",
                //assertion checks that totalPrice of the testCart != null
                () -> assertNotNull(testCart.getTotalPrice()),
                //assertion checks that TAX is included into calculation of the total price
                () -> assertEquals(priceOfRealItem + (priceOfRealItem * 0.2), testCart.getTotalPrice())
                );

    }

    @Test
    @DisplayName("Checking the addVirtualItem() method: ensure that Tax is included")
    void addVirtualItem() {
        Cart testCart = new Cart ("testTaxCart");
        double priceOfVirtualItem = 12.2;
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setPrice(priceOfVirtualItem);
        testCart.addVirtualItem(virtualItem);
        assertAll("virtualItem",
                //assertion checks that totalPrice of the testCart != null
                () -> assertNotNull(testCart.getTotalPrice()),
                //assertion checks that TAX is included into calculation of the total price
                () -> assertEquals(priceOfVirtualItem + (priceOfVirtualItem * 0.2), testCart.getTotalPrice())
        );
    }

}