import java.io.*;
import java.util.*;

/**
 * 19941. 햄버거 분배
 * 1. n : 식탁의 길이
 * 2. k : 햄버거 선택할 수 있는 거리
 * => 햄버거를 먹을 수 있는 사람의 최대 수
 */
public class Main {
    static int n,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        char[] pos = br.readLine().toCharArray();

        int answer = 0;
        // 왼쪽에 있는 H부터 찾고, 없으면 오른쪽에 찾기
        for(int idx=0; idx<n; idx++) {
            if(pos[idx] == 'P') {

                boolean flag = false;
                // 가장 멀리 있는 거 부터 먹기
                int leftIdx = idx-k;
                if(leftIdx < 0) leftIdx = 0;
                for(; leftIdx<idx; leftIdx++) {
                    if(pos[leftIdx] == 'H') {
                        answer++;
                        pos[leftIdx] = 'O';
                        flag = true;
                        break;
                    }
                }

                // 먹었으면 skip
                if(flag) continue;

                // 안먹었으면 오른쪽 찾기
                for(int rightIdx=idx+1; rightIdx<=idx+k; rightIdx++) {
                    // 범위 벗어나면,
                    if(rightIdx == n) break;

                    if(pos[rightIdx] == 'H') {
                        answer++;
                        pos[rightIdx] = 'O';
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
