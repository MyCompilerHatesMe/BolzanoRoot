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
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;

        // Function label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel functionLabel = new JLabel("Function:");
        this.add(functionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField functionField = new JTextField();
        functionField.setPreferredSize(new Dimension(200, 50));
        this.add(functionField, gbc);

        // Variable label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel variableLabel = new JLabel("Variable Name:");
        this.add(variableLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField variableField = new JTextField();
        variableField.setPreferredSize(new Dimension(200, 50));
        this.add(variableField, gbc);

        // Iterations label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel iterationsLabel = new JLabel("Iterations:");
        this.add(iterationsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField iterationsField = new JTextField();
        iterationsField.setPreferredSize(new Dimension(200, 50));
        this.add(iterationsField, gbc);

        // Lower limit label and field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lowerLimitLabel = new JLabel("Lower Limit:");
        this.add(lowerLimitLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField lowerLimitField = new JTextField();
        lowerLimitField.setPreferredSize(new Dimension(200, 50));
        this.add(lowerLimitField, gbc);

        // Upper limit label and field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel upperLimitLabel = new JLabel("Upper Limit:");
        this.add(upperLimitLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField upperLimitField = new JTextField();
        upperLimitField.setPreferredSize(new Dimension(200, 50));
        this.add(upperLimitField, gbc);

        // Step label and field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel stepLabel = new JLabel("Step:");
        this.add(stepLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField stepField = new JTextField();
        stepField.setPreferredSize(new Dimension(200, 50));
        this.add(stepField, gbc);

        // Output labels
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel output1 = new JLabel("Root: 0");
        output1.setPreferredSize(new Dimension(200, 50));
        this.add(output1, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        JLabel output2 = new JLabel("Iterations: 0");
        output2.setPreferredSize(new Dimension(200, 50));
        this.add(output2, gbc);

        // Error label
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        JLabel errorLabel = new JLabel();
        errorLabel.setPreferredSize(new Dimension(600, 75));
        this.add(errorLabel, gbc);

        // Submit button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        JButton submit = new JButton("Calculate");
        submit.setPreferredSize(new Dimension(600, 75));
        submit.addActionListener(e -> {
            String expression = functionField.getText();
            String varNameStr = variableField.getText();
            String iterationStr = iterationsField.getText();
            String lowerLimitStr = lowerLimitField.getText();
            String upperLimitStr = upperLimitField.getText();
            String stepStr = stepField.getText();

            double[] results = calculateRoots(expression, varNameStr, iterationStr, lowerLimitStr, upperLimitStr, stepStr);

            if(results.length == 3){
                if(results[2] == 1) errorLabel.setText("Input error");
                if(results[2] == 2) errorLabel.setText("Expression is incorrect");
                if(results[2] == 3) errorLabel.setText("No continuous region found between " + lowerLimitStr + " " + upperLimitStr);
                if(results[2] == 4) errorLabel.setText("Error occurred in finding roots");
                if(results[2] == 5) errorLabel.setText("Please input a variable");
            }else{
                output1.setText(String.format("Roots: %.16f", results[0]));
                output2.setText("Iterations: " + (int) results[1]);
                errorLabel.setText("");
            }
        });
        this.add(submit, gbc);
    }
    public double[] calculateRoots(String expression, String varNameStr, String iterationStr, String lowerLimitStr, String upperLimitStr, String stepStr){

        char varName;
        int iterations;
        double lowerLimit, upperLimit, step;

        try{
            varName = varNameStr.charAt(0);
            if(varNameStr.length() > 1) throw new NumberFormatException();
            iterations = Integer.parseInt(iterationStr);
            lowerLimit = Double.parseDouble(lowerLimitStr);
            upperLimit = Double.parseDouble(upperLimitStr);
            step = Double.parseDouble(stepStr);
        }catch(NumberFormatException e){
            return new double[]{0, 0, 1};
        }catch(StringIndexOutOfBoundsException e){
            return new double[]{0, 0, 5};
        }

        if(!Main.buildExpression(expression, varName)) return new double[]{0, 0, 2};

        double[] continuityLimits = Main.findLimits(step, lowerLimit, upperLimit);
        if(continuityLimits == null)  return new double[]{0, 0, 3};

        double[] details = Main.findRoot(iterations, 0.0000000000001, continuityLimits[1], continuityLimits[0]);

        if(details == null) return new double[]{0, 0, 4};

        return details;
    }
}