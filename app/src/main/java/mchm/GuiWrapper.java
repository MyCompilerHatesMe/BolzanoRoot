package mchm;

import javax.swing.*;
import java.awt.*;

public class GuiWrapper{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(800, 800));

        frame.add(new Panel());

        frame.setVisible(true);
        frame.setTitle("Bolzano Root");
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}

class Panel extends JPanel{
    Panel(){
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(5, 5, 5, 5);
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;


        gbc.gridx = 0;
        gbc.gridy = 0;
        JTextField functionField = new JTextField("Function");
        functionField.setPreferredSize(new Dimension(200, 50));
        this.add(functionField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField variableField = new JTextField("Variable Name");
        variableField.setPreferredSize(new Dimension(200, 50));
        this.add(variableField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextField iterationsField = new JTextField("Iterations");
        iterationsField.setPreferredSize(new Dimension(200, 50));
        this.add(iterationsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField lowerLimitField = new JTextField("Lower limit");
        lowerLimitField.setPreferredSize(new Dimension(200, 50));
        this.add(lowerLimitField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JTextField upperLimitField = new JTextField("Upper limit");
        upperLimitField.setPreferredSize(new Dimension(200, 50));
        this.add(upperLimitField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JTextField stepField = new JTextField("Step");
        stepField.setPreferredSize(new Dimension(200, 50));
        this.add(stepField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel output1 = new JLabel("Root: 0");
        output1.setPreferredSize(new Dimension(200, 50));
        this.add(output1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel output2 = new JLabel("Iterations: 0");
        output2.setPreferredSize(new Dimension(200, 50));
        this.add(output2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        JButton submit = new JButton("Calculate");
        submit.setPreferredSize(new Dimension(400, 75));
        submit.addActionListener(e -> {
            String expression = functionField.getText();
            String varName = variableField.getText();
            String iterationStr = iterationsField.getText();
            String lowerLimitStr = lowerLimitField.getText();
            String upperLimitStr = upperLimitField.getText();
            String stepStr = stepField.getText();
        });
        this.add(submit, gbc);
    }
}