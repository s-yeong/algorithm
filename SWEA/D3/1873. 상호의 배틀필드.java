import java.io.*;
import java.util.*;


class Solution {
	static int H,W;
	static char[][] board;
	
	static void solution(char[] in, int idxI, int idxJ) {
		
		for(char x : in) {
			if(x == 'U') {
				if(idxI - 1 >= 0 && board[idxI-1][idxJ] == '.') {
					board[idxI][idxJ] = '.';
					idxI = idxI-1;
				}
				board[idxI][idxJ] = '^';
			}
			else if(x == 'D') {
				if(idxI + 1 < H && board[idxI+1][idxJ] == '.') {
					board[idxI][idxJ] = '.';
					idxI = idxI+1;
				}
				board[idxI][idxJ] = 'v';
			}
			else if(x == 'L') {
				if(idxJ - 1 >= 0 && board[idxI][idxJ-1] == '.') {
					board[idxI][idxJ] = '.';
					idxJ = idxJ-1;
				}
				board[idxI][idxJ] = '<';
			}
			else if(x == 'R') {
				if(idxJ + 1 < W && board[idxI][idxJ+1] == '.') {
					board[idxI][idxJ] = '.';		
					idxJ = idxJ+1;
				}
				board[idxI][idxJ] = '>';
			}
			else { // 포탄 발사
				char tmp = board[idxI][idxJ];
				// 벽에 부딪히면 -> 포탄 소멸, 벽돌이면 벽 파괴되어 평지, 강철이면 아무일도 X
				int nI=idxI;
				int nJ=idxJ;
				// 발사 지점
				switch(tmp) {
				case '^': nI++; break;
				case 'v': nI--; break;
				case '<': nJ--; break;
				case '>': nJ++; break;
				}
				
				while(nI>=0 && nJ >=0 && nI < H && nJ < W) {
					// 벽돌 벽에 부딪히면 -> 평지, 멈춤
					if(board[nI][nJ] == '*') {
						board[nI][nJ] = '.';
						break;
					}
					// 강철이면,멈춤
					else if(board[nI][nJ] == '#') break;
					
					else {
						switch(tmp) {
						case '^': nI++; break;
						case 'v': nI--; break;
						case '<': nJ--; break;
						case '>': nJ++; break;
						}
					}
				}
				
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
	  H = Integer.parseInt(st.nextToken());	// 높이
	  W = Integer.parseInt(st.nextToken());	// 너비
	  // HxW 격자판
	  board = new char[H][W];
	  
	  for(int i=0; i<H; i++) {
		  //길이가 W인 문자열
		  board[i] = br.readLine().toCharArray();
	  }
	  // 사용자가 넣을 입력의 개수
	  int N = Integer.parseInt(br.readLine());
	  // 길이가 N인 문자열
	  char[] in = br.readLine().toCharArray();
	  
	  int idxI = 0;
	  int idxJ = 0;
	  for(int i=0; i<H; i++) {
		  for(int j=0; j<W; j++) {
			  // 전차 위치 파악
			  if(board[i][j] == '^' || board[i][j] == 'v' 
					  || board[i][j] == '<' || board[i][j] == '>') {
				  idxI = i;
				  idxJ = j;
			  }
		  }
	  }
	  
	  solution(in, idxI, idxJ);
	   
	   
	  sb.append("#").append(tc).append(" ");
	  for(int i=0; i<H; i++) {
		  for(int j=0; j<W; j++) {
			  sb.append(board[i][j]);
		  }
		  sb.append("\n");
	  }
   }
   System.out.println(sb);	
 }
}