package p1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
    private static ArrayList<ArrayList<Long>> data;

    private static boolean input() {
        final int TEST_CASE = 3;
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            data = new ArrayList<>();

            for(int i = 0; i < TEST_CASE; i++) {
                ArrayList<Long> l = new ArrayList<>();

                int size = Integer.parseInt(kb.readLine());
                for(int j = 0; j < size; j++) {
                    l.add(Long.parseLong(kb.readLine()));
                }
                data.add(l);
            }

            state = true;
        } catch (IOException e) {
            state = false;
        } catch (Exception e) {
            state = false;
        }

        return state;
    }

    private static int getSign(ArrayList<Long> list) {
        BigInteger acc = BigInteger.ZERO;

        for(Long t : list) {
            acc = acc.add(new BigInteger(String.valueOf(t)));
        }
        return acc.signum();
    }

    public static void main(String[] args) {
        if(input()) {
            for(ArrayList<Long> list : data) {
                int sign = getSign(list);

                if(sign == 1) System.out.println('+');
                else if(sign == 0) System.out.println('0');
                else System.out.println('-');
            }
        }
        else {
            System.out.println("Input Error");
        }
    }
}
