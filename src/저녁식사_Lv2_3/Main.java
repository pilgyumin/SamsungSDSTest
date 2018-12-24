package Àú³á½Ä»ç_Lv2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int num;
		
		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Node arg0) {
			if(this.x > arg0.x) {
				return 1;
			}
			else if(this.x < arg0.x) {
				return -1;
			}
			else {
				if(this.y > arg0.y) {
					return 1;
				}
				else if(this.y < arg0.y) {
					return -1;
				}
			}
			return 0;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", num=" + num + "]";
		}

	}


	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1; i <= test; i++) {
				int n = Integer.parseInt(br.readLine());
				ArrayList<Node> input = new ArrayList<Node>();
				ArrayList<Node> ans = new ArrayList<Node>();
				for(int j = 1; j <= n; j++) {
					String[] s = br.readLine().split(" ");
					input.add(new Node(Integer.parseInt(s[0]),Integer.parseInt(s[1]),j));
				}
				
				Collections.sort(input);

				Node min = input.get(0);
				int size = input.size();
				ans.add(min);
				for(int j = 0; j < size; j++) {
					Node comp = input.get(j);
					if(min.y > comp.y) {
						ans.add(comp);
						min = input.get(j);
					}
				}

				Collections.sort(ans);

				System.out.printf("#%d ",i);
				for(int j = 0; j < ans.size(); j++) {
					System.out.print(ans.get(j).num + " ");
				}
				System.out.println();
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
