import java.util.ArrayList;
import java.util.Scanner;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int n, m, len, answer = Integer.MAX_VALUE;
    static int[] combi; // 조합 저장

    static ArrayList<Point> hs, pz;

    public static void DFS(int L, int s) {  // L - Level, s - start(1 2나 2 1 같으니까 start 시점 필요)

        if(L == m) {
            int sum = 0;
            for(Point p : hs) { // hs의 첫번째집, 두번째집 ...
                int dis = Integer.MAX_VALUE;
                for(int i : combi) {
                    dis = Math.min(dis, Math.abs(p.x - pz.get(i).x) + Math.abs(p.y - pz.get(i).y));
                } // 이 집의 피자 배달 거리
                sum += dis;
            } // 도시의 피자 배달 거리
            answer = Math.min(answer, sum);
        } else {

            for(int i=s; i<len; i++) {
                combi[L] = i;
                DFS(L + 1, i + 1);
            }

        }

    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        pz = new ArrayList<>();
        hs = new ArrayList<>();

        combi = new int[m];


        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int tmp = sc.nextInt();
                if(tmp == 1) hs.add(new Point(i, j));
                else if(tmp == 2) pz.add(new Point(i, j));
            }
        }

        len = pz.size();    // len에서 m개 선택!! => lenCm
        DFS(0, 0);

        System.out.println(answer);


    }
}