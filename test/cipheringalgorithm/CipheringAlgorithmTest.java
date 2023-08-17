package cipheringalgorithm;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
public class CipheringAlgorithmTest {
    
    public CipheringAlgorithmTest() {
    }
    
    @Test
    public void testFill() {
        //Teszt: A 'Fill()' metódus feltölti-e rendesen a 'values' listát. Ha igen akkor 27 elemet kell tartalmaznia (26 betű és ' ').
        CipheringAlgorithm test = new CipheringAlgorithm();
        int expectedLength = 27;
        int actualLength;
        
        test.Fill();
        actualLength = CipheringAlgorithm.values.size();
        
        assertEquals(expectedLength, actualLength);
    }
    
    @Test
    public void testCiphering() {
        //Teszt: A 'helloworld' szó 'abcdefgijkl' kódolással 'hfnosauzun' üzenetet ad vissza.
        CipheringAlgorithm test = new CipheringAlgorithm();        
        String expected = "hfnosauzun";
        String actual;
        
        actual = test.Ciphering("helloworld", "abcdefgijkl");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDeciphering() {
        //Teszt: A 'hfnosauzun' rejtjelezett üzenet 'abcdefgijkl' kódolással 'helloworld' üzenetet ad vissza.
        CipheringAlgorithm test = new CipheringAlgorithm();
        String expected = "helloworld";
        String actual;
        
        actual = test.Deciphering("hfnosauzun", "abcdefgijkl");
        
        assertEquals(expected, actual);
    }
       
}
