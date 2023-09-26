import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Body implements Comparable<Body>{
    int h;
    int w;

    public Body(int cm, int kg) {
        this.h = cm;
        this.w = kg;
    }

    @Override
    public int compareTo(Body o) {
            return o.h - this.h;
    }
}


public class Main {

    static int n;

    public static int solution(ArrayList<Body> arr, int n) {
        Collections.sort(arr);
        /*
        int cnt = 1;
        Body tmp = arr.get(0);
        for(Body p : arr) {
            if(tmp.w < p.w) {
                cnt++;
                tmp = p;
            }
        }
        */
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for(Body ob : arr) {
            if(ob.w > max) {
                max = ob.w;
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Body> arr = new ArrayList<>();
        n = sc.nextInt();
        for(int i=0; i<n; i++) {
            int cm = sc.nextInt();
            int kg = sc.nextInt();
            arr.add(new Body(cm, kg));
        }

        System.out.println(solution(arr, n));
    }
}