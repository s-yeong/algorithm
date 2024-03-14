import java.io.*;
import java.util.*;

/**
 * 3980. 선발 명단
 * 1. 4-4-2 다이아몬드 전술을 사용
 * 2. 어떤 선수를 어느 포지션에 배치할지 결정 X
 * 3. 각 포지션의 능력을 0부터 100까지 수치화
 * => 모든 선수의 포지션을 정하는 프로그램 (0인 포지션에 배치 불가능)
 * - 모든 선수에게 적합한 포지션 수는 최대 5개
 */
public class Main {
    static int[][] skills;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<T; tc++) {

            skills = new int[11][11];
            for(int row=0; row<11; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int col=0; col<11; col++) {
                    skills[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            recur(0, new boolean[11], 0);
            sb.append(answer + "\n");
        }
        System.out.print(sb);
    }
    static void recur(int depth, boolean[] ch, int sum) {

        if(depth == 11) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int idx=0; idx<11; idx++) {
            if(ch[idx] || skills[depth][idx] == 0) continue;
            ch[idx] = true;
            recur(depth+1 ,ch, sum + skills[depth][idx]);
            ch[idx] = false;
        }
    }
}
