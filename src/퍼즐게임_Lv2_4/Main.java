package 퍼즐게임_Lv2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1; i <= test; i++) {
				map = new int[6][4];
				visited = new boolean[6][4];
				for(int j = 0 ; j < 6; j++) {
					String[] s = br.readLine().split(" ");
					for(int k = 0; k < 4; k++) {
						map[j][k] = Integer.parseInt(s[k]);
						if(map[j][k] == 0) {
							visited[j][k] = true;
						}
					}
				}
				
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		

	}

}
