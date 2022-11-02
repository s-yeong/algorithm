import java.io.*;
import java.util.*;

public class Main {

    static boolean flag = false;
    static int[] ch = new int[9];
    static int[] arr = new int[9];
    static ArrayList<Integer> answer = new ArrayList<>();
    static void DFS(int n) {
        if(flag) return;
        if(n == 9) {
            int sum = 0;
            int cnt = 0;
            for(int i=0; i<9; i++) {
                if(ch[i] == 1) {
                    sum += arr[i];
                    cnt++;
                }
            }

            if(cnt != 7) return;

            if(sum == 100) {
                flag = true;
                for(int i=0; i<9; i++) {
                    if(ch[i] == 1) answer.add(arr[i]);
                }
            }
        }
        else {
            ch[n] = 1;
            DFS(n + 1);
            ch[n] = 0;
            DFS(n + 1);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[9];
        for (int i = 0; i < 9; i++) arr[i] = Integer.parseInt(br.readLine());

        DFS(0);
        Collections.sort(answer);
        for(int x : answer) System.out.println(x);


    }
}