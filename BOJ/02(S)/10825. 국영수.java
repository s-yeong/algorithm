import java.io.*;
import java.util.*;

public class Main {
    static class Person implements Comparable<Person>{
        String name;
        int kor;
        int eng;
        int math;

        public Person(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Person o) {
            if(this.kor != o.kor) return o.kor - this.kor;
            else if(this.eng != o.eng) return this.eng - o.eng;
            else if(this.math != o.math) return o.math - this.math;
            else return this.name.compareTo(o.name);    // 문자열 정렬!
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Person> list = new ArrayList<>();
        while(n-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Person(st.nextToken(),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        //정렬
        Collections.sort(list);
        //출력
        StringBuilder sb = new StringBuilder();
        for(Person ob : list) {
            sb.append(ob.name).append("\n");
        }
        System.out.print(sb);
    }
}