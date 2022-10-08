import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
class Lecture implements Comparable<Lecture> {
    int money;
    int date;

    public Lecture(int money, int date) {
        this.money = money;
        this.date = date;
    }

    @Override
    public int compareTo(Lecture o) {
        return o.date - this.date;
    }
}

public class Main {

    static int max, n;
    public static int solution(ArrayList<Lecture> arr) {

        int answer = 0;
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        Collections.sort(arr);

        int j = 0;
        for(int i=max; i>=1; i--) {
            for(; j<n; j++) {
                if (arr.get(j).date < i) break;  // break 해야한다!
                // 이 조건이 아니라면 time이 크거나 같으니까 그 시간 때에 강연할 수 있다.
                pQ.offer(arr.get(j).money);
            }
            if(!pQ.isEmpty()) answer += pQ.poll();  // 비어있을 수도 있으니까
        }


        return answer;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        max = Integer.MIN_VALUE;
        ArrayList<Lecture> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int money = sc.nextInt();
            int date = sc.nextInt();
            if(date > max) max = date;
            arr.add(new Lecture(money, date));
        }

        System.out.print(solution(arr));

    }
}