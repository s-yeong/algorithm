import java.io.*;
import java.util.*;

/**
 * 20920. 영단어 암기는 괴로워
 * 1. 단어 순서
 * 1-1. 자주 나오는 단어일수록 앞에 배치
 * 1-2. 해당 단어의 길이가 길수록 앞에 배치
 * 1-3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치
 * 2. N은 10만
 * 3. m이하는 무시
 */
public class Main {
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //input
        HashMap<String, Integer> map = new HashMap<>();
        while(n-->0) {
            String str = br.readLine();
            if(str.length() < m) continue;
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        ArrayList<Word> list = new ArrayList<>();
        for(String str : map.keySet()) {
            list.add(new Word(map.get(str), str.length(), str));
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(Word word : list) {
            sb.append(word.str + "\n");
        }
        System.out.println(sb);
    }
    static class Word implements Comparable<Word>{
        int count;
        int length;
        String str;

        public Word(int count, int length, String str) {
            this.count=count;
            this.length=length;
            this.str=str;
        }

        public int compareTo(Word ob) {
            if(this.count != ob.count) return ob.count - this.count;
            else if(this.length != ob.length) return ob.length - this.length;
            else return this.str.compareTo(ob.str);
        }
    }
}