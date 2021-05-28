import java.awt.*;

public class Main {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int axisX = screenSize.width;
    private int axisY = screenSize.height;
    private int previousAxisX = axisX;
    private int previousAxisY = axisY;
    private int centerX;
    private int centerY;

    private final Graphics g;
    private final DrawableFrame df;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        df = new DrawableFrame("Graduation Display");
        df.show();
        df.setSize(axisX, axisY);
        g = df.getGraphicsContext();

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, axisX, axisY);

        while (true) {
            axisX = df.getWidth();
            axisY = df.getHeight();
            centerX = axisX / 2;
            centerY = axisY / 2;

            if (previousAxisX != axisX || previousAxisY != axisY) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 0, axisX, axisY);
                previousAxisX = axisX;
                previousAxisY = axisY;
                topRightSize = 0;
                topLeftSize = 0;
                bottomRightCounter = 0;
                line = 0;
            }

            topRight();
            topLeft();
            bottomRight();
            bottomLeft();
        }
    }

    private int[] getRandomColors() {
        int r = (int) (Math.random() * 255 + 1);
        int g = (int) (Math.random() * 255 + 1);
        int b = (int) (Math.random() * 255 + 1);

        return new int[]{r, g, b};
    }

    private int topRightSize;

    private void topRight() {
        int maxBoundariesY = centerY;

        int centerX = this.centerX - centerY / 2;
        int centerY = maxBoundariesY / 2;

        int startPosition = topRightSize / 2;

        int positionX = centerX - startPosition;
        int positionY = centerY + startPosition;
        int[] colors = getRandomColors();

        if (topRightSize >= maxBoundariesY) {
            topRightSize = 1;
        }

        g.setColor(new Color(colors[0], colors[1], colors[2]));

        for (int i = 0; i < topRightSize; i++) {
            g.fillRect(positionX, positionY, 1, 1);
            positionX++;
            df.repaint();
        }

        for (int i = 0; i < topRightSize - 1; i++) {
            g.fillRect(positionX, positionY, 1, 1);
            positionY--;
            df.repaint();
        }

        for (int i = 0; i < topRightSize - 1; i++) {
            g.fillRect(positionX, positionY, 1, 1);
            positionX--;
            df.repaint();
        }

        for (int i = 0; i < topRightSize - 1; i++) {
            g.fillRect(positionX, positionY, 1, 1);
            positionY++;
            df.repaint();
        }

        topRightSize += 2;
    }

    private int topLeftSize;

    private void topLeft() {
        int maxBoundariesX = centerX + centerY;
        int maxBoundariesY = centerY;

        int centerX = (maxBoundariesX + this.centerX) / 2;
        int centerY = maxBoundariesY / 2;

        int startPosition = topLeftSize / 2;

        int positionX = centerX - startPosition;
        int positionY = centerY + startPosition;
        getRandomColors();
        int[] colors;

        if (topLeftSize >= maxBoundariesY) {
            topLeftSize = 1;
        }

        for (int i = 0; i < topLeftSize; i++) {
            colors = getRandomColors();
            g.setColor(new Color(colors[0], colors[1], colors[2]));
            g.fillRect(positionX, positionY, 1, 1);
            positionX++;
            df.repaint();
        }

        for (int i = 0; i < topLeftSize - 1; i++) {
            colors = getRandomColors();
            g.setColor(new Color(colors[0], colors[1], colors[2]));
            g.fillRect(positionX, positionY, 1, 1);
            positionY--;
            df.repaint();
        }

        for (int i = 0; i < topLeftSize - 1; i++) {
            colors = getRandomColors();
            g.setColor(new Color(colors[0], colors[1], colors[2]));
            g.fillRect(positionX, positionY, 1, 1);
            positionX--;
            df.repaint();
        }

        for (int i = 0; i < topLeftSize - 1; i++) {
            colors = getRandomColors();
            g.setColor(new Color(colors[0], colors[1], colors[2]));
            g.fillRect(positionX, positionY, 1, 1);
            positionY++;
            df.repaint();
        }

        topLeftSize += 2;
    }

    int bottomRightCounter;

    private void bottomRight() {

        if (bottomRightCounter == 3000) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(centerX, centerY, centerX + centerY, centerY);
            bottomRightCounter = 0;
        }
        int maximumX = centerX + centerY;

        int[] colors = getRandomColors();
        g.setColor(new Color(colors[0], colors[1], colors[2]));

        int x = (int) (Math.random() * (maximumX - centerX) + centerX);
        int y = (int) (Math.random() * (axisY - centerY) + centerY);

        int height = (int) (Math.random() * 10 + 1);
        int width = (int) (Math.random() * 10 + 1);

        g.fillRect(x, y, width, height);
        df.repaint();
        bottomRightCounter++;
    }

    private int red, green, blue;
    private int line;

    private void bottomLeft() {
        int minimumX = centerX - centerY;

        int line = this.line + centerY;

        if (line == axisY) {
            this.line = 0;
            line = centerY;
        }

        if (blue == 256) {
            blue = 0;
            green++;
        }
        if (green == 256) {
            green = 0;
            red++;
        }
        if (red == 256) {
            red = 0;
        }

        g.setColor(new Color(red, green, blue));
        g.fillRect(minimumX, line, centerX - minimumX, 1);
        df.repaint();
        this.line++;
        blue++;
    }
}
