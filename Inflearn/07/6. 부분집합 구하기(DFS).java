
public class Main6 {
    static int n;   // 집합 원소의 개수
    static int[] ch;    // 체크 배열 - 숫자를 부분 집합으로 사용할지 안할지

    public void DFS(int L) {    // L이라는 값을 부분집합으로 사용하느냐 않느냐

            if(L==n+1) {    // 1로 체크되어있는 배열의 인덱스를 출력
                String tmp = "";
                for(int i=1; i<=n; i++) {
                    if (ch[i] == 1) tmp += (i + " ");
                }
                if (tmp.length() > 0) System.out.println(tmp);  // 공집합 제외
            } else {
                ch[L] = 1;
                DFS(L + 1); // 사용한다 (왼쪽)
                ch[L] = 0;
                DFS(L + 1); // 사용안한다 (오른쪽)
            }

        }



    public static void main(String[] args) {

        Main6 T = new Main6();
        n = 3;
        ch = new int[n + 1];
        T.DFS(1);
    }
}