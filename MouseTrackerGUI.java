import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseTrackerGUI extends JFrame implements MouseMotionListener {
    private JLabel lblCoords;

    public MouseTrackerGUI() {
        // 1. Create JFrame window & set layout
        super("Mouse Motion Tracker");
        setLayout(new FlowLayout());

        // Optional: Label to also display coordinates live inside the window
        lblCoords = new JLabel("Move or drag the mouse inside this window");
        add(lblCoords);

        // 2. Register MouseMotionListener with JFrame
        addMouseMotionListener(this);

        // 3. Configure and display JFrame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 4. On mouseDragged event
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String msg = "Mouse dragged at (" + x + "," + y + ")";
        
        System.out.println(msg);
        lblCoords.setText(msg);
    }

    // 5. On mouseMoved event
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String msg = "Mouse moved at (" + x + "," + y + ")";
        
        System.out.println(msg);
        lblCoords.setText(msg);
    }

    public static void main(String[] args) {
        new MouseTrackerGUI();
    }
}