import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 그 시간에 가능한 것들만 우선순위 큐 넣기
        PriorityQueue<Job> pQ = new PriorityQueue<>();
        int len = jobs.length;
        int currenttime = 0;
        
        // 정렬
        Arrays.sort(jobs, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        
        // 처음 시간에 가능한 job 우선순위 큐 넣기
        int idx = 0;
        
        // 계산
        while(true) {
            // 현재 시간 대 가능한 최소 작업 시간
            if(!pQ.isEmpty()) {
                Job minJob = pQ.poll();
                currenttime += minJob.time;
                answer += currenttime - minJob.start;
            }
            // 비어있으면, 시간 늘리기
            else {
                currenttime = jobs[idx][0];
            }
            // 현재 시간 기준 작업 가능한 것들 넣기
            for(;idx<len;idx++) {
                int start = jobs[idx][0];
                int time = jobs[idx][1];
                if(currenttime >= start) {
                    pQ.offer(new Job(start, time));
                }
                else break;
            }
            
            // 비어있고, len 끝이면, break
            if(pQ.isEmpty() && len == idx) break;
        }
        
        
        return (int)(answer / (double) len);
    }
    static class Job implements Comparable<Job> {
        int start;
        int time;
        public Job(int start, int time) {
            this.start=start;
            this.time=time;
        }
        
        public int compareTo(Job ob) {
            return this.time - ob.time;
        }
    }
}