import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1861. 정사각형 방 
 * 1. N^2의 방, 1이상 N2 이하의 수 Ai
 * 2. 상하좌우 이동
 * 3. 이동하려는 방이 존재 + 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야함 
 * => 처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하기
 * 
 * 문제 풀이
 * N 최대 1000, N^2 최대는 100만
 * 모든 방들을 탐색 => N^2*N^2 => 시간초과 => BFS + 메모이제이션
 * 모든 방 숫자가 중복되는 값이 없다 -> 해당 방 기준으로 올 수 있는 길은 하나밖에 없다
 */
public class SWEA_1861_정사각형방 {
	
	static int N;
	static int[][] roomArr;
	
	// 상하좌우
	static int[] deltaCol = {0,0,-1,1};
	static int[] deltaRow = {-1,1,0,0};
	
	// 각 방에서 갈 수 있는 방의 개수 저장 배열
	static int[][] roomCountArr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			N = Integer.parseInt(br.readLine().trim());
			roomArr = new int[N][N];
			roomCountArr = new int[N][N];
			
			for(int rowIdx=0; rowIdx<N; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx=0; colIdx<N; colIdx++) {
					roomArr[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 처음에 출발해야 하는 방번호 + 최대 몇개의 방을 이동할 수 있는지 
			int maxRoomNumber = 0;
			int maxRoomCount = 0;
			for(int rowIdx=0; rowIdx<N; rowIdx++) {
				for(int colIdx=0; colIdx<N; colIdx++) {
					bfs(rowIdx, colIdx);
					
					if(roomCountArr[rowIdx][colIdx] > maxRoomCount) {
						maxRoomCount = roomCountArr[rowIdx][colIdx];
						maxRoomNumber = roomArr[rowIdx][colIdx];
					}
					// 최대값이 같은 경우 최소 번호
					else if(roomCountArr[rowIdx][colIdx] == maxRoomCount) {
						maxRoomNumber = Math.min(maxRoomNumber, roomArr[rowIdx][colIdx]);
					}
				}
			}
			
			// 처음에 출발해야 하는 방번호 + 최대 몇개의 방을 이동할 수 있는지 
			for(int rowIdx=0; rowIdx<N; rowIdx++) {
				for(int colIdx=0; colIdx<N; colIdx++) {
					if(roomCountArr[rowIdx][colIdx] > maxRoomCount) {
						maxRoomCount = roomCountArr[rowIdx][colIdx];
						maxRoomNumber = roomArr[rowIdx][colIdx];
					}
					// 최대값이 같은 경우 최소 번호
					else if(roomCountArr[rowIdx][colIdx] == maxRoomCount) {
						maxRoomNumber = Math.min(maxRoomNumber, roomArr[rowIdx][colIdx]);
					}
				}
			}
			
			// 자기 자신도 포함해야 하므로 1 더해서 출력
			sb.append("#").append(testCase).append(" ").append(maxRoomNumber).append(" ").append(maxRoomCount+1).append("\n");
		}
		System.out.print(sb);
	}
	
	static void bfs(int startRow, int startCol) {
		
		Queue<int[]> Q = new ArrayDeque<>();
		
		Q.offer(new int[] {startRow, startCol});
		
		while(!Q.isEmpty()) {
			
			// 현재 방
			int[] cur = Q.poll();
			
			for(int direction=0; direction<4; direction++) {
				
				int nextRow = cur[0] + deltaRow[direction];
				int nextCol = cur[1] + deltaCol[direction];
				
				// 갈 수 있는 조건
				if(nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < N) {
					
					if(roomArr[cur[0]][cur[1]] + 1 == roomArr[nextRow][nextCol]) {
						
						// 값이 존재 했다면, 이미 구한거임
						if(roomCountArr[nextRow][nextCol] != 0) {
							// 현재 까지 갈수 있는 방 개수 + 다음을 시작점으로 갈 수 있는 방 개수 + 1
							roomCountArr[startRow][startCol] = roomCountArr[startRow][startCol] + roomCountArr[nextRow][nextCol] + 1;
							return;
						}
						
						roomCountArr[startRow][startCol]++;
						Q.offer(new int[]{nextRow, nextCol});
					}
					
				}
				
			}
			
		}
		
		
		
	}
}
