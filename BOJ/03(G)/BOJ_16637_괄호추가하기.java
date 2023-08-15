import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 16637. 괄호 추가하기
 * 1. 연산자의 우선순위가 *, / 가 먼저가 아닌 왼쪽부터 순서대로 계산하기
 * 2. 수식에 괄호가 추가되면, 괄호 안에 들어있는 식은 먼저 계산하기
 * 3. 괄호 안에 연산자가 하나만 들어 있어야 함
 * 4. 중첩된 괄호는 금지
 * => 괄호를 "적절히 추가"해 만들 수 있는 수의 최대값 구하기 (괄호는 추가하거나 없어도 됨)
 *	
 * 풀이
 * 1. 최대 숫자는 10개, 수식은 9개, 최대 괄호 5개
 * 2. 괄호를 추가할지 안할지 -> 부분집합
 * 3. 괄호 안에는 연산자가 '하나만' 들어 있어야 한다.
 * 4. 숫자를 기준으로 
 * 4-1. 수식 포함 계산(괄호)을 한다음 이전 수식을 계산 하느냐
 * 4-2. 하지 않고 이전 수식을 계산하느냐
 */
public class BOJ_16637_괄호추가하기 {

	static int n;
	static String expression;
	// 수식 배열, 문자 int형으로 저장
	static int[] expressionArr;
	// 최대값
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		expression = br.readLine();	
		expressionArr = new int[n];
		for(int idx=0; idx<n; idx++) {
			// 짝수 인덱스는 숫자에 해당하므로 여기서 바로 숫자로 변환해준다.
			// 재귀에서 변환할 시 계산된 값인지, 원래 입력받은 숫자인지 구분하기 힘들다.
			if(idx%2 == 0) expressionArr[idx] = expression.charAt(idx) - '0';
			else {
				expressionArr[idx] = expression.charAt(idx);
			}
		}
		answer = Integer.MIN_VALUE;	
		recursive(0, 0);
		System.out.println(answer);
	}
	
	// 재귀 종료 시점에 최대값 갱신
	static void recursive(int idx, int sum) {
		
		// 최대값 갱신
		if(idx >= n) {
			answer = Math.max(answer, sum);
		}
		
		else {
			// 괄호를 쓰고  계산
			if(idx+2 < n) {	// 괄호 계산시 idx+2까지 확인해서 계산하므로 해당 조건이 만족해야함
				// 괄호 계산
				int calSum = calculate(expressionArr[idx], expressionArr[idx+2], expressionArr[idx+1]);
				if(idx == 0) {
					recursive(idx + 4, calSum); 
				}
				else {
					// 괄호 계산 + 이전 수식 계산
					recursive(idx + 4, calculate(sum, calSum, expressionArr[idx-1]));
				}
			}
			// 괄호를 안쓰고 계산
			if(idx == 0) recursive(idx+2 , expressionArr[idx]);
			else {
				// 이전 수식 계산
				recursive(idx + 2, calculate(sum, expressionArr[idx], expressionArr[idx-1]));
			}
		}
		
	}
	
	// 계산 하기
	static int calculate(int num1, int num2, int opInt) {
		
		char op = (char) opInt;
		
		if(op == '*') {
			return num1 * num2;
		}
		else if(op == '+') {
			return num1 + num2;
		}
		else if(op == '-') {
			return num1 - num2;
		}
		return 0;
	}
}

