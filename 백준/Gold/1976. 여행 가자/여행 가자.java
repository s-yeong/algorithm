import java.io.*;
import java.util.*;


/**
 * 1976. 여행 가자
 * 여행 가능한 경로인지 알아보기
 * 1. 중간에 경유 가능
 * 2. 같은 도시 반복 방문 가능
 * 3. 무방향 그래프
 * [풀이]
 * 해당 지점을 방문체크 할 수 있으면 여행 가능한 경로다.
 * => 유니온 파인드로 풀어보기
 * 1. 같은 집합에 속하지 않으면, NO
 */
public class Main {

    static int N, M;
    static int[] plan;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        //init
        parents = new int[N+1];
        for(int idx=1; idx<=N; idx++) {
            parents[idx] = idx;
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                int isLink = Integer.parseInt(st.nextToken());
                if(isLink == 1) {
                    union(i, j);
                }
            }
        }

        String answer = "YES";
        // 여행 계획
        int root = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {

            int num = Integer.parseInt(st.nextToken());

            if(i==0) root = find(num);
            else {
                if(root != find(num)) {
                    answer = "NO";
                }
            }
        }

        System.out.println(answer);
    }
    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    static void union(int a, int b) {

        int fa = find(a);
        int fb = find(b);

        if(fa == fb) return;

        if(fa < fb) parents[fa] = fb;
        else parents[fb] = fa;
    }
}