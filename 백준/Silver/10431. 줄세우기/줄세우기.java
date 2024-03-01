import java.io.*;
import java.util.*;

/**
 * 10431. 줄세우기
 * 0. 아무나 한 명을 뽑아 줄의 맨 앞에 세운다. 다음부터는 학생이 한 명씩 줄의 맨 뒤에 서면서 다음 과정을 거친다.
 * 1. 자기 앞에 자기보다 키가 큰 학생이 없다면 그냥 그 자리에 서고 차례가 끝난다.
 * 2. 자기 앞에 자기보다 키가 큰 학생이 한 명 이상 있다면 그중 가장 앞에 있는 학생(A)의 바로 앞에 선다. 이때, A부터 그 뒤의 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러서게 된다.
 */
public class Main {

    static final int CLASS_SIZE = 20;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int testCase = Integer.parseInt(st.nextToken());
            int[] arr = new int[CLASS_SIZE];
            for (int idx = 0; idx < CLASS_SIZE; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;

            for(int idx=1; idx< CLASS_SIZE; idx++) {
                int num = arr[idx];
                // 자기 앞에 자기보다 키가 큰 학생 찾기
                for(int leftIdx=0; leftIdx<idx; leftIdx++) {
                    // 자기보다 키가 크면
                    if(arr[leftIdx] > num) {
                        // 뒤로 한 칸씩 물러나기
                        for(int targetIdx=idx-1; targetIdx>=leftIdx; targetIdx--) {
                            arr[targetIdx+1] = arr[targetIdx];
                            answer++;
                        }
                        // 그 자리에 서기
                        arr[leftIdx] = num;
                        break;
                    }
                }
            }
            sb.append(testCase + " " + answer + "\n");
        }
        System.out.println(sb);


    }
}