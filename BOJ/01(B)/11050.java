
import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // nCk = n-1Ck + n-1Ck-1
        System.out.println(solution(n,k));
    }

    static int solution(int n, int k) {
        if(k==n) return 1;
        if(k==0) return 1;
        else return solution(n - 1, k) + solution(n - 1, k - 1);
    }

}