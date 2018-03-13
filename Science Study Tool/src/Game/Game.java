package Game;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class Game extends JFrame {

    private Panel panel = new Panel();

    public Game() {

        initUI();
    }

    private void initUI() {

        add(panel);
        setResizable(false);
        setSize((int)panel.width_, (int)panel.height_);
        setTitle("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game ex = new Game();
                ex.setVisible(true);
            }
        });
    }
}
