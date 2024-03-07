import java.io.*;
import java.util.*;

/**
 * 16719. ZOAC
 * 1. 문자열을 보여주는 새로운 규칙
 * 2. 아직 보여주지 않은 문자 중 추가했을 때의 문자열이 사전 순으로 가장 앞에 오도록 하는 문자를 보여주는 것
 */
public class Main {
    static StringBuilder answer;
    static Set<Integer> idxSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();
        char[] chars = br.readLine().toCharArray();
        idxSet = new TreeSet<>();
        ch = new boolean[chars.length];
        recur(0, chars.length-1, chars);
        System.out.println(answer);
    }
    static boolean[] ch;
    static void recur(int leftIdx, int rightIdx, char[] chars) {

        if(leftIdx > rightIdx) return;
        if(idxSet.size() == chars.length) return;

        char minChar = 'Z' + 1;
        int minIdx = -1;
        for(int idx=leftIdx; idx<=rightIdx; idx++) {
            if(ch[idx]) continue;
            if(chars[idx] < minChar) {
                minChar = chars[idx];
                minIdx = idx;
            }
        }
        // 변경 사항이 없는 경우 리턴
        // 더이상 범위 사이에 탐색할 문자가 존재 X
        if(minIdx == -1) return;

        // 방문 처리
        ch[minIdx] = true;
        // 출력될 idx set에 추가
        idxSet.add(minIdx);
        // 출력
        for(int idx : idxSet) {
            answer.append(chars[idx]);
        }
        answer.append("\n");

        // 재귀
        recur(minIdx+1, rightIdx, chars);
        recur(leftIdx, minIdx-1, chars);
    }
}
