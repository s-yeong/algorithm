import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= 10; test_case++) {

            // 암호문의 길이
            int n = Integer.parseInt(br.readLine());

            // 원본 암호문
            ArrayList<Integer> arr = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i=0; i<n; i++) arr.add(Integer.parseInt(st.nextToken()));

            // 명령어 개수
            int m = Integer.parseInt(br.readLine());

            // I : 삽입, D : 삭제

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++) {

                String str = st.nextToken();
                int x,y;

                // 삽입
                if(str.equals("I")) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for(int j=0; j<y; j++) {
                        arr.add(x++, Integer.parseInt(st.nextToken()));
                    }
                }

                // 삭제
                else if(str.equals("D")) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for(int j=0; j<y; j++) {
                        arr.remove(x);
                    }
                }

                // 추가
                else {
                    y = Integer.parseInt(st.nextToken());
                    for(int j=0; j<y; j++) {
                        arr.add(Integer.parseInt(st.nextToken()));
                    }
                }

            }


            sb.append("#").append(test_case).append(" ");
            for(int i=0; i<10; i++) sb.append(arr.get(i)).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}