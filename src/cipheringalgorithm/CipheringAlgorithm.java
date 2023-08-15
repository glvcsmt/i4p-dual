package cipheringalgorithm;

import static cipheringalgorithm.CipheringAlgorithm.values;
import java.util.ArrayList;

/**
 *
 * @author glvcsmt
 */
public class CipheringAlgorithm {

    //Karaktereket és kódjaikat tartalmazó ArrayList
    public static ArrayList<Value> values = new ArrayList<>();

    //A karaktereket és kójaikat tartalmazó ArrayList feltöltése
    public static void Fill() {
        char c;
        int value = 0;

        for (c = 'a'; c <= 'z'; ++c) {
            Value v = new Value();
            v.setC(c);
            v.setValue(value);
            values.add(v);
            value++;
        }

        Value space = new Value(' ', 26);
        values.add(space);
    }

    //Titkosítás
    public static void Ciphering(String message, String clue) {

        Fill();

        char[] messageToChars = message.toCharArray();
        clue = clue.substring(0, messageToChars.length);
        char[] clueToChars = clue.toCharArray();
        String secret = "";

        for (int i = 0; i < messageToChars.length; i++) {
            int sum = 0;
            char mess = messageToChars[i], cl = clueToChars[i];

            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).getC() == mess) {
                    sum += values.get(j).getValue();
                }
                if (values.get(j).getC() == cl) {
                    sum += values.get(j).getValue();
                }
            }

            if (sum > 26) {
                sum = sum % 27;
            }

            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).getValue() == sum) {
                    secret += values.get(j).getC();
                    break;
                }
            }
        }

        System.out.println(secret);
    }

    //Megfejtés
    public static void Deciphering(String secret, String clue) {

        Fill();

        char[] secretToChars = secret.toCharArray();
        clue = clue.substring(0, secretToChars.length);
        char[] clueToChars = clue.toCharArray();
        String message = "";

        for (int i = 0; i < secretToChars.length; i++) {
            int secInt = 0, clInt = 0;
            char sec = secretToChars[i], cl = clueToChars[i];

            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).getC() == secretToChars[i]) {
                    secInt = values.get(j).getValue();
                }
                if (values.get(j).getC() == clueToChars[i]) {
                    clInt = values.get(j).getValue();
                }
            }

            if (clInt > secInt) {
                secInt += 27;
            }

            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).getValue() == (secInt - clInt)) {
                    message += values.get(j).getC();
                    break;
                }
            }
        }
        
        System.out.println(message);
    }

    public static void main(String[] args) {
        
    }

}
