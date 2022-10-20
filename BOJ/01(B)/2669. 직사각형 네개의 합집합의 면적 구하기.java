import java.util.*;


public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int[][] map = new int[101][101];
        int cnt = 0;

        for(int i=0; i<4; i++) {
            // 왼쪽 아래 x, y 오른쪽 위 x,y
            int lx = sc.nextInt();
            int ly = sc.nextInt();
            int rx = sc.nextInt();
            int ry = sc.nextInt();

            for(int j=ly; j<ry; j++) {
                for(int k=lx; k<rx; k++) {
                    if(map[j][k] == 0) {
                        cnt++;
                        map[j][k] = 1;
                    }
                }
            }
        }


        System.out.println(cnt);





    }
}