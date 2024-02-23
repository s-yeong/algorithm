import java.io.*;
import java.util.*;

/**
 * 1138. 한 줄로 서기
 * 1. 오민식의 지시대로 선다.
 * 2. 사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만 기억함
 * 3. 키는 1부터 N까지 모두 다름
 * 4. 줄 세우기
 */
public class Main {

    static int[] arr;
    static int n;
    static int[] perm;
    static boolean[] ch;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        perm = new int[n+1];
        ch = new boolean[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int height=1; height<=n; height++) {
            arr[height] = Integer.parseInt(st.nextToken());
        }

        recur(1);
    }
    static void recur(int depth) {

        if(depth == n+1) {
            for(int idx=1; idx<=n; idx++) {
                int height = perm[idx];
                int leftCount = arr[height];

                if(leftCount >= idx) return;
                for(int left=idx-1; left>=0; left--) {
                    int leftHeight = perm[left];
                    // 나보다 키 큰 사람이면 마이너스
                    if(height < leftHeight) leftCount--;
                }

                // leftCount가 0이 아니라면 성립 X
                if(leftCount != 0) {
                    return;
                }
            }
            // 모든 경우 가능하면, 출력
            StringBuilder sb = new StringBuilder();
            for(int idx=1; idx<=n; idx++) {
                sb.append(perm[idx] + " ");
            }
            System.out.println(sb);
            return;
        }

        for(int num=1; num<=n; num++) {
            if(ch[num]) continue;
            ch[num] = true;
            perm[depth] = num;
            recur(depth+1);
            ch[num] = false;
        }
    }
}
