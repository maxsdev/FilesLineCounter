package maxsdev.linecounter.app;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.HashMap;

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
            infoPane.setText("");
            try {
                int count = counter.getLinesCount(textField.getText());
                HashMap<String, Integer> map = counter.getMap();
                for(String key : map.keySet()){
                    infoPane.setText(infoPane.getText() + key + ": " + map.get(key) + "\n");
                }
                infoPane.setText(infoPane.getText() + "Lines count: " + count);
            } catch (FileNotFoundException ex){
                infoPane.setText(ex.getMessage());
            }
        });

        setVisible(true);
    }

}
