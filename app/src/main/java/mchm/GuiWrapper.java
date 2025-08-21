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
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        JLabel errorLabel  = new JLabel();
        errorLabel.setPreferredSize(new Dimension(400, 75));
        this.add(errorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        JButton submit = new JButton("Calculate");
        submit.setPreferredSize(new Dimension(400, 75));
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
        }

        if(!Main.buildExpression(expression, varName)) return new double[]{0, 0, 2};

        double[] continuityLimits = Main.findLimits(step, lowerLimit, upperLimit);
        if(continuityLimits == null)  return new double[]{0, 0, 3};

        double[] details = Main.findRoot(iterations, 0.000000000001, continuityLimits[1], continuityLimits[0]);

        if(details == null) return new double[]{0, 0, 4};

        return details;
    }
}