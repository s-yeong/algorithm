import java.io.*;
import java.util.*; 

class Solution {
    static List<List<Integer>> graph;
    static int[] info;
    static int answer;
    public int solution(int[] tmpInfo, int[][] edges) {
        info = tmpInfo;
        int n = info.length;
        graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }
        ch = new boolean[n];
        List<Integer> list = new ArrayList<>(graph.get(0));
        ch[0] = true;
        recur(1, 0, list);
        
        return answer;
    }
    static int sc,wc;
    static boolean[] ch;
    //이전에 방문했던 모든 노드의 자식 중 아직 방문하지 않은 노드를 관리해야 함
    static void recur(int sc, int wc, List<Integer> list) {
        
        answer = Math.max(answer, sc);
        
        for(int i=0; i<list.size(); i++) {
            int next = list.get(i);
            
            if(ch[next]) continue;
            
            int nsc = sc + (info[next] == 0 ? 1 : 0);
            int nwc = wc + (info[next] == 1 ? 1 : 0);

            //양 개수 <= 늑대 개수
            if(nsc <= nwc) continue;
            
            List<Integer> newList = new ArrayList<>(list);
            newList.remove(i);
            for(int child : graph.get(next)) {
                if(!ch[child]) newList.add(child);
            }
            
            ch[next] = true;
            recur(nsc, nwc, newList);
            ch[next] = false;
        }
        
        
    }
}

