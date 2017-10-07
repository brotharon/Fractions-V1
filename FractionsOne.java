/**
 * Pre - Requires input of a .txt file which contains only fractions
 * formatted as #/#
 * Post - Outputs to console the unique, fully reduced, fractions
 * and the number of times each appears in the file
 * @name Calvin Evans
 * @version 06/21/2017
 *
 *    Can you complete this without using arrays? What is the least number 
 *    of variables you can use to solve this problem?
 *
 *    Yes, you could more easily solve this with classes. If using classes
 *    you could solve this with 3 variables (numerator, denominator, and
 *    a 3rd to keep track of number of times the fraction appears. 
 *
 *    Can you use just one array to solve this? What would the data type
 *    be of that array?
 *
 *    I suppose this could be solved with a single int array with some
 *    careful looping algorithms.
 *
 *    What does it mean to make a class so another class is inside (or part 
 *    of) the first class, so that it is composed of other data types?  What 
 *    does "composition" mean in that case?  How is it done?
 *    
 *    Nested classes are used in this way to more logically arrange class
 *    structures. This can makes it easier to use and maintain the classes.
 *    Composition is an inheritance based relationship in which classes
 *    are able to work together using instance variables of other objects.

 *    What are some solutions to the reduction problem other than Euclid's 
 *    GCD algorithm?
 *    
 *    You could take the smaller of the numerator and the denominator, 
 *    attempt to divide both by that number, and then decrement the
 *    divisor until you find the first number that both can be divided
 *    by. That should be the greatest common divisor. 
 *
 *
 */

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class FractionsOne {

    public static void main (String[] args) {
        File inputFile = new File("fractions.txt");
        int[] fractions = new int[0]; //main fraction array
        try {
            Scanner fractionList = new Scanner(inputFile);

            while (fractionList.hasNext()) {
                String currentFraction = fractionList.next();
                fractions = makeFractionArray(fractions, currentFraction);
            }
        }catch (Exception e) {
            System.out.println("File not found");
        }

        int[] num = getNumerators(fractions);
        int[] den = getDenominators(fractions);
        double[] decimals = getDecimals(num, den);

        int[] uniqueNum = new int[num.length];
        int[] uniqueDen = new int[den.length];
        int[] instances = new int[den.length];
        double[] uniqueDecimal = new double[decimals.length];
        int counter = 0;

        /**
         * This loop compares decimal values and determines which
         * fractions are unique and how many times each appears
         * in the file
         */
        boolean duplicate = false;
        for (int i = 0; i < num.length; i++) {
            duplicate = false;           
            double check = decimals[i];
            for (int j = 0; j < num.length; j++) {
                if (check == uniqueDecimal[j]) {
                    instances[j]++;
                    duplicate = true;
                }
            }

            if (duplicate == false) {
                uniqueNum[counter] = num[i];
                uniqueDen[counter] = den[i];
                instances[counter]++;
                uniqueDecimal[counter] = decimals[i];
                counter++;
            }
        }

        for (int i = 0; i < counter; i++) {
            System.out.print(uniqueNum[i] + "/" + uniqueDen[i] + " has a count of : ");
            System.out.println(instances[i]);
        }
    }

    /**
     * Iterates over the initial array and collects all
     * of the numerators in an array
     */
    public static int[] getNumerators(int[] fractions) {
        int[] numerators = new int[fractions.length / 2];
        for (int i = 0; i < fractions.length / 2; i++) {
            numerators[i] = fractions[i * 2];
        }
        return numerators;
    }

    /**
     * Iterates over the initial array and collects all
     * of the denominators in an array
     */
    public static int[] getDenominators(int[] fractions) {
        int[] denominators = new int[fractions.length / 2];
        for (int i = 0; i < fractions.length / 2; i++) {
            denominators[i] = fractions[i * 2 +1];
        }
        return denominators;
    }

    /**
     * divides the collected numerators by the denominators
     * and makes an array of decimals
     */
    public static double[] getDecimals(int[] num, int[] den) {
        double[] decimals = new double[num.length];
        for (int i = 0; i < decimals.length; i++) {
            decimals[i] = (double)num[i] / (double)den[i];
        }
        return decimals;
    }

    /**
     * Takes in the text from the file and creates the initial 
     * one dimensional array of all values
     */
    public static int[] makeFractionArray(int[] currentArray, String currentFraction) {
        String[] workingString = currentFraction.split("/");
        int[] workingInt = {Integer.parseInt(workingString[0]), Integer.parseInt(workingString[1])};
        int divisor = getCommonDivisor(workingInt[0], workingInt[1]);
        workingInt[0] = workingInt[0] / divisor;
        workingInt[1] = workingInt[1] / divisor;
        int[] appendedArray = appendArray(currentArray, workingInt);
        return appendedArray;
    }

    /**
     * Takes in the current array and the values that need added, copies the
     * current array to an array with additional space for the new values,
     * adds the new values to the additional spaces at the end of the array
     */
    public static int[] appendArray(int[] currentArray, int[] toAdd) {
        int[] newFractionArray = Arrays.copyOf(currentArray, currentArray.length + toAdd.length);
        for (int i = 0; i < toAdd.length; i++) {
            newFractionArray[newFractionArray.length - (toAdd.length - i)] = toAdd[i];
        }
        return newFractionArray;
    }

    /**
     * This code block was taken from https://stackoverflow.com/questions/
     * 4009198/java-get-greatest-common-divisor.
     * This uses a recursive method to get to the greatest common divisor
     * with Euclid's Algorithm.
     */
    public static int getCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getCommonDivisor(b,a % b);
    }
}