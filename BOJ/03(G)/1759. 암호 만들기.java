import java.util.*;
import java.io.*;

class Main {
    static char[] arr;
    static char[] answer;
    static int L, C;
    static void DFS(int Level, int S) {

        if(Level == L) {
            // 출력
            // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
            StringBuilder str = new StringBuilder();
            int cnt1 = 0;   // 모음
            int cnt2 = 0;   // 자음
            for(char x : answer) {
                if(x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') cnt1++;
                else cnt2++;
                str.append(x);
            }
            if(cnt1 >= 1 && cnt2 >= 2) System.out.println(str);
        }
        else {

            for(int i=S; i<C; i++) {
                answer[Level] = arr[i];
                DFS(Level + 1, i + 1);
            }
        }

    }


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }


        // C개로 L개 추측
        // 오름차순 정렬
        Arrays.sort(arr);

        answer = new char[L];
        DFS(0, 0);



    }
}
