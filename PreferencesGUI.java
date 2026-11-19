import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PreferencesGUI extends JFrame implements ItemListener {
    private JCheckBox cbMusic, cbSports, cbReading;
    private JLabel lblStatus;

    public PreferencesGUI() {
        // 1. Create JFrame window and set layout
        super("User Preferences");
        setLayout(new FlowLayout());

        // 2. Create multiple JCheckBoxes for preferences
        cbMusic = new JCheckBox("Music");
        cbSports = new JCheckBox("Sports");
        cbReading = new JCheckBox("Reading");

        // 3. Create status JLabel
        lblStatus = new JLabel("Selected preferences: None");

        // 4. Add ItemListener to each checkbox
        cbMusic.addItemListener(this);
        cbSports.addItemListener(this);
        cbReading.addItemListener(this);

        // 5. Add checkboxes and status label to JFrame
        add(cbMusic);
        add(cbSports);
        add(cbReading);
        add(lblStatus);

        // 6. Configure and show JFrame
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 7. Handle selection/deselection dynamically
    @Override
    public void itemStateChanged(ItemEvent e) {
        List<String> selected = new ArrayList<>();

        // Check which checkboxes are currently selected
        if (cbMusic.isSelected()) {
            selected.add(cbMusic.getText());
        }
        if (cbSports.isSelected()) {
            selected.add(cbSports.getText());
        }
        if (cbReading.isSelected()) {
            selected.add(cbReading.getText());
        }

        // Update status JLabel with comma-separated list
        if (selected.isEmpty()) {
            lblStatus.setText("Selected preferences: None");
        } else {
            lblStatus.setText("Selected preferences: " + String.join(", ", selected));
        }
    }

    public static void main(String[] args) {
        new PreferencesGUI();
    }
}