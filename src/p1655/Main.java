package p1655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/1655
 */
public class Main {
    private static int[] arr; // 입력되는 데이타

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(kb.readLine());

            arr = new int[n];

            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(kb.readLine());
            }
            state = true;
        } catch (IOException e) {
            state = false;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static void printAnswer() {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        int t;
        var it = Arrays.stream(arr).iterator();
        if(it.hasNext()) {
            t = it.next();
            System.out.print(t + " ");
            maxQ.offer(t);
        } else return;

        if(it.hasNext()) {
            t = it.next();
            if(maxQ.peek() > t) {
                minQ.offer(maxQ.poll());
                maxQ.offer(t);
                System.out.println(t + " ");
            } else {
                minQ.offer(t);
                System.out.print(maxQ.peek() + " ");
            }
        } else return;

        while(it.hasNext()) {
            t = it.next();

            if(minQ.size() == maxQ.size()) {
                if(minQ.peek() >= t) {
                    maxQ.offer(t);
                } else {
                    int a = minQ.poll();
                    minQ.offer(t);
                    maxQ.offer(a);
                }
                System.out.print(maxQ.peek() + " ");
            }
            else {
                if(t >= maxQ.peek()) {
                    minQ.offer(t);
                } else {
                    int a = maxQ.poll();
                    maxQ.offer(t);
                    minQ.offer(a);
                }
                System.out.print(maxQ.peek() + " ");
            }
        }
    }

    public static void main(String[] args) {
        if(input()) printAnswer();
    }
}
