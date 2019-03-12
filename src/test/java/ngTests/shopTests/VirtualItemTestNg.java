package ngTests.shopTests;

import org.testng.annotations.Test;
import shop.VirtualItem;

import static org.testng.Assert.assertEquals;

public class VirtualItemTestNg {
//test checks the toString method returns appropriate string
    @Test(groups = "VirtualItemTests")
    public void testToString() {
        VirtualItem pubgGame = new VirtualItem();
        pubgGame.setSizeOnDisk(20000.00);
        System.out.println(pubgGame.toString());
        assertEquals(pubgGame.toString(),"Class: class shop.VirtualItem; Name: null; Price: 0.0; Size on disk: "+pubgGame.getSizeOnDisk());
    }
}