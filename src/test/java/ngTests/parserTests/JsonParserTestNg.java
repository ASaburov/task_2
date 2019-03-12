package ngTests.parserTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.JsonParser;
import shop.Cart;
import java.io.File;
import java.io.IOException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class JsonParserTestNg {

    JsonParser testJsonParser;
    File testFile;

    @BeforeMethod
    void setUp() {
        testJsonParser = new JsonParser();
    }

    @AfterMethod
    void tearDown() {
        testJsonParser = null;
        testFile.delete();
    }

    //data provider for following writeToFile test
    @DataProvider
    public Object[] cartNameSource(){
        return new Object[]{
                "testCart", "!@#cart", "$%^cart", "@^&cart", "123&()cart"
        };
    }

    //test checks that method could use various cart names with symbols
    @Test(dataProvider = "cartNameSource", groups = "jsonParser")
    void writeToFile(String cartName) {
        Cart testCart = new Cart(cartName);
        testJsonParser.writeToFile(testCart);
        testFile = new File("src\\main\\resources\\"+cartName+".json");
        assertEquals(cartName, testJsonParser.readFromFile(testFile).getCartName());
    }

    //data provider for following readFromFile test
    @DataProvider
    public Object[] fileLocationSource(){
        return new Object[]{
                "src\\\\main\\\\resources\\\\fake",
                "src\\\\main\\\\resources\\\\f1.json"
        };
    }

    //test checks that corresponding input parameters could cause NoSuchFileException and JsonSyntaxException
    @Test(dataProvider = "fileLocationSource", expectedExceptions = Exception.class, groups = "jsonParser")
    void readFromFile(String fileName) {
        testFile = new File(fileName);
        testJsonParser.readFromFile(testFile);
        }

     // //data provider for following readFromIncompatibleFile test
    @DataProvider
    public Object[] fileExtensionSource(){
        return new Object[]{
                "cart.png","cart.jpg","cart.mcv","cart.mp3", "cart.wav"
        };
    }

    //checks that reading from incompatible file returns null
    @Test(dataProvider = "fileExtensionSource", groups = "jsonParser")
    void readFromIncompatibleFile(String fileName) {
        testFile = new File ("src\\main\\resources\\"+fileName);
        try {
            testFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cart cart1 = testJsonParser.readFromFile(testFile);
        assertThrows(NullPointerException.class, ()-> cart1.showItems());
    }
}