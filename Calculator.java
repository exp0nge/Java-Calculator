/**
 * Created by MD on 4/17/2015.
 */
public class Calculator {
    private double a, b;
    private double result;

    private String operator;
    private int operandIndex;
    private String [] fixedArray;

    public Calculator() {
        this.a = 0.0;
        this.b = 0.0;
        this.result = 0.0;
        this.operator = "null";
    }

    private void parseInput(String input){
        boolean emptySpaces = true;
        String [] inputArray = input.split("");
        //Removing empty spaces
        int originalArrLength = inputArray.length;
        for (int i = 0; i < originalArrLength; i++) {
            if(inputArray[i].equals(" ")) {
                System.arraycopy(inputArray, i + 1, inputArray, i, inputArray.length - 1 - i);
                originalArrLength--;
                if(emptySpaces == false)
                    emptySpaces = true;
            }
        }
        if(emptySpaces) {
            //Making new array with no spaces
            this.fixedArray = new String[originalArrLength];
            System.arraycopy(inputArray, 0, this.fixedArray, 0, originalArrLength);
        }
        this.fixedArray = inputArray;
    }

    private boolean setOperator(){
        for (int i = 0; i < this.fixedArray.length; i++) {
            if(this.fixedArray[i].equals("+")) {
                this.operator = "+";
                operandIndex = i;
            }
            else if(this.fixedArray[i].equals("-")) {
                this.operator = "-";
                operandIndex = i;
            }
            else if(this.fixedArray[i].equals("*")) {
                this.operator = "*";
                operandIndex = i;
            }
            else if(this.fixedArray[i].equals("/")) {
                this.operator = "/";
                operandIndex = i;
            }
        }
        if (operandIndex == -1 || this.operator == "null")
            return false;
        return true;
    }

    private void doOperation(){
        String beforeOp = "", afterOp = "";

        for (int i = 0; i < this.operandIndex; i++) {
            beforeOp += this.fixedArray[i];
        }

        for (int i = this.operandIndex + 1; i < this.fixedArray.length; i++) {
            afterOp += this.fixedArray[i];
        }
        if(beforeOp.equals("") && afterOp.equals("")){
            result = 0.0;
        }
        else {
            this.a = Double.parseDouble(beforeOp);
            this.b = Double.parseDouble(afterOp);

            if (this.operator == "+") {
                result = a + b;
            } else if (this.operator == "-") {
                result = a - b;
            } else if (this.operator == "*") {
                result = a * b;
            } else if (this.operator == "/") {
                result = a / b;
            }
        }

    }

    private double getResult() {
        return this.result;
    }

    public double setInput(String input){
        parseInput(input);
        setOperator();
        doOperation();
        return getResult();
    }
}
