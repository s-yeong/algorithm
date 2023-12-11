import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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

            int[] charsCount = new int[26];
            for (char ch : str.toCharArray()) {
                charsCount[ch - 'a']++;
            }
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int idx = 0; idx < str.length(); idx++) {

                if(charsCount[str.charAt(idx) - 'a'] < K) continue;

                int count = 1;
                for (int targetIdx = idx + 1; targetIdx < str.length(); targetIdx++) {
                    if (str.charAt(idx) == str.charAt(targetIdx)) {
                        count++;
                    }

                    if(count == K) {
                        min = Math.min(min, targetIdx - idx + 1);
                        max = Math.max(max, targetIdx - idx + 1);
                        break;
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