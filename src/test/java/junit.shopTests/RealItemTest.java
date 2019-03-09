package junit.shopTests;

import org.junit.jupiter.api.Test;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RealItemTest {

    @Test
    void realItemToString() {
        RealItem iron = new RealItem();
        iron.setWeight(2.5);
        assertEquals("Class: class shop.RealItem; Name: null; Price: 0.0; Weight: "+ iron.getWeight(), iron.toString());
    }

}