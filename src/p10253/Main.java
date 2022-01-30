package p10253;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static class Fraction {
        public BigInteger numerator;
        public BigInteger denominator;

        public Fraction(int numerator, int denominator) {
            this.numerator = new BigInteger(String.valueOf(numerator));
            this.denominator = new BigInteger(String.valueOf(denominator));
        }

        public Fraction(BigInteger numerator, BigInteger denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public void subtract(Fraction other) {
            BigInteger de = denominator.multiply(other.denominator);
            BigInteger t = numerator.multiply(other.denominator);
            BigInteger u = other.numerator.multiply(denominator);
            this.numerator = t.subtract(u);
            this.denominator = de;
        }

        /**
         * GE : Greater and Equal
         * @param other
         * @return
         * other 보다 크거나 같으면 true, otherwise false
         */
        public boolean isGE(Fraction other) {
            BigInteger t = numerator.multiply(other.denominator);
            BigInteger u = other.numerator.multiply(denominator);
            BigInteger v = t.subtract(u);
            if(v.signum() == 0 || v.signum() == 1) return true;
            else return false;
        }

        public void reduce() {
            BigInteger common = gcd(numerator, denominator);

            numerator = numerator.divide(common);
            denominator = denominator.divide(common);
        }
    }

    private static BigInteger solve(Fraction fraction) {
        fraction.reduce();
        if(fraction.numerator.equals(BigInteger.ONE)) return fraction.denominator;

        BigInteger d = BigInteger.TWO;

        while(true) {
            Fraction t = new Fraction(BigInteger.ONE, d);
            if(fraction.isGE(t)) {
                fraction.subtract(t);
                fraction.reduce();

                if(fraction.numerator.equals(BigInteger.ONE)) return fraction.denominator;
            }
            d = d.add(BigInteger.ONE);
        }
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        if(b.equals(BigInteger.ZERO)) return a;
        else {
            BigInteger t = a.mod(b);
            return gcd(b, t);
        }
    }

    private static ArrayList<Fraction> data;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            data = new ArrayList<>();

            int n = kb.nextInt();
            for(int i = 0; i < n; i++) {
                int numerator = kb.nextInt();
                int denominator = kb.nextInt();
                data.add(new Fraction(numerator, denominator));
            }

            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    public static void main(String[] args) {
        if(input()) {
            for(Fraction fraction : data) {
                System.out.println(solve(fraction));
            }
        }
    }
}
