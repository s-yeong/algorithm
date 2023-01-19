import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Word> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<N; tc++) {
            String str = br.readLine();
            arr.add(new Word(str, str.length()));
        }

        Collections.sort(arr);
        sb.append(arr.get(0).str).append("\n");
        for(int i=1; i<arr.size(); i++) {
            if(!arr.get(i).str.equals(arr.get(i-1).str)) {
                sb.append(arr.get(i).str).append("\n");
            }
        }

        // 출력
        System.out.println(sb);
    }

    static class Word implements Comparable<Word>{
        String str;
        int length;

        public Word(String str, int length) {
            this.str = str;
            this.length = length;
        }

        @Override
        public int compareTo(Word o) {
            if(o.length!=this.length) return this.length - o.length;
            else {
                // 문자 하나씩 비교
                for(int i=0; i<o.length; i++) {
                    if(this.str.charAt(i) != o.str.charAt(i)) {
                        return this.str.charAt(i) - o.str.charAt(i);
                    }
                }
                // 같은 단어인 경우
                return 0;
            }
        }
    }
}