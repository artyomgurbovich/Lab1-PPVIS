import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Labwork 1 for discipline "Designing programs in intelligent systems".
 *
 * @version 1.0 
 * @author Artyom Gurbovich
 */
public class Lab1 {
    static final int TEXT_FIELD_SIZE = 20;
    static final int VERTICAL_STRUT_SIZE = 5;
    
    public static JPanel createPanel(String name) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setColumns(TEXT_FIELD_SIZE);
        return textField;
    }

    public static JTable createEmptyTable(int columnCount) {
        JTable table = new JTable(0, columnCount);
        // Only one row can be selected at one time
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    public static void displayErrorMessage(String errorText) {
        JOptionPane.showMessageDialog(new JFrame(), errorText, "Error",
                                      JOptionPane.ERROR_MESSAGE);
    }

    public static void addVerticalStrut(JPanel panel) {
        panel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
    }

    private static JPanel createFirstPanel() {
        JPanel firstPanel = createPanel("First panel");
        JTextField textField = createTextField();
        JButton addToComboBoxButton = new JButton("Add to combo box");
        JComboBox<String> comboBox = new JComboBox<String>();

        addToComboBoxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                Boolean isExist = false;
                int itemCount = comboBox.getItemCount();

                for (int i = 0; i < itemCount; i++) {
                    if (currentText.equals(comboBox.getItemAt(i))) {
                        isExist = true;
                        displayErrorMessage("Value already exists!");
                    }
                }

                if (!isExist) {
                    comboBox.addItem(currentText);
                }
            }
        });

        addVerticalStrut(firstPanel);
        firstPanel.add(textField);
        addVerticalStrut(firstPanel);
        firstPanel.add(comboBox);
        addVerticalStrut(firstPanel);
        firstPanel.add(addToComboBoxButton);
        addVerticalStrut(firstPanel);
        return firstPanel;
    }

    private static JPanel createSecondPanel() {
        JPanel secondPanel = createPanel("Second panel");
        JTextField textField = createTextField();
        JButton renameButton = new JButton("Rename bottom button");
        JButton swapButton = new JButton("Swap buttons text");

        renameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swapButton.setText(textField.getText());
            }
        });

        swapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String swapButtonText = swapButton.getText();
                swapButton.setText(renameButton.getText());
                renameButton.setText(swapButtonText);
            }
        });

        addVerticalStrut(secondPanel);
        secondPanel.add(textField);
        addVerticalStrut(secondPanel);
        secondPanel.add(renameButton);
        addVerticalStrut(secondPanel);
        secondPanel.add(swapButton);
        addVerticalStrut(secondPanel);
        return secondPanel;
    }

    private static JPanel createThirdPanel() {
        JPanel thirdPanel = createPanel("Third panel");
        JTextField textField = createTextField();
        JButton selectButton = new JButton("Select by name");
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton firstRadioButton = new JRadioButton("First", true);
        JRadioButton secondRadioButton = new JRadioButton("Second", false);
        JRadioButton thirdRadioButton = new JRadioButton("Third", false);

        buttonGroup.add(firstRadioButton);
        buttonGroup.add(secondRadioButton);
        buttonGroup.add(thirdRadioButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                if (currentText.equals("First")) {
                    firstRadioButton.setSelected(true);
                }
                else if (currentText.equals("Second")) {
                    secondRadioButton.setSelected(true);
                }
                else if (currentText.equals("Third")) {
                    thirdRadioButton.setSelected(true);
                }
                else {
                    displayErrorMessage("Wrong radio button name!");
                }
            }
        });

        addVerticalStrut(thirdPanel);
        thirdPanel.add(textField);
        addVerticalStrut(thirdPanel);
        thirdPanel.add(selectButton);
        addVerticalStrut(thirdPanel);
        thirdPanel.add(firstRadioButton);
        addVerticalStrut(thirdPanel);
        thirdPanel.add(secondRadioButton);
        addVerticalStrut(thirdPanel);
        thirdPanel.add(thirdRadioButton);
        addVerticalStrut(thirdPanel);
        return thirdPanel;
    }

    private static JPanel createFourthPanel() {
        JPanel fourthPanel = createPanel("Fourth panel");
        JTextField textField = createTextField();
        JButton selectButton = new JButton("Select by name");
        JCheckBox firstCheckBox = new JCheckBox("First");
        JCheckBox secondCheckBox = new JCheckBox("Second");
        JCheckBox thirdCheckBox = new JCheckBox("Third");

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                if (currentText.equals("First")) {
                    firstCheckBox.setSelected(true);
                }
                else if (currentText.equals("Second")) {
                    secondCheckBox.setSelected(true);
                }
                else if (currentText.equals("Third")) {
                    thirdCheckBox.setSelected(true);
                }
                else {
                    displayErrorMessage("Wrong check box name!");
                }
            }
        });

        addVerticalStrut(fourthPanel);
        fourthPanel.add(textField);
        addVerticalStrut(fourthPanel);
        fourthPanel.add(selectButton);
        addVerticalStrut(fourthPanel);
        fourthPanel.add(firstCheckBox);
        addVerticalStrut(fourthPanel);
        fourthPanel.add(secondCheckBox);
        addVerticalStrut(fourthPanel);
        fourthPanel.add(thirdCheckBox);
        addVerticalStrut(fourthPanel);
        return fourthPanel;
    }

    private static JPanel createFifthPanel(JFrame frame) {
        JPanel fifthPanel = createPanel("Fifth panel");
        JTextField textField = createTextField();
        JButton addButton = new JButton("Add to first column");
        JButton moveFirstButton = new JButton("Move first column to second");
        JButton moveSecondButton = new JButton("Move second column to first");
        JTable table = createEmptyTable(2);
        // Convert TableModel to DefaultTableModel
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                model.addRow(new String[]{currentText, ""});
                if (model.getRowCount() == 1) {
                    // Select first row
                    table.setRowSelectionInterval(0, 0);
                }
                // Rewrite frame for new row
                frame.pack();
            }
        });

        moveFirstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() > 0) {
                    int rowIndex = table.getSelectedRow();
                    table.setValueAt(model.getValueAt(rowIndex, 0),
                                     rowIndex, 1);
                    table.setValueAt("", rowIndex, 0);
                }
            }
        });

        moveSecondButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() > 0) {
                    int rowIndex = table.getSelectedRow();
                    table.setValueAt(model.getValueAt(rowIndex, 1),
                                     rowIndex, 0);
                    table.setValueAt("", rowIndex, 1);
                }
            }
        });

        addVerticalStrut(fifthPanel);
        fifthPanel.add(textField);
        addVerticalStrut(fifthPanel);
        fifthPanel.add(addButton);
        addVerticalStrut(fifthPanel);
        fifthPanel.add(moveFirstButton);
        addVerticalStrut(fifthPanel);
        fifthPanel.add(moveSecondButton);
        addVerticalStrut(fifthPanel);
        fifthPanel.add(table);
        addVerticalStrut(fifthPanel);
        return fifthPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab1");
        JPanel mainPanel = createPanel("Group box");

        // Exit program when window will be closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        mainPanel.add(createFirstPanel());
        mainPanel.add(createSecondPanel());
        mainPanel.add(createThirdPanel());
        mainPanel.add(createFourthPanel());
        mainPanel.add(createFifthPanel(frame));
        frame.pack();
        frame.setVisible(true);
    }
}