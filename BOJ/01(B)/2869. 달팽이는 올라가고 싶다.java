import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 달팽이가 높이가 V인 나무 막대에 올라간다
        // 낮에 A미터 올라감, 밤에 B미터 미끄러짐

        double time = (double) (V - B) / (A - B);

        int answer = (int)Math.ceil(time);

        System.out.println(answer);


    }
}
