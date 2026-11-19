import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Custom JPanel for drawing shapes
class DrawingPanel extends JPanel {
    // Predefined dimensions for Circle, Rectangle, Oval, and Square
    private int circleSize = 60;
    private int rectWidth = 100, rectHeight = 60;
    private int ovalWidth = 110, ovalHeight = 50;
    private int squareSize = 60;

    private static final int MIN_SIZE = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convert Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Draw Circle (Top-Left quadrant)
        g2d.setColor(Color.RED);
        g2d.fillOval(40, 40, circleSize, circleSize);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Circle", 40, 30);

        // 2. Draw Rectangle (Top-Right quadrant)
        g2d.setColor(Color.BLUE);
        g2d.fillRect(230, 40, rectWidth, rectHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Rectangle", 230, 30);

        // 3. Draw Oval (Bottom-Left quadrant)
        g2d.setColor(Color.GREEN.darker());
        g2d.fillOval(40, 200, ovalWidth, ovalHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Oval", 40, 190);

        // 4. Draw Square (Bottom-Right quadrant)
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(230, 200, squareSize, squareSize);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Square", 230, 190);
    }

    // Increase dimensions by 10 pixels and refresh
    public void growShapes() {
        circleSize += 10;
        rectWidth += 10;
        rectHeight += 10;
        ovalWidth += 10;
        ovalHeight += 10;
        squareSize += 10;
        repaint();
    }

    // Decrease dimensions by 10 pixels (enforcing minimum 20px) and refresh
    public void shrinkShapes() {
        if (circleSize - 10 >= MIN_SIZE) circleSize -= 10;
        if (rectWidth - 10 >= MIN_SIZE) rectWidth -= 10;
        if (rectHeight - 10 >= MIN_SIZE) rectHeight -= 10;
        if (ovalWidth - 10 >= MIN_SIZE) ovalWidth -= 10;
        if (ovalHeight - 10 >= MIN_SIZE) ovalHeight -= 10;
        if (squareSize - 10 >= MIN_SIZE) squareSize -= 10;
        repaint();
    }
}

public class DynamicShapeResizer extends JFrame {
    private DrawingPanel drawingPanel;
    private JButton btnGrow, btnShrink;

    public DynamicShapeResizer() {
        // 1. Create JFrame window with BorderLayout
        super("Dynamic Shape Resizer");
        setLayout(new BorderLayout());

        // 2. Create custom JPanel for drawing and place in CENTER
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);

        // 3. Create control panel for Grow and Shrink buttons at SOUTH
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnGrow = new JButton("Grow");
        btnShrink = new JButton("Shrink");
        buttonPanel.add(btnGrow);
        buttonPanel.add(btnShrink);
        add(buttonPanel, BorderLayout.SOUTH);

        // 4. Register ActionListener with Grow button
        btnGrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.growShapes();
            }
        });

        // 5. Register ActionListener with Shrink button
        btnShrink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.shrinkShapes();
            }
        });

        // 6. Configure and display JFrame
        setSize(480, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DynamicShapeResizer();
    }
}