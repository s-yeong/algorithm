import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main7 {

    static class Point implements Comparable<Point>{

        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x == o.x) {
                return this.y - o.y;
            }
            else return this.x - o.x;
        }
    }
    public static void solution(int n, int[] x, int[] y) {

        ArrayList<Point> answer = new ArrayList<>();

        for(int i=0; i<n; i++) {
            answer.add(new Point(x[i], y[i]));
        }

        Collections.sort(answer);
        for(Point o : answer) {
            System.out.println(o.x +" "+ o.y);
        }


    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        solution(n, x, y);


    }
}