import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by MD on 4/17/2015.
 */

public class CalculatorGUI extends JFrame{
    final static int row = 7;
    final static int colm = 4;

    private String input;

    private Calculator calculatorInst;

    public CalculatorGUI(){
        calculatorInst = new Calculator();
        initComponents();
    }
    private void setInput(String in){
        this.input = in;
    }
    private String getInput(){
        return this.input;
    }
    private void concatInput(String in){
        this.input += in;
    }
    private JButton calculateButton (final JTextField inputField, final JTextField outputField){
        final JButton calculateButton = new JButton();
        calculateButton.setText("=");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                setInput(userInput);
                double result = calculatorInst.setInput(userInput);
                inputField.setText("");
                String output = Double.toString(result);
                outputField.setText(output);
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
    private void initComponents(){
        JFrame frame = new JFrame("Calculator");

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
        c.gridwidth = 4;
        c.ipady = 20;
        c.fill = GridBagConstraints.BOTH;
        outputField.setEditable(false);
        panel.add(outputField, c);

        //Adding inputField
        JTextField inputField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.ipady = 20;
        c.fill = GridBagConstraints.BOTH;
        panel.add(inputField, c);

        c.ipady = 0;
        c.gridwidth = 1; //reset for buttons
        //Memory buttons
        JButton MC = new JButton("MC");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(MC, c);

        JButton Mplus = new JButton("M+");
        c.gridx = 1;
        c.gridy = 2;
        panel.add(Mplus, c);

        JButton Mminus = new JButton("M-");
        c.gridx = 2;
        c.gridy = 2;
        panel.add(Mminus, c);

        JButton MR = new JButton("MR");
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

        //Adding memory label
        //TODO: Implement this
        JLabel currentMemory = new JLabel("Current memory: ");
        c.gridx = 0;
        c.gridy = 7;
        panel.add(currentMemory, c);

        frame.setVisible(true);
    }
}
