import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class QuadraticEquation {
    private double a, b, c;
    private List<Double> solution;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.solution = null;
    }

    public static class Discriminant {
        public static double getDiscriminant(double a, double b, double c) {
            return b * b - 4 * a * c;
        }
    }

    // checking for equality to 0. With a comparison with the eps = 10e-12.
    public boolean isZero(double x) {
        double eps = 10e-12;
        return abs(x) < eps;
    }

    public void getInfo() {
        if (a != 0 || b != 0) {
            System.out.print("Original equation:\n");
            if (a != 0) {
                System.out.print(a + " x^2");
            }
            if (b < 0) {
                System.out.print(" - " + abs(b) + " x");
            } else if (b > 0) {
                System.out.print(" + " + abs(b) + " x");
            }
            if (c < 0) {
                System.out.print(" - " + abs(c));
            } else if (c > 0) {
                System.out.print(" + " + abs(c));
            }
            System.out.println(" = 0");
        }
        if (solution == null) {
            System.out.println("The system has not been resolved yet.(Please run getSolve first)");
            return;
        }
        if (solution.isEmpty()) {
            System.out.println("There aren't roots (or real roots)");
        } else if (solution.size() == 1) {
            System.out.println("Solution:\nx = " + solution.get(0));
        } else if (solution.size() == 2) {
            System.out.println("Solution:\nx1 = " + solution.get(0) + "\nx2 = " + solution.get(1));
        }
        ;
    }

    public void getSolve() {
        if (solution != null) {
            return;
        }   // If current equation has already been solved there is no need to solve it again
        double tmp = 0.;
        solution = new ArrayList<>();
        if (isZero(a)) a = 0.;
        if (isZero(b)) b = 0.;
        if (isZero(c)) c = 0.;
        if (a == 0. && b == 0.) {
            return;
        } else if (a == 0.) {
            tmp = -c / b;
            if (isZero(tmp)) tmp = 0.;
            solution.add(tmp);
            return;
        }
        double d = Discriminant.getDiscriminant(a, b, c);
        if (isZero(d)) d = 0.;

        if (d == 0.) {
            tmp = -b / (2. * a);
            if (isZero(tmp)) tmp = 0.;
            solution.add(tmp);
        } else if (d > 0.) {
            tmp = (-b + sqrt(d)) / (2. * a);
            if (isZero(tmp)) tmp = 0.;
            solution.add(tmp);
            tmp = (-b - sqrt(d)) / (2. * a);
            if (isZero(tmp)) tmp = 0.;
            solution.add(tmp);
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        double a = 0., b = 0., c = 0.;
        try {
            System.out.print("Input a, b, c: ");
            a = in.nextDouble();
            b = in.nextDouble();
            c = in.nextDouble();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        QuadraticEquation equ1 = new QuadraticEquation(a, b, c);
        equ1.getSolve();
        equ1.getInfo();
    }
}
