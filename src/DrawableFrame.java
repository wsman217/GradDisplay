import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawableFrame extends Frame {
    private Image imageBuffer = null;
    private Insets insets;

    public DrawableFrame(String title) {
        super(title);
        this.addWindowListener(new WindowCloser());
    }

    public void paint(Graphics g) {
        if (this.imageBuffer != null) {
            g.drawImage(this.imageBuffer, this.insets.left, this.insets.top, null);
        }
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    public void setSize(int width, int height) {
        this.insets = this.getInsets();
        super.setSize(width + this.insets.left + this.insets.right, height + this.insets.top + this.insets.bottom);
        this.imageBuffer = this.createImage(width, height);
    }

    public Graphics getGraphicsContext() {
        return this.imageBuffer.getGraphics();
    }

    static class WindowCloser extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            System.exit(0);
        }
    }
}
