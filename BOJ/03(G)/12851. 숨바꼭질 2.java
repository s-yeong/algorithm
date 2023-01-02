import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 방법의 수를 구하려면 x초에 똑같은 위치인 값들은 다 넣고 이후에 방문 처리해야함
        // x+1초 뒤에 나온건 체크할 필요X -> x초 넘어간 뒤에 x+1초에서 체크하면 된다!
        // => 큐에서 꺼낼 떄 체크
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 출력 - 가장 빠른 시간, 동생을 찾는 방법의 수
        if(N==K) System.out.println(0 + "\n" + 1);
        else {
            int[] answer = BFS(N, K);
            for (int x : answer) System.out.println(x);
        }
    }

    static int[] dx = {-1, 1, 0};
    static int[] ddx = {0, 0, 2};
    static int[] BFS(int N, int K) {

        Queue<Integer> Q = new LinkedList<>();
        boolean[] ch = new boolean[100001];
        // 수빈이 시점 큐에 넣기
        Q.offer(N);
        // 방문/체크 - 시간이 증가했을 떄 다시 수빈이 지점으로 돌아온 것은 어차피 최소가 될 수 없음
        ch[N] = true;

        int[] answer = new int[2];  // 빠른 시간, 방법의 수
        int time = 0;
        while(!Q.isEmpty()) {

            int len = Q.size();
            while(len --> 0) {

                // 현재 위치
                int x = Q.poll();
                ch[x] = true;

                // 이동
                for(int i=0; i<3; i++) {

                    int nx = dx[i] != 0 ? x + dx[i] : x * ddx[i];

                    // 동생 찾으면
                    if(nx == K) {
                        answer[0] = time+1;
                        answer[1]++;
                    }

                    // 음수로 이동하기 때문에 범위 항상 고려해야함
                    if (nx >= 0 && nx <= 100000 && !ch[nx]) {
                        Q.offer(nx);
                    }
                }
            }
            // 그 time일 때 동생을 한 번이라도 찾으면 time+1 일 때 이후는 볼 필요 없음
            if(answer[1] != 0) break;
            time++;
        }
        return answer;
    }
}