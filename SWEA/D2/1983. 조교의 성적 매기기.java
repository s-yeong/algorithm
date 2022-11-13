import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());	// 학생수
            int k = Integer.parseInt(st.nextToken());	// 학생번호
            Integer[] sum = new Integer[n];
            // 시험 및 과제점수
            // 중간35 기말45 과제20	// 총점이 높은순 ->같은 비율로 부여가능

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                // 중간35 기말45 과제20
                int mid = Integer.parseInt(st.nextToken());
                int fin = Integer.parseInt(st.nextToken());
                int hom = Integer.parseInt(st.nextToken());
                sum[i] = mid*35 + fin*45 + hom*20;
            }

            String answer="";

            // k번쨰 학생 학점
            int score = sum[k-1];

            Arrays.sort(sum, Collections.reverseOrder());

            String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
            int cnt = n/10;
            int i=0;
            int p=0;

            while(i<n) {
                if(sum[i] == score) {
                    answer += grade[p];
                    break;
                }
                else {
                    cnt--;
                    if(cnt == 0) {
                        cnt = n/10;
                        p++;
                    }
                }
                i++;
            }


            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}