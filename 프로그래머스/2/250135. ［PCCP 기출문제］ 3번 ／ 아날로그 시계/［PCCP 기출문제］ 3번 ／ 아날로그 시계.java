import java.io.*;
import java.util.*;

//초침이 시침/분침과 겹칠 때마다 알람이 울리는 기능
//특정 시간 동안 알람이 울린 횟수
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        // 60초 동안 시침, 분침 겹침 => 2
        // 시침=분침이면 60초동안 시침=분침 겹침 => 1
        // 초침=분침
        // 초침/5=시침
        
        int ts1 = h1*60*60 + m1*60 + s1;
        int ts2 = h2*60*60 + m2*60 + s2;
        
        //처음 같은 경우
        //0시0분0초거나 12시0분0초인 경우 말고 X
        if(ts1 == 0 || ts1 == 3600*12) {
            answer++;
        }
        
        // 1초씩 이동
        while(ts1 < ts2) {
            //시침 12시간=360도/1시간=30도/1분=30/60도/1초=30/60/60도
            //1초=1/120도
            double h_angle = (ts1/(double)120) % 360;
            //분침 1분=6도/1초=6/60도
            //1초=1/10도
            double m_angle = (ts1/(double)10) % 360;
            //초침 1초=6도
            //1초=6도
            double s_angle = (ts1*6) % 360;
            
            // 1초 증가 후 각도
            double h_angle_n = ((ts1+1)/(double)120) % 360;
            double m_angle_n = ((ts1+1)/(double)10) % 360;
            double s_angle_n = ((ts1+1)*6) % 360;
            // 0도 방지
            if(h_angle_n==0) h_angle_n = 360;
            if(m_angle_n==0) m_angle_n = 360;
            if(s_angle_n==0) s_angle_n = 360;
            
            //초침이 시침 지난 경우
            if(s_angle < h_angle && s_angle_n >= h_angle_n) {
                // System.out.println("초침이 시침 지난 경우");
                answer++;
            }
            //초침이 분침 지난 경우
            if(s_angle < m_angle && s_angle_n >= m_angle_n) {
                // System.out.println("초침이 분침 지난 경우");
                answer++;
            }
            //초침=시침=분침
            //분침 시침 같은 경우
            if(m_angle_n == h_angle_n) {
                // System.out.println("분침 시침 같은 경우");
                answer--;
            }
                        
            ts1++;
        }
        
        
        return answer;
    }
}