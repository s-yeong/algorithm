import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] arr = {"c=",
                "c-",
                "dz=",
                "d-",
                "lj",
                "nj",
                "s=",
                "z="};

        for(int i=0; i<arr.length; i++) {

            if(str.contains(arr[i])) {
                str = str.replace(arr[i], "x");
            }
        }

        System.out.println(str.length());
    }
}
