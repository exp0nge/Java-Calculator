import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MD on 4/17/2015.
 */

public class CalculatorGUI extends JFrame{
    protected String input;
    private Calculator calculatorInst;

    public CalculatorGUI(){
        calculatorInst = new Calculator();
        initComponents();
    }
    private void initComponents(){
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2, 5, 5));
        setSize(200, 100);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        final JTextField inputField = new JTextField();

        final JTextField outputField = new JTextField();

        final JButton calculateButton = new JButton();


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double result = calculatorInst.setInput(inputField.getText());
                inputField.setText("");
                String output = Double.toString(result);
                outputField.setText(output);
            }
        });
        outputField.setEditable(false);
        contentPane.add(inputField);
        contentPane.add(outputField);
        contentPane.add(calculateButton);
        setVisible(true);
    }
}
