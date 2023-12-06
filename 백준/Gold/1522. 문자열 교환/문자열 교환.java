import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * 1522. 문자열 교환
 * 1. a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를 최소로 하는 프로그램
 * 2. 문자열은 원형이다.
 *
 * 1. a의 개수를 세고, a의 개수 만큼 윈도우 크기를 잡는다.
 * 2. 윈도우를 옮기면서, 최소한의 b의 개수를 가지고 있을 때가 결국 교환의 최소값이 된다.
 * 3. a의 개수가 1이면, 계산 필요 X
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int a_count = 0;
        for(char ch : str.toCharArray()) {
            if(ch == 'a') a_count++;
        }

        //a의 개수가 1이면, 계산 필요 X
        if(a_count == 1) {
            System.out.println(0);
            System.exit(0);
        }

        // answer
        int min_b_count = str.length();

        int b_count = 0;
        // 처음 계산
        for(int lt=0; lt<a_count; lt++) {
            if(str.charAt(lt) == 'b') b_count++;
        }
        min_b_count = Math.min(min_b_count, b_count);
        
        // 슬라이딩 윈도우
        int rt = a_count-1;
        for(int lt=1; lt<str.length(); lt++) {

            if(str.charAt(lt-1) == 'b') b_count--;

            rt = (rt+1) % str.length();
            if(str.charAt(rt) == 'b') b_count++;

            // 매 윈도우 옮길 때 마다 최소값 갱신
            min_b_count = Math.min(min_b_count, b_count);
        }

        System.out.println(min_b_count);
    }
}