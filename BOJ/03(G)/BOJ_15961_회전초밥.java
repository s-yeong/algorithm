import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 15961. 회전 초밥
 *  1. 벨트 위에 같은 종류의 초밥이 둘 이상 있을 수 있다.
 *  2. 두가지 행사
 *  2-1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 `할인된 정액 가격`으로 제공
 *  2-2. `각 고객`에게 초밥의 종류 `하나`가 쓰인 쿠폰을 발행, -> 2-1 행사에 참가할 경우 쿠폰에
 *  적혀진 종류의 초밥 하나를 `추가로 무료 제공` (없으면 새로 만들어서 제공) 
 *	=> 손님이 먹을 수 있는 초밥 가짓수의 최대값 구하기
 *
 * 풀이
 * 0. 접시 수는 최대 300만, 연속해서 먹은 접시 수 최대 3000 => 단순 반복문으로 해결X
 * => 투포인터 알고리즘을 이용해서 구하자
 * 1. 해시맵을 통해서 연속해서 먹은 접시 수만큼 해시맵에 추가한다음. key의 개수를 반환한다.
 * 2. 이 때 쿠폰이 포함되어 있으면 +1을 안하고, 포함되어있지 않으면, +1을 한다.
 * 3. 원형 연결리스트로 되어 있기 때문에, 배열 길이를 넘어가면 나눗셈 연산을 한다.
 *  
 */
public class BOJ_15961_회전초밥 {
	
	static int plateCount, sushiTypeCount, continuousPlateCount, cuponNo;
	static int[] plateArr;
	static boolean existSushiType;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 접시 수
		plateCount = Integer.parseInt(st.nextToken());
		// 초밥 가지 수
		sushiTypeCount = Integer.parseInt(st.nextToken());
		// 연속해서 먹은 접시 수
		continuousPlateCount = Integer.parseInt(st.nextToken());
		// 쿠폰 번호
		cuponNo = Integer.parseInt(st.nextToken());
		
		// 쿠폰 번호에 해당하는 스시 종류가 벨트에 있는지 
		existSushiType = false;
		
		// 회전 방향을에 따른 접시 배열
		plateArr = new int[plateCount];
		for(int plateIdx=0; plateIdx<plateCount; plateIdx++) {
			plateArr[plateIdx] = Integer.parseInt(br.readLine());
			// 3. (없으면 새로 만들어서 제공)하기 때문에 해당 초밥이 벨트에 있는지 체크해야한다.
			if(plateArr[plateIdx] == cuponNo) existSushiType = true;
		} 
		
		System.out.println(solution());
	}
	
	static int solution() {
		
		int answer = 0;
		
		//1. 해시맵을 통해서 연속해서 먹은 접시 수만큼 해시맵에 추가한다음. key의 개수를 반환한다.
		Map<Integer, Integer> map = new HashMap<>();
		
		// 처음 개수 구하기
		for(int idx=0; idx<continuousPlateCount; idx++) {
			map.put(plateArr[idx], map.getOrDefault(plateArr[idx], 0) + 1);
		}
		//2. 이 때 쿠폰이 포함되어 있으면 +1을 안하고, 포함되어있지 않으면, +1을 한다.
		answer = map.size();
		if(!map.containsKey(cuponNo)) {
			answer++;
		}
		
		// left부터 right까지 합 = continusePlateCount
		int right = continuousPlateCount - 1;
		for(int left=1; left<plateCount; left++) {
			
			// right 증가
			//4. 원형 연결리스트로 되어 있기 때문에, 배열 길이를 넘어가면 나눗셈 연산을 한다.
			right = (right+1) % plateCount;
			
			// left-1은 뺴고, right는 추가하고
			map.put(plateArr[left-1], map.get(plateArr[left-1]) -1);
			// 0이 된 경우 key 삭제
			if(map.get(plateArr[left-1]) == 0) map.remove(plateArr[left-1]);
			
			map.put(plateArr[right], map.getOrDefault(plateArr[right], 0) + 1);
			
			// 초밥 개수 구하기
			int count = map.size();
			if(existSushiType) {
				if(!map.containsKey(cuponNo)) {
					count++;
				}
			} else count++;
			
			// 최대값 갱신
			answer = Math.max(answer, count);
		}
		
		return answer;
	}

}
