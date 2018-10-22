/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperatureconverter;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author cstuser
 */
public class TemperatureConverter extends JFrame{

    private JLabel celsiusLabel;
    private JTextField CelsiusValueTextField;
    private JLabel FahrenheitLabel;
    private JLabel fahrenheitValueLabel;
    private JButton convertButton;
    private JButton closeButton;
    
    public static void main(String[] args) {
        TemperatureConverter window = new TemperatureConverter("Hello Swing", 300, 120);
        window.setVisible(true);
    }
    
    public TemperatureConverter(String title, int width, int height){
        super(title);
        setLocationRelativeTo(null);
        setSize(width, height);
        CelsiusValueTextField = new JTextField();
        celsiusLabel = new JLabel("℃");
        
        fahrenheitValueLabel = new JLabel("", SwingConstants.RIGHT);
        FahrenheitLabel = new JLabel("°F");
        
        convertButton = new JButton("Convert");
        closeButton = new JButton("close");
        
        convertButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                float celsius = Float.parseFloat(CelsiusValueTextField.getText());
                float fahrenheit = celsius * 1.8f+32.0f;
                fahrenheitValueLabel.setText(String.format("%.2f", fahrenheit));
            }
        });
        
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispatchEvent(new WindowEvent(TemperatureConverter.this, WindowEvent.WINDOW_CLOSING));
            }
        });
        
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3,2));
        
        pane.add(CelsiusValueTextField);
        pane.add(celsiusLabel);
        pane.add(fahrenheitValueLabel);
        pane.add(FahrenheitLabel);
        pane.add(convertButton);
        pane.add(closeButton);
    }
}
