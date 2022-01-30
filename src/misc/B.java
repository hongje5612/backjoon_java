package misc;

import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int x, a = sc.nextInt(), b = sc.nextInt();
            while(a!=1){
                x = (b % a == 0) ? (b / a) : (b / a + 1);
                a = a*x - b; b *= x;
                int g = gcd(a, b); a /= g; b /= g;
            }
            System.out.println(b);
        }
        sc.close();
    }
    public static int gcd(int a, int b){ return b==0?a:gcd(b,a%b); }
}