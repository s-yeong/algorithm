import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1228. 암호문1
 * 1. I x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들
 * 2. 암호문 수정하고, 수정된 결과의 처음 10개의 숫자를 출력
 */
public class SWEA_1228_암호문1 {
	
	// 암호문 길이
	static int passwordLength;
	// 암호문 배열
	static List<Integer> passwordList;
	// 명령문 길이
	static int commandLength;
	
	// 출력.
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1; tc<=10; tc++) {
			
			// 암호문 길이
			passwordLength = Integer.parseInt(br.readLine());
			
			// 암호문 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			passwordList = new LinkedList<>();
			while(passwordLength --> 0) {
				passwordList.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령문 길이
			commandLength = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while(commandLength --> 0) {
				
				String command = st.nextToken();
				if(command.equals("I")) {
					// 1. I x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들
					int insertIdx = Integer.parseInt(st.nextToken());	// 삽입 위치
					int insertCount = Integer.parseInt(st.nextToken()); // 삽입수
					
					for(int count=0; count<insertCount; count++) {
						passwordList.add(insertIdx, Integer.parseInt(st.nextToken()));
						// 그 다음수는 삽입 위치의 다음에 넣어야 하기 때문에 플러스 해준다.
						insertIdx++;
					}
				}
			}
			
			// 수정된 결과 10개 출력
			sb.append("#").append(tc).append(" ");
			for(int idx=0; idx<10; idx++) {
				sb.append(passwordList.get(idx)).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
