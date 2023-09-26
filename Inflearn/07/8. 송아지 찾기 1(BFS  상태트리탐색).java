import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    int answer = 0;
    int[] dis = {1, -1, 5}; // 앞으로 1칸, 뒤로 1칸, 앞으로 5칸
    int[] ch;   // 체크 배열
    Queue<Integer> Q = new LinkedList<>();

    public int BFS(int s, int e) {

        ch = new int[10001];    // 좌표 점 1 부터 10,000까지
        ch[s]=1;
        Q.offer(s);
        int L = 0;
        while(!Q.isEmpty()) {

            int len = Q.size();
            for(int i=0; i<len; i++) {

                int x = Q.poll();
//                if(x==e) return L;
                for(int j=0; j<3; j++) {    // 점프
                    int nx = x + dis[j];
                    if(nx==e) return L+1;  // 큐에 집어넣기 전에 확인
                    if(nx >=1 && nx <= 10000 && ch[nx] == 0) {  // 방문 안한 경우
                        // 음수로 갈 수도 있으니 nx 범위 정해줌
                        ch[nx] = 1;
                        Q.offer(nx);
                    }
                }
            }
            L++;
        }


        return answer;
    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int e = sc.nextInt();
        Main T = new Main();
        System.out.println(T.BFS(s, e));
    }
}