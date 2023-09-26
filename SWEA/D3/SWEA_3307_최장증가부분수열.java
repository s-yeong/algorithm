import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;
import java.util.StringTokenizer;


/**
 * 3307. 최장 증가 부분 수열
 * 1. 배열의 "순서를 유지"하면서 증가하는 가장 긴 부분 배열
 * 2. N=1000 -> 2^N (부분집합) -> 시간초과
 * 3. DP => O(N^2) 풀기
 * 3-1. dp = "해당 인덱스"를 부분 수열의 끝으로 했을 경우에 LIS 길이
 * 3-2. "해당 인덱스" 값보다 작은 값 중 최대값(LIS) + 1
 * 3-3. dp 배열에서 최대값이 답
 */
public class SWEA_3307_최장증가부분수열 {

    static int[] arr, dp;
    static int arrLen;


    public static void main(String[] args) throws IOException {

        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            arrLen = Integer.parseInt(br.readLine());
            arr = new int[arrLen];
            st = new StringTokenizer(br.readLine());
            for (int idx = 0; idx < arrLen; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }

            //3-1. dp = "해당 인덱스"를 부분 수열의 끝으로 했을 경우에 LIS 길이
            dp = new int[arrLen];

            for (int endIdx = 0; endIdx < arrLen; endIdx++) {
                int endNum = arr[endIdx];
                for (int targetIdx = endIdx; targetIdx >= 0; targetIdx--) {
                    //3-2. "해당 인덱스" 값보다 작은 값 중 최대값(LIS) + 1
                    if(endNum >= arr[targetIdx] && dp[endIdx] < dp[targetIdx] + 1) {
                        dp[endIdx] = dp[targetIdx] + 1;
                    }
                }
            }


            //3-3. dp 배열에서 최대값이 답
            int max = 0;
            for (int idx = 0; idx < arrLen; idx++) {
                max = Math.max(max, dp[idx]);
            }

            sb.append("#" + testCase + " " + max + "\n");
        }

        System.out.print(sb);
    }

}
