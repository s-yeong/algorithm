import java.io.*;
import java.util.*;

/**
 * 2668. 숫자고르기
 * 1. 1~n의 정수 집합을 뽑았을 때
 * 2. 그 바로 아래 집합과 동일해야함
 * [입력]
 * 1. n : 100이하
 *
 * [풀이]
 * 1. idx == arr[idx]야함
 */
public class Main {
    static int n;
    static int[] arr;
    static boolean[] ch;
    static Set<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = new TreeSet<>();
        arr = new int[n+1];

        for(int idx=1; idx<=n; idx++) {
            arr[idx] = Integer.parseInt(br.readLine());
        }
        ch = new boolean[n+1];

        for(int idx=1; idx<=n; idx++) {
            dfs(idx);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for(Integer num : answer) {
            sb.append(num).append("\n");
        }
        System.out.print(sb);
    }
    static void dfs(int cur) {

        if(ch[arr[cur]]) {
            answer.add(arr[cur]);
            return;
        }

        ch[arr[cur]]=true;
        dfs(arr[cur]);
        ch[arr[cur]]=false;
    }
}