import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int len = jobs.length;
        
        /**
        * 각 작업의 요청부터 종료까지 걸린 시간의 평균 최소값 구하기
        * [0]ms 시점에 [1] 걸리는 작업 요청이 들어온다.
        * 현재 시작할 수 있는 것 중에서 요청 시간이 짧은 것을 선택?
        * 시작할 수 있는지 어떻게 아나..
        * 1. jobs 시간으로 정렬하기
        * 2. 시간 기준으로 시작할 수 있는 것들만 우선수위 큐에 넣기
        **/
        
        PriorityQueue<int[]> pQ = new PriorityQueue<>((o1, o2)
                                                     -> o1[1] - o2[1]);
        Arrays.sort(jobs, (o1,o2) 
                    -> o1[0] - o2[0]);
        
        int time = 0;
        int i = 0;
        int count = 0;
        while(count < len) {
            
            
            for(; i<len; i++) {
                if(time >= jobs[i][0]) {
                    System.out.println("pq.offer");
                    pQ.add(jobs[i]);
                }
                else break;
            }
            
            
            // 현재 time 기준 가능한 것들 중 가장 짧은 시간
            if(!pQ.isEmpty()) {
                count++;
                int[] tmp = pQ.poll();
                System.out.println("pq.poll");
                
                // 시간 흐름
                time+=tmp[1];
                
                // 요청부터 종료까지 시간
                answer += time-tmp[0];
                System.out.println(answer);
            }
            // 없으면, time을 다음 요청의 처음으로 맞춰줌
            else time = jobs[i][0];
        }
        
        
        return answer/len;
    }
}