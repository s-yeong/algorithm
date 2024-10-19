import java.io.*;
import java.util.*;

class Solution {
    int maxCount;
    int rowLen, colLen;
    int[][] groupMap;
    int group;
    
    public int solution(int[][] land) {
        int answer = 0;
        rowLen = land.length;
        colLen = land[0].length;
        groupMap = new int[rowLen][colLen];
        group = 0;
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (land[row][col] == 1 && groupMap[row][col] == 0) {
                    maxCount = 0;
                    bfs(row, col, land);
                    allocate(row, col, land);
                    group++;
                }
            }
        }
        
        for (int col = 0; col < colLen; col++) {
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for (int row = 0; row < rowLen; row++) {
                if (land[row][col] > 0) {
                    if (!set.contains(groupMap[row][col])) {
                        sum += land[row][col];
                        set.add(groupMap[row][col]);
                    }
                }
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    void bfs(int row, int col, int[][] land) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        land[row][col] = -1;
        maxCount++;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = curRow + dr[dir];
                int nextCol = curCol + dc[dir];
                
                if (nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen) continue;
                if (land[nextRow][nextCol] != 1) continue;
                
                land[nextRow][nextCol] = -1;
                maxCount++;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
    }
    
    void allocate(int row, int col, int[][] land) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        land[row][col] = maxCount;
        groupMap[row][col] = group;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = curRow + dr[dir];
                int nextCol = curCol + dc[dir];
                
                if (nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen) continue;
                if (land[nextRow][nextCol] != -1) continue;
                
                land[nextRow][nextCol] = maxCount;
                groupMap[nextRow][nextCol] = group;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
    }
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
}
