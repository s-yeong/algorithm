import java.io.*;
import java.util.*;

class Block {
    int x;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int y;
}

public class Main {
    static int width,height;

    static Block findXY(int dir, int dis) {
        int x = 0, y = 0;
        switch(dir) {
            case 1:
                x = dis;
                y = 0;
                break;
            case 2:
                x= dis;
                y = height;
                break;
            case 3:
                x = 0;
                y = dis;
                break;
            case 4:
                x = width;
                y = dis;
                break;
        }
        return new Block(x, y);
    }
    static int disCnt(int x1, int x2, int y1, int y2) {

        // 같은 dir에서 만나는 경우
        if ((y1 == 0 && y2 == 0) || (y1 == height && y2 == height)) return Math.abs(x1 - x2);
        else if((x1 == 0 && x2 == 0) || (x1 == width && x2 == width)) return Math.abs(y1 - y2);
        // 동서에서 만나는 경우
        else if((x1 == 0 && x2 == width) || (x1 == width && x2 == 0)) {
            int sumX = width;
            int sumY = Math.min(y1 + y2, height - y1 + height - y2);
            return sumX + sumY;
        }
        // 남북에서 만나는 경우
        else if((y1 == 0 && y2 == height) || (y1 == height && y2 == 0)) {
            int sumX = Math.min(x1 + x2, width - x1 + width - x2);;
            int sumY = height;
            return sumX + sumY;
        }
        else {
            int sumX = Math.abs(x1 - x2);
            int sumY = Math.abs(y1 - y2);
            return sumX+sumY;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Block> arr = new ArrayList<>();
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 블록의 가로 세로
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        // 상점 개수
        int num = Integer.parseInt(br.readLine());

        // 상점의 위치 // 꼭지점이 될 수 없음
        for(int i=0; i<num; i++) {
            st = new StringTokenizer(br.readLine());

            // 상점이 위치한 방향   1 - 북쪽(0), 2 - 남쪽(height), 3 - 서쪽(0), 4 - 동쪽(width)
            int dir = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            arr.add(findXY(dir, dis));
        }

        // 동근의 위치
        st = new StringTokenizer(br.readLine());
        int dir = Integer.parseInt(st.nextToken());
        int dis = Integer.parseInt(st.nextToken());
        Block b = findXY(dir, dis);


        for(Block ob : arr) {
            answer += disCnt(b.x, ob.x, b.y, ob.y);
        }
        System.out.println(answer);





    }
}