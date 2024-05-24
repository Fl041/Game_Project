package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * class which defines the game window
 */
public class Window {
    private JFrame jframe;

    /**
     *Constructor which allows you to add the panel to the window and define the position of the window
     * @param gamePanel
     */
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

            /**
             * call a function that stops interaction with the window when it is no longer in the foreground
             * @param e the event to be processed
             */
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
