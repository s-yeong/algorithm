import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Time implements Comparable<Time> {
    int s;
    int e;

    public Time(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Time o) {

        if(this.e != o.e) {
            return this.e - o.e;
        } else {
            return this.s - o.s;
        }
    }
}


public class Main {

    public static int solution(ArrayList<Time> arr, int n) {

        Collections.sort(arr);
        /*
        int cnt = 1;
        int time = arr.get(0).e;
        for(int i=1; i<arr.size(); i++) {
            if(time <= arr.get(i).s) {
                cnt++;
                time = arr.get(i).e;
            }
        }
        */
        int cnt = 0;
        int et = 0;
        for(Time ob : arr) {
            if(ob.s >= et) {
                cnt++;
                et = ob.e;
            }
        }

        return cnt;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Time> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            arr.add(new Time(s, e));
        }

        System.out.print(solution(arr, n));

    }
}