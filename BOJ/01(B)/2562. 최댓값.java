import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        for(int i=0; i<9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] answer = new int[2];
        for(int i=0; i<9; i++) {
            if(answer[0] < arr[i]) {
                answer[0] = arr[i];
                answer[1] = i+1;
            }
        }
        for(int x : answer) System.out.println(x);

    }
}