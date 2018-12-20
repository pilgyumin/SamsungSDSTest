package 배달아르바이트_Lv2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int[][] map;
	private static boolean[] visited;
	private static int n;

	private static int dijkstra(int start, int end) {
		visited = new boolean[n];
		int dist[] = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = 4000;
		}
		dist[start] = 0;
		for (int i = 0; i < n; i++) {
			int mincost = 4000, minvertex = 0;
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
		}
		return dist[end];
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
				int s = Integer.parseInt(nmks[3]);
				map = new int[n][n];

				for (int j = 0; j < n; j++) {
					for (int z = 0; z < n; z++) {
						if (j == z) {
							map[j][z] = 0;
							continue;
						}
						map[j][z] = 4000;
					}
				}

				for (int j = 0; j < m; j++) {
					String[] ss = br.readLine().split(" ");
					int a = Integer.parseInt(ss[0]) - 1;
					int b = Integer.parseInt(ss[1]) - 1;
					map[b][a] = 1;
					map[a][b] = 1;
				}

				int[] startend = new int[k + 2];
				for (int j = 0; j < startend.length; j++) {
					if (j == 0 || j == startend.length - 1) {
						startend[j] = s;
						continue;
					}
					startend[j] = Integer.parseInt(stend[j - 1]);
				}

				int ans = 0;
				for (int j = 0; j < startend.length - 1; j++) {
					int a = startend[j] - 1;
					int b = startend[j + 1] - 1;
					ans += dijkstra(a, b);
				}
				System.out.printf("#%d %d\n", i, ans);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
