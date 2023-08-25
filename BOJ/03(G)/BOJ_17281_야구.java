import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 17281. 야구
 * 1. 야구는 9명으로 이루어진 두 팀의 공격과 수비를 번갈아 하는 게임
 * 2. 총 N이닝 동안 게임 진행. 하나의 이닝은 공격과 수비로 이루어짐.
 * 3. 한 이닝에 3아웃 발생시 이닝이 종료, 두 팀 공수교대
 * 4. 선수 번호 1번부터 9번까지. 1번 선수는 4번 타자
 * 5. 다른 선수의 타순을 모두 결정해야함	
 * => 가장 많은 득점을 하는 타순 찾기
 * 
 * 조건
 * 1. 각 이닝에는 아웃을 기록하는 타자가 `적어도 한 명` 존재
 * 2. 이닝 수 최대 50
 * 
 * 풀이
 * 1. 1번 선수는 4번 타자 고정 -> 8명 순서에 따라 값 달라짐 -> 8! = 40320 x 50 = 200만
 * 2. 각 선수 순서 배치한 다음 최대값 플레이 진행 후 최대값 갱신
 *
 */
public class BOJ_17281_야구 {

	// 이닝 수
	static int inningCount;
	// 각 선수 이닝에서 얻은 결과
	static int[][] playerScore;
	// 타자 순서 배열
	static int[] perm;
	// 순열 방문체크
	static boolean[] ch;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		inningCount = Integer.parseInt(br.readLine());
		playerScore = new int[inningCount][10];
		
		for(int count=0; count<inningCount; count++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int playerNum=1; playerNum<=9; playerNum++) {
				playerScore[count][playerNum] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		perm = new int[9];
		ch = new boolean[10];
		
		//1. 1번 선수는 `4`번 타자 고정
		perm[3] = 1;
		recur(0);
		
		System.out.println(answer);
	}
	// 순열 재귀
	static void recur(int depth) {
		
		//1. 1번 선수는 4번 타자 고정
		if(depth == 3) depth++;
		
		// 9명 배치 완료시
		if(depth == 9) {
			// 플레이하기
			answer = Math.max(answer, play());
		}
		else {
			for(int playerIdx=2; playerIdx<=9; playerIdx++) {
				if(ch[playerIdx]) continue;
				ch[playerIdx] = true;
				perm[depth] = playerIdx;
				recur(depth+1);
				ch[playerIdx] = false;
			}
		}
	}
	
	static int play() {
		
		int score = 0;
		
		// 루에 있는 상태 (0: 타석에 있음, 1~3루)
		int[] status = new int[4];

		// 이동을 위해 1로 초기화 (타자)
		status[0] = 1;
		
		// 타자 Idx (0부터 시작하기 위해서)
		int batterIdx = -1;
		
		for(int inning=0; inning<inningCount; inning++) {
			
			// 루에 있는 상태 초기화
			status[3] = status[2] = status[1] = 0;
			
			// 아웃 수
			int outCount = 0;
			
			while(true) {
				
				batterIdx = (batterIdx+1) % 9;
				int idx = perm[batterIdx];
				int result = playerScore[inning][idx];
				
				// 안타: 1
				//안타: 타자와 모든 주자가 한 루씩 진루한다.
				if(result == 1) {
					
					// 3루에서 홈으로 이동
					score += status[3];
					
					// 각각 한칸씩 이동
					for(int statusIdx=3; statusIdx>=1; statusIdx--) {
						status[statusIdx] = status[statusIdx-1];
					}
				}
				
				// 2루타: 2
				//2루타: 타자와 모든 주자가 두 루씩 진루한다.
				else if(result == 2) {
				
					// 2루, 3루 홈으로 이동
					score += status[3] + status[2];
					
					// 각각 두칸씩 이동
					for(int statusIdx=3; statusIdx>=2; statusIdx--) {
						status[statusIdx] = status[statusIdx-2];
					}
					
					// 1루 상태 이동했으니 0으로
					status[1] = 0;
				}
				// 3루타: 3
				//3루타: 타자와 모든 주자가 세 루씩 진루한다.
				else if(result == 3) {
					
					// 1루, 2루, 3루 홈으로 이동
					score += status[3] + status[2] + status[1];
					
					// 3루로 이동
					status[3] = status[0];
					
					// 2루, 1루 이동했으니 0으로
					status[2] = status[1] = 0;
				}
				// 홈런
				//홈런: 타자와 모든 주자가 홈까지 진루한다.
				else if(result == 4) {
					
					// 1루, 2루, 3루 홈으로 이동 + 1
					score += status[3] + status[2] + status[1] + status[0];
					
					// 모두 이동했으니 0으로
					status[3] = status[2] = status[1] = 0;
				}
				// 아웃
				else {
					outCount++;
					//3. 한 이닝에 3아웃 발생시 이닝이 종료, 두 팀 공수교대
					if(outCount == 3) break;
				}	
				
			}	// count end
		}	// inning end
		
		return score;
	}
}
