import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 20437. 문자열 게임 2
 * 1. 소문자로 이루어진 문자열
 * 2. 양의 정수 K
 * 3. 어떤 문자를 정확히 K개 포함하는 가장 짧은 문자열의 길이
 * 4. 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gameCount = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (gameCount-- > 0) {

            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if(K==1) {
                sb.append("1 1").append("\n");
                continue;
            }

            List<Integer>[] charsIdxList = new ArrayList[26];
            for(int i=0; i<26; i++) {
                charsIdxList[i] = new ArrayList<>();
            }

            for(int idx=0; idx<str.length(); idx++) {
                char ch = str.charAt(idx);
                charsIdxList[ch - 'a'].add(idx);
            }
            int min = Integer.MAX_VALUE;
            int max = 0;

            for(List<Integer> list : charsIdxList) {

                int lt = 0;
                for(int rt = 0; rt<list.size(); rt++) {

                    while(rt-lt+1 > K) lt++;

                    if(rt-lt+1 == K) {
                       min = Math.min(min, list.get(rt) - list.get(lt) + 1);
                       max = Math.max(max, list.get(rt) - list.get(lt) + 1);
                    }
                }
            }



            if (min == Integer.MAX_VALUE || max == 0) {
                sb.append("-1").append("\n");
            } else {
                sb.append(min + " " + max).append("\n");
            }
        }
        System.out.print(sb);
    }

}