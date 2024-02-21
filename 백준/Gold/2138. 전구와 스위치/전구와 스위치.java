import java.io.*;

/**
 * 2138. 전구와 스위치
 * 1. i번 스위치 누르면, i-1, i, i+1 세 개의 전구의 상태가 바뀜
 * 2. 1번은 1,2번 N번은 N-1, N번 전구 상태 바뀜
 * => 스위치를 최소 몇 번 눌러야 하는지 구하기
 *
 */
public class Main {

    static int n;
    static int[] count;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] current = new int[n];
        int[] current2 = new int[n];
        int[] target = new int[n];
        count = new int[2];

        String[] input = br.readLine().split("");
        for(int idx=0; idx<n; idx++) {
            current[idx] = current2[idx] = Integer.parseInt(input[idx]);
        }
        input = br.readLine().split("");
        for(int idx=0; idx<n; idx++) {
            target[idx] = Integer.parseInt(input[idx]);
        }

        // 처음 스위치를 누르냐
        changeCurrent(current, 0);
        count[0]++;
        for(int idx=1; idx<n; idx++) {
            // 이전 전구 비교
            // 다르면, 스위치 바꾸기
            if(current[idx-1] != target[idx-1]) {
                changeCurrent(current, idx);
                count[0]++;
            }
        }
        // 마지막 전구가 타겟 전구와 같아야 한다.
        // 다르면 만들 수 없는 상태
        if(current[n-1] != target[n-1]) {
            // 만들 수 없는 경우 n+1로 설정 (최소값은 최대 n까지 가능하다)
            count[0] = n+1;
        }

        // 처음 스위치를 안누르냐
        for(int idx=1; idx<n; idx++) {
            // 이전 전구 비교
            // 다르면, 스위치 바꾸기
            if(current2[idx-1] != target[idx-1]) {
                changeCurrent(current2, idx);
                count[1]++;
            }
        }
        // 마지막 전구가 타겟 전구와 같아야 한다.
        // 다르면 만들 수 없는 상태
        if(current2[n-1] != target[n-1]) {
            // 만들 수 없는 경우 n+1로 설정 (최소값은 최대 n까지 가능하다)
            count[1] = n+1;
        }
        int answer = Math.min(count[0], count[1]);
        if(answer == n+1) answer = -1;
        System.out.println(answer);
    }

    static void changeCurrent(int[] current, int targetIdx) {

        current[targetIdx] = current[targetIdx] == 0 ? 1 : 0;
        if(targetIdx == 0) {
            current[targetIdx+1] = current[targetIdx+1] == 0? 1 : 0;
        }
        else if(targetIdx == n-1) {
            current[targetIdx-1] = current[targetIdx-1] == 0? 1 : 0;
        }
        else {
            current[targetIdx - 1] = current[targetIdx - 1] == 0 ? 1 : 0;
            current[targetIdx + 1] = current[targetIdx + 1] == 0 ? 1 : 0;
        }
    }
}
