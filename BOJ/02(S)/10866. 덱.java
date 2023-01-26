
import java.io.*;
import java.util.*;

class Main {
    static int[] arr = new int[20001];
    static int front = 10000;
    static int back = 10000;
    static int size = 0;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if(command.equals("push_front")) {
                push_front(Integer.parseInt(st.nextToken()));
            }

            else if(command.equals("push_back")) {
                push_back(Integer.parseInt(st.nextToken()));
            }

            else if(command.equals("pop_front")) {
                sb.append(pop_front()).append("\n");
            }

            else if(command.equals("pop_back")) {
                sb.append(pop_back()).append("\n");
            }

            else if(command.equals("size")) {
                sb.append(size()).append("\n");
            }

            else if(command.equals("empty")) {
                sb.append(empty()).append("\n");
            }

            else if(command.equals("front")) {
                sb.append(front()).append("\n");
            }

            else if(command.equals("back")) {
                sb.append(back()).append("\n");
            }
        }
        System.out.println(sb);
    }

    // front : 값을 둘 자리
    // back : 값을 둔 자리

    static int empty() {
        // 덱이 비어있으면 1을, 아니면 0을 출력한다.
        if(size == 0) return 1;
        else return 0;
    }
    static void push_front(int num) {
        arr[front--] = num;
        size++;
    }

    static void push_back(int num) {
        arr[++back] = num;
        size++;
    }

    static int pop_front() {
        if(empty() == 1) return -1;
        int tmp = arr[front+1];
        front++;
        size--;
        return tmp;
    }

    static int pop_back() {
        if(empty() == 1) return -1;
        size--;
        return arr[back--];
    }

    static int size() {
        return size;
    }

    static int front() {
        if(empty() == 1) return -1;
        return arr[front+1];
    }

    static int back() {
        if(empty() == 1) return -1;
        return arr[back];
    }

}