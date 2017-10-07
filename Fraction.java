/**
 * @author Calvin Evans
 * @version 06/27/2017
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction() { //default constructor makes 1/1 fraction
        this.numerator = 1;
        this.denominator = 1;
    }

    /**
     * Pre: Takes in any 2 integers as a numerator and denominator. If a 0 is
     * passed in for a denominator value the constructor will set the 0 value
     * to 1 and continue to run.
     * Post: Checks values, sets any 0 value denominator to a value of 1,
     * fully reduces the fraction, properly assigns negative values, and sets
     * the instance variables for the Fraction object
     *
     * @param num numerator passed from driver main method
     * @param den denominator passed from driver main method
     */
    public Fraction(int num, int den) {
        this.numerator = num;
        if (den == 0) {//checks to make sure no division by 0
            this.denominator = 1;
            System.out.print("Denominator 0 has been set to 1");
            System.out.println(" for fraction " + num + "/" + den);
        } else {
            this.denominator = den;
        }
        int divisor = getCommonDivisor(this.numerator, this.denominator);
        this.numerator = this.numerator / divisor;
        this.denominator = this.denominator / divisor;
        if (this.numerator < 0 && this.denominator < 0) { //changes -#/-#
            // fractions to #/#
            this.numerator = Math.abs(this.numerator);
            this.denominator = Math.abs(this.denominator);
        } else if (this.numerator > 0 && this.denominator < 0) { //changes
            // #/-# fractions to -#/# format
            this.denominator = Math.abs(this.denominator);
            this.numerator = this.numerator - (this.numerator * 2);
        }
    }

    /**
     * This code block was taken from https://stackoverflow.com/questions/
     * 4009198/java-get-greatest-common-divisor.
     * This uses a recursive method to get to the greatest common divisor
     * with Euclid's Algorithm.
     */
    private int getCommonDivisor(int num, int den) {
        if (den == 0) {
            return num;
        }
        return getCommonDivisor(den, num % den);
    }

    /**
     * Takes no arguments.
     *
     * @return returns a copy of the value in numerator, does not change the
     * instance variable or allow it to be changed.
     */
    int getNumerator() {
        return this.numerator;
    }

    /**
     * Takes no arguments.
     *
     * @return returns a copy of the value in denominator, does not change the
     * instance variable or allow it to be changed.
     */
    int getDenominator() {
        return this.denominator;
    }

    /**
     * Takes in any integer. Is capable of handling 0 and negative numbers
     *
     * @param num can be any integer
     * @return returns a string indicating what the new value is stored in
     * the numerator instance variable
     */
    String setNumerator(int num) {
        this.numerator = num;
        return "numerator set to " + this.numerator;
    }

    /**
     * Takes in any integer. Is capable of handling 0 and negative numbers
     *
     * @param num can be any integer
     * @return returns a string indicating what the new value is stored in
     * the denominator instance variable
     */
    String setDenominator(int den) {
        if (den < 0 && numerator > 0) {
            this.denominator = Math.abs(den);
            this.numerator = this.numerator - (this.numerator * 2);
            return "denominator set to " + this.denominator;
        } else if (den < 0 && numerator < 0) {
            this.denominator = Math.abs(den);
            this.numerator = Math.abs(this.numerator);
            return "denominator set to " + this.denominator;
        } else if (den > 0){
            this.denominator = den;
        }
        return "You entered 0. Denominator not changed from " +
                this.denominator;
    }

    /**
     * Pre: None
     * Post: Overrides the toString method to convert the stored fraction
     * @return returns a String object of the stored Fraction in the format #/#
     */
    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    /**
     * compares this instance of Fraction to any other object. Only possible
     * to be equal if the other object is also a Fraction object
     *
     * @param other any instance of an object
     * @return true if other is a fraction of the same value or false if
     * value is different or other is not a Fraction object
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction checkFrac = (Fraction) other;
            if (checkFrac.getNumerator() == this.numerator && checkFrac
                    .getDenominator() == this.denominator) {
                return true;
            }
        }
        return false;
    }
}

