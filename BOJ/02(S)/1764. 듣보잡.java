import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 듣도 못한 사람의 수 N, 보도 못한 사람의 수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            // 듣도 못한 사람의 이름
            set.add(br.readLine());
        }
        for(int i=0; i<M; i++) {
            // 보도 못한 사람의 이름
            String str = br.readLine();
            if(set.contains(str)) {
                list.add(str);
            }
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(String s : list) System.out.println(s);
    }
}