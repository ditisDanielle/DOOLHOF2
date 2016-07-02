package doolhofspel;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Groep 7: Danielle & Agnita
 */
public class BordTest {
    
    public BordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void setTellerTest() {
        System.out.println("Teller test");
        Doolhof doolhof = new Doolhof();
        int aantal = 0;
        int result;
        String simulatedUserInput = "KeyEvent.VK_UP";
        Scanner in = new Scanner(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        if (in.hasNext(simulatedUserInput)) {
            aantal = 1;
        }
        else {
            aantal = 0;
        }
        doolhof.setTeller(aantal);
        result = aantal;
        int expResult = 1;
        assertEquals(expResult, result);
        System.out.println("Het resultaat is " + result + " en het verwachtte resultaat is " + expResult);
    }
    
}
