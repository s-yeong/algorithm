import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] arrSort = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arrSort[i] = arr[i];
        }

        // 최악 O(n^2), 평균 O(nlogn)
        Arrays.sort(arrSort);
        HashMap<Integer, Integer> map = new HashMap<>();

        int cnt = 0;
        map.put(arrSort[0], cnt++);
        for(int i=1; i<arrSort.length; i++) {
            if(arrSort[i-1] != arrSort[i]) {
                map.put(arrSort[i], cnt++);
            }
        }

        for(int i=0; i<N; i++) {
            sb.append(map.get(arr[i])).append(" ");
        }
        System.out.println(sb);
    }
}