import java.io.*;
import java.util.*;


/**
 * 14719. 빗물
 * 고이는 빗물 총량 구하기
 *
 * [풀이]
 * 0. W,H <= 500
 * 1. 처음과 마지막은 빗물 고일 수 없음
 * 2. 현재 인덱스 기준으로 왼쪽 최대 높이, 오른쪽 최대 높이 구하기
 * 3. 왼쪽, 오른쪽 최대 높이 보다 작은 경우 빗물 고임
 * 3-1. 둘 중 작은 높이에 빗물 계산
 */
public class Main {

    static int height, width;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        int[] arr = new int[width];

        st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<width; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;

        for(int idx=1; idx<width-1; idx++) {

            //2. 현재 인덱스 기준으로 왼쪽 최대 높이, 오른쪽 최대 높이 구하기
            int leftMax = 0;
            int rightMax = 0;

            for(int left=idx-1; left>=0; left--) {
                leftMax = Math.max(leftMax, arr[left]);
            }
            for(int right=idx+1; right<width; right++) {
                rightMax = Math.max(rightMax, arr[right]);
            }

            //3. 왼쪽, 오른쪽 최대 높이 보다 작은 경우 빗물 고임
            int targetMax = Math.min(leftMax, rightMax);
            if(arr[idx] < targetMax) {
                //3-1. 둘 중 작은 높이에 빗물 계산
                sum += targetMax - arr[idx];
            }
        }

        System.out.println(sum);


    }
}