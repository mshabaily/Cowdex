import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IoFrame extends JFrame implements ActionListener{

    public ImageIcon cowIcon = new ImageIcon("cowdexIcon.png");

    private JPanel m_leftPanel = new JPanel();
    private JPanel m_rightPanel = new JPanel();

    private boolean m_factButtonPressed = false;

    public void setFactButtonPressed(boolean state){
        m_factButtonPressed = state;
    }

    public JPanel getLeftPanel(){
        return m_leftPanel;
    }

    public JPanel getRightPanel(){
        return m_rightPanel;
    }

    public IoFrame(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(cowIcon.getImage());
        this.setLayout(null);
        this.setSize(500,525);
        this.setResizable(false);

        this.m_leftPanel.setBackground(Color.decode("#00C9A5"));
        this.m_leftPanel.setBounds(0, 0, 90, 500);
        this.add(this.m_leftPanel);
        this.m_leftPanel.setLayout(null);

        this.m_rightPanel.setBackground(Color.decode("#C7FBFE"));
        this.m_rightPanel.setBounds(90, 0, 410, 500);
        this.add(this.m_rightPanel);
        this.m_rightPanel.setLayout(null);
    }

    private JButton factButton;
    private JButton editButton;
    private JButton nextButton;
    public void homeScreen(){
        ImageIcon factIcon = new ImageIcon("factIcon.png");
        ImageIcon editIcon = new ImageIcon("editIcon.png");
        ImageIcon nextIcon = new ImageIcon("nextCowIcon.png");

        factButton = new JButton();
        factButton.setBounds(5, 50, 80, 80);
        factButton.addActionListener(this);
        factButton.setIcon(factIcon);
        factButton.setFocusable(false);
        factButton.setBackground(Color.decode("#00C9A5"));
        factButton.setBorderPainted(false);
        this.m_leftPanel.add(factButton);

        editButton = new JButton();
        editButton.setBounds(5, 200, 80, 80);
        editButton.addActionListener(this);
        editButton.setIcon(editIcon);
        editButton.setFocusable(false);
        editButton.setBackground(Color.decode("#00C9A5"));
        editButton.setBorderPainted(false);
        this.m_leftPanel.add(editButton);

        nextButton = new JButton();
        nextButton.setBounds(5,350, 80, 80);
        nextButton.addActionListener(this);
        nextButton.setIcon(nextIcon);
        nextButton.setFocusable(false);
        nextButton.setBackground(Color.decode("#00C9A5"));
        nextButton.setBorderPainted(false);
        this.m_leftPanel.add(nextButton);

        CowChoice cowChoice = new CowChoice(1);
        JLabel cowLabel = new JLabel();
        cowLabel.setBounds(255, 320, 155, 181);
        cowLabel.setIcon(cowIcon);
        cowLabel.setBackground(Color.decode("#C7FBFE"));
        this.m_rightPanel.add(cowLabel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        System.out.println(event.getSource());
        System.out.println(factButton);
        if(event.getSource().equals(factButton)){
            System.out.println("factButton");
            if(this.m_factButtonPressed == true){
                System.out.println(m_factButtonPressed + "clear");
                this.m_rightPanel.removeAll();
                homeScreen();
                this.m_rightPanel.setVisible(false);
                this.m_rightPanel.setVisible(true);
            }
            BubbleHandler bubbleHandler = new BubbleHandler(this);
            bubbleHandler.outputBubble(1,1);
        }
        if(event.getSource().equals(editButton)){
            System.out.println("NEWSREEN2");
        }
        if(event.getSource().equals(nextButton)){
            FarmerHandler farmerHandler = new FarmerHandler(this);
            farmerHandler.moveFarmer(0);
        }
    }
}