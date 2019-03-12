package junit.shopTests;

import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.testng.Assert.assertEquals;


class VirtualItemTest {

    @Test
    void virtualItemToString() {
        VirtualItem pubgGame = new VirtualItem();
        pubgGame.setSizeOnDisk(20000.00);
        System.out.println(pubgGame.toString());
        assertEquals("Class: class shop.VirtualItem; Name: null; Price: 0.0; Size on disk: "+pubgGame.getSizeOnDisk(), pubgGame.toString());
    }

}