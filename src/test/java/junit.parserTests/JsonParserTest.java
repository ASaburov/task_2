package junit.parserTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonParserTest {

    JsonParser testJsonParser;
    File testFile;

    @BeforeEach
    void setUp() {
       testJsonParser = new JsonParser();
    }

    @AfterEach
    void tearDown() {
        testJsonParser = null;
        testFile.delete();
    }

//test checks that method could use various cart names with symbols
    @ParameterizedTest
    @ValueSource(strings = {"testCart", "!@#cart", "$%^cart", "@^&cart", "123&()cart"})
    void writeToFile(String cartName) {
        Cart testCart = new Cart(cartName);
        testJsonParser.writeToFile(testCart);
        testFile = new File("src\\main\\resources\\"+cartName+".json");
        assertEquals(cartName, testJsonParser.readFromFile(testFile).getCartName());
    }

//test checks that corresponding input parameters could cause NoSuchFileException and JsonSyntaxException
    @ParameterizedTest
    @ValueSource(strings = {"src\\main\\resources\\fake", "src\\main\\resources\\f1.json"})
    void readFromFile(String fileName) {
        testFile = new File(fileName);
        Exception exception = assertThrows(Exception.class, () -> testJsonParser.readFromFile(testFile));
        if (exception instanceof NoSuchFileException) {
            assertEquals("File " + fileName + ".json not found!", exception.getMessage());
        } else {
            assertEquals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $", exception.getMessage());
        }
    }
//checks that reading from incompatible file returns null
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"cart.png","cart.jpg","cart.mcv","cart.mp3", "cart.wav"})
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