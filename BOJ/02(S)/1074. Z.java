import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

       int n = Integer.parseInt(st.nextToken());
       int r = Integer.parseInt(st.nextToken());
       int c = Integer.parseInt(st.nextToken());

        /**
         * r행 c열을 몇 번째로 방문하는지
         * 0행 0열부터 시작
         */

        recursion(r, c, (int)Math.pow(2, n));
        System.out.println(answer);
    }
    static int answer = 0;
    static void recursion(int r, int c, int size) {

        if(size == 1) {
            return;
        }
        else {

            // 왼상
            if(r < size/2 && c < size/2) {
                recursion(r, c, size/2);
            }
            // 우상
            else if(r < size/2 && c >= size/2) {
                answer += (size*size) / 4;
                recursion(r, c-size/2, size/2);
            }
            // 왼하
            else if(r >= size/2 && c < size/2) {
                answer += ((size*size) / 4) * 2;
                recursion(r-size/2, c, size/2);
            }
            // 우하
            else if(r >= size/2 && c >= size/2) {
                answer += ((size*size) / 4) * 3;
                recursion(r-size/2, c-size/2, size/2);
            }
        }
    }
}