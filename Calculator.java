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

        String [] inputArray = input.split("");
        input.replace(" ", "");
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

        if (this.operator == "null") {
            String arrayText = "";
            for (int i = 0; i < this.fixedArray.length; i++) {
                arrayText += this.fixedArray[i];
            }
            try{
                result = Double.parseDouble(arrayText);
            }
            catch (NumberFormatException e){
                result = 0;
            }
            return;
        }

        for (int i = 0; i < this.operandIndex; i++) {
            beforeOp += this.fixedArray[i];
        }

        for (int i = this.operandIndex + 1; i < this.fixedArray.length; i++) {
            afterOp += this.fixedArray[i];
        }


        if(beforeOp.equals("")){
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
        this.operator = "null";

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
