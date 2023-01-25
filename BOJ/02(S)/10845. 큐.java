
import java.io.*;
import java.util.*;

class Main {
    static int[] arr = new int[100001]; // 정수 저장 배열(->큐)
    static int left = 0; // 앞 위치
    static int right = 0; // 뒤 위치
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(N --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            // 명령 처리
            switch (command) {
                case "push" :
                    int num = Integer.parseInt(st.nextToken());
                    push(num);
                    break;
                case "pop" :
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "front":
                    sb.append(front()).append("\n");
                    break;
                case "back":
                    sb.append(back()).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }

    static void push(int num) {
        arr[right++] = num;
    }

    static int empty() {

        // 큐가 비어있으면 1
        if(right - left == 0) return 1;
        // 아니면 0
        else return 0;
    }

    static int pop() {

        // 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력
        if(empty() == 1) return -1;

        // 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다.
        int tmp = arr[left];
        left++; // 현재 위치 감소
        return tmp;
    }

    static int size() {
        return right-left;
    }

    static int front() {

        //만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력
        if(empty() == 1) return -1;

        //큐의 가장 앞에 있는 정수를 출력
        return arr[left];
    }

    static int back() {
        //만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력
        if(empty() == 1) return -1;

        // 큐의 가장 뒤에 있는 정수를 출력
        return arr[right-1];
    }


}