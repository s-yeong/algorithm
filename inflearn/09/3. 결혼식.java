import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Time implements Comparable<Time>  {
    int time;
    char state;

    public Time(int time, char state) {
        this.time = time;
        this.state = state;
    }

    @Override
    public int compareTo(Time o) {

        if(this.time != o.time) {
            return this.time - o.time;
        } else {
            return this.state - o.state;
        }
    }
}

public class Main {

    public static int solution(ArrayList<Time> arr) {

        int answer = Integer.MIN_VALUE;
        Collections.sort(arr);
        int cnt = 0;
        for(Time t : arr) {
            if(t.state == 's') cnt++;
            else cnt--;
            answer = Math.max(answer, cnt);
        }

        return answer;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int timeS = sc.nextInt();
            int timeE = sc.nextInt();

            arr.add(new Time(timeS, 's'));
            arr.add(new Time(timeE, 'e'));
        }

        System.out.print(solution(arr));

    }
}