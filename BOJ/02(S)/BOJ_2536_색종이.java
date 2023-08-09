import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2536. 색종이
 * 1-1. 흰색 도화지 - 가로,세로 각각 100
 * 1-2. 검은색 색종이 - 가로, 세로 각각 10
 * 1-3. 색종이의 변과 도화지의 변이 평행
 * 2. 색종이가 도화지 밖으로 나가는 경우는 없다
 * => 색종이가 붙은 검은 영역의 넓이를 구하기
 *	
 * 문제 풀이
 * 1. 왼쪽 변 사이의 거리 + 아래쪽 변 사이의 거리 => 왼쪽 아래 꼭지점이 주어진다고 생각
 * 2. 중복되는 사각형을 제거? => 계산하기 힘듬
 * 3. 왼쪽 아래 꼭지점에서 색종이 크기 만큼 1을 대입한다. => 중복되는 부분 고려 필요X
 * 4. 도화지에서 1인 사이즈를 모두 더하면 그게 검은 영역의 넓이
 */
public class BOJ_2536_색종이 {

	// 색종이 수
	static int confettiCount;
	static int[][] paper;
	static final int confettiSize = 10;
	static final int paperSize = 100;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		confettiCount = Integer.parseInt(br.readLine());
		paper = new int[paperSize][paperSize];
		
		for(int count = 0; count<confettiCount; count++) {
			st = new StringTokenizer(br.readLine());
			
			// 색종이의 왼쪽 변과 도화지의 왼쪽 변 사이의 거리
			int leftDiff = Integer.parseInt(st.nextToken());
					
			// 색종이의 아래쪽 변과 도화지의 아래쪽 변 사이의 거리
			int downDiff = Integer.parseInt(st.nextToken());
		
			for(int row=downDiff; row<downDiff+confettiSize; row++) {
				for(int col=leftDiff; col<leftDiff+confettiSize; col++) {
					paper[row][col] = 1;
				}
			}
		}
		
		// 색종이 칠한 부분 구하기
		int answer = 0;
		for(int row=0; row<paperSize; row++) {
			for(int col=0; col<paperSize; col++) {
				if(paper[row][col] == 1) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

}
