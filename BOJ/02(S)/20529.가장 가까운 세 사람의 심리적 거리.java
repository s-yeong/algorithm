import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /**
         * N명의 학생들의 MBTI 유형이 주어질 떄,
         * `가장 가까운 세 학생 사이`의 심리적인 거리
         * O(N^3) - 시간초과
         * mbti는 16가지 유형 => N이 33개 이상이면, 무조건 중복되는 3가지 나옴 => 0 출력
         * => 시간 초과 피할 수 있음
         */

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            if(N>32) {
                sb.append(0).append("\n");
                continue;
            }

            String[] arr = new String[N];

            for(int i=0; i<N; i++) {
                arr[i] = st.nextToken();
            }

            int min = Integer.MAX_VALUE;
            for(int i=0; i<N; i++) {
                for(int j=i+1; j<N; j++) {
                    for(int k=j+1; k<N; k++) {

                        // ij, ik, jk 비교
                        int dis = 0;
                        for(int p=0; p<4; p++) {
                            // ij 비교
                            if(arr[i].charAt(p) != arr[j].charAt(p)) dis++;
                            // ik 비교
                            if(arr[i].charAt(p) != arr[k].charAt(p)) dis++;
                            // jk 비교
                            if(arr[j].charAt(p) != arr[k].charAt(p)) dis++;
                        }
                        min = Math.min(min, dis);
                    }
                }
            }
            sb.append(min).append("\n");
        }
        System.out.print(sb);
    }
}