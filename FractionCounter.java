/**
 * @author Calvin Evans
 * @version 06/27/2017
 */
public class FractionCounter {
    private Fraction frac;
    private int counter;

    /**
     * Pre:Requires a valid Fraction object.
     * Post:Takes in a Fraction object and stores it and the number of times
     * a Fraction of this specific value has been observed
     *
     * @param input an instance of the Fraction class
     */

    public FractionCounter(Fraction input) {
        this.frac = input;
        counter = 1;
    }

    /**
     * pre: takes in no arguments
     * post: used to add 1 to the counter each time the driver attempts to
     * pass in a fraction that has already been observed in the ArrayList
     */
    void incrementCounter() {
        counter++;
    }

    /**
     * This method is not used in my code. This will return a pointer to the
     * private object in this class, and is a potential privacy leak.
      * @return returns a pointer to the Fraction object in this class
     */
    Fraction getFrac() {
        return frac;
    }

    /**
     * Pre: none
     * Post: basic accessor for the counter variable
     * @return returns a copy of the int stored in the counter variable.
     */
    int getCounter() {
        return counter;
    }

    /**
     * Pre: takes in any object
     * Post: compares the object "other" to this object. If the other object
     * is not a Fraction object it will not be equal.
     * @param other can be any object
     * @return returns a boolean as true if the objects are equal and false
     * if the objects are not equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction checkFrac = (Fraction) other;
            if (checkFrac.getNumerator() == this.frac.getNumerator() &&
                    checkFrac.getDenominator() == this.frac.getDenominator()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pre none
     * Post: this only returns the fraction value stored in this object
     * @return returns as a String the fraction contained in this object in
     * the format #/#
     */
    @Override
    public String toString() {
        return this.frac.getNumerator() + "/" + this.frac.getDenominator();
    }
}
