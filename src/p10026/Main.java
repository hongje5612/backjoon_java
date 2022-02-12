package p10026;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static char[][] picture;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            picture = new char[n][n];

            for(int i = 0; i < n; i++) {
                String t = kb.next();

                int j = 0;
                for(char c : t.toCharArray()) {
                    picture[i][j++] = c;
                }
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static class Solver {
        private static final int[] dx = { 0, 0, -1, 1 };
        private static final int[] dy = { -1, 1, 0, 0 };

        private char[][] picture;
        private Map<Character, Predicate<Character>> predicateMap;
        private Predicate<Character> predicate;


        public Solver(char[][] picture, Map<Character, Predicate<Character>> predicateMap) {
            this.picture = new char[picture.length][picture.length];
            int index = 0;
            for(char[] arr : picture) {
                this.picture[index++] = Arrays.copyOf(arr, arr.length);
            }
            this.predicateMap = predicateMap;
            this.predicate = null;
        }

        public int solve() {
            int answer = 0;
            for(int r = 0; r < picture.length; r++) {
                for(int c = 0; c < picture[0].length; c++) {
                    if(predicateMap.containsKey(picture[r][c])) {
                        predicate = predicateMap.get(picture[r][c]);
                        recursion(r, c);
                        answer++;
                    }
                }
            }
            return answer;
        }

        public void recursion(int row, int col) {
            picture[row][col] = 'v'; // visited;
            for(int i = 0; i < dx.length; i++) {
                int r = row + dy[i];
                int c = col + dx[i];

                if(r >= 0 && r < picture.length && c >= 0 && c < picture[0].length && picture[r][c] != 'v') {
                    if(predicate.test(picture[r][c])) recursion(r, c);
                }
            }
        }

        public void show() {
            for(int r = 0; r < picture.length; r++) {
                for(int c = 0; c < picture[r].length; c++) {
                    System.out.print(picture[r][c] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void printAnswer() {
       Map<Character, Predicate<Character>> normal = new HashMap<>();
       Map<Character, Predicate<Character>> disable = new HashMap<>();

       normal.put('R', (x) -> x == 'R');
       normal.put('G', (x) -> x == 'G');
       normal.put('B', (x) -> x == 'B');

       disable.put('R', (x) -> x == 'R' || x == 'G');
       disable.put('G', (x) -> x == 'G' || x == 'R');
       disable.put('B', (x) -> x == 'B');

       Solver normalSolver = new Solver(picture, normal);
       Solver disableSolver = new Solver(picture, disable);

       System.out.println(normalSolver.solve() + " " + disableSolver.solve());
    }

    public static void main(String[] args) {
        if(input()) {
            printAnswer();
        }
    }
}
