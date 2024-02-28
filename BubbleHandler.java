import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BubbleHandler{
    private int count = 0;
    private int delay = 40;
    private Timer stopwatch;
    private int bubbleLoop = 0;
    private String[] bubblePaths = new String[]{
        "thoughtBubbleIconA.png",
        "thoughtBubbleIconB.png",
        "thoughtBubbleIconC.png",
        "thoughtBubbleIconD.png"};
    private Rectangle bubbleBounds = new Rectangle(245,400,27,20);
    private JLabel thoughtBubble;

    public IoFrame m_ioFrame;
    public JPanel rightPanel;


    public BubbleHandler(IoFrame ioFrame){
        m_ioFrame = ioFrame;
        m_ioFrame.setFactButtonPressed(true);
        rightPanel = m_ioFrame.getRightPanel();
    }

    public void outputBubble(int countPassed, int bubbleLoopPassed){
        System.out.println("output");
        ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                bubbleLoop = bubbleLoopPassed;
                if(bubbleLoop > 4){
                    stopwatch.stop();
                    JLabel factText = new JLabel();
                    factText.setText("<HTML>Because of the limited exports, a number of downright hilarious myths have arisen to explain their high fat content. One of the more amusing is that Japanese farmers massage their cows while feeding them beer and playing classical music to help them relax.<HTML>");
                    factText.setBounds(50,0,300,295);
                    rightPanel.add(factText);
                    ImageIcon mainBubbleIcon = new ImageIcon("thoughtBubbleIconE.png");
                    JLabel thoughtBubbleMain = new JLabel();
                    thoughtBubbleMain.setIcon(mainBubbleIcon);
                    thoughtBubbleMain.setBounds(0,0,385,295);
                    rightPanel.add(thoughtBubbleMain);
                    rightPanel.setVisible(false);
                    rightPanel.setVisible(true);
                    }
                else{
                    if(count == 0){
                        stopwatch.stop();
                        thoughtBubble = new JLabel();   
                        ImageIcon nextThoughtBubbleIcon = new ImageIcon(bubblePaths[bubbleLoop-1]);
                        thoughtBubble.setBounds(bubbleBounds);
                        bubbleBounds.setBounds((int)(bubbleBounds.getX()-30),(int)(bubbleBounds.getY()-(bubbleLoop*10)-30),nextThoughtBubbleIcon.getIconWidth()*2, nextThoughtBubbleIcon.getIconHeight()*2);
                        thoughtBubble.setIcon(nextThoughtBubbleIcon);
                        thoughtBubble.setBackground(Color.decode("#C7FBFE"));
                        rightPanel.add(thoughtBubble);
                        rightPanel.setVisible(false);
                        rightPanel.setVisible(true);
                        bubbleLoop++;
                        outputBubble(3,bubbleLoop);
                    }
                    else{
                        count --;
                    }
                }
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start();
        count = countPassed;
    }
}