import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        // O(N^2) => 100,000 * 100,000 => 시간초과
        // => 이진 탐색 O(logN)
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(binarySearch(A, num)) {
                sb.append(1).append("\n");
            } else sb.append(0).append("\n");
        }

        System.out.print(sb);
    }

    static boolean binarySearch(int[] A, int num) {


        int lt = 0;
        int rt = A.length - 1;

        while(lt<=rt) {

            int mid = (lt + rt) / 2;
            if(A[mid] == num) {
                return true;
            }
            else if(A[mid] > num) {
                rt = mid - 1;
            }
            else {
                lt = mid + 1;
            }
        }
        return false;
    }

}