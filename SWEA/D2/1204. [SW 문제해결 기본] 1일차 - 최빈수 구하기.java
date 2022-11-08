import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            int tc = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[101];
            for(int j=0; j<1000; j++) {
                int score = Integer.parseInt(st.nextToken());
                arr[score]++;
            }

            int max = Integer.MIN_VALUE;
            int index=0;

            for(int j=100; j>=0; j--) {
                if (max < arr[j]) {
                    max = arr[j];
                    index = j;
                }
            }

            sb.append("#").append(tc).append(" ").append(index).append("\n");
        }



            System.out.println(sb);
        }
    }