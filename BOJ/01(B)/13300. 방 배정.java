import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {


    int sex;
    int year;

    public Student(int sex, int year) {
        this.sex = sex;
        this.year = year;
    }

    @Override
    public int compareTo(Student o) {
        if(this.year != o.year) return this.year - o.year;
        else return this.sex - o.sex;
    }
}


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int k = Integer.parseInt(st.nextToken()); // 한 방 배정 최대 수
        ArrayList<Student> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            // 성별 S, 학년 Y   S=0 여학생 S=1 남학생
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Student(s, y));
        }

        // 필요한 방의 최소 개수
        Collections.sort(arr);
        int room = 0;
        int cnt = 0;
        int curY = Integer.MAX_VALUE;
        int curS = Integer.MAX_VALUE;

        for(Student ob : arr) {
            if(ob.year != curY || ob.sex != curS) {
                room++;
                cnt = 0;
                if(ob.year != curY) curY = ob.year;
                if(ob.sex != curS) curS = ob.sex;
            }

            cnt++;
            if(cnt>k) {
                cnt = 1;
                room++;
            }
        }
        System.out.println(room);



    }
}