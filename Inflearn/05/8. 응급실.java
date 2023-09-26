import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main8 {
    
    static class Person {
        int id;
        int priority;

        public Person(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }
    public static int solution(int n, int m, int[] arr) {


        int answer = 0;

        Queue<Person> Q = new LinkedList<>();

        for(int i=0; i<n; i++) {
            Person p = new Person(i, arr[i]);
            Q.offer(p);
        }

        while(!Q.isEmpty()) {
            Person tmp = Q.poll();
            // 꺼낸 환자가 진료를 받을 수 있느냐,

            for(Person x : Q) { // 꺼낸 환자들을 모두 비교
                if(x.priority > tmp.priority) { // 우선순위가 밀려나면
                    Q.offer(tmp);   // 뒤에 넣고
                    tmp = null;
                    break;
                }
            }
            if(tmp != null) {
                answer++;
                if(tmp.id==m) return answer;
            }

        }



        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = sc.nextInt();


        System.out.println(solution(n, m, arr));
    }

}