package 플라이하이_Lv2_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static class Node {
		int x;
		int y;
		int count;

		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", count=" + count + "]";
		}

	}

	private static int[][] map;
	private static int[][] cache;

	private static int dfs(int x, int y) {

		int ret = 0;

		if (map[x - 1][y] > map[x][y]) {
			if (cache[x - 1][y] == 0) {
				int prev = dfs(x - 1, y);
				ret = ret > prev ? ret : prev;
			} 
			else {
				ret = ret > cache[x - 1][y] ? ret : cache[x - 1][y];
			}
		}

		if (map[x + 1][y] > map[x][y]) {
			if (cache[x + 1][y] == 0) {
				int prev = dfs(x + 1, y);
				ret = ret > prev ? ret : prev;
			} 
			else {
				ret = ret > cache[x + 1][y] ? ret : cache[x + 1][y];
			}
		}

		if (map[x][y - 1] > map[x][y]) {
			if (cache[x][y-1] == 0) {
				int prev = dfs(x, y-1);
				ret = ret > prev ? ret : prev;
			} 
			else {
				ret = ret > cache[x][y-1] ? ret : cache[x][y-1];
			}
		}

		if (map[x][y + 1] > map[x][y]) {
			if (cache[x][y+1] == 0) {
				int prev = dfs(x, y+1);
				ret = ret > prev ? ret : prev;
			} 
			else {
				ret = ret > cache[x][y+1]? ret : cache[x][y+1];
			}
		}

		if (cache[x][y] < ret + 1) {
			cache[x][y] = (++ret);
		}

		return ret;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[n + 2][n + 2];
			cache = new int[n + 2][n + 2];

			for (int i = 1; i <= n; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(s[j - 1]);
				}
			}

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (cache[i][j] == 0) {
						dfs(i, j);
					}
				}
			}

			int ans = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (ans < cache[i][j]) {
						ans = cache[i][j];
					}
				}
			}
//			for(int i = 1; i <= n; i++) {
//				for(int j = 1; j <= n; j++) {
//					System.out.print(cache[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
	}
}
