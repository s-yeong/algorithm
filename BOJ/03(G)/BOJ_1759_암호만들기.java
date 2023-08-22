import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1759. 암호 만들기 
 * 1. 암호는 서로 다른 L개의 알파벳 소문자 = 최소 한 개의 모음(a, e, i, o, u) + 최소 두 개의 자음으로 구성
 * 2. 알파벳이 암호에서 증가하는 순서로 배열
 * 3. C개의 문자들이 모두 주어졌을 때 가능성 있는 암호들 모두 구하기
 *
 * 풀이
 * 1. C개의 문자를 통해 L개의 알파벳 만들기 (조합)
 * 2. 모음 한개 + 두 개의 자음을 구성하고 있는지 체크하고 카운트
 * 3. 증가하는 순서로 배열해야 하기 때문에, 미리 정렬을 하고 조합을 짜면 된다.
 */
public class BOJ_1759_암호만들기 {
	
	static int combiLen, alphabetLength;
	static char[] alphabets;
	static char[] combi;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		combiLen = Integer.parseInt(st.nextToken());
		alphabetLength = Integer.parseInt(st.nextToken());
		alphabets = new char[alphabetLength];
		
		st = new StringTokenizer(br.readLine());
		for(int idx=0; idx<alphabetLength; idx++) {
			alphabets[idx] = st.nextToken().charAt(0);
		}
		combi = new char[combiLen];
		
		// 정렬
		Arrays.sort(alphabets);
		recur(0, 0);
		System.out.print(sb);
	}
	
	
	static void recur(int depth, int start) {
		
		// 조합 완료
		if(depth == combiLen) {
			//2. 모음 한개 + 두 개의 자음을 구성하고 있는지 체크하기;
			int vowelCount = 0;	// 모음 수
			for(char alphabet : combi) {
				if(alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') {
					vowelCount++;
				}
			}
			if(vowelCount > 0 && (combiLen-vowelCount) >= 2) {
				sb.append(String.valueOf(combi)).append("\n");
			}
			
		}
		else {
			for(int idx=start; idx<alphabetLength; idx++) {
				combi[depth] = alphabets[idx];
				recur(depth+1, idx+1);
			}
		}
		
	}
}
