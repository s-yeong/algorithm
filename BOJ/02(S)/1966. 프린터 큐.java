import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
        1. 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인
        2-1. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면,
        이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치
        2-2. 그렇지 않다면 바로 인쇄
         */
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 0; testCase < tc; testCase++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> Q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                // 인덱스 번호, 중요도
                Q.offer(new int[]{i, Integer.parseInt(st.nextToken())});
            }

            int count = 0;
            while(!Q.isEmpty()) {

                int[] cur = Q.poll();
                boolean flag = true;
                for(int[] next : Q) {
                    if(next[1] > cur[1]) {
                        flag = false;
                        break;
                    }
                }
                if(!flag) Q.offer(cur);
                else {
                    count++;
                    if(cur[0] == M) {
                        sb.append(count).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.print(sb);
    }
}