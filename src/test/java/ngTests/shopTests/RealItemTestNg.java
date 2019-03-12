package ngTests.shopTests;

import org.testng.annotations.Test;
import shop.RealItem;
import static org.testng.Assert.assertEquals;

public class RealItemTestNg {

    //test checks that toString method returns appropriate string
    @Test(groups = "RealItemTests")
    public void testToString() {
        RealItem iron = new RealItem();
        iron.setWeight(2.5);
        assertEquals(iron.toString(), "Class: class shop.RealItem; Name: null; Price: 0.0; Weight: "+ iron.getWeight());
    }
}