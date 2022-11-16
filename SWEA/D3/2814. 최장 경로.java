import java.io.*;
import java.util.*;


class Solution {
	
	static ArrayList<ArrayList<Integer>> graph;
	static int N, answer;
	static int[] ch,dis;
	static void DFS(int v, int dis) {
		answer = Math.max(answer, dis);
		
		for(int nv : graph.get(v)) {
			if(ch[nv] == 0) {
				ch[nv] = 1;
				DFS(nv, dis+1);
				ch[nv] = 0;
			}
		}
	}
	
	// alt+s+a+g  : 매개변수 있는 생성자 생성
 public static void main(String args[]) throws Exception {
	 
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder sb = new StringBuilder();
   int T = Integer.parseInt(br.readLine().trim());
   
   for(int tc = 1; tc<=T; tc++) {
	   
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   int N = Integer.parseInt(st.nextToken());
	   int M = Integer.parseInt(st.nextToken());
	   ch = new int[N+1];
	   
	   // 무방향 그래프, 최장 경로의 길이
	   graph = new ArrayList<>();
	   for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
	   
	   // 경로에는 같은 정점의 번호가 2번 이상 등장할 수 없으며
	   // 경로 상의 인접한 점들 사이에는 반드시 두 정점을 연결하는 간선이 존재해야 한다.
	   // 경로의 길이는 경로 상에 등장하는 정점의 개수를 나타낸다.
	   	
	   
	   // M개의 줄에 걸쳐 그래프 간선 정보
	   for(int i=0; i<M; i++) {
		   st = new StringTokenizer(br.readLine());
		   int a = Integer.parseInt(st.nextToken());
		   int b = Integer.parseInt(st.nextToken());
		   graph.get(a).add(b);
		   graph.get(b).add(a);
	   }
	   
	    
//	   if(M == 0) answer = 1;
//	   else {
		   for(int i=1; i<=N; i++) {
//			   if(graph.get(i).size() != 0) {
				   ch[i] = 1;
				   DFS(i, 1);
				   ch[i] = 0;
//			   }
		   }
//	   }
	 
	   
	  sb.append("#").append(tc).append(" ").append(answer).append("\n");
   }
   System.out.println(sb);	
 }
}