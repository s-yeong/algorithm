import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 16435. 스네이크버드
 * 1. 과일 하나 먹으면 길이 1 증가
 * 2. 과일의 높이 h
 * 3. 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있음
 * => 스네이크버드의 처음 길이가 L일때 과일들을 먹어 늘릴 수 있는 최대 길이
 * 
 * 풀이
 * 1. 정렬을 통해 가장 적은 과일 부터 먹으면서 길이 증가 시키면된다.
 *
 */
public class BOJ_16435_스네이크버드 {

	// 과일 개수
	static int fruitCount;
	// 스네이크 버드 길이
	static int birdLen;
	// 과일 배열
	static int[] fruitArr;
	
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		fruitCount = Integer.parseInt(st.nextToken());
		birdLen = Integer.parseInt(st.nextToken());
		fruitArr = new int[fruitCount];
		
		st = new StringTokenizer(br.readLine());
		for(int idx=0; idx<fruitCount; idx++) {
			fruitArr[idx] = Integer.parseInt(st.nextToken());
		}
		// 정렬
		Arrays.sort(fruitArr);
		
		for(int idx=0; idx<fruitCount; idx++) {
			
			// 3. 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있음
			if(birdLen >= fruitArr[idx]) {
				// 1. 과일 하나 먹으면 길이 1 증가
				birdLen++;
			}
		}
		System.out.println(birdLen);
	}

}
