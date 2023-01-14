import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            list.add(new int[]{weight, height});
        }

        // 완탐
        for(int i=0; i<list.size(); i++) {
            int[] person = list.get(i);
            int count = 0;  // 자기보다 덩치 큰 수
            for(int j=0; j<list.size(); j++) {

                // 자신보다 키도 크면서, 덩치도 큰 경우
                if(person[0] < list.get(j)[0] && person[1] < list.get(j)[1]) {
                    count++;
                }
            }
            // 덩치 등수 = count+1
            sb.append(count+1).append(" ");
        }
        System.out.println(sb);
    }
}