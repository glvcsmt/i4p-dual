package cipheringalgorithm;

/**
 *
 * @author glvcsmt
 */
//Önálló osztály a karakterek és kódjaik egyszerűbb tárolásához, felhasználásához
public class Value {

    private char c;
    private int value;

    public Value() {
    }

    public Value(char c, int value) {
        this.c = c;
        this.value = value;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value: c = " + c + ", value = " + value;
    }

}
