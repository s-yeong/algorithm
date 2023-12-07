import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * 12919. A와 B 2
 * 1. 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임
 * 2. 두 가지 연산
 * 2-1. 문자열 뒤에 A 추가
 * 2-2. 문자열 뒤에 B 추가하고 뒤집기
 * => 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램
 *
 * [풀이]
 * 1. s에서 t로 만들려면 2^50의 시간 복잡도 가짐 => 반대로 생각하면, 조건에 맞을 때만 동작!
 *
 * 2. 빼는 연산
 * 2-1. 문자열 뒤에 A 빼기
 * 2-2. 문자열 앞에 B 빼고 뒤집기
 *
 */
public class Main {
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();
        flag = false;

        recur(T, S);
        System.out.println(flag? 1: 0);
    }
    static void recur(String str, String S) {

        if(str.length() == S.length()) {
            if(str.equals(S)) {
                flag = true;
            }
            return;
        }

        //2-1. 문자열 뒤에 A 빼기
        if(str.charAt(str.length()-1) == 'A') {
            String substr = str.substring(0, str.length() - 1);
            recur(substr, S);
        }
        //2-2. 문자열 앞에 B 빼고 뒤집기
        if(str.charAt(0) == 'B') {
            String substr = new StringBuilder(str.substring(1, str.length())).reverse().toString();
            recur(substr, S);
        }
    }
}