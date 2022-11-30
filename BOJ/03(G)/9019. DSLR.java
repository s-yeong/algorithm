import java.util.*;
import java.io.*;

class Command {
    String com;
    int num;

    public Command(String com, int num) {
        this.com = com;
        this.num = num;
    }
}

class Main {


    static int[] ch;
    static StringBuilder sb = new StringBuilder();
    static int D (int cur){

        int tmp = cur*2;
//        if(tmp > 9999) tmp = tmp % 10000;
        return tmp % 10000;
    }

    static int S(int cur){
        int tmp = cur-1;
        if(cur == 0) tmp = 9999;
        return tmp;
    }

    static int L(int cur) {

        int tmp1 = (cur % 1000);   // 앞자리 제외 수
        int tmp2 = cur/1000;    // 앞자리 수
        return tmp1*10 + tmp2;
    }

    static int R(int cur) {
        int tmp1 = cur / 10;    // 뒷자리 제외 수
        int tmp2 = cur % 10;    // 뒷자리 수
        return tmp2*1000 + tmp1;
    }

    static void BFS(int L, int A, int B) {

        Queue<Command> Q = new LinkedList<>();
        Q.offer(new Command("", A));

        while(!Q.isEmpty()) {
            Command tmp = Q.poll();
            // 정답인 경우
            if(tmp.num == B) {
                sb.append(tmp.com).append("\n");
                return;
            }

            // D
            int n = D(tmp.num);
            if(ch[n] == 0) {
                Q.offer(new Command(tmp.com + "D", n));
                ch[n] = 1;
            }
            // S
            n = S(tmp.num);
            if(n>=0 && ch[n] == 0) {
                Q.offer(new Command(tmp.com + "S", n));
                ch[n] = 1;
            }
            // L
            n = L(tmp.num);
            if(ch[n] == 0) {
                Q.offer(new Command(tmp.com + "L", n));
                ch[n] = 1;
            }
            // R
            n = R(tmp.num);
            if(ch[n] == 0) {
                Q.offer(new Command(tmp.com + "R", n));
                ch[n] = 1;
            }

        }


    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tc=0 ; tc<T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());   // 레지스터 초기값
            int B = Integer.parseInt(st.nextToken());   // 레지스터 최종값

            // A에서 B로 변환하기 위해 필요한 "최소한의 명령어" 나열을 출력
            // A 와 B는 모두 0 이상 10,000 미만
            ch = new int[10000];
            BFS(0, A, B);
        }

        System.out.println(sb);


    }
}
