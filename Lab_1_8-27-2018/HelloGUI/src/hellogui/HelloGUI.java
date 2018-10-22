/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellogui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author cstuser
 */
public class HelloGUI extends JFrame{

    private JLabel celsiusLable;
    private JLabel CelsiusValueTextField;
    private JLabel FahrenheitLabel;
    private JLabel fahrenheitValueLabel;
    private JButton converButton;
    private JButton closeButton;
    
    
    public HelloGUI() {
        super("Hello!");
        setSize(640, 480);
    }
    
    public static void main(String[] args) {
        TemperatureConverter window = new TemperatureConverter("Hello Swing", 300, 120);
        window.setVisible(true);
    }
    
    public TemperatureConverter(String title, int width, int height){
        
    }
}
