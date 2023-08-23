package cipheringalgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author glvcsmt
 */
public class CipheringAlgorithm {

    public CipheringAlgorithm() {
        fill();
    }

    //Karaktereket és kódjaikat tartalmazó ArrayList:
    public ArrayList<Value> values = new ArrayList<>();

    //A karaktereket és kódjaikat tartalmazó ArrayList feltöltése:
    public void fill() {
        values.clear();
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

    //Titkosítás:
    public String ciphering(String message, String clue) {

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

        return secret;
    }

    //Megfejtés:
    public String deciphering(String secret, String clue) {

        char[] secretToChars = secret.toCharArray();
        if (secret.length() < clue.length()) {
            clue = clue.substring(0, secretToChars.length);
        }
        char[] clueToChars = clue.toCharArray();
        String message = "";

        for (int i = 0; i < clueToChars.length; i++) {
            int secInt = 0, clInt = 0;

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

        return message;
    }

    //Metódus, amely visszafejti az adott szöveg rejtjelezési kulcsát.
    //Ehhez felhasználva az eredeti üzenetet és annak rejtjelezett változatát.
    public String keyFinder(String secret, String message) {

        if (message.length() < secret.length()) {
            secret = secret.substring(0, message.length());
        }
        char[] messageToChars = message.toCharArray();
        char[] secretToChars = secret.toCharArray();

        String key = "";

        for (int i = 0; i < secretToChars.length; i++) {
            int secInt = 0, messInt = 0;

            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).getC() == secretToChars[i]) {
                    secInt = values.get(j).getValue();
                }
                if (values.get(j).getC() == messageToChars[i]) {
                    messInt = values.get(j).getValue();
                }
            }

            if (messInt > secInt) {
                secInt += 27;
            }

            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).getValue() == (secInt - messInt)) {
                    key += values.get(j).getC();
                    break;
                }
            }
        }

        return key;
    }

    //Lista, ami a 'words.txt' elemeit tárolja:
    ArrayList<String> words = new ArrayList<>();

    //A 'words.txt' file tartalmának beolvasása:
    private void read() {
        try {
            FileReader fr = new FileReader("words.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CipheringAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CipheringAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metódus, ami megtippeli a közös kulcs egy részét:
    public String makeAGuess(String codedMess1, String codedMess2) {
        String fullKey = "";
        int originalLength = codedMess1.length();
        int start = 0;
        boolean first = true;
        String originalCodedMess1 = codedMess1;
        String originalCodedMess2 = codedMess2;
        String decoded2 = "";

        outerWhileLoop:
        while (start < originalLength) {

            if (first == false) {
                codedMess1 = originalCodedMess1.substring(start);
                codedMess2 = originalCodedMess2.substring(start);
            }

            outerForLoop:
            for (int i = 0; i < words.size(); i++) {
                String keyPart = keyFinder(codedMess1, words.get(i).concat(" "));
                //String tryIfMakesSense = deciphering(codedMess2, fullKey.concat(keyPart));
                String tryIfMakesSense = deciphering(codedMess2, keyPart);
                boolean isItValid = true;

                if (tryIfMakesSense.contains(" ")) {
                    String[] tryWords = (decoded2 + tryIfMakesSense).trim().split(" ");
                    for (int k = 0; k < tryWords.length - 1 && isItValid; k++) {
                        if (!words.contains(tryWords[k])) {
                            isItValid = false;
                        }
                    }
                    if (isItValid) {
                        tryIfMakesSense = tryWords[tryWords.length - 1];
                        decoded2 = "";
                        isItValid = tryIfMakesSense.trim().length() > 0;
                    }
                }

                for (int j = 0; isItValid && j < words.size(); j++) {
                    if (words.get(j).startsWith(decoded2 + tryIfMakesSense)) {

                        fullKey = fullKey.concat(keyPart);
                        start += words.get(i).concat(" ").length();
                        decoded2 += tryIfMakesSense;
                        
                        if (decoded2.contains(" ")) {
                            decoded2 = decoded2.substring(decoded2.lastIndexOf(" ")).trim();
                        }
                        if (words.contains(decoded2)) {
                            decoded2 = "";
                        }

                        break outerForLoop;
                    }
                }
            }
            first = false;
        }

        return fullKey;
    }

    //Metódus, ami a megfelelő függvények meghivásával visszafejti a kódolást:
    public String keyCracker(String codedMess1, String codedMess2) {
        String fullKey = "";;
        read();

        if (codedMess1.length() > codedMess2.length()) {
            String temp = codedMess1;
            codedMess1 = codedMess2;
            codedMess2 = temp;
        }
        fullKey = fullKey.concat(makeAGuess(codedMess1, codedMess2));
        System.out.println(fullKey);
        return fullKey;
    }

    public static void main(String[] args) {

    }

}
