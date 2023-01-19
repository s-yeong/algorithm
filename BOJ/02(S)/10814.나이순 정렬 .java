import java.io.*;
import java.util.*;

class Main {
    static class Person implements Comparable<Person>{
        int age;
        String name;
        int order;

        public Person(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }

        @Override
        public int compareTo(Person o) {
            if(this.age != o.age) return this.age-o.age;
            else {
                // 먼저 가입한 사람이 앞에 오는 순서로
                return this.order - o.order;
            }
        }
    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Person> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Person(age, name, i));
        }

        // 정렬 후 출력
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for(Person ob : list) {
            sb.append(ob.age).append(" ").append(ob.name).append("\n");
        }
        System.out.print(sb);
    }
}