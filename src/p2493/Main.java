package p2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
https://www.acmicpc.net/problem/2493
 */
public class Main {
    private static class Building {
        public int order, height;

        public Building(int order, int height) {
            this.order = order;
            this.height = height;
        }
    }

    private static Building[] buildings;

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(kb.readLine());
            buildings = new Building[n];

            String[] ss = kb.readLine().split("\\s+");

            int i = 0;
            for(String s : ss) {
                buildings[i] = new Building(i + 1, Integer.parseInt(s));
                i++;
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
        Stack<Building> stack = new Stack<>();
        stack.push(new Building(0, Integer.MAX_VALUE));

        for(Building b : buildings) {
            while(!stack.empty()) {
                Building t = stack.peek();
                if(t.height >= b.height) {
                    System.out.print(t.order + " ");
                    stack.push(b);
                    break;
                } else {
                    stack.pop();
                }
            }
        }
    }

    public static void main(String[] args) {
        if(input()) printAnswer();
    }
}
