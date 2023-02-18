import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 위치
            int x = Integer.parseInt(st.nextToken());
            // 색깔
            int y = Integer.parseInt(st.nextToken());
            // y 색깔 리스트에 저장
            list.get(y).add(x);
        }

        int sum = 0;
        for(int i=1; i<=N; i++) {

            if(list.get(i).size() > 0) {
                ArrayList<Integer> tmp = list.get(i);
                Collections.sort(tmp);

                sum += tmp.get(1) - tmp.get(0) + tmp.get(tmp.size()-1) - tmp.get(tmp.size()-2);
                for(int j=1; j<tmp.size()-1; j++) {
                    sum += Math.min(tmp.get(j) - tmp.get(j-1), tmp.get(j+1) - tmp.get(j));
                }
            }
        }
        System.out.println(sum);
    }
}