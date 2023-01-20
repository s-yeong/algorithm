import java.io.*;
import java.util.*;

class Main {
    static int w,h;
    static char[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            // 가로 w 세로 h
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 0 0 -> break
            if(w==0&&h==0) break;

            // 로봇 시작 위치 + 더러운 칸 리스트
            list = new ArrayList<>();
            board = new char[h][w];

            for(int i=0; i<h; i++) {
                board[i] = br.readLine().toCharArray();
                for(int j=0; j<w; j++) {
                    // 로봇 시작 위치면, 0번째 인덱스에 추가
                    if(board[i][j] == 'o') list.add(0, new int[]{j, i});
                    // 더러운 칸이면, 추가
                    if(board[i][j] == '*') list.add(new int[]{j, i});
                }
            }

            /*
            1. 로봇 청소기는 더러운 칸을 방문해서 깨끗한 칸으로 변경함
            2. 로봇 청소기는 가구로 이동 불가(가구=벽)
            3. 인접한 칸 이동, 같은 칸 여러번 방문 가능
            4. 더러운 칸에 접근 못해서 존재하면 -1 출력
             */

            // --더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동 횟수의 최솟값--
            // .: 깨끗한 칸, *: 더러운 칸, x: 가구, o: 로봇 청소기의 시작 위치

            /*
            1. 더러운 칸을 어디부터 갈지? -> 모든 순서 고려
            -> 더러운 칸 순열로 방문 순서 구해서 그 중 최소값 구하기 -> DFS
            2. 매번 순열로 정한 후 이동할 수는 없으니 각각의 더러운 칸, 로봇 시작 위치 사이의 거리를 구하기 (최소값)
            -> 가구가 있기 때문에 좌표로 구할 수 없음 -> 직접 이동시켜야함 -> BFS
            -> 더러운 칸 거리 배열 (+로봇 시작 위치까지) + 위치 좌표 ArrayList
            로봇 시작 위치 좌표와 더러운 칸 위치 좌표를 구별하기 위해 로봇 시작 위치는 0번째 인덱스로
            ("순열을 구할 때 로봇 시작 위치가 처음"이라는 조건이 필요!!)
             */
            sb.append(solution()).append("\n");
        }
        // 출력
        System.out.print(sb);
    }
    static int[][] dis;
    static ArrayList<int[]> list;
    static int min;
    static int solution() {

        // 최소값(=정답)
        min = Integer.MAX_VALUE;

        // 2. 각각의 더러운 칸, 로봇 시작 위치 사이의 거리를 구하기 (최소값)
        int len = list.size();
        dis = new int[len][len];
        for(int i=0; i<len; i++) {
            for(int j=i+1; j<len; j++) {
                // i번째 더러운 칸과 j번째 더러운 칸 사이 거리 구하기
                int distance = BFS(list.get(i)[0], list.get(i)[1], list.get(j)[0], list.get(j)[1]);
                // 더러운 칸 접근X
                if(distance == -1) {
                    return -1;
                }

                // 거리 넣기 (양방향으로)
                dis[i][j] = dis[j][i] = distance;
                }
            }

        // 1. 더러운 칸 순열로 방문 순서 구해서 그 중 최소값 구하기
        // 순열을 구할 때 로봇 시작 위치가 처음 => 0->(1~list.size 순열)

        int[] pm = new int[len];
        pm[0] = 0;
        // 순열 위한 체크 배열
        boolean[] pmCh = new boolean[len];
        DFS(1, pm, pmCh);

        return min;
    }

    static void DFS(int L, int[] pm, boolean[] pmCh) {

        // 순열 완성
        if(L==list.size()) {
            // 이동 거리 세기
            int sum = 0;
            for(int i=1; i<list.size(); i++) {
                // pm[i] -> list의 인덱스
                sum += dis[pm[i-1]][pm[i]];
            }

            min = Math.min(min, sum);
        }

        else {
            for(int i=1; i<list.size(); i++) {
                if(!pmCh[i]) {
                    pmCh[i] = true;
                    pm[L] = i;
                    DFS(L + 1, pm, pmCh);
                    pmCh[i] = false;
                }
            }
        }


    }



    // 상 하 좌 우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int BFS(int xs, int ys, int xe, int ye) {

        Queue<int[]> Q = new LinkedList<>();
        boolean[][] ch = new boolean[h][w];
        Q.offer(new int[]{xs, ys});
        ch[ys][xs] = true;
        int L = 0;

        while(!Q.isEmpty()) {

            int len = Q.size();
            while(len --> 0) {

                int[] cur = Q.poll();
                // 도착 지점 도착시 종료
                if(cur[0] == xe && cur[1] == ye) return L;

                // 이동
                for(int i=0; i<4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 범위 + 방문 체크 + 가구X
                    if(nx >= 0 && ny >= 0 && nx<w && ny<h && !ch[ny][nx] && board[ny][nx] != 'x') {
                        ch[ny][nx] = true;
                        Q.offer(new int[]{nx,ny});
                    }
                }
            }
            L++;
        }
        // 만약 그 칸으로 이동 못하면, -> 더러운 칸 접근 X
        return -1;
    }
}