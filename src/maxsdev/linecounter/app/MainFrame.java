package maxsdev.linecounter.app;

import javax.swing.*;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame{

    private JTextField textField;
    private JButton button;
    private JPanel rootPanel;
    private JTextPane infoPane;

    public MainFrame() {
        super("Line Counter");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button.addActionListener(e -> {
            Counter counter = new Counter();
            try {
                int count = counter.getLinesCount(textField.getText());
                infoPane.setText("Lines count: " + count);
            } catch (FileNotFoundException ex){
                infoPane.setText(ex.getMessage());
            }
        });

        setVisible(true);
    }

}
