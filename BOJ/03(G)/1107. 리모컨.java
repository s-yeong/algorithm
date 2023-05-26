import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /**
         * 리모컨
         * 버튼 0~9(고장난 버튼 있음), +, -
         * 0에서 -를 누른 경우 채널이 변하지 않고, 채널은 무한대 만큼 있다.
         * 현재 채널 100
         *
         * 채널 N으로 이동하기 위해 `최소` 몇 번 눌러야 하는지
         * 최소 거리, 비용 동일 -> bfs
         * => +,-와 버튼을 누를 때 구분해야함 -> yes, no로 구분
         * => 체크배열도 구분해야함!!
         *      100 -> 99 -> 100으로 갔을 때 방문해버리면
         *      1000을 버튼으로 구할 수 없기 때문에!
         */

        int n = Integer.parseInt(br.readLine());    // 채널 n
        int m = Integer.parseInt(br.readLine());    // 고장난 버튼 수 m

        StringTokenizer st = null;
        if(m!=0) st = new StringTokenizer(br.readLine());

        // 리모컨 버튼
        boolean[] button = new boolean[10];
        Arrays.fill(button, true);
        for(int i=0; i<m; i++) {
            int x = Integer.parseInt(st.nextToken());
            button[x] = false;
        }

        int answer = bfs(n, button);
        System.out.println(answer);
    }
    static int bfs(int n, boolean[] button) {

        String end = String.valueOf(n);

        // 채널 100에서 n으로 가기 위한 최소 거리
        Queue<String[]> Q = new LinkedList<>();
        // String[0] : 버튼, String[1]:yes=버튼 누르는 상태, no=버튼 누른상태
        boolean[] ch_yes = new boolean[1000001];
        boolean[] ch_no = new boolean[1000001];
        int L = 0;
        Q.offer(new String[]{"100", "no"});
        ch_no[100] = true;
        Q.offer(new String[]{"", "yes"});

        while(!Q.isEmpty()) {

            int len = Q.size();

            while(len --> 0) {

                String[] cur = Q.poll();

                if(end.equals(cur[0])) {
                    return L;
                }

                // +, - 버튼
                if("no".equals(cur[1])) {
                    int next_plus = Integer.parseInt(cur[0]) + 1;
                    if (next_plus >= 0 && next_plus <= 1000000 && !ch_no[next_plus]) {
                        ch_no[next_plus] = true;
                        Q.offer(new String[]{String.valueOf(next_plus), "no"});
                    }
                    int next_minus = Integer.parseInt(cur[0]) - 1;
                    if (next_minus >= 0 && next_minus <= 1000000 && !ch_no[next_minus]) {
                        ch_no[next_minus] = true;
                        Q.offer(new String[]{String.valueOf(next_minus), "no"});
                    }
                }

                // 0~9 버튼 =>
                if("yes".equals(cur[1])) {
                    for (int i = 0; i <= 9; i++) {

                        // 고장난 버튼이면, continue
                        if (!button[i]) continue;

                        int next;
                        next = Integer.parseInt(cur[0] + i);

                        if (next >= 0 && next <= 1000000 && !ch_yes[next]) {
                            ch_yes[next] = true;
                            ch_no[next] = true;
                            Q.offer(new String[]{String.valueOf(next), "yes"});
                            Q.offer(new String[]{String.valueOf(next), "no"});
                        }
                    }
                }
            }
            L++;
        }
        return 0;
    }
}
