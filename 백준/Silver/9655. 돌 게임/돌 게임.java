import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 9655. 돌 게임
 * 1. 상근이가 먼저 시작
 * 2. 마지막 돌을 가져 가는 사람이 게임 이김
 * 3. 돌은 1개 또는 3개 가져갈 수 있다.
 *
 * [풀이]
 * 홀수면, 상근이가, 짝수면 창영이가 승
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stone_count = Integer.parseInt(br.readLine());
        String answer = (stone_count % 2 == 0) ? "CY" : "SK";
        System.out.println(answer);
    }
}