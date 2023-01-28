import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 도감에 수록되어 있는 포켓몬의 개수 N이랑 내가 맞춰야 하는 문제의 개수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = new String[N+1];

        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            arr[i] = str;
            map.put(str, i);
        }

        StringBuilder sb = new StringBuilder();
        while(M-->0) {
            String str = br.readLine();
            if(Character.isDigit(str.charAt(0))) {
                sb.append(arr[Integer.parseInt(str)]);
            }
            else {
                sb.append(map.get(str));
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }
}