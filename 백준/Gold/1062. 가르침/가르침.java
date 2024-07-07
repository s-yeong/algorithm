import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    static String[] arr;
    static List<Character> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        arr = new String[n];

        for(int idx=0; idx<n; idx++) {
            String word = br.readLine();
            arr[idx] = word.substring(4, word.length() - 4);
            for(char ch : arr[idx].toCharArray()) {
                if(ch != 'a' && ch != 'n' && ch != 't' && ch != 'i' && ch != 'c' &&
                    !list.contains(ch)) list.add(ch);
            }
        }

        if(k < 5) {
            System.out.println(0);
            return;
        }
        if(k == 26) {
            System.out.println(n);
            return;
        }

        if(list.size() < k-5) {
            HashSet<Character> set = new HashSet<>();
            set.add('a');
            set.add('n');
            set.add('t');
            set.add('i');
            set.add('c');
            set.addAll(list);

            for(int idx=0; idx<n; idx++) {
                boolean flag = true;
                // 해당 문자가 없으면 읽을 수 없음
                for(Character ch : arr[idx].toCharArray()) {
                    if(!set.contains(ch)) {
                        flag = false;
                        break;
                    }
                }
                // 읽을 수 있으면,
                if(flag) {
                    answer++;
                }
            }
            System.out.println(answer);
            return;
        }

        // k 개 가르칠 떄 읽을 수 있는 최대 단어 개수
        // a~z 중에 k-5개 골랐을 때 최대 단어 개수
        combi = new char[k-5];
        answer = 0;
        recur(0, 0);
        System.out.println(answer);
    }
    static char[] combi;
    static int answer;
    static void recur(int depth, int start) {

        if(depth == k-5) {
            int count = 0;
            HashSet<Character> set = new HashSet<>();
            set.add('a');
            set.add('n');
            set.add('t');
            set.add('i');
            set.add('c');
            for(int idx=0; idx<k-5; idx++) {
                set.add(combi[idx]);
            }

            // 현재 문자 기준,
            for(int idx=0; idx<n; idx++) {
                boolean flag = true;
                // 해당 문자가 없으면 읽을 수 없음
                for(Character ch : arr[idx].toCharArray()) {
                    if(!set.contains(ch)) {
                        flag = false;
                        break;
                    }
                }
                // 읽을 수 있으면,
                if(flag) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
            return;
        }

        for(int idx=start; idx<list.size(); idx++) {
            combi[depth] = list.get(idx);
            recur(depth+1, idx+1);
        }

    }
}