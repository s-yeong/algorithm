import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());   // w - 가로 길이
        int h = Integer.parseInt(st.nextToken());   // h - 세로 길이

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());    // t 시간 이후의 위치

        p = p + t%(2*w);         // t가 2*w 만큼 움직였을 때 제자리로 감
        q = q + t%(2*h);        // t가 2*h 만큼 움직였을 떄 제자리로 감

        if(p>w) p = Math.abs(w * 2 - p);
        if(q>h) q = Math.abs(h * 2 - q);
//        p = w - Math.abs(w - p);
//        q = h - Math.abs(h - q);

        System.out.println(p + " " + q);

    }
}