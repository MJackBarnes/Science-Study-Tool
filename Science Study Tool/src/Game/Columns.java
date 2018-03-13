package Game;

import java.awt.*;
import java.util.ArrayList;

import static Game.Panel.SPEED;
import static Game.Panel.height_;
import static Game.Panel.width_;

public class Columns {
    public ArrayList<Rectangle> columns = new ArrayList<Rectangle>();
    public int columnwidth = 100;

    public void reset(){
        columns.clear();
    }

    public void addColumn(boolean start){
        int space = 300;
        int width = 100;
        int height = 50 + (int)(Math.random() * 300);

        if (start)
        {
            columns.add(new Rectangle((int)width_ + width + (columns.size() * 300), (int)height_ - height - 120, width, height));
            columns.add(new Rectangle((int)width_ + width + (columns.size() - 1) * 300, 0, width, (int)height_ - height - space));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, (int)height_ - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, (int)height_ - height - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column){
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void moveColumn(){
        for (int i = 0; i < columns.size(); i++)
        {
        Rectangle column = columns.get(i);

        column.x -= SPEED;
        }



        for (int i = 0; i < columns.size(); i++)
        {
            Rectangle column = columns.get(i);

            if (column.x + column.width < 0)
            {
                columns.remove(column);

                if (column.y == 0)
                {
                    addColumn(false);
                }
            }
        }}
}
