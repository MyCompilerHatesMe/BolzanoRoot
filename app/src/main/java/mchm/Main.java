package mchm;

import java.util.InputMismatchException;
import java.util.Scanner;
import net.objecthunter.exp4j.*;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

public class Main {

    static String expression = "";
    static Expression expr;
    static char varName = 'x';

    public static void main(String[] args) {
        int iterations = 0;
        double tolerance = 0, step = 0, start = 0, end = 0;
        double lower, upper;

        // calc f(a), f(b)
        // if f(a)*f(b) <= 0 apply theorem
        // if f(a+b/2) = 0 , found root
        // if f(a) * f(m) <= 0 change the b -> m
        // else set a -> b;

        Scanner sc = new Scanner(System.in);

        boolean allAnswersValid = false;

        while (!allAnswersValid) {
            try {
                System.out.print("Input number of iterations: ");
                iterations = sc.nextInt();

                System.out.print("Input lower limit to locate continuity: ");
                start = sc.nextDouble();

                System.out.print("Input upper limit to locate continuity: ");
                end = sc.nextDouble();

                if(end <= start) throw new IllegalArgumentException("End limit must be greater start");

                System.out.print("Input step value: ");
                step = sc.nextDouble();
                if(step <= 0) throw new IllegalArgumentException("Step value must be greater than 0");

                sc.nextLine();
                System.out.print("Input the function: ");
                expression = sc.nextLine();

                System.out.print("Input the variable name: ");
                varName = sc.next().charAt(0);

                allAnswersValid = true; // everything read successfully
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values.");
                sc.nextLine(); // clear the invalid token so scanner doesn’t loop infinitely
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            expr = new ExpressionBuilder(expression).variables(String.valueOf(varName)).build();
        } catch (UnknownFunctionOrVariableException e){
            System.out.println(e.getMessage());
            return;
        }


        double[] rangeArr = findLimits(step, start, end);

        if(rangeArr == null){
            System.out.printf("No continuity in range %f, %f", start, step);
            return;
        }
        lower = rangeArr[0];
        upper = rangeArr[1];

        double[] details = findRoot(iterations, tolerance, upper, lower);
        if (details == null){
            System.out.println("Some error occurred");
            return;
        }
        System.out.println("Root: " + details[0]);
        System.out.println("Iterations: " + details[1]);
    }

    static double[] findRoot(int iterations, double tolerance, double upper, double lower){
        for(int i = 0; i < iterations; i++) {
            double mid = (lower+upper)/2;
            double fLower = evaluate(lower);
            double fMid = evaluate(mid);
            if(Math.abs(fMid) <= tolerance){
                return new double[] {mid, i};
            }
            else{
                if(fLower * fMid < 0) upper = mid;
                else lower = mid;
            }
        }
        return null; //on failure
    }


    private static double evaluate(double f){
        expr.setVariable(String.valueOf(varName), f);
        return expr.evaluate();
    }
    private static double[] findLimits(double step, double start, double end){

        double prev = start;
        double fPrev = evaluate(start);

        for(double i = start; i <= end; i+=step){
            double fCurr = evaluate(i);
            if(fPrev*fCurr < 0) return new double[]{prev, i};
            prev = i;
            fPrev = fCurr;
        }
        return null;
    }
}
