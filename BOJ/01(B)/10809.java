import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] arr = new int['z' - 'a' + 1];
        Arrays.fill(arr, -1);
        for(int i=0; i<s.length(); i++) {
            char x = s.charAt(i);
            // 처음 등장이면
            if(arr[x-'a'] == -1) {
                arr[x-'a'] = i;
            }
        }
        for(int ans : arr) System.out.print(ans + " ");

    }
}