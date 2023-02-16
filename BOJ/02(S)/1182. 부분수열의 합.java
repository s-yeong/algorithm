import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수
        recur(0,0,0);
        System.out.println(answer);
    }
    static int answer = 0;
    static void recur(int L, int sum, int size) {
        if(L==N) {
            // 1개 이상 수열 사용
            if(size > 0 && sum == S) answer++;
        }
        else {
            recur(L+1, sum+arr[L],size+1);
            recur(L+1,sum,size);
        }
    }
}