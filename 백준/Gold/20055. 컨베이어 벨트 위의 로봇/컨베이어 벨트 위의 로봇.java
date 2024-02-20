import java.io.*;
import java.util.*;

/**
 * 20055. 컨베이어 벨트 위의 로봇
 * 1. 벨트 한 칸 회전 - 다음 번호 칸으로 이동 (2N번 칸은 1번 칸으로 이동)
 * 2. 1번 칸이 있는 위치 : 올리는 위치
 * 3. N번 칸이 있는 위치 : 내리는 위치
 *
 * 4. 컨베이어 벨트에 박스 모양 로봇을 하나씩 올리려고 한다.
 * 4-1. 로봇은 '올리는 위치 = 1번 칸이 있는 위치' 에만 올릴 수 있음
 * 4-2. 로봇은 '내리는 위치 = N번 칸이 있는 위치'에 도달하면 '즉시' 내림
 *      4-3. 로봇을 '올리는 위치에 올리거나' '어떤 칸으로 이동'하면 내구도 1 감소
 *      => 벨트 회전이 아닌 '로봇 이동'시 내구도 1 감소!!
 *
 * 5. 로봇을 옮기는 과정
 * 5-1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
 * 5-2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 '있으면' 이동 (이동 못하면 가만히 있음)
 *      5-2-2. 이동하려는 칸에 로봇이 없으면서 그 칸의 내구도가 1 이상 남아 있어야 함
 * 5-3. '올리는 위치 = 1번 칸'에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 * 5-4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료. 그렇지 않으면 5-1로 돌아감
 *
 */
public class Main {

    static int n,k;
    static int[][] belt;
    static int beltSize;
    static int answer;
    static int robotCount;
    static int count;
    static final int DURABILITY = 0, ROBOT = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        beltSize = 2 * n;
        belt = new int[beltSize][2];
        st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<beltSize; idx++) {
            belt[idx][0] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
        count = 0;


        while(true) {

            answer++;

            // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
            moveBelt();

            // 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있으'면' 이동
            moveRobot();

            // 올리는 위치 = 1번 칸'에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            liftRobot();

            // 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료. 그렇지 않으면 4-1로 돌아감
            if(count >= k) {
                System.out.println(answer);
                break;
            }
        }
    }

    static void moveBelt() {

        int[] firstBelt = new int[2];
        firstBelt[DURABILITY] = belt[beltSize - 1][DURABILITY];
        firstBelt[ROBOT] = belt[beltSize - 1][ROBOT];

        for(int idx = beltSize-1; idx >=1; idx--) {
            belt[idx][DURABILITY] = belt[idx - 1][DURABILITY];
            belt[idx][ROBOT] = belt[idx - 1][ROBOT];
        }

        // 로봇이 내리는 위치에 도달하면 즉시 내림
        if (belt[n-1][ROBOT] == 1) {
            belt[n-1][ROBOT] = 0;
            robotCount--;
        }

        belt[0][DURABILITY] = firstBelt[DURABILITY];
        belt[0][ROBOT] = firstBelt[ROBOT];
    }

    static void moveRobot() {

        // 가장 먼저 올라간 로봇부터 이동 시도
        for (int idx = n-2; idx >= 0; idx--) {

            if (belt[idx][ROBOT] == 1 && belt[idx+1][ROBOT] == 0 && belt[idx+1][DURABILITY] > 0) {

                // 현재 위치 로봇 제거
                belt[idx][ROBOT] = 0;
                // 로봇 이동
                belt[idx+1][ROBOT] = 1;
                belt[idx+1][DURABILITY]--;
                
                // 내구도 0인 개수 증가
                if (belt[idx+1][DURABILITY] == 0) count++;
            }
        }

        // 로봇이 내리는 위치에 도달하면 즉시 내림
        if (belt[n-1][ROBOT] == 1) {
            belt[n-1][ROBOT] = 0;
            robotCount--;
        }
    }


    static void liftRobot() {

        if(belt[0][DURABILITY] <= 0) {
            return;
        }

        // 올리는 위치에 로봇 올림
        belt[0][ROBOT] = 1;
        belt[0][DURABILITY]--;
        if(belt[0][DURABILITY] == 0) count++;
        robotCount++;
    }
}