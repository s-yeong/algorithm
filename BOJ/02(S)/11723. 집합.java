import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        /**
         * 비트 마스크
         * int 형 => 32비트 => 32가지의 경우에 대해서 참, 거짓을 판단할 수 있다.
         * 00000000 00000000 00000000 00000000
         * x 범위 1 ~ 20
         */

        int bit_set = 0;
        int x;
        for(int i=0; i<m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            String comm = st.nextToken();

            if("add".equals(comm)){

                x = Integer.parseInt(st.nextToken());
                bit_set = bit_set | 1<<(x-1);
            }

            else if("remove".equals(comm)){

                x = Integer.parseInt(st.nextToken());
                bit_set = bit_set & ~(1<<(x-1));
            }

            else if("check".equals(comm)){

                x = Integer.parseInt(st.nextToken());
                // x가 있으면 1, 없으면 0
                sb.append((bit_set & 1<<(x-1)) == 1<<(x-1) ? 1 : 0).append("\n");
            }

            else if("toggle".equals(comm)){

                x = Integer.parseInt(st.nextToken());
                // S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다.
                bit_set = bit_set ^ 1<<(x-1);
            }
            else if("all".equals(comm)){
                // S를 {1, 2, ..., 20} 으로 바꾼다.
                bit_set = (1<<20) - 1;
            }
            else if("empty".equals(comm)) {
                // S를 공집합으로 바꾼다.
                bit_set = 0;
            }
        }
        System.out.print(sb);

    }
}