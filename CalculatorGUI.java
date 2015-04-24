import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by MD on 4/17/2015.
 */

public class CalculatorGUI extends JFrame{
    /**
     * Row value
     */
    final static int row = 7;
    /**
     * Column value
     */
    final static int colm = 5;
    /**
     * String to hold output
     */
    String currentOuput = "";
    /**
     * String to hold current input
     */
    private String input;
    /**
     * Double to hodl memory
     */
    private double memoryInput;

    private Calculator calculatorInst;

    /**
     * Creates instance of calculator to get the result and inits component method
     */
    public CalculatorGUI(){
        calculatorInst = new Calculator();
        initComponents();
    }
    /**
     * Setter for input
     * @param in Input
     */
    private void setInput(String in){
        this.input = in;
    }

    /**
     * Getter for input
     * @return current input value
     */
    private String getInput(){
        return this.input;
    }

    /**
     * Adds to the current value of input
     * @param in New text to add to input
     */
    private void concatInput(String in){
        this.input += in;
    }

    /**
     * Method for calculate button "="
     * @param inputField JTextField reference to input field in GUI
     * @param outputField JTextField reference to output field in GUI
     * @return Instance of calculate button
     */
    private JButton calculateButton (final JTextField inputField, final JTextField outputField){
        final JButton calculateButton = new JButton();
        calculateButton.setText("=");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                try {
                    double result = calculatorInst.setInput(userInput);
                    currentOuput = Double.toString(result);
                    inputField.setText("");
                    setInput(userInput);
                    String output = Double.toString(result);
                    outputField.setText(output);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException nFE) {
                    JOptionPane.showMessageDialog(inputField, "Bad input.");
                }
            }
        });

        calculateButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                calculateButton.setBackground(Color.ORANGE);
            }

            public void mouseExited(MouseEvent o) {
                calculateButton.setBackground(UIManager.getColor("control"));
            }
        });

        return calculateButton;
    }

    /**
     * Method to make numPad buttons
     * @param inputField JTextField reference to input field in GUI
     * @param numPadText Text to display on button
     * @return Instance of button
     */
    private JButton numPad(final JTextField inputField, final String numPadText){
        JButton button = new JButton(numPadText);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                concatInput(numPadText);
                String currentInputFieldText = inputField.getText();
                currentInputFieldText += numPadText;
                inputField.setText(currentInputFieldText);
            }
        });
        return button;
    }

    /**
     * Method to generate buttons for memory control.
     * @param outputField JTextField reference to output field in GUI
     * @param inputfield JTextField reference to input field in GUI
     * @param currentMemory Reference to memory label
     * @param memoryButtonText Determines what button to make
     * @return Instance of button
     */
    private JButton memoryButtons(final JTextField outputField, final JTextField inputfield, final JLabel currentMemory, final String memoryButtonText){
        final JButton button = new JButton(memoryButtonText);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memoryButtonText.equals("MC")){
                    try {
                        memoryInput = 0;
                        outputField.setText("");
                        currentMemory.setText("Current memory: ");
                    }
                    catch (NumberFormatException g){
                        JOptionPane.showMessageDialog(inputfield, "MC ERROR");
                    }
                }
                else if(memoryButtonText.equals("M+")){
                    try {
                        final double currentInputText = Double.parseDouble(outputField.getText());
                        memoryInput += currentInputText;
                        String newMemoryOutput = Double.toString(memoryInput);
                        outputField.setText(newMemoryOutput);
                        currentMemory.setText("Current memory: " + newMemoryOutput);
                    }
                    catch(NumberFormatException g){
                        JOptionPane.showMessageDialog(inputfield, "M+ ERROR");
                    }
                }
                else if(memoryButtonText.equals("M-")){
                    try{
                        final double currentInputText = Double.parseDouble(outputField.getText());
                        memoryInput -= currentInputText;
                        String newMemoryOutput = Double.toString(memoryInput);
                        outputField.setText(newMemoryOutput);
                        currentMemory.setText("Current memory: " + newMemoryOutput);
                    }
                    catch (NumberFormatException g){
                        JOptionPane.showMessageDialog(inputfield, "M- ERROR");
                    }
                }
                else if(memoryButtonText.equals("MR")){
                    try{
                        String currentInputFieldText = inputfield.getText();
                        currentInputFieldText += Double.toString(memoryInput);
                        inputfield.setText(currentInputFieldText);
                    }
                    catch (NumberFormatException g){
                        JOptionPane.showMessageDialog(inputfield, "MR ERROR");
                    }
                }
            }
        });
        return button;
    }

    /**
     * Method to make input field editor buttons (<--, CLR, etc)
     * @param inputField JTextField reference to input field in GUI
     * @param nameOfButton Name of button to put in instance
     * @return returns instance of the button
     */
    private JButton inputFieldEditorButtons(final JTextField inputField, String nameOfButton){
        JButton button = new JButton(nameOfButton);

        if(nameOfButton.equals("<--")){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final String input = inputField.getText();
                    if (input.length() > 0)
                        inputField.setText(input.substring(0, input.length()-1));
                }
            });
        }
        else if(nameOfButton.equals("CLR")){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inputField.setText("");
                }
            });
        }
        else if(nameOfButton.equals("ANS")){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentInput = inputField.getText();
                    currentInput += currentOuput;
                    inputField.setText(currentInput);
                }
            });
        }
        return button;
    }

    /**
     * Inits all components of GUI (Buttons/fields/etc).
     */
    private void initComponents(){
        JFrame frame = new JFrame("MD's Calculator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize((colm*100), (row*40)-20);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        GridBagConstraints c = new GridBagConstraints();

        //Adding outputField
        JTextField outputField = new JTextField();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.ipady = 20;
        c.fill = GridBagConstraints.BOTH;
        outputField.setEditable(false);
        panel.add(outputField, c);

        //Adding inputField
        JTextField inputField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 5;
        c.weightx = 1;
        c.ipady = 20;
        c.fill = GridBagConstraints.BOTH;
        panel.add(inputField, c);

        c.ipady = 0; //reset for buttons

        //Adding memory label
        JLabel currentMemory = new JLabel("Current memory: ");
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 4;
        panel.add(currentMemory, c);

        c.gridwidth = 1; //reset for buttons
        //Memory buttons
        JButton MC = memoryButtons(outputField, inputField, currentMemory, "MC");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(MC, c);

        JButton mPlus = memoryButtons(outputField, inputField, currentMemory, "M+");
        c.gridx = 1;
        c.gridy = 2;
        panel.add(mPlus, c);

        JButton mMinus = memoryButtons(outputField, inputField, currentMemory, "M-");
        c.gridx = 2;
        c.gridy = 2;
        panel.add(mMinus, c);

        JButton MR = memoryButtons(outputField, inputField, currentMemory, "MR");
        c.gridx = 3;
        c.gridy = 2;
        panel.add(MR, c);

        //Numberpad stuff
        JButton seven = numPad(inputField, "7");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(seven, c);

        JButton eight = numPad(inputField, "8");
        c.gridx = 1;
        c.gridy = 3;
        panel.add(eight, c);

        JButton nine = numPad(inputField, "9");
        c.gridx = 2;
        c.gridy = 3;
        panel.add(nine, c);

        JButton four = numPad(inputField, "4");
        c.gridx = 0;
        c.gridy = 4;
        panel.add(four, c);

        JButton five = numPad(inputField, "5");
        c.gridx = 1;
        c.gridy = 4;
        panel.add(five, c);

        JButton six = numPad(inputField, "6");
        c.gridx = 2;
        c.gridy = 4;
        panel.add(six, c);

        JButton one = numPad(inputField, "1");
        c.gridx = 0;
        c.gridy = 5;
        panel.add(one, c);

        JButton two = numPad(inputField, "2");
        c.gridx = 1;
        c.gridy = 5;
        panel.add(two, c);

        JButton three = numPad(inputField, "3");
        c.gridx = 2;
        c.gridy = 5;
        panel.add(three, c);

        JButton zero = numPad(inputField, "0");
        c.gridx = 0;
        c.gridy = 6;
        panel.add(zero, c);

        //Adding '=' button
        JButton equalsButton = calculateButton(inputField, outputField);
        c.gridx = 1;
        c.gridy = 6;
        panel.add(equalsButton, c);

        //Adding operators
        JButton plusButton = numPad(inputField, "+");
        c.gridx = 3;
        c.gridy = 3;
        panel.add(plusButton, c);

        JButton minusButton = numPad(inputField, "-");
        c.gridx = 3;
        c.gridy = 4;
        panel.add(minusButton, c);

        JButton multiplicationButton = numPad(inputField, "*");
        c.gridx = 3;
        c.gridy = 5;
        panel.add(multiplicationButton, c);

        JButton divisionButton = numPad(inputField, "/");
        c.gridx = 3;
        c.gridy = 6;
        panel.add(divisionButton, c);

        JButton dotButton = numPad(inputField, ".");
        c.gridx = 2;
        c.gridy = 6;
        panel.add(dotButton, c);

        //Input editor buttons
        JButton clear = inputFieldEditorButtons(inputField, "CLR");
        c.gridx = 4;
        c.gridy = 2;
        panel.add(clear, c);

        JButton backSpace = inputFieldEditorButtons(inputField, "<--");
        c.gridx = 4;
        c.gridy = 3;
        panel.add(backSpace, c);

        JButton prevAns = inputFieldEditorButtons(inputField, "ANS");
        c.gridx = 4;
        c.gridy = 4;
        panel.add(prevAns, c);


        frame.setVisible(true);
    }
}
