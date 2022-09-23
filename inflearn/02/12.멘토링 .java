import java.util.Scanner;

public class Main12 {

    public static int solution(int n, int m, int[][] arr) {


        int answer = 0; // 멘토와 멘티가 되는 짝을 만들 수 있는 경우


        // i와 j가 멘토 멘티 관계가 될 수 있냐
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                int cnt = 0;
                for(int k=1; k<=m; k++) {    // 1번 테스트 부터 m번 테스트까지
                    int pi = 0, pj = 0; // i의 등수, j의 등수
                    for(int s=1; s<=n; s++)  { // 1등부터 m등 까지
                        if(arr[k][s] == i) pi = s;
                        if(arr[k][s] == j) pj = s;
                    }
                    if(pi < pj) cnt++;
                }
                if(cnt == m) answer++;
            }
        }


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 학생 수
        int m = sc.nextInt();   // m번의 수학 테스트
        int[][] arr = new int[m+1][n+1];

        for(int i=1; i<=m; i++) {   // 수학 테스트 m번
            for(int j=1; j<=n; j++) {  // 1등부터 n등까지
                arr[i][j] = sc.nextInt();   // 학생 번호 입력
            }
        }
        System.out.print(solution(n, m, arr));
    }

}