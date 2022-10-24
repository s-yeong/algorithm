import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // 선언

        int n = Integer.parseInt(br.readLine()); // 학생의 수
        StringTokenizer st = new StringTokenizer(br.readLine());


        ArrayList<Integer> arr = new ArrayList<>();
        int cnt = 0;
        for(int i=0; i<n; i++) {

            cnt++;
            int a = Integer.parseInt(st.nextToken());

            arr.add(cnt - a - 1, cnt);
        }

        for(int x : arr) System.out.print(x + " ");










    }
}
