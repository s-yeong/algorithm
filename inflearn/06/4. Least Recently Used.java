import java.util.Scanner;

public class Main4 {

    public static int[] solution(int s, int n, int[] arr) {

        int[] answer = new int[s];

        for(int x : arr) {

            int pos = -1;
            for(int i=0; i<s; i++) if(x==answer[i]) pos = i;

            if(pos == -1) { // Miss
                for(int i=s-1; i>=1; i--) {
                    answer[i] = answer[i-1];
                }
            } else {    // Hit
                for(int i=pos; i>=1; i--) {
                    answer[i] = answer[i - 1];
                }
            }

            answer[0] = x;
        }


/*
       for(int i=0; i<n; i++) {
            boolean isHit = false;
            for(int j=0; j<s; j++) {

                if(arr[i] == answer[j]) {
                    // Hit 인 경우, index j에 담겨 있는 것을 맨 앞으로
                    int tmp = answer[j];
                    for(int k=j-1; k>=0; k--) {
                        answer[k+1] = answer[k];
                    }
                    answer[0] = tmp;
                    isHit = true;
                }
            }
            if(!isHit) {
                // Miss인 경우, 생성해서 맨 끝에꺼 밀려남
                for(int k=s-2; k>=0; k--) {
                    answer[k + 1] = answer[k];
                }
                answer[0] = arr[i];
            }
        }
*/

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int s = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = sc.nextInt();


        for(int x : solution(s, n, arr)) {
            System.out.print(x + " ");
        }
    }

}