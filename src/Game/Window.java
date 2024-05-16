package Game;

import gamestates.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Window {
    private JFrame jframe;

    private Playing playing;

    public Window(GamePanel gamePanel){
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);

        jframe.setResizable(false);
        jframe.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jframe.setLocation((int) (screenSize.getWidth()/2-jframe.getSize().getWidth()/2), (int) (screenSize.getHeight()/2-jframe.getSize().getHeight()/2));
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
