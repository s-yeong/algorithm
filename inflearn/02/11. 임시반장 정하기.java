import java.util.Scanner;

public class Main11 {

    public static int solution(int n, int[][] arr) {

        int answer = 0;
        int max = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++) {
            int cnt = 0;    // i번 학생과 같은 반을 했던 j번째 학생은 몇 명있는가
            for(int j=1; j<=n; j++) {   // 자기 자신 넣어도 모든 학생에게 동등하게 넣으니까 상관X
                for(int k=1; k<=5; k++) {
                    if(arr[i][k] == arr[j][k]) {
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt>max) {
                max = cnt;
                answer = i;
            }
        }


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n+1][6];

        for(int i=1; i<=n; i++) {   // i - 학생 번호
            for(int j=1; j<=5; j++) {   // j - 학년
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.print(solution(n, arr));
    }

}