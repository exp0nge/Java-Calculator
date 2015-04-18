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
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));
        setSize(300, 250);

        setResizable(false);

        final JTextField inputField = new JTextField();

        final JButton calculateButton = new JButton();


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double result = calculatorInst.setInput(inputField.getText());
                String output = Double.toString(result);
                JOptionPane.showMessageDialog(null, output);
            }
        });

        contentPane.add(inputField);
        contentPane.add(calculateButton);
        setVisible(true);
    }
}
