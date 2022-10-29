import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int k = Integer.parseInt(st.nextToken()); // 한 방 배정 최대 수

        int[][] student = new int[7][2];    // 1~6학년, 여:0,남:1

        for(int i=0; i<n; i++) {
            // 성별 S, 학년 Y   S=0 여학생 S=1 남학생
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            student[y][s]++;
        }

        int room = 0;
        for(int i=1; i<=6; i++) {
            for(int j=0; j<=1; j++) {
                while(student[i][j] > 0) {
                    student[i][j] = student[i][j] - k;
                    room++;
                }
//                room += student[i][j] / k;
//                if(student[i][j] % k != 0) room++;
            }
        }

        System.out.println(room);



    }
}