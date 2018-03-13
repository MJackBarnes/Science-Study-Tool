package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Panel extends JPanel implements ActionListener{

    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 10;
    public static final int SPEED = 2;
    public static double width_ = 950;
    public static double height_ = 700;
    public boolean gameOver = false;
    public boolean lookingAtImage = false;
    public boolean newGame = true;
    public int image = 0;


    public Columns c = new Columns();
    public Image igneous;
    public ArrayList<Image> images = new ArrayList<Image>();
    private Timer timer;
    public Keyboard key = new Keyboard();
    public Bird bird = new Bird(width_/2, height_/2 - height_/10, width_, height_, c, this);
    public ButtonListener button = new ButtonListener(key, this);

    public Panel(){
        initPanel();
    }

    public void initPanel(){
        setBackground(Color.cyan);
        button.init(this);
        loadImage("src/Game/Igneous.png", 0);
        loadImage("src/Game/Metamorphic.jpg", 1);
        loadImage("src/Game/Sedimentary.jpg", 2);
        setDoubleBuffered(true);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void loadImage(String fileLocation, int index){
        ImageIcon ii = new ImageIcon(fileLocation);
        images.add(index, ii.getImage());

    }

    public void drawImage(Graphics g, ArrayList<Image> images, int index){
        g.drawImage(images.get(index), 0, 0, this);
        button.gameOver(g, this, false);
    }

    public void drawGround(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle grass = new Rectangle(0, (int)(height_-(height_/5)), (int)width_, (int)height_/20);
        Rectangle dirt = new Rectangle(0, (int)(height_-(height_/5)), (int)width_, (int)height_/5);
        g2d.setColor(Color.orange);
        g2d.fill(dirt);
        g2d.setColor(Color.GREEN);
        g2d.fill(grass);
    }

    public void drawNewGame(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(button.font);
        g2d.setColor(Color.red);
        g2d.drawString("New Game", (int) width_ / 2 - (int) width_ / 20 - (int)width_/20, (int) height_ / 2 - 30);
        g2d.setFont(button.font);
        g2d.drawString("Press Space To Start", (int) width_ / 2 - (int) width_ / 20 - (int)width_/10, (int) height_ / 2);
        drawGround(g);
    }

    public void nextImage(){
        if (image < 2  ){
            image += 1;
        }
        else {
            image = 0;
        }
    }

    public void draw(Graphics g){
        if (!newGame) {
            if (!gameOver) {
                bird.drawBird(g);
                for (int i = 0; i < c.columns.size(); i++) {
                    Rectangle rect = c.columns.get(i);
                    c.paintColumn(g, rect);
                }

                drawGround(g);
            } else if (button.resurrectBird) {
                lookingAtImage = true;

                if (lookingAtImage && !(key.keyCode == KeyEvent.VK_SPACE) && !key.isPressed) {
                    drawImage(g, images, image);
                } else {
                    nextImage();
                    lookingAtImage = false;
                    gameOver = false;
                    button.resurrectBird = false;
                    bird.resurect(c, this);
                    button.gameOver(g, this, false);
                    onStart();
                }
            } else {
                button.gameOver(g, this, true);
            }
        }
        else {
            if (key.isPressed && key.keyCode == KeyEvent.VK_SPACE){
                newGame = false;
            }
            else {
                drawNewGame(g);
            }
        }
    }

    public void onStart(){
        c.addColumn(true);
        c.addColumn(true);
        c.addColumn(true);
        c.addColumn(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!bird.alive){
            gameOver = true;
        }
        if (!gameOver && !newGame){
            bird.move(c, key);
        }
        c.moveColumn();
        repaint();

    }
    
}