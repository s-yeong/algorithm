import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i<=j) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}