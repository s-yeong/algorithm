import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 8979. 올림픽
 * 1. 어느 나라가 잘 했는지 결정
 * 1-1. 금메달 수가 더 많은 나라
 * 1-2. 금메달 수가 같으면, 은메달 수가 더 많은 나라
 * 1-3. 금, 은메달 수가 모두 같으면, 동메달 수가 더 많은 나라
 *
 * => 각 국가의 금, 은, 동메달 정보를 입력받아서, 어느 국가가 몇 등을 했는지 알려주는 프로그램
 *
 */
public class Main {

    static final int NUM = 0,GOLD = 1, SILVER = 2, BRONZE = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nation_count = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[][] nation_arr = new int[nation_count][4];

        for(int count=0; count<nation_count; count++) {

            st = new StringTokenizer(br.readLine());
            int nation_num = Integer.parseInt(st.nextToken());

            nation_arr[count][NUM] = nation_num;

            for(int idx=1; idx<=3; idx++) {
                nation_arr[count][idx] = Integer.parseInt(st.nextToken());
            }
        }

        // 정렬
        Arrays.sort(nation_arr, (o1, o2) -> {
            if(o1[GOLD] != o2[GOLD]) return o2[GOLD] - o1[GOLD];
            else if(o1[SILVER] != o2[SILVER]) return o2[SILVER] - o1[SILVER];
            else return o2[BRONZE] - o1[BRONZE];
        });

        int rank = 1;
        int count = 1;
        // 1등인 경우,
        if(nation_arr[0][NUM] == target) {
            System.out.println(rank);
            System.exit(0);
        }

        // 동메달까지 같은 경우 rank 더하기 X
        // 정렬했기 때문에 같은 경우는 오른쪽 인덱스만 살피면 된다.
        for(int idx=1; idx<nation_count; idx++) {

            if(nation_arr[idx-1][GOLD] > nation_arr[idx][GOLD]) {
                if(count != rank) rank = count;
                rank++;
                count++;
            }
            else if(nation_arr[idx-1][SILVER] > nation_arr[idx][SILVER]) {
                if(count != rank) rank = count;
                rank++;
                count++;
            }
            else if(nation_arr[idx-1][BRONZE] > nation_arr[idx][BRONZE]) {
                if(count != rank) rank = count;
                rank++;
                count++;
            }
            else {
                count++;
            }


            if(nation_arr[idx][NUM] == target) {
                System.out.println(rank);
                break;
            }
        }
    }
}