import java.io.*;
import java.util.*;

/**
 * 16987. 계란으로 계란치기
 * 1.
 * 1-1. 계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
 * 1-2. 내구도가 0 이하가 되는 순간 깨진다.
 * 2.
 * 2-1. 가장 왼쪽에 계란을 든다.
 * 2-2. 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
 * 2-3. 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다. 이후 손에 든 계란을 원래 자리에 내려놓고 2-4번 과정을 진행한다.
 * 2-4. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2-2번 과정을 다시 진행한다. 단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
 * => 최대 몇 개의 계란을 깰 수 있는지
 *
 * 계란을 드는 순서는 정해져 있지만, 어떤 계란을 칠지는 정해져있지 X
 * => 완전 탐색
 */
public class Main {
    static int n;
    static List<Egg> list;
    static boolean[] ch;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        n = Integer.parseInt(br.readLine());
        for(int idx=0; idx<n; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.add(new Egg(durability, weight));
        }
        ch = new boolean[n];
        answer = 0;
        //2-1. 가장 왼쪽에 계란을 든다.
        recur(0);
        System.out.println(answer);
    }
    static void recur(int depth) {

        //단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
        if(depth == n) {
            int count = 0;
            for(int idx=0; idx<n; idx++) {
                if(ch[idx]) count++;
            }
            answer = Math.max(answer, count);
            return;
        }

        // 계란을 든다.
        Egg egg = list.get(depth);
        // 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
        boolean flag = false;
        if(!ch[depth]) {

            // 2-2. 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
            for (int idx = 0; idx < n; idx++) {
                // 자기 자신, 깨진 계란 깰 수 X
                if(depth == idx || ch[idx]) continue;

                flag = true;
                Egg targetEgg = list.get(idx);
                // 계란 치기
                //계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
                egg.durability -= targetEgg.weight;
                targetEgg.durability -= egg.weight;
                
                // 깨졌으면, 체크
                if(egg.durability <= 0) ch[depth] = true;
                if(targetEgg.durability <= 0) ch[idx] = true;

                recur(depth+1);
                
                // 계란 치기 원상 복구
                egg.durability += targetEgg.weight;
                targetEgg.durability += egg.weight;
                if(egg.durability > 0) ch[depth] = false;
                if(targetEgg.durability > 0) ch[idx] = false;
            }
        }

        // 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
        if(!flag) recur(depth+1);
    }
    static class Egg {
        int durability;
        int weight;

        public Egg (int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
}
