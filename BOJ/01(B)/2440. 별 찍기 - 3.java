import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            for(int j=i; j<=n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}