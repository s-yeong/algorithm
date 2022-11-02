import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 색종이 수

        int cnt = 0;
        int[][] map = new int[100][100];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 색종이의 왼쪽 변과 도화지의 왼쪽 변 사이의 거리
            int lx = Integer.parseInt(st.nextToken());
            // 색종이의 아래쪽 변과 도화지의 아래쪽 변 사이의 거리
            int ly = Integer.parseInt(st.nextToken());

            for(int j=ly; j<ly+10; j++) {
                for(int k=lx; k<lx+10; k++) {
                    if(map[j][k] == 0) {
                        cnt++;
                        map[j][k] = 1;
                    }
                }
            }

        }

        System.out.println(cnt);

    }
}