import java.io.*;
import java.util.*;

public class Main {
    public static int count(int[] arr, int capacity) {

        int cnt = 1;    // DVD 장수
        int sum = 0;   // DVD에 담아낸 곡들의 합

        for(int x : arr) {
            sum += x;
            if(sum > capacity) {
                cnt++;
                sum = x;
            }
        }
        return cnt;
    }
    public static int solution(int n, int m, int[] arr) {

        int answer = 0;

        int lt = Arrays.stream(arr).max().getAsInt();
        // arr의 최대값이 DVD가 될 수 있는 최소 용량(DVD에 arr 최대값 하나만 들어간 경우)
        int rt = Arrays.stream(arr).sum();

        while(lt <= rt) {
            int mid = (lt + rt) / 2;    // DVD 한장 용량

            if(count(arr, mid) <= m) {
                answer = mid;
                rt = mid - 1;
            } else lt = mid + 1;

        }


        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(solution(n,m,arr));


    }
}