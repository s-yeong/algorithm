import java.util.*;
class Brick implements Comparable<Brick>{
    int area;
    int height;
    int weight;

    public Brick(int area, int height, int weight) {
        this.area = area;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Brick o) {
        return o.area - this.area;
    }
}

public class Main {
    static int[] dy;
    public static int solution (int n, ArrayList<Brick> arr) {

        int answer = 0;

        Collections.sort(arr);

        dy[0] = arr.get(0).height;
        answer = dy[0]; // dy[0]의 무게가 가장 작으면, 올릴 수 없기 때문에 초기화 시켜놔야함!!

        for(int i=1; i<n; i++) {
            int max = 0;    // 최대 높이
            for(int j=i-1; j>=0; j--) {
                if(arr.get(i).weight < arr.get(j).weight && dy[j] > max) max = dy[j];
            }
            dy[i] = max + arr.get(i).height;
            answer = Math.max(answer, dy[i]);
        }



        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ArrayList<Brick> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int area = sc.nextInt();
            int height = sc.nextInt();
            int weight = sc.nextInt();
            arr.add(new Brick((area), height, weight));
        }

        dy = new int[n];
        System.out.print(solution(n, arr));



    }
}