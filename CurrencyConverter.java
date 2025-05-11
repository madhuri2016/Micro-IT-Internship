import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
public class CurrencyConverter {
    private JFrame frame;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private HashMap<String, Double> rates;

    public CurrencyConverter() {
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.91);
        rates.put("INR", 83.25);
        rates.put("JPY", 156.5);
        rates.put("GBP", 0.78);
        frame = new JFrame("💱 Currency Converter");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1, 10, 5));
        fromCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        frame.add(new JLabel("From Currency:"));
        frame.add(fromCurrency);
        toCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        frame.add(new JLabel("To Currency:"));
        frame.add(toCurrency);
        amountField = new JTextField();
        frame.add(new JLabel("Enter Amount:"));
        frame.add(amountField);
        JButton convertButton = new JButton("🔁 Convert");
        convertButton.addActionListener(e -> convertCurrency());
        frame.add(convertButton);
        resultLabel = new JLabel("Converted Amount: ", SwingConstants.CENTER);
        frame.add(resultLabel);
        frame.setVisible(true);
    }
    private void convertCurrency() {
        try {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            double inUSD = amount / rates.get(from);
            double converted = inUSD * rates.get(to);
            resultLabel.setText(String.format("Converted Amount: %.2f %s", converted, to));
        } catch (NumberFormatException e) {
            resultLabel.setText("⚠️ Please enter a valid number!");
        } catch (Exception e) {
            resultLabel.setText("❌ Conversion error.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter());
    }
}
