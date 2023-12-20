import java.io.*;
import java.util.*;

/**
 * 7490. 0 만들기
 * 1. 완전 탐색
 */
public class Main {
    static int T, N;
    static StringBuilder answer;
    static ArrayList<String> answerList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while(T --> 0) {
            N = Integer.parseInt(br.readLine());
            answerList = new ArrayList<>();
            recur(1, new char[N]);

            Collections.sort(answerList);

            for(String str : answerList) {
                answer.append(str + "\n");
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }
    static void recur(int num, char[] cal) {

        if(num == N) {
            
            // 조합
            StringBuilder sb = new StringBuilder();
            for(int idx=1; idx<N; idx++) {
                sb.append(idx).append(cal[idx]);
            }
            sb.append(N);
            String originStr = sb.toString();
            String str = sb.toString().replace(" ", "");

            int numIdx = 0;
            String[] numArr = str.split("\\+|\\-");

            int sum = Integer.parseInt(numArr[numIdx++]);
            for(char ch : str.toCharArray()) {
                if(ch == '+') {
                    sum += Integer.parseInt(numArr[numIdx++]);
                }
                else if(ch == '-') {
                    sum -= Integer.parseInt(numArr[numIdx++]);
                }
            }
            if(sum == 0) {
                answerList.add(originStr);
            }

            return;
        }

        //PLUS
        cal[num] = '+';
        recur(num+1, cal);
        //MINUS
        cal[num] = '-';
        recur(num+1, cal);
        //BLANK
        cal[num] = ' ';
        recur(num+1, cal);
    }
}