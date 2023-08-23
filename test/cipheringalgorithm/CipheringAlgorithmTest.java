package cipheringalgorithm;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author glvcsmt
 */
public class CipheringAlgorithmTest {

    public CipheringAlgorithmTest() {
    }

    @Test
    public void testFill() {
        //Teszt: A 'Fill()' metódus feltölti-e rendesen a 'values' listát. 
        //Ha igen, akkor 27 elemet kell tartalmaznia (26 betű és ' ').
        CipheringAlgorithm test = new CipheringAlgorithm();
        int expectedLength = 27;
        int actualLength;

        test.fill();
        actualLength = test.values.size();

        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testCiphering() {
        //Teszt: A 'helloworld' szó 'abcdefgijkl' kódolással 'hfnosauzun' üzenetet ad vissza.
        CipheringAlgorithm test = new CipheringAlgorithm();
        String expected = "hfnosauzun";
        String actual;

        actual = test.ciphering("helloworld", "abcdefgijkl");

        assertEquals(expected, actual);
    }

    @Test
    public void testDeciphering() {
        //Teszt: A 'hfnosauzun' rejtjelezett üzenet 'abcdefgijkl' kódolással 'helloworld' üzenetet ad vissza.
        CipheringAlgorithm test = new CipheringAlgorithm();
        String expected = "helloworld";
        String actual;

        actual = test.deciphering("hfnosauzun", "abcdefgijkl");

        assertEquals(expected, actual);
    }

    @Test
    public void testKeyFinder() {
        //Teszt: A metódus a megadott 'curiosity killed the cat' karakterlánccal és annak
        //rejtjelezett 'cvtlsxo fiutxzttqk yuyxq' változatával megadja a kódolási kulcsot,
        //ami a tesztelt esetben 'abcdefghijklmopqrstuvwxy'.
        CipheringAlgorithm test = new CipheringAlgorithm();
        String expected = "abcdefghijklmopqrstuvwxy";
        String actual;
        
        actual = test.keyFinder("cvtlsxo fiutxzttqk yuyxq", "curiosity killed the cat");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMakeAGuess(){
        //Teszt: 'gscwfkiclckutdmwjgzhdpaz' és 'izczpsbseg oilk ofrwlr blez' 
        //biztosan közös kódja 'ezmostakodamititkosdenag'. 
        //Ezt a metódust használja fel a keyCracker metódus is a kulcs visszafejtésére.
        CipheringAlgorithm test = new CipheringAlgorithm();
        String expected = "abcdefghijklmopqrstuvwxy";
        String actual;
        
        actual = test.keyCracker("cvtlsxo fiutxzttqk yuyxq", "ebtobehpzmjnmgrxvjsmb wtmqm");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testKeyCracker() {
        //Teszt: A 'gscwfkiclckutdmwjgzhdpaz' és 'izczpsbseg oilk ofrwlr blez' 
        //biztosan közös kódja 'ezmostakodamititkosdenag'. Ehhez meghivja a szükséges, 
        //korábban már elkészitett metódusokat, megfelelően táplálja be az adatokat azokba.
        
        //A metódus félkész, csak adott tesztesettel működik, 
        //amihez a lista is csak a megfejtendő karakterlánc szavait tartalmazza!
        CipheringAlgorithm test = new CipheringAlgorithm();
        String expected = "abcdefghijklmopqrstuvwxy";
        String actual;

        actual = test.keyCracker("cvtlsxo fiutxzttqk yuyxq", "ebtobehpzmjnmgrxvjsmb wtmqm");

        assertEquals(expected, actual);
    }
}
