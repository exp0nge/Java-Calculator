import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MD on 4/17/2015.
 */

/**
 * This class holds all the methods that the calculator GUI accesses.
 */
public class Calculator {

    /**
     * Result of operation stored.
     */
    private double result;

    /**
     * Input as string stored.
     */
    private String input;

    /**
     * Regex to filter out operators and numbers.
     */
    private String regex =  "(?<=\\G(\\w+(?!\\w+)|==|<=|>=|\\+|/|\\*|\\(|\\)|-|(<|>)(?!=)))\\s*";

    /**
     * Constructor to init result to 0.
     */
    public Calculator() {
        this.result = 0.0;
    }

    /**
     * Setter for input
     * @param input Input taken from input field in GUI.
     */
    private void parseInput(String input){
        this.input = input;
    }

    /**
     * Checks for parenthesis and then does operation inside, then outside.
     */
    private void searchAndConquer(){
        int leftPIndex;
        int rightPIndex;
        String [] regexArray = this.input.split(this.regex);
        List<String> list = new ArrayList<String>(Arrays.asList(regexArray));
        if(list.contains("(")){
            leftPIndex = list.indexOf("(");
            rightPIndex = list.indexOf(")");
            list.add(leftPIndex, tryOperations(list.subList(leftPIndex + 1, rightPIndex)));
            for (int i = leftPIndex + 1; i <= rightPIndex; i++) {
                list.remove(i);
            }
        }
        result = Double.parseDouble(tryOperations(list));

    }
    //TODO: Figure out why this doesn't work for multiple operations.

    /**
     * Does operations in terms of precedence. Does not work for more than one operator.
     * @param arrOpInput List of inputs after regex applied.
     * @return Result of calculation.
     */
    private String tryOperations(List<String> arrOpInput){
        List<String> list = arrOpInput;
        double temp;
        int opIndex;
        while(true) {
            if (list.contains("*")) {
                opIndex = list.indexOf("*");
                temp = Double.parseDouble(list.get(opIndex-1)) * Double.parseDouble(list.get(opIndex+1));
                list.add(opIndex - 1, Double.toString(temp));
                list.remove(opIndex);
                list.remove(opIndex);
            }
            else if(list.contains("/")) {
                opIndex = list.indexOf("/");
                temp = Double.parseDouble(list.get(opIndex-1)) / Double.parseDouble(list.get(opIndex+1));
                list.add(opIndex - 1, Double.toString(temp));
                list.remove(opIndex);
                list.remove(opIndex);
            }
            else if(list.contains("+")) {
                opIndex = list.indexOf("+");
                temp = Double.parseDouble(list.get(opIndex-1)) + Double.parseDouble(list.get(opIndex+1));
                list.add(opIndex - 1, Double.toString(temp));
                list.remove(opIndex);
                list.remove(opIndex);
            }
            else if(list.contains("-")) {
                opIndex = list.indexOf("-");
                temp = Double.parseDouble(list.get(opIndex-1)) - Double.parseDouble(list.get(opIndex+1));
                list.add(opIndex - 1, Double.toString(temp));
                list.remove(opIndex);
                list.remove(opIndex );
            }
            else
                break;
        }

        return list.get(0);

    }

    /**
     * Getter for result
     * @return result value
     */
    private double getResult() {
        return this.result;
    }

    /**
     * Main method to call when passing an input from GUI
     * @param input Input from input field
     * @return result from calculation
     */
    public double setInput(String input){
        parseInput(input);
        searchAndConquer();
        return getResult();
    }
}
