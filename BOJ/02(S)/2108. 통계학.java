import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        // 입력
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 산술 평균 : N개의 수들의 합을 N으로 나눈 값
        double sum = 0;
        for(int i=0; i<N; i++) {
            sum += arr[i];
        }
        answer = (int)Math.round(sum/N);
        System.out.println(answer);

        // 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
        Arrays.sort(arr);
        answer = arr[N / 2];
        System.out.println(answer);

        // 최빈값 : N개의 수들 중 가장 많이 나타나는 값
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        ArrayList<Integer> maxArr = new ArrayList<>();
        int maxCnt=0;
        for(int key : map.keySet()) {
            if(map.get(key) > maxCnt) {
                maxCnt = map.get(key);
                maxArr.clear();
                maxArr.add(key);
            }
            else if(map.get(key) == maxCnt) {
                maxArr.add(key);
            }
        }

        if(maxArr.size() == 1) answer = maxArr.get(0);
        else {
            Collections.sort(maxArr);
            answer = maxArr.get(1);
        }

        System.out.println(answer);

        // 범위 : 최대값 - 최소값 차이
        answer = arr[N - 1] - arr[0];
        System.out.println(answer);
    }
}