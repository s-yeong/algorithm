import java.io.*;
import java.util.*;

public class Main {
    static class Number implements Comparable<Number>{
        int num;
        int idx;

        public Number(int num, int idx){
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Number o) {
            return this.num - o.num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Number> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(new Number(Integer.parseInt(st.nextToken()), i));
        }

        Collections.sort(list);
        int[] P = new int[N];
        for(int i=0; i<N; i++) {
            Number ob = list.get(i);
            P[ob.idx] = i;
        }


        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) sb.append(P[i]).append(" ");
        System.out.print(sb);

        /*
        P[0] ~ P[N-1] => 0부터 N-1까지의 수를 한 번씩 포함하고 있는 수열
        수열 P를 길이가 N인 배열 A에 적용하면, 길이가 N인 배열 B가 된다
        적용 방법 B[P[i]] = A[i]
         */

    }
}