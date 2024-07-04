import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] arr, int location) {
        int answer = 0;
        //특정 프로세스가 몇 번째로 실행되는지
        
        Queue<int[]> Q = new ArrayDeque<>();
        
        // 0:위치 1:우선순위
        for(int idx=0; idx<arr.length; idx++) {
            Q.offer(new int[] {idx, arr[idx]});
        }
        
        Arrays.sort(arr);
        Stack<Integer> st = new Stack<>();
        for(int idx=0; idx<arr.length; idx++) {
            st.push(arr[idx]);
        }
        
        
        int count = 0;
        int max = st.pop();
        while(!Q.isEmpty()) {
            int[] temp = Q.poll();
            // 우선순위가 더 높은 프로세스가 있다면
            if(temp[1] < max) {
                // 다시 넣기
                Q.offer(temp);
            }
            // 꺼낸 프로세스 실행
            else {
                count++;
                if(temp[0] == location) {
                    return count;
                }
                // 큐 내부 최대값 갱신
                max = st.pop();
            }
        }
        
        return answer;
    }
}