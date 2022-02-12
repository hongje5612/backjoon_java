package p2535;

import java.util.*;

public class Main {
    private static class Player {
        public int country;
        public int number;
        public int score;

        public Player(int country, int number, int score) {
            this.country = country;
            this.number = number;
            this.score = score;
        }
    }

    private static List<Player> playerList;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            playerList = new ArrayList<>();

            int n = kb.nextInt();
            for(int i = 0; i < n; i++) {
                int country = kb.nextInt();
                int number = kb.nextInt();
                int score = kb.nextInt();

                playerList.add(new Player(country, number, score));
            }
            state = true;
        } catch(Exception e) {
            state = false;
        }
        return state;
    }

    private static void printAnswer() {
        playerList.sort(new Comparator<Player>() {
            @Override
            public int compare(Player a, Player b) {
                int t = Integer.compare(a.score, b.score);
                if(t == 1) return -1;
                else if(t == 0) return 0;
                else return 1;
            }
        });

        Map<Integer, Integer> numberOfMedal = new HashMap<>(); // 각 나라에 지급된 메달 수

        int cnt = 0;
        var it = playerList.iterator();
        while(cnt < 3) {
            if(it.hasNext()) {
                var p = it.next();
                if(numberOfMedal.containsKey(p.country)) {
                    if(numberOfMedal.get(p.country) < 2) {
                        System.out.println(p.country + " " + p.number);
                        cnt++;
                        numberOfMedal.replace(p.country, numberOfMedal.get(p.country) + 1);
                    }
                }
                else {
                    System.out.println(p.country + " " + p.number);
                    cnt++;
                    numberOfMedal.put(p.country, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        if(input()) printAnswer();
    }
}
