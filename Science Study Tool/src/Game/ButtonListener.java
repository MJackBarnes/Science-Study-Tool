package Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Panel.height_;
import static Game.Panel.width_;

public class ButtonListener implements ActionListener, ChangeListener {

    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    JButton reStart = new JButton("Restart");
    JButton quit = new JButton("Quit");
    Keyboard k;
    Panel p;

    public ButtonListener(Keyboard keyboard, Panel panel){
        k = keyboard;
        p = panel;

    }

    public void init(JPanel panel){
        quit.addActionListener(this);
        quit.setFont(font1);
        quit.setActionCommand("Quit");
        reStart.addActionListener(this);
        reStart.setFont(font1);
        quit.setBounds((int) (width_ / 2 - width_ / 5 - width_ / 20), (int) height_ / 2, (int) width_ / 5, (int) height_ / 20);
        reStart.setBounds((int) (width_ / 2 + width_ / 20), (int) height_ / 2, (int) width_ / 5, (int) height_ / 20);
        reStart.setVisible(false);
        quit.setVisible(false);
        panel.add(quit);
        panel.add(reStart);

    }

    public void gameOver(Graphics g, JPanel panel, boolean gameOver){
        if (gameOver) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(font);
            g2d.drawString("Game Over", (int) width_ / 2 - (int) width_ / 10, (int) height_ / 2 - 30);
            quit.setBounds((int) (width_ / 2 - width_ / 5 - width_ / 20), (int) height_ / 2, (int) width_ / 5, (int) height_ / 20);
            reStart.setBounds((int) (width_ / 2 + width_ / 20), (int) height_ / 2, (int) width_ / 5, (int) height_ / 20);
            quit.setVisible(true);
            reStart.setVisible(true);
            Rectangle grass = new Rectangle(0, (int) (height_ - (height_ / 5)), (int) width_, (int) height_ / 20);
            Rectangle dirt = new Rectangle(0, (int) (height_ - (height_ / 5)), (int) width_, (int) height_ / 5);
            g2d.setColor(Color.orange);
            g2d.fill(dirt);
            g2d.setColor(Color.GREEN);
            g2d.fill(grass);
        }
        else{
            quit.setVisible(false);
            reStart.setVisible(false);
        }
    }

    public boolean resurrectBird = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Quit".equals(e.getActionCommand())){
            System.exit(0);
        }
        else {
            resurrectBird = true;
        }
    }

    

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
