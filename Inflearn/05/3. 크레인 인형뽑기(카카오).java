import java.util.Scanner;
import java.util.Stack;

public class Main3 {
    public static int solution(int n, int m, int[][] arr, int[] moves) {

        int answer = 0;
        Stack<Integer> st = new Stack<>();

        for(int pos : moves) {
            for(int i=0; i<arr.length; i++) {
                if(arr[i][pos-1] != 0) {
                    int tmp = arr[i][pos - 1];
                    arr[i][pos - 1] = 0;
                    if(!st.isEmpty() && st.peek() == tmp) {
                        st.pop();
                        answer+=2;
                    } else st.push(tmp);
                    break;
                }

            }
        }
/*
        int i;
        for(int pos : moves) {
            i = 0;
            while(i < n && arr[i][pos-1]==0) i++;

            if(i == n) continue;

            if(!st.isEmpty() && st.peek() == arr[i][pos-1]) {
                st.pop();
                answer+=2;
            } else st.push(arr[i][pos-1]);

            arr[i][pos-1] = 0;
        }
*/


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][n];

        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int m = sc.nextInt();
        int[] moves = new int[m];

        for(int i=0;i<m;i++) {
            moves[i] = sc.nextInt();
        }

        System.out.println(solution(n,m,arr,moves));
    }

}