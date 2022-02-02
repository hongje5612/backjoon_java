package p3029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int toInt(String time) {
        String[] ss = time.split(":");
        int answer = 0;

        for(int i = 0; i < ss.length; i++) {
            answer += Integer.parseInt(ss[i]) * (int)Math.pow(60, ss.length - i - 1);
        }
        return answer;
    }

    private static String toTime(int n) {
        int hour = n / 3600;
        n %= 3600;
        int min = n / 60;
        n %= 60;

        StringBuilder sb = new StringBuilder();
        if(hour < 10) sb.append(0);
        sb.append(hour);
        sb.append(":");
        if(min < 10) sb.append(0);
        sb.append(min);
        sb.append(":");
        if(n < 10) sb.append(0);
        sb.append(n);

        return sb.toString();
    }

    public static void main(String[] args) {
        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String t1 = kb.readLine();
            String t2 = kb.readLine();

            int n1 = toInt(t1);
            int n2 = toInt(t2);

            if(n1 == n2) System.out.println("24:00:00");
            else {
                if(n2 < n1) n2 += 24 * 3600;
                System.out.println(toTime(n2 - n1));
            }
        } catch(IOException e) {

        } catch(Exception e) {

        }
    }
}
