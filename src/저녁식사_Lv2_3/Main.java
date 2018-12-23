package ����Ļ�_Lv2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			if(this.x < arg0.x) {
				return -1;
			}
			else if(this.x > arg0.x) {
				return 1;
			}
			else {
				if(this.y < arg0.y) {
					return -1;
				}
				else if(this.y > arg0.y) {
					return 1;
				}
			}
			return 0;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", num=" + num + "]";
		}

	}
	
	private static ArrayList<Node> input;
	private static PriorityQueue<Node> xComp;
	private static ArrayList<Node> yComp;
	private static ArrayList<Node> ans;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1; i <= test; i++) {
				int n = Integer.parseInt(br.readLine());
				input = new ArrayList<Node>();
				xComp = new PriorityQueue<Node>();
				yComp = new ArrayList<Node>();
				ans = new ArrayList<Node>();
				for(int j = 1; j <= n; j++) {
					String[] s = br.readLine().split(" ");
					input.add(new Node(Integer.parseInt(s[0]),Integer.parseInt(s[1]),j));
				}
				
				Collections.sort(input, new Comparator<Node>() {

					@Override
					public int compare(Node o1, Node o2) {
						if(Math.abs(o1.x - o1.y) < Math.abs(o2.x - o2.y)) {
							return -1;
						}
						else if(Math.abs(o1.x - o1.y) > Math.abs(o2.x - o2.y)) {
							return 1;
						}
						else {
							if(o1.x < o2.x) {
								return -1;
							}
							else if(o1.x > o2.x) {
								return 1;
							}
							else {
								if(o1.y < o2.y) {
									return -1;
								}
								else if(o1.y > o2.y) {
									return 1;
								}
							}
						}
						return 0;
					}
				});
//				
//				for(int j = 0; j < input.size(); j++) {
//					System.out.println(input.get(j));
//				}
//				System.out.println();
				
				Node min = input.get(0);
				int size = input.size();
				
				for(int j = 1; j < size; j++) {
					Node comp = input.get(j);
					if(min.x < comp.x && min.y < comp.y) {
						continue;
					}
					if(min.x > comp.x && min.y > comp.y) {
						min.x = comp.x;
						min.y = comp.y;
						min.num = comp.num;
						continue;
					}
					xComp.add(comp);
				}
				
				xComp.add(min);
				
				size = xComp.size();
//				for(int j = 0; j < size; j++) {
//					System.out.println(xComp.poll());
//				}
//				System.out.println();
				
				Node prevx = xComp.poll();
				Node add = new Node(prevx.x,prevx.y,prevx.num);
				while(!xComp.isEmpty()) {
					Node comp = xComp.poll();
					if(prevx.x == comp.x && prevx.y < comp.y) {
						continue;
					}
					yComp.add(comp);
					prevx = comp;
				}
				yComp.add(add);
				
				size = yComp.size();
//				for(int j = 0; j < size; j++) {
//					System.out.println(yComp.get(j));
//				}
//				System.out.println();
//				
				Collections.sort(yComp, new Comparator<Node>() {

					@Override
					public int compare(Node o1, Node o2) {
						if(o1.y < o2.y) {
							return -1;
						}
						else if(o1.y > o2.y){
							return 1;
						}
						else {
							if(o1.x < o2.x) {
								return -1;
							}
							else if(o1.x > o2.x) {
								return 1;
							}
						}
						return 0;
					}
				});
				
				size = yComp.size();
				Node prevy = yComp.get(0);
				ans.add(prevy);
				for(int j = 1; j < size; j++) {
					Node comp = yComp.get(j);
					if(prevy.y == comp.y && prevy.x < comp.x) {
						continue;
					}
					ans.add(comp);
					prevy = comp;
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
