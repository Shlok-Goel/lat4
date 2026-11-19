import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MovingShapesPanel extends JPanel {
    // Initial horizontal positions (x-coordinates) of the two circles
    private int leftCircleX = 60;
    private int rightCircleX = 320;

    // Constant vertical position and diameter
    private static final int Y_POS = 100;
    private static final int DIAMETER = 60;
    private static final int STEP = 15;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Left Circle
        g2d.setColor(Color.BLUE);
        g2d.fillOval(leftCircleX, Y_POS, DIAMETER, DIAMETER);

        // Draw Right Circle
        g2d.setColor(Color.RED);
        g2d.fillOval(rightCircleX, Y_POS, DIAMETER, DIAMETER);
    }

    // Move circles closer toward the center without overlapping
    public void moveCloser() {
        // Right edge of left circle is (leftCircleX + DIAMETER)
        // Left edge of right circle is rightCircleX
        if ((leftCircleX + DIAMETER + STEP) <= (rightCircleX - STEP)) {
            leftCircleX += STEP;
            rightCircleX -= STEP;
            repaint();
        }
    }

    // Move circles further apart without going outside the panel boundaries
    public void moveFurther() {
        int panelWidth = getWidth();

        // Check left boundary (0) and right boundary (panelWidth)
        if ((leftCircleX - STEP >= 0) && (rightCircleX + DIAMETER + STEP <= panelWidth)) {
            leftCircleX -= STEP;
            rightCircleX += STEP;
            repaint();
        }
    }
}

public class ShapeDistanceController extends JFrame {
    private MovingShapesPanel canvas;
    private JButton btnCloser, btnFurther;

    public ShapeDistanceController() {
        super("Shape Distance & Boundary Demo");
        setLayout(new BorderLayout());

        // 1. Add custom drawing canvas in the center
        canvas = new MovingShapesPanel();
        canvas.setBackground(Color.WHITE);
        add(canvas, BorderLayout.CENTER);

        // 2. Add control buttons at the bottom
        JPanel controlPanel = new JPanel(new FlowLayout());
        btnCloser = new JButton("Closer");
        btnFurther = new JButton("Further");
        controlPanel.add(btnCloser);
        controlPanel.add(btnFurther);
        add(controlPanel, BorderLayout.SOUTH);

        // 3. Register ActionListeners
        btnCloser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.moveCloser();
            }
        });

        btnFurther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.moveFurther();
            }
        });

        // 4. Configure JFrame
        setSize(460, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShapeDistanceController();
    }
}