import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        for(int row=0; row<n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col=0; col<n; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
                if(board[row][col] == 2) chickenList.add(new int[]{row, col});
                if(board[row][col] == 1) houseList.add(new int[]{row, col});
            }
        }
        //폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력
        // 빈 칸, 치킨집, 집
        // 치킨거리 = 집과 가장 가까운 치킨 거리
        // 각각의 집은 치킨 거리 존재
        //도시의 치킨 거리는 모든 집의 치킨 거리의 합

        // 1. M개의 치킨집 고르기
        // 2. 최소값 찾기
        combi = new int[m];
        sum = Integer.MAX_VALUE;
        recur(0,0);
        System.out.println(sum);
    }
    static int sum;
    static List<int[]> chickenList, houseList;
    static int[] combi;
    static void recur(int depth, int start) {

        // m개만큼 뽑았을 때,
        if(depth == m) {
            // 도시의 치킨 거리 구하기
            int citySum = 0;
            for(int[] house : houseList) {
                int minDis = Integer.MAX_VALUE;
                for (int idx : combi) {
                    int[] chicken = chickenList.get(idx);
                    int dis = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
                    minDis = Math.min(minDis, dis);
                }
                citySum += minDis;
            }
            sum = Math.min(citySum, sum);
            return;
        }

        for(int idx=start; idx<chickenList.size(); idx++) {
            combi[depth] = idx;
            recur(depth+1, idx+1);
        }

    }
}