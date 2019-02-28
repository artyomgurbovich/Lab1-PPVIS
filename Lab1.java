import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Labwork 1 for discipline "Designing programs in intelligent systems".
 *
 * @version 1.2
 * @author Artyom Gurbovich
 */
public class Lab1 {
    private final int TEXT_FIELD_SIZE = 20;

    private final int VERTICAL_STRUT_SIZE = 5;

    private final String[] NUMBERS_FOR_RADIOBUTTONS = {"1", "2", "3"};

    private final String[] NUMBERS_FOR_CHECKBUTTONS = {"1", "2", "3"};

    private final String[] TABLE_HEADER = {"First", "Second"};

    private final ImageIcon DEFAULT_IMAGE = new ImageIcon("redHeart.jpg");

    private final ImageIcon ROLLOVER_IMAGE = new ImageIcon("greenHeart.jpg");
    
    public JPanel createPanel(String name) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setColumns(TEXT_FIELD_SIZE);
        return textField;
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setIcon(DEFAULT_IMAGE);
        button.setRolloverIcon(ROLLOVER_IMAGE);
        button.setPressedIcon(ROLLOVER_IMAGE);
        button.setForeground(Color.white);
        return button;
    }

    public JTable createEmptyTable(String[] header) {
        JTable table = new JTable(0, header.length);
        for (int i = 0; i < header.length; i++) {
            table.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(header[i]);
        }
        // Only one row can be selected at one time
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    public void displayErrorMessage(String errorText) {
        JOptionPane.showMessageDialog(new JFrame(), errorText, "Error",
                                      JOptionPane.ERROR_MESSAGE);
    }

    public void addVerticalStrut(JPanel panel) {
        panel.add(Box.createVerticalStrut(VERTICAL_STRUT_SIZE));
    }

    public void moveColumn(JTable table, DefaultTableModel model, int initPosition) {
        if (model.getRowCount() > 0) {
            int rowIndex = table.getSelectedRow();
            String value = (String) model.getValueAt(rowIndex, initPosition);
            if (!value.equals("")) {
                table.setValueAt(value, rowIndex, 1 - initPosition);
                table.setValueAt("", rowIndex, initPosition);
            }
        }
    }

    public JPanel createFirstPanel() {
        JPanel firstPanel = createPanel("First panel");
        JTextField textField = createTextField();
        JButton addToComboBoxButton = createButton("Add to combo box");
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

    public JPanel createSecondPanel() {
        JPanel secondPanel = createPanel("Second panel");
        JTextField textField = createTextField();
        JButton renameButton = createButton("Rename bottom button");
        JButton swapButton = createButton("Swap buttons text");

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

    public JPanel createThirdPanel() {
        JPanel thirdPanel = createPanel("Third panel");
        JTextField textField = createTextField();
        JButton selectButton = createButton("Select radio button by name");
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton firstRadioButton = new JRadioButton(NUMBERS_FOR_RADIOBUTTONS[0], true);
        JRadioButton secondRadioButton = new JRadioButton(NUMBERS_FOR_RADIOBUTTONS[1], false);
        JRadioButton thirdRadioButton = new JRadioButton(NUMBERS_FOR_RADIOBUTTONS[2], false);

        buttonGroup.add(firstRadioButton);
        buttonGroup.add(secondRadioButton);
        buttonGroup.add(thirdRadioButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                if (currentText.equals(NUMBERS_FOR_RADIOBUTTONS[0])) {
                    firstRadioButton.setSelected(true);
                } else if (currentText.equals(NUMBERS_FOR_RADIOBUTTONS[1])) {
                    secondRadioButton.setSelected(true);
                } else if (currentText.equals(NUMBERS_FOR_RADIOBUTTONS[2])) {
                    thirdRadioButton.setSelected(true);
                } else {
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

    public JPanel createFourthPanel() {
        JPanel fourthPanel = createPanel("Fourth panel");
        JTextField textField = createTextField();
        JButton selectButton = createButton("Select check box by name");
        JCheckBox firstCheckBox = new JCheckBox(NUMBERS_FOR_CHECKBUTTONS[0]);
        JCheckBox secondCheckBox = new JCheckBox(NUMBERS_FOR_CHECKBUTTONS[1]);
        JCheckBox thirdCheckBox = new JCheckBox(NUMBERS_FOR_CHECKBUTTONS[2]);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                if (currentText.equals(NUMBERS_FOR_CHECKBUTTONS[0])) {
                    firstCheckBox.setSelected(!firstCheckBox.isSelected());
                } else if (currentText.equals(NUMBERS_FOR_CHECKBUTTONS[1])) {
                    secondCheckBox.setSelected(!secondCheckBox.isSelected());
                } else if (currentText.equals(NUMBERS_FOR_CHECKBUTTONS[2])) {
                    thirdCheckBox.setSelected(!thirdCheckBox.isSelected());
                } else {
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

    public JPanel createFifthPanel() {
        JPanel fifthPanel = createPanel("Fifth panel");
        JTextField textField = createTextField();
        JButton addButton = createButton("Add to first column");
        JButton moveFirstButton = createButton("Move first column to second");
        JButton moveSecondButton = createButton("Move second column to first");
        JTable table = createEmptyTable(TABLE_HEADER);
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
            }
        });

        moveFirstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveColumn(table, model, 0);
            }
        });

        moveSecondButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveColumn(table, model, 1);
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
        fifthPanel.add(new JScrollPane(table));
        addVerticalStrut(fifthPanel);
        return fifthPanel;
    }

    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        JFrame frame = new JFrame("Lab1");
        JPanel mainPanel = lab1.createPanel("Group box");

        // Exit program when window will be closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        mainPanel.add(lab1.createFirstPanel());
        mainPanel.add(lab1.createSecondPanel());
        mainPanel.add(lab1.createThirdPanel());
        mainPanel.add(lab1.createFourthPanel());
        mainPanel.add(lab1.createFifthPanel());
        frame.pack();
        frame.setVisible(true);
    }
}