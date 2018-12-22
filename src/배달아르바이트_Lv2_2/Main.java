package 배달아르바이트_Lv2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
 
	private static int[][] map;
	private static boolean[] visited;
	private static int n;
	private static int s;
	private static ArrayList<Integer> ar;
	private static int end;
	private static int ans;

	private static int dijkstra(int start, int cnt, ArrayList<Integer> arC, boolean[] bl, int d) {
		if(cnt >= end+1) {
			System.out.println("final");
			System.out.println("ans : " + d);
			if(ans > d) {
				ans = d;
				return 0;
			}
			return 0;
		}
		
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		boolean[] bAr = new boolean[bl.length];
		for(int i = 0; i < arC.size(); i++) {
			arr.add(arC.get(i));
			bAr[i] = bl[i];
		}
		
		int dis = d;
		bAr[arr.indexOf(start)] = true;
		for(int i = 0; i < arC.size(); i++) {
			System.out.print(bAr[i] + " ");
		}
		System.out.println();
		System.out.println(start + "-------------" );
		visited = new boolean[n];
		int dist[] = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = 987654321;
		}
		dist[start] = 0;
		for (int i = 0; i < n; i++) {
			int mincost = 987654321, minvertex = 0;
			for (int j = 0; j < n; j++) {
				if (!visited[j] && dist[j] < mincost) {
					mincost = dist[j];
					minvertex = j;
				}
			}
			visited[minvertex] = true;
			for (int j = 0; j < n; j++) {
				if (dist[j] > dist[minvertex] + map[minvertex][j]) {
					dist[j] = dist[minvertex] + map[minvertex][j];
				}
			}
			 for (int j = 0; j < n; j++) {
			 System.out.print(dist[j] + " ");
			 }
			 System.out.println();
			 System.out.println();
		}
		
		int min = 987654321;
		int index = 987654321;
		for(int k = 0; k < arr.size(); k++) {
			for (int j = 0; j < n; j++) {
				if(arr.get(k) == j && !bAr[arr.indexOf(j)]) {
					if(min > dist[j]) {
//						System.out.println(cnt + " " + arr.indexOf(j) + " " + j);
						min = dist[j];
						index = k;
					}
				}
			}
		}
		System.out.println("min : " + min);
		boolean check = true;
		for(int j = 0; j < bAr.length; j++) {
			if(!bAr[j]) {
				check = false;
			}
		}
		
		if(check) {
//			System.out.println("check!!!");
			min = dist[s-1];
			dijkstra(2,cnt+1,arr,bAr,dis+min);
			return 0;
		}
		for (int j = 0; j < n; j++) {
			if(min == dist[j] && arr.contains(j) && !bAr[arr.indexOf(j)]) {
				System.out.println(cnt+ " " + j + " " + (dis+min));
			}
		}
		for (int j = 0; j < n; j++) {
			if(min == dist[j] && arr.contains(j) && !bAr[arr.indexOf(j)]) {
				System.out.println(cnt+ " " + j + " " + (dis+min));
				dijkstra(j,cnt+1,arr,bAr,dis+min);
				System.out.println("out!!!");
				for(int i = 0; i < arC.size(); i++) {
					System.out.print(bAr[i] + " ");
				}
				System.out.println();
			}
		}


		return ans;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for (int i = 1; i <= test; i++) {
				String[] nmks = br.readLine().split(" ");
				String[] stend = br.readLine().split(" ");
				n = Integer.parseInt(nmks[0]);
				int m = Integer.parseInt(nmks[1]);
				int k = Integer.parseInt(nmks[2]);
				s = Integer.parseInt(nmks[3]);
				map = new int[n][n];
				ans = 987654321;
				for (int j = 0; j < n; j++) {
					for (int z = 0; z < n; z++) {
						if (j == z) {
							map[j][z] = 0;
							continue;
						}
						map[j][z] = 987654321;
					}
				}

				for (int j = 0; j < m; j++) {
					String[] ss = br.readLine().split(" ");
					int a = Integer.parseInt(ss[0]) - 1;
					int b = Integer.parseInt(ss[1]) - 1;
					map[a][b] = 1;
					map[b][a] = 1;
				}

				ar = new ArrayList<Integer>();
				
				for (int j = 0; j < stend.length; j++) {
					ar.add(Integer.parseInt(stend[j]) - 1);
				}
				Collections.sort(ar);
				ar.add(0, s-1);
				
//				for (int j = 0; j < ar.size(); j++) {
//					System.out.print(ar.get(j) + " ");
//				}
//				System.out.println();
				end = stend.length;
				boolean[] bl = new boolean[end+1];

				dijkstra(s - 1, 0, ar,bl,0);
				System.out.printf("#%d %d\n", i, ans);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
