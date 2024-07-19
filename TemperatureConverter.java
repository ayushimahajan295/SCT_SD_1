import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {

    private JTextField inputField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel inputLabel = new JLabel("Temperature:");
        inputLabel.setBounds(20, 20, 100, 25);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(130, 20, 100, 25);
        add(inputField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(20, 50, 100, 25);
        add(fromLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromComboBox = new JComboBox<>(units);
        fromComboBox.setBounds(130, 50, 100, 25);
        add(fromComboBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(20, 80, 100, 25);
        add(toLabel);

        toComboBox = new JComboBox<>(units);
        toComboBox.setBounds(130, 80, 100, 25);
        add(toComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(80, 110, 100, 25);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        add(convertButton);

        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(20, 140, 250, 25);
        add(resultLabel);
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String fromUnit = (String) fromComboBox.getSelectedItem();
            String toUnit = (String) toComboBox.getSelectedItem();
            double result = 0.0;

            switch (fromUnit) {
                case "Celsius":
                    if (toUnit.equals("Fahrenheit")) {
                        result = (inputTemp * 9/5) + 32;
                    } else if (toUnit.equals("Kelvin")) {
                        result = inputTemp + 273.15;
                    } else {
                        result = inputTemp;
                    }
                    break;
                case "Fahrenheit":
                    if (toUnit.equals("Celsius")) {
                        result = (inputTemp - 32) * 5/9;
                    } else if (toUnit.equals("Kelvin")) {
                        result = ((inputTemp - 32) * 5/9) + 273.15;
                    } else {
                        result = inputTemp;
                    }
                    break;
                case "Kelvin":
                    if (toUnit.equals("Celsius")) {
                        result = inputTemp - 273.15;
                    } else if (toUnit.equals("Fahrenheit")) {
                        result = ((inputTemp - 273.15) * 9/5) + 32;
                    } else {
                        result = inputTemp;
                    }
                    break;
            }

            resultLabel.setText("Result: " + result + " " + toUnit);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}
