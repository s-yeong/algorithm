import java.io.*;
import java.util.*;

/*
모두 귀가할 때 최저 요금
간선 비용: 요금
=> 최소 간선 비용으로 도착
1. 양방향
2. 모든 경로 구해서 비교 하면 ((n-2)!)^2 => 시간 초과
3. 플로이드워샬 => n^3
*/
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 100000000;
        int[][] dist = new int[n+1][n+1];
        
        //init
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if(i==j) dist[i][j] = 0;
                else dist[i][j] = 100000000;
            }
        }
        
        for(int[] edges : fares) {
            int x = edges[0];
            int y = edges[1]; 
            int w = edges[2];
            
            dist[x][y] = w;
            dist[y][x] = w;
        }
        
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
       
        // 경유지 계산
        for(int k=1; k<=n; k++) {
            int tmp = dist[s][k];
            int atmp = dist[k][a];
            int btmp = dist[k][b];
            int sum = tmp + atmp + btmp;
            answer = Math.min(answer, sum);
        }
        // 경유지X
        answer = Math.min(answer, dist[s][a] + dist[s][b]);
        
        
        return answer;
    }
    
}