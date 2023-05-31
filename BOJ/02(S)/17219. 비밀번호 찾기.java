import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, String> map = new HashMap<>();

        // n개의 줄에 걸쳐 각 줄에 사이트 주소와 비밀번호
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++) {

            String site = br.readLine();
            sb.append(map.get(site)).append("\n");
        }
        System.out.print(sb);

    }
}
