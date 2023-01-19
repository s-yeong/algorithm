import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<N; tc++) {
            String str = br.readLine();
            arr[tc] = str;
        }

        /**
         * Arrays.sort + Comparator
         */
        Arrays.sort(arr, new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                // 길이 같으면 사전 순 -> 원래 구현되어있는 대로
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                }
                // 다르면 길이 순
                else {
                    return s1.length() - s2.length();
                }
            }
        });

        sb.append(arr[0]).append("\n");
        for(int i=1; i<N; i++) {
            if(!arr[i].equals(arr[i-1])) {
                sb.append(arr[i]).append("\n");
            }
        }

        // 출력
        System.out.println(sb);
    }
}