import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pre:Requires an input file of fractions with a format of #/# and at least
 * a space between each fraction. File must be named fractions.txt and must
 * be placed in the root folder.
 * Post:Parses out the fractions from the file, reduces the fractions,
 * compares all fractions to the other fractions and outputs what fraction
 * values were in the list and how many instances of each fraction appeared
 * in the list.
 * @author Calvin Evans
 * @version  06/27/2017
 */

public class FractionDriver {
    public static void main(String[] args) {
        File inputFile = new File("fractions.txt");
        ArrayList<Fraction> fractionList = new ArrayList<Fraction>();
        try {
            Scanner fractionFile = new Scanner(inputFile);

            // Pre: Requires Scanner created by valid input file
            // Post: Creates an ArrayList of Fraction objects from the input
            // file
            while (fractionFile.hasNext()) {
                String currentFraction = fractionFile.next();
                Fraction f = makeFraction(currentFraction);
                fractionList.add(f);
            }
            fractionFile.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        ArrayList<FractionCounter> counterList = makeCounterArrayList
                (fractionList); //creates an ArrayList of FractionCounter
        // objects
        outputArrayList(counterList);
    }

    /**
     * Pre: takes input of 2 integers (a numerator and denominator) and
     * converts them into a Fraction object
     * Post: will return a Fraction object made from the input integers
     *
     * @param input requires a valid String object
     * @return a Fraction object with a fully reduced numerator and denominator
     */
    private static Fraction makeFraction(String input) {
        int num;
        int den;
        String[] workingString = input.split("/");
        num = Integer.parseInt(workingString[0]);
        den = Integer.parseInt(workingString[1]);
        return new Fraction(num, den);//passes the split and parsed integers
        // to the Fraction class to be reduced and returned to the main method
    }

    /**
     * Pre: Requires an ArrayList of Fraction objects
     * Post: Creates a new ArrayList of unique FractionCounter objects which
     * contain a unique Fraction object and a count of how many times that
     * Fraction appeared in the ArrayList of Fraction objects.
     *
     * @param fractions as an ArrayList of Fraction objects
     */
    private static ArrayList<FractionCounter> makeCounterArrayList
    (ArrayList<Fraction> fractions) {
        ArrayList<FractionCounter> counterList = new
                ArrayList<FractionCounter>();
        for (Fraction f : fractions) {
            boolean check = false; //used to determine if a match has been
            // made between two fractions. Breaks while loop once match found
            while (!check) {
                if (counterList.isEmpty()) { //creates first item in
                    // FractionCounter ArrayList
                    FractionCounter fc = new FractionCounter(f);
                    counterList.add(fc);
                    check = true;
                } else {
                    boolean internalCheck = false; //breaks internal loop
                    for (FractionCounter current : counterList) {
                        //increments current item in FractionCounter
                        // ArrayList if it is the same Fraction as the value
                        // from the outer loop.
                        if (current.equals(f)) {
                            current.incrementCounter();
                            internalCheck = true;
                        }
                    }
                    if (!internalCheck) { //adds the outer loop Fraction to
                        // the FractionCounter ArrayList as a new object if
                        // no match was found.
                        FractionCounter fc = new FractionCounter(f);
                        counterList.add(fc);
                    }
                    check = true;
                }

            }
        }
        return counterList;
    }

    /**
     * Pre: Takes in an ArrayList of FractionCounter objects
     * Post:Prints to console the Fractions contained in the FractionCounter
     * objects and their respective counts
     *
     * @param output as an ArrayList of FractionCounter objects
     */
    private static void outputArrayList(ArrayList<FractionCounter> output) {
        for (FractionCounter fc : output) {
            System.out.println(fc.toString() + " has a count of " + fc
                    .getCounter());
        }
    }
}

