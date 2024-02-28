import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FarmerHandler{
    private Timer m_stopwatch;
    private int m_count = 0;
    private int m_delay = 1;
    private IoFrame m_ioFrame;

    public FarmerHandler(IoFrame ioFrame){
        m_ioFrame = ioFrame;
    }

    public void moveFarmer(int countPassed){
        JPanel rightPanel = m_ioFrame.getRightPanel();
        ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent farmEvent){
                JLabel hay = new JLabel();
                ImageIcon hayIcon = new ImageIcon("hay.png");
                hay.setIcon(hayIcon);
                rightPanel.add(hay);
                if(m_count == 150){
                    rightPanel.removeAll();
                    m_ioFrame.homeScreen();
                    rightPanel.setVisible(false);
                    rightPanel.setVisible(true);
                    m_stopwatch.stop();
                }
                else if(m_count % 2 == 0){
                    m_count ++;
                    rightPanel.removeAll();
                    m_ioFrame.homeScreen();
                    JLabel farmer = new JLabel();
                    ImageIcon farmerIcon = new ImageIcon("farmer.png");
                    farmer.setIcon(farmerIcon);
                    rightPanel.add(farmer);
                    farmer.setBounds(0,300,74,200);
                }
                else{
                    m_count ++;
                    hay.setBounds(80+m_count,400,120,100);
                    rightPanel.add(hay);
                }
            }
        };
        m_stopwatch = new Timer(m_delay, action);
        m_stopwatch.setInitialDelay(0);
        m_stopwatch.start();
        m_count = countPassed;
    }
}