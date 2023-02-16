import java.io.*;
import java.util.*;

public class Main {
    static int n, operator_cnt;
    static int[] arr;
    static ArrayList<Character> operator_list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int cnt;
        st = new StringTokenizer(br.readLine());
        // 덧셈의 개수
        cnt = Integer.parseInt(st.nextToken());
        while(cnt --> 0) operator_list.add('+');
        // 뺄셈의 개수
        cnt = Integer.parseInt(st.nextToken());
        while(cnt --> 0) operator_list.add('-');
        // 곱셈의 개수
        cnt = Integer.parseInt(st.nextToken());
        while(cnt --> 0) operator_list.add('*');
        // 나눗셈의 개수
        cnt = Integer.parseInt(st.nextToken());
        while(cnt --> 0) operator_list.add('/');

        operator_cnt = operator_list.size();
        permu = new int[operator_cnt];
        ch = new boolean[operator_cnt];

        // 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성
        recur(0);
        System.out.println(answer_max);
        System.out.println(answer_min);
    }
    static int answer_min = Integer.MAX_VALUE;
    static int answer_max = Integer.MIN_VALUE;
    static int[] permu;
    static boolean[] ch;
    static void recur(int L) {

        if(L == operator_cnt) {

            int t=0;
            int sum = arr[t];
            for(int idx : permu) {
                char op = operator_list.get(idx);

                if(op == '+') sum = sum + arr[++t];
                else if(op == '-') sum = sum - arr[++t];
                else if(op == '*') sum = sum * arr[++t];
                else sum = sum / arr[++t];
            }
            answer_min = Math.min(answer_min, sum);
            answer_max = Math.max(answer_max, sum);
        }
        else {

            for (int i = 0; i < operator_cnt; i++) {
                if(!ch[i]) {
                    ch[i] = true;
                    permu[L] = i;
                    recur(L+1);
                    ch[i] = false;
                }
            }
        }
    }

}