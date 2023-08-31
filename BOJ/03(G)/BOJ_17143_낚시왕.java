

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 17143. 낚시왕
 * => 낚시왕이 잡은 상어 크기 합 구하기
 * 1. 처음 시작 1번 열의 한 칸 왼쪽
 * 1-1. 낚시왕이 `오른쪽` 한 칸 이동
 * 1-2. 낚시왕이 열에있는 `가장 가까운 상어` 잡음 -> 잡으면 격자판에서 사라짐
 * 1-3. 상어 이동
 * 
 * 2. 상어 이동
 * 2-1. 속도만큼 이동 격자판 경계를 만났을 때 방향 반대로 바꿔서 남은 만큼 이동
 * 2-2. 그다음 남은 만큼 이동
 * 2-3. 상어가 이동후 한 칸에 두마리 이상 있을 시 `가장 큰` 상어가 나머지 상어 모두 잡아 먹음
 * 
 * 풀이
 * 1. 상어가 사라지는 경우는 낚시왕이 잡거나 같은 칸에서 자기보다 큰 상어가 먹거나
 * 2. 상어 클래스를 만들어서 상어가 각각 움직일 수 있도록 하고, 잡아 먹혔을 때는 size를 0으로 둬야한다. (size는 항상 1보다 크기 떄문에)
 * 3. 같은 크기의 상어가 없기 때문에 size로 구분하면 된다. => board에 size를 삽입
 * 4. 상어의 크기를 내림차순으로 정렬해서 큰거부터 상어를 이동시키면, 모든 상어를 이동시킨 다음 해당 위치가 중복되어 있는지 체크할 필요가 없다.
 * 5. 상어가 이동한 다음, 해당 위치에 자기보다 큰 상어가 있으면, 죽으면 되고 자기보다 작은 상어가 있으면 무시하면 된다. 어차피 작은 상어는 이동할 거니까  
 *  
 */
public class BOJ_17143_낚시왕 {
	
	static int rowLen, colLen, sharkCount;
	static int[][] board;
	static ArrayList<Shark> sharkList;
	static int removeSharkSize;	// 삭제 시킬 sharkSize
	static class Shark implements Comparable<Shark>{
		int row, col;
		int speed;
		int dir;
		int size;
		public Shark(int row, int col, int speed, int dir, int size) {
			this.row=row;
			this.col=col;
			this.speed=speed;
			this.dir=dir;
			this.size=size;
		}
		
		public int compareTo(Shark ob) {
			return ob.size - this.size;
		}
	}
	
	//상하우좌
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowLen = Integer.parseInt(st.nextToken());
		colLen = Integer.parseInt(st.nextToken());
		sharkCount = Integer.parseInt(st.nextToken());
		board = new int[rowLen][colLen];
		sharkList = new ArrayList<>();
		removeSharkSize = 0;
		
		for(int count=0; count<sharkCount; count++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());
			board[row][col] = size;
			sharkList.add(new Shark(row, col, speed, dir, size));
		}
		
		
		// 상어리스트 내림차순 정렬
		Collections.sort(sharkList);
		
		System.out.println(solve());
	}
	
	static int solve() {
		
		int sum = 0;
		// 1. 낚시왕 상어 잡기
		
		//1-1. 낚시왕이 `오른쪽` 한 칸 이동
		for(int kingCol=0; kingCol<colLen; kingCol++) {
			
			sum += catchShark(kingCol);
			
			//2. 상어 이동
			moveShark();
		}
		
		return sum;
	}
	
	static int catchShark(int kingCol) {
		
		int sharkSize = 0;
		
		//1-2. 낚시왕이 열에있는 `가장 가까운 상어` 잡음 -> 잡으면 격자판에서 사라짐
		for(int row=0; row<rowLen; row++) {
			// 0이 아니면 상어 존재
			if(board[row][kingCol] != 0) {
				sharkSize = board[row][kingCol];
				board[row][kingCol] = 0;
				// 삭제할 상어 삽입
				removeSharkSize = sharkSize;
				break;
			}
		}
		
		return sharkSize;
	}
	
	static void moveShark() {
		
		// 상어 이동 위치를 기억하는 리스트
		ArrayList<int[]> moveSharkList = new ArrayList<>();
		
		for(int idx=0; idx<sharkList.size(); idx++) {
			
			Shark shark = sharkList.get(idx);
			
			// 이미 삭제된 상어면,
			if(shark.size == 0) continue;
			
			// 낚시왕이 잡은 삭제할 상어면,
			if(shark.size == removeSharkSize) {
				shark.size = 0;
				continue;
			}
			
			//2-1. 속도만큼 이동 격자판 경계를 만났을 때 방향 반대로 바꿔서 남은 만큼 이동
			// 한칸씩 이동하면, 시간초과 난다!
			int row = shark.row;
			int col = shark.col;
			row = row + dr[shark.dir] * shark.speed;
			col = col + dc[shark.dir] * shark.speed;
			
			while(row < 0 || col < 0 || row >= rowLen || col >= colLen) {
				
				// 범위 벗어난 경우 한번에 못가기 때문에 방향 돌려서 가야함
				if(row < 0) {
					row = row * -1;
					swapDir(shark);
				}
				else if(col < 0) {
					col = col * -1;
					swapDir(shark);
				}
				else if(row >= rowLen) {
					row = rowLen-1 - (row - rowLen + 1);
					swapDir(shark);
				}
				else if(col >= colLen) {
					col = colLen-1 - (col - colLen + 1);
					swapDir(shark);
				}
			}
			
			// 상어 이동 위치 저장
			moveSharkList.add(new int[] {row, col});
		}
		
		// 따로 움직일 idx 구분해야함
		// 삭제된 상어인 경우 움직이는 리스트를 저장하지 않았기 때문
		int moveIdx = 0;
		// 모든 상어 이동 위치 저장 후 위치 바꾸기
		for(int idx=0; idx<sharkList.size(); idx++) {
			
			Shark shark = sharkList.get(idx);
			
			// 상어 죽었으면 skip
			if(shark.size == 0) continue;
			
			int[] movePos = moveSharkList.get(moveIdx++);
			
			// 현재 위치 0으로 두고 움직이기
			// 이전에 상어가 내 현재 위치에 이동했을 수도 있으니 사이즈가 같을때만 0으로 
			if(board[shark.row][shark.col] == shark.size) board[shark.row][shark.col] = 0;
			shark.row = movePos[0];
			shark.col = movePos[1];
			
			// 이동 위치 size 두기
			// 이동 위치에 현재보다 크면 해당 shark는 잡아먹힘
			if(board[shark.row][shark.col] > shark.size) {
				shark.size = 0;
			}
			else {
				board[shark.row][shark.col] = shark.size;
			}
		}
		
		
	}
	static void swapDir(Shark shark) {
		
		if(shark.dir == 0 || shark.dir == 2) {
			shark.dir++;
		}
		else {
			shark.dir--;
		}
		
	}

}
