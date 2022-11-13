import java.io.*;
import java.util.*;

class Hambug {
    int score;
    int cal;


    public Hambug(int score, int cal) {
        this.score = score;
        this.cal = cal;
    }
}

class Main {
    // alt+s+a+g  : 매개변수 있는 생성자 생성
    static int N,L;
    static int max;
    static ArrayList<Hambug> arr;
    static int[] ch;
    static void DFS(int Level, int score, int cal) {

        // -> 시간초과
        /*for(int i=0; i<N; i++) {
            int ns = arr.get(i).score;
            int nc = arr.get(i).cal;
            if(cal + nc <= L && ch[i] == 0) {
                max = Math.max(max, score+ns);
                ch[i] = 1;
                DFS(score+ns, cal+nc);
                ch[i] = 0;
            }
        }*/

        // 부분집합으로 풀기
        if(cal > L) return;
        if(Level == N) {
            max = Math.max(max, score);
        }
        else {
            DFS(Level + 1, score+arr.get(Level).score, cal+arr.get(Level).cal);
            DFS(Level + 1, score, cal);
        }

    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<=T; test_case++) {

            arr = new ArrayList<>();
            // 재료 수, 제한 칼로리
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ch = new int[N + 1];
            max = Integer.MIN_VALUE;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                // 맛 점수
                int t = Integer.parseInt(st.nextToken());
                // 칼로리
                int k = Integer.parseInt(st.nextToken());
                arr.add(new Hambug(t,k));
            }

            DFS(0,0,0);


            sb.append("#").append(test_case).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}