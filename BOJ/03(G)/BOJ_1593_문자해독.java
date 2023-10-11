import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 1593. 문자 해독
 * 단어(W)가 문자열(S)에 들어갈 수 있는 모든 가짓수 계산
 * => 문자열 S안에서 문자열 W의 순열 중 하나가 부분 문자열로 들어가있는 모든 경우의 수
 *
 * 풀이
 * 0. word의 순열을 구한다음 해당 문자열이 str에 포함되어 있는지 확인 (순열 => 시간복잡도 3000! 풀 수 없음)
 *
 * 1. 단어의 개수를 저장하는 Map을 만든 다음 투포인터를 활용해 해당 개수와 동일하다면 카운팅 하는 방식으로 풀기
 * => 슬라이딩 윈도우
 *
 */
public class BOJ_1593_문자해독 {

    static int wordLen, strLen;
    static String word, str;

    public static void main(String[] args) throws IOException {

        // 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        wordLen = Integer.parseInt(st.nextToken());
        strLen = Integer.parseInt(st.nextToken());
        word = br.readLine();
        str = br.readLine();

        int answer = 0;

        Map<Character, Integer> wordMap = new HashMap<>();
        Map<Character, Integer> strMap = new HashMap<>();

        // 각 단어들 저장
        for(char ch : word.toCharArray()) {
            wordMap.put(ch, wordMap.getOrDefault(ch, 0) + 1);
        }

        // 처음 값 넣기
        for(int idx=0; idx<wordLen; idx++) {
            strMap.put(str.charAt(idx), strMap.getOrDefault(str.charAt(idx), 0) + 1);
        }
        // 처음 값 비교
        if(wordMap.equals(strMap)) {
            answer++;
        }

        // 투포인터
        int lt = 0;
        for(int rt=wordLen; rt<strLen; rt++) {

            // lt 줄이고
            strMap.put(str.charAt(lt), strMap.getOrDefault(str.charAt(lt), 0) - 1);
            // 0인 경우 제거
            if(strMap.get(str.charAt(lt)) == 0) strMap.remove(str.charAt(lt));

            // rt 늘리고
            strMap.put(str.charAt(rt), strMap.getOrDefault(str.charAt(rt), 0) + 1);

            // 비교
            if(wordMap.equals(strMap)) {
                answer++;
            }
            lt++;
        }

        System.out.println(answer);
    }
}
