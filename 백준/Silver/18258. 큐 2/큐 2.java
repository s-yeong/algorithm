import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Queue queue = new Queue();
        for(int idx=0; idx<n; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if ("push".equals(command)) {
                queue.push(Integer.parseInt(st.nextToken()));
            }
            else if("pop".equals(command)) {
                sb.append(queue.pop() + "\n");
            }
            else if("size".equals(command)) {
                sb.append(queue.size() + "\n");
            }
            else if("empty".equals(command)) {
                sb.append(queue.empty() + "\n");
            }
            else if("front".equals(command)) {
                sb.append(queue.front() + "\n");
            }
            else {
                sb.append(queue.back() + "\n");
            }
        }
        System.out.print(sb);
    }

    static class Queue {

        private final int[] elements = new int[2000001];

        private int head = 0;

        private int tail = 0;

        void push(int num) {
            elements[tail++] = num;
        }
        int pop() {
            if(empty() == 1) return -1;
            return elements[head++];
        }
        int size() {
            return tail - head;
        }
        int empty() {
            if(head == tail) return 1;
            else return 0;
        }

        int front() {
            if(empty() == 1) return -1;
            return elements[head];
        }

        int back() {
            if(empty() == 1) return -1;
            return elements[tail-1];
        }
    }

}
