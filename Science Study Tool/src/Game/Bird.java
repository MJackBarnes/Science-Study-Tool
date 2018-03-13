package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import static Game.Panel.height_;
import static Game.Panel.width_;

public class Bird {

    public double x;
    public double y;
    public double initx;
    public double inity;
    public final double GRAVITY = 3;
    public final double JUMP = height_/100;
    public boolean alive = true;


    public Bird(double startx, double starty, double width, double height, Columns clm, Panel panel){
        x = startx - (width/10)/2;
        y =  starty - (width/10) - height/10;
        initx = x;
        inity = y;
        resurect(clm, panel);
        clm.addColumn(true);
        clm.addColumn(true);
        clm.addColumn(true);
        clm.addColumn(true);
    }

    public void jump(){
        y = y - JUMP;
    }

    public void fall(){
        y = y + GRAVITY;
    }
    public boolean isVisible(){
        if (((x < 0) || (x > width_) || (y < 0) || (y > (height_ - height_/5)))){
            return false;
        }
        else {return true;}
    }

    public void move(Columns clm, Keyboard key){
        if ((key.isPressed && key.keyCode == KeyEvent.VK_ESCAPE) || !isVisible() || isTouchingColumn(clm)){
            alive = false;
        } else if (key.isPressed && key.keyCode == KeyEvent.VK_SPACE){
            jump();
        } else {
            fall();
        }
    }

    public void resurect(Columns clm, Panel panel){
        alive = true;
        x = initx;
        y = inity;
        clm.reset();
        panel.newGame = true;
    }

    public void drawBird(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.black);
        Ellipse2D sprite = new Ellipse2D.Double((int) x, (int) y, width_/30, width_/30);
        g2d.draw(sprite);
        g2d.setColor(Color.red);
        g2d.fill(sprite);
    }

    public boolean isTouchingColumn(Columns clm){
        boolean ded = false;
        for (int i = 0; i < clm.columns.size(); i ++){
            Rectangle r = clm.columns.get(i);
            if (r.intersects((int)x, (int)y, width_/30, width_/30)){
                ded = true;
            }
        }
        return ded;
    }

}
