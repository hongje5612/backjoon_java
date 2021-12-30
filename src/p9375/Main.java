package p9375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

    private static class TestCase {
        private Map<Integer, Integer> map;

        public TestCase() throws IOException, Exception {
            map = new HashMap<Integer, Integer>();

            int n = Integer.parseInt(kb.readLine());

            for(int i = 0; i < n; i++) {
                String[] ss = kb.readLine().split("\\s+");
                int hashCode = ss[1].hashCode();

                if(map.containsKey(hashCode)) map.replace(hashCode, map.get(hashCode) + 1);
                else map.put(hashCode, 1);
            }
        }

        public long getAnswer() {
            if(map.isEmpty()) return 0;

            long answer = 1;
            for(var entry : map.entrySet()) {
                answer *= (entry.getValue() + 1);
            }
            return --answer;
        }
    }

    public static void main(String[] args) {
        int n;

        try {
            n = Integer.parseInt(kb.readLine());

            for(int i = 0; i < n; i++) {
                TestCase testCase = new TestCase();
                System.out.println(testCase.getAnswer());
            }
        } catch (IOException e) {
            ; // nop
        } catch(Exception e) {
            ; // nop
        } finally {
            try {
                kb.close();
            } catch (IOException e) {
                ; // nop
            }
        }
    }
}
