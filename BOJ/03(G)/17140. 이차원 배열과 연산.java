import java.util.*;
import java.io.*;

class Number implements Comparable<Number>{
    int num;
    int count;

    public Number(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(Number o) {
        if(this.count != o.count) return this.count - o.count;
        else return this.num - o.num;
    }
}

class Main {
    static int time = -1;
    static int[][] arr = new int[100][100];
    static int maxlenR = 3;
    static int maxlenC = 3;
    static void R() {
        // 행마다 진행
        int maxlen = 0;
        for(int i=0; i<maxlenR; i++) {
            ArrayList<Number> list = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            int len = 0;

            for(int j=0; j<maxlenC; j++) {
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            for(int key : map.keySet()) {
                if(key != 0) {
                    list.add(new Number(key, map.get(key)));
                    len += 2;
                }
            }
            Collections.sort(list);

            int idx = 0;
            for(Number n : list) {
                if(idx == 100) break;
                arr[i][idx++] = n.num;
                if(idx == 100) break;
                arr[i][idx++] = n.count;
            }

            // 뒤에 부분 0으로 만들기
            while(idx<100) arr[i][idx++] = 0;

            maxlen = Math.max(maxlen, len);
        }
        maxlenC = maxlen;
    }

    static void C() {
        // 열마다 진행
        int maxlen = 0;
        for(int i=0; i<maxlenC; i++) {
            ArrayList<Number> list = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            int len = 0;
            for(int j=0; j<maxlenR; j++) {
                map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
            }

            for(int key : map.keySet()) {
                if(key != 0) {
                    list.add(new Number(key, map.get(key)));
                    len += 2;
                }
            }
            Collections.sort(list);

            int idx = 0;
            for(Number n : list) {
                if(idx == 100) break;
                arr[idx++][i] = n.num;
                if(idx == 100) break;
                arr[idx++][i] = n.count;
            }
            while(idx<100) arr[idx++][i] = 0;
            maxlen = Math.max(maxlen, len);
        }
        maxlenR = maxlen;
    }

    static boolean solution(int r, int c, int k) {
        if(arr[r-1][c-1] == k) return true;
        else {
            if(maxlenR >= maxlenC) R();
            else C();
        }
        return false;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        // arr[r][c] == k 일 때 최소 시간 출력
            for (int i = 0; i <= 100; i++) {
                if(solution(r, c, k)) {
                    time = i;
                    break;
                }
            }

        System.out.println(time);
    }
}
