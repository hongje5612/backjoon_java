package p13414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Value {
        private int number;
        private boolean deleted;

        public Value(int number) {
            this.number = number;
            deleted = false;
        }
    }

    public static void main(String[] args) {
        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            Map<Integer, Integer> map = new HashMap<>(); // key : number, value : index;
            List<Value> list = new ArrayList<>();

            String[] ss = kb.readLine().split("\\s+");
            int k = Integer.parseInt(ss[0]);
            int l = Integer.parseInt(ss[1]);

            for(int i = 0; i < l; i++) {
                int t = Integer.parseInt(kb.readLine());

                if(map.containsKey(t)) {
                    int index = map.get(t);
                    list.get(index).deleted = true;
                }

                list.add(new Value(t));
                map.put(t, list.size() - 1);
            }

            int i = 0;
            for(var t : list) {
                if(!t.deleted) {
                    System.out.println(t.number);
                    i++;
                }
                if(i >= k) break;
            }

        } catch (IOException e) {

        } catch (Exception e) {

        }
    }
}
