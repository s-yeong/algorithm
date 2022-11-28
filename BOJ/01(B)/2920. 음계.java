import java.util.*;
import java.io.*;

class Main {
    static void solution(int[] arr) {
        int[] arrSort = {1, 2, 3, 4, 5, 6, 7, 8};

        int cntA = 0;
        int cntD = 0;
        for(int i=0; i<8; i++) {
            if(arr[i] == arrSort[i]) cntA++;
            else if(arr[i] == arrSort[7-i]) cntD++;
        }
        if(cntA == 8) System.out.println("ascending");
        else if(cntD == 8) System.out.println("descending");
        else System.out.println("mixed");

    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[8];
        for(int i=0; i<8; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(arr);


    }
}
