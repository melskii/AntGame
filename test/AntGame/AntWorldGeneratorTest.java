
package AntGame;

import AntGame.exceptions.AntWorldGeneratorException;
import AntGame.exceptions.PositionException;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Toby
 */
public class AntWorldGeneratorTest {
    
    public AntWorldGeneratorTest() {
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

    /**
     * Test of antWorldGenerator method, of class AntWorldGenerator.
     */
    @Test
    public void testAntWorldGenerator_File() throws Exception {
        File unknownChar = new File("C:\\Users\\Toby\\UnknownChar.world");
        File validWorld = new File("C:\\Users\\Toby\\ValidWorld.world");
        File noInitialDigits = new File("C:\\Users\\Toby\\noInitialDigits.world");
        File missingCell = new File("C:\\Users\\Toby\\missingCell.world");
        AntWorldGenerator gen = new AntWorldGenerator();
        
        boolean thrown = false;
        try {
            gen.antWorldGenerator(unknownChar);
        }
        catch (AntWorldGeneratorException e) {
            thrown = true;
            
        }
        
        assertTrue(thrown);
        
        thrown = false;
        
        try {
            AntWorld world = gen.antWorldGenerator(validWorld);
            assertEquals(world.getPosition(2, 3).getAntHill(), "Black");
        }
        catch (AntWorldGeneratorException e) {
            thrown = true;
        }
        
        assertFalse(thrown);
        
        thrown = false;
        
        try {
            gen.antWorldGenerator(noInitialDigits);
        }
        catch (AntWorldGeneratorException e) {
            thrown = true;
            System.out.println(e.getMessage());
        }
        
        assertTrue(thrown);
        
        try {
            gen.antWorldGenerator(missingCell);
        }
        catch (AntWorldGeneratorException e) {
            thrown = true;
            System.out.println(e.getMessage());
        }
        
        assertTrue(thrown);
    }

    /**
     * Test of antWorldGenerator method, of class AntWorldGenerator.
     */
    @Test
    public void testAntWorldGenerator_boolean() throws PositionException, AntWorldGeneratorException {
        
        AntWorldGenerator gen = new AntWorldGenerator();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            AntWorld world = gen.antWorldGenerator();
            
        }
        
    }

    /**
     * Test of validWorld method, of class AntWorldGenerator.
     */
    @Test
    public void testValidWorld() {
    }
    
    @Test
    public void testFoodGen() throws PositionException, AntWorldGeneratorException { //Shows foodGen builds the same rectangle of food when starting from opposite corner with opposite direction
        AntWorld antWorld = new AntWorld(10, 10);
        AntWorld antWorld2 = new AntWorld(10, 10);
        AntWorldGenerator gen = new AntWorldGenerator();
        antWorld = gen.foodGen(antWorld, 6, 4, 3);
        antWorld2 = gen.foodGen(antWorld2, 0, 0, 0);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(antWorld.getPosition(i, j).toString(), antWorld2.getPosition(i, j).toString());
            }
        }
    }
}
