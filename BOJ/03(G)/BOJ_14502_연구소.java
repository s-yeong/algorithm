import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14502. 연구소
 * 1. 연구소는 빈 칸, 벽으로 이루어짐, 일부 칸은 바이러스 존재
 * 1-2. 바이러스는 `상하좌우` 인접한 빈 칸으로 퍼짐
 * 2. 벽을 무조건 3개 세워야 함
 * => 안전 영역 크기의 최댓값을 구하는 프로그램
 * 
 * 풀이
 * 1. 64개중에서 3개를 조합하여 벽을 세우기
 * 2. 벽을 세운 다음 바이러스를 퍼트린다.
 * 3. 퍼트린 바이러스 제외하고 안전 영역 최대 구하기
 * 4. 벽을 세우고 바이러스를 퍼트리기 때문에 임시 배열 필요
 * 5. 매 번 벽 세운다음 안전 영역 최대값 갱신 
 * 
 */
public class BOJ_14502_연구소 {
	
	static int[][] map, tempMap;
	static int rowLen, colLen;
	static final int BLANK = 0, WALL = 1, VIRUS = 2;
	
	// 빈칸 리스트
	static ArrayList<int[]> blankList;
	// 바이러스 리스트
	static ArrayList<int[]> virusList;
	
	// 조합 인덱스 배열
	static int[] combiIdx;
	
	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	// 정답
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		// 입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowLen = Integer.parseInt(st.nextToken());
		colLen = Integer.parseInt(st.nextToken());
		map = new int[rowLen][colLen];
		blankList = new ArrayList<>();
		virusList = new ArrayList<>();
		answer = 0;
		
		for(int row=0; row<rowLen; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<colLen; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == BLANK) blankList.add(new int[] {row,col});
				else if(map[row][col] == VIRUS) virusList.add(new int[] {row,col});
			}
		}
		combiIdx = new int[3];
		
		// 조합 시작
		combi(0, 0);
		
		System.out.println(answer);
	}
	
	// 64C3
	// 조합
	static void combi(int depth, int start) {
		
		// 조합 완성
		if(depth == 3) {
			
			// 임시 배열 초기화
			copyArr();
			
			// 벽 세우기
			for(int idx : combiIdx) {
				int[] pos = blankList.get(idx);
				tempMap[pos[0]][pos[1]] = 1;
			}
			
			// 바이러스 퍼지기
			bfs();
			
			// 개수 세기(최대값 갱신)
			answer = Math.max(answer, getBlankCount());
		}
		else {
			for(int idx=start; idx<blankList.size(); idx++) {
				combiIdx[depth] = idx;
				combi(depth+1, idx+1);
			}
		}
	}
	
	static void bfs() {
		
		// 바이러스를 담을 큐
		Queue<int[]> Q = new ArrayDeque<>();
		
		// 바이러스 리스트를 큐에 담는다.
		for(int[] virusPos : virusList) {
			Q.offer(virusPos);
		}
		
		while(!Q.isEmpty()) {
			
			int[] virus = Q.poll();
			
			for(int dir=0; dir<4; dir++) {
				
				int nextRow = virus[0] + dr[dir];
				int nextCol = virus[1] + dc[dir];
				
				// 빈칸인 경우만, 바이러스에 넣고 퍼지기
				if(nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen &&
						tempMap[nextRow][nextCol] == BLANK) {
					
					tempMap[nextRow][nextCol] = VIRUS;
					Q.offer(new int[] {nextRow, nextCol});
				}
			}
		}
	}
	
	static int getBlankCount() {
		
		int count = 0;
		
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				if(tempMap[row][col] == BLANK) count++;
			}
		}
		return count;
	}
	
	// 임시 배열 초기화
	static void copyArr() {
		tempMap = new int[rowLen][colLen];
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				tempMap[row][col] = map[row][col];
			}
		}
	}
}
