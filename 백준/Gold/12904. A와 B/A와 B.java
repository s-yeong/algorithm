import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 12904. A와 B
 * 1. 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임
 * 2. 두 가지 연산
 * 2-1. 문자열 뒤에 A 추가
 * 2-2. 문자열 뒤집고 뒤에 B 추가
 * => 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램
 *
 * [풀이]
 * 1. 반대로 생각했을 떄, 결국 맨 뒤가 A냐 B냐에 따라 뺴는 연산을 하면 된다.
 * 2. 빼는 연산
 * 2-1. 문자열 뒤에 A면 A 뺴기
 * 2-2. 문자열 뒤에 B면 B 빼고 뒤집기
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();;

        System.out.println(solve(T, S)? 1:0);
    }
    static boolean solve(String T, String S) {

        while(T.length() != S.length()) {
            //2-1. 문자열 뒤에 A면 A 뺴기
            if(T.charAt(T.length()-1) == 'A') {
                T = T.substring(0, T.length()-1);
            }
            //2-2. 문자열 뒤에 B면 B 빼고 뒤집기
            else {
                T = T.substring(0, T.length()-1);
                T = new StringBuilder(T).reverse().toString();
            }
        }

        return T.equals(S);
    }
}