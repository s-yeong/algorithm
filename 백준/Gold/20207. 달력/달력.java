import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 20207. 달력
 * 1. 연속된 두 일자에 각각 일정이 1개 이상 있다면, 이를 일정이 연속되었다고 표현한다.
 * 2. 연속된 모든 일정은 하나의 직사각형에 포함되어야 한다.
 * 3. 연속된 일정을 모두 감싸는 가장 작은 직사각형의 크기만큼 코팅지를 오린다.
 *
 * 달력
 * 1. 일정은 시작~종료 날짜 포함
 * 2. 시작일이 가장 앞선 일정부터 차례대로 채워진다.
 * 3. 시작일이 같을 경우 일정의 기간이 긴 것이 먼저 채워진다.
 * 4. 일정은 가능한 최 상단에 배치된다.
 * 5. 일정 하나의 세로의 길이는 1아고, 하루의 폭은 1이다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 365일까지 일정있는 경우, 366일에서 계산
        int[] calender = new int[367];
        for(int idx=0; idx<n; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int day=start; day<=end; day++) {
                calender[day]++;
            }
        }

        int answer = 0;
        int start = 0;
        int height = 0;
        boolean flag = false;
        for(int day=1; day<=366; day++) {
            if(flag && calender[day] > 0) {
                height = Math.max(height, calender[day]);
            }
            else if(flag && calender[day] == 0) {
                answer += (day - start) * height;
                flag = false;
                height = 0;
                start = 0;
            }
            else if(!flag && calender[day] > 0) {
                start = day;
                height = calender[day];
                flag = true;
            }
        }
        System.out.println(answer);
    }
}
