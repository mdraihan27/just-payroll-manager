package ui;

// Source - https://stackoverflow.com/a
// Posted by Lalchand, modified by community. See post 'Timeline' for change history
// Retrieved 2026-01-28, License - CC BY-SA 3.0

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        g.setColor(new Color(139, 174, 102));
    }
}
