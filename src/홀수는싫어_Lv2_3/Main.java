package 홀수는싫어_Lv2_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int hol;
		int zak;
		
		public Node(int x, int y, int hol, int zak) {
			this.x = x;
			this.y = y;
			this.hol = hol;
			this.zak = zak;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", hol=" + hol + ", zak=" + zak + "]";
		}

		@Override
		public int compareTo(Node o) {
			if(this.hol == o.hol) {
				return this.zak - o.zak;
			}
			return this.hol - o.hol;
		}

	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int test = Integer.parseInt(br.readLine());
		for(int t = 1; t <= test; t++) {
			
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n+2][n+2];
			int[][][] visited = new int[2][n+2][n+2];
			
			for(int i = 0; i < n+2; i++) {
				Arrays.fill(map[i], -1);
				
			}

			for(int i = 1; i <= n; i++) {
				String[] s = br.readLine().split(" ");
				for(int j = 1; j <= n; j++) {
					int num = Integer.parseInt(s[j-1]);
					if(num % 2 == 1) {
						map[i][j] = 1;
					}
					else {
						map[i][j] = 0;
					}
					visited[0][i][j] = 90000;
					visited[1][i][j] = 90000;
				}
			}

			PriorityQueue<Node> pq = new PriorityQueue<>();
			
			if(map[1][1] == 0) {
				pq.add(new Node(1,1,0,1));
				visited[0][1][1] = 0;
				visited[1][1][1] = 1;
			}
			else {
				pq.add(new Node(1,1,1,0));
				visited[0][1][1] = 1;
				visited[1][1][1] = 0;
			}
			
			Node no = new Node(0,0,n*n+1,n*n+1);
			
			while(!pq.isEmpty()) {
				Node nn = pq.poll();
				
				int x = nn.x;
				int y = nn.y;
				int h = nn.hol;
				int j = nn.zak;
				
				if(x == n && y == n) {
					if(no.hol > h) {
						no.hol = h;
						no.zak = j;
					}
					else if(no.hol == h && no.zak > j) {
						no.zak = j;
					}
					continue;
				}
				
				if(no.hol < h) {
					continue;
				}

				if(no.hol == h && no.zak <= j) {
					continue;
				}
				
				if(visited[0][x-1][y] >= h && map[x-1][y] == 0) {
					if(visited[0][x-1][y] == h && visited[1][x-1][y] > j+1) {
						pq.add(new Node(x-1,y,h,j+1));
						visited[1][x-1][y] = j+1;
					}
					else if(visited[0][x-1][y] > h) {
						pq.add(new Node(x-1,y,h,j+1));
						visited[0][x-1][y] = h;
						visited[1][x-1][y] = j+1;
					}
				}
				
				if(visited[0][x+1][y] >= h && map[x+1][y] == 0) {
					if(visited[0][x+1][y] == h && visited[1][x+1][y] > j+1) {
						pq.add(new Node(x+1,y,h,j+1));
						visited[1][x+1][y] = j+1;
					}
					else if(visited[0][x+1][y] > h) {
						pq.add(new Node(x+1,y,h,j+1));
						visited[0][x+1][y] = h;
						visited[1][x+1][y] = j+1;
					}
				}
				
				if(visited[0][x][y-1] >= h && map[x][y-1] == 0) {
					if(visited[0][x][y-1] == h && visited[1][x][y-1] > j+1) {
						pq.add(new Node(x,y-1,h,j+1));
						visited[1][x][y-1] = j+1;
					}
					else if(visited[0][x][y-1] > h) {
						pq.add(new Node(x,y-1,h,j+1));
						visited[0][x][y-1] = h;
						visited[1][x][y-1] = j+1;
					}
				}
				
				if(visited[0][x][y+1] >= h && map[x][y+1] == 0) {
					if(visited[0][x][y+1] == h && visited[1][x][y+1] > j+1) {
						pq.add(new Node(x,y+1,h,j+1));
						visited[1][x][y+1] = j+1;
					}
					else if(visited[0][x][y+1] > h) {
						pq.add(new Node(x,y+1,h,j+1));
						visited[0][x][y+1] = h;
						visited[1][x][y+1] = j+1;
					}
				}

				if(visited[0][x-1][y] >= h+1 && map[x-1][y] == 1) {
					if(visited[0][x-1][y] == h+1 && visited[1][x-1][y] > j) {
						pq.add(new Node(x-1,y,h+1,j));
						visited[1][x-1][y] = j;
					}
					else if(visited[0][x-1][y] > h+1) {
						pq.add(new Node(x-1,y,h+1,j));
						visited[0][x-1][y] = h+1;
						visited[1][x-1][y] = j;
					}
				}
				
				if(visited[0][x+1][y] >= h+1 && map[x+1][y] == 1) {
					if(visited[0][x+1][y] == h+1 && visited[1][x+1][y] > j) {
						pq.add(new Node(x+1,y,h+1,j));
						visited[1][x+1][y] = j;
					}
					else if(visited[0][x+1][y] > h+1) {
						pq.add(new Node(x+1,y,h+1,j));
						visited[0][x+1][y] = h+1;
						visited[1][x+1][y] = j;
					}
				}
				
				if(visited[0][x][y-1] >= h+1 && map[x][y-1] == 1) {
					if(visited[0][x][y-1] == h+1 && visited[1][x][y-1] > j) {
						pq.add(new Node(x,y-1,h+1,j));
						visited[1][x][y-1] = j;
					}
					else if(visited[0][x][y-1] > h+1) {
						pq.add(new Node(x,y-1,h+1,j));
						visited[0][x][y-1] = h+1;
						visited[1][x][y-1] = j;
					}
				}
				
				if(visited[0][x][y+1] >= h+1 && map[x][y+1] == 1) {
					if(visited[0][x][y+1] == h+1 && visited[1][x][y+1] > j) {
						pq.add(new Node(x,y+1,h+1,j));
						visited[1][x][y+1] = j;
					}
					else if(visited[0][x][y+1] > h+1) {
						pq.add(new Node(x,y+1,h+1,j));
						visited[0][x][y+1] = h+1;
						visited[1][x][y+1] = j;
					}
				}
				
			}
			bw.write("#"+t+" "+no.hol+" "+no.zak+"\n");
		}
		bw.flush();
	}
}
