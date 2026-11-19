import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ColorCanvas extends JPanel {
    // 1. State variable: array of colors and current index
    private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA };
    private int colorIndex = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 2. Use the current color state variable before drawing
        g2d.setColor(colors[colorIndex]);
        g2d.fillOval(120, 50, 120, 120);
    }

    // 3. Method to update state variable and refresh
    public void nextColor() {
        colorIndex = (colorIndex + 1) % colors.length; // Cycles cleanly back to 0 at the end
        repaint();
    }
}

public class ShapeColorChanger extends JFrame {
    private ColorCanvas canvas;
    private JButton btnChangeColor;

    public ShapeColorChanger() {
        super("Shape Color Changer");
        setLayout(new BorderLayout());

        // Add custom canvas in CENTER
        canvas = new ColorCanvas();
        canvas.setBackground(Color.WHITE);
        add(canvas, BorderLayout.CENTER);

        // Add button at SOUTH
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnChangeColor = new JButton("Change Color");
        buttonPanel.add(btnChangeColor);
        add(buttonPanel, BorderLayout.SOUTH);

        // Trigger color update on click
        btnChangeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.nextColor();
            }
        });

        setSize(380, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShapeColorChanger();
    }
}