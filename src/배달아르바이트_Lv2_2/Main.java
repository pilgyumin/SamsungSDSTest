package 배달아르바이트_Lv2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	static class Node implements Comparable<Node>{
		int end;
		int weight;
		
		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node arg0) {
			if(this.weight > arg0.weight) {
				return 1;
			}
			else if(this.weight < arg0.weight) {
				return -1;
			}
			return 0;
		}
	}
	
	private static int[][] result;
	private static int n;
	private static int s;
	private static int ans;
	private static ArrayList<Integer> ar;
	private static ArrayList<ArrayList<Node>> nlist;

	private static void dijkstra(int start) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(Collections.nCopies(n, 987654321));
		list.set(start, 0);
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node no = pq.poll();	
			int node = no.end;
			int weight = no.weight;
			
			if( weight > list.get(node) ) {
				continue;
			}
			
			Iterator<Node> it = nlist.get(node).iterator();
			while(it.hasNext()) {
				Node nInfo = it.next();
				int nNode = nInfo.end;
				int nWeight = nInfo.weight;
				
				int weight_node = list.get(node) + nWeight;
				if( list.get(nNode) > weight_node) {
					list.set(nNode, weight_node);
					pq.add(new Node(nNode, list.get(nNode)));
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			result[start][i] = list.get(i);
		}

	}

	public static int cal(int start, int distance, int cnt, boolean[] bb) {
		if(distance > ans) {
			return 0;
		}
		boolean[] bar = new boolean[bb.length];
		for (int i = 0; i < bb.length; i++) {
			bar[i] = bb[i];
		}
		if (cnt == ar.size()) {
			int comp = distance + result[start][s-1];
			if (ans > comp) {
				ans = comp;
			}
		}
		
		for (int i = 0; i < ar.size(); i++) {
			if (bar[i]) {
				continue;
			}
			bar[i] = true;
			cal(ar.get(i), result[start][ar.get(i)] + distance, cnt + 1, bar);
			bar[i] = false;
		}
		return 0;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test;
		try {
			test = Integer.parseInt(br.readLine());
			for (int i = 1; i <= test; i++) {
				String[] nmks = br.readLine().split(" ");
				String[] stend = br.readLine().split(" ");
				n = Integer.parseInt(nmks[0]);
				int m = Integer.parseInt(nmks[1]);
				int k = Integer.parseInt(nmks[2]);
				s = Integer.parseInt(nmks[3]);
				result = new int[n][n];
				ans = 987654321;
				nlist = new ArrayList<ArrayList<Node>>();
				
				for(int  j = 0; j < n; j++) {
					nlist.add(new ArrayList<Node>());
				}
				

				for (int j = 0; j < m; j++) {
					String[] ss = br.readLine().split(" ");
					int a = Integer.parseInt(ss[0]) - 1;
					int b = Integer.parseInt(ss[1]) - 1;
					nlist.get(a).add(new Node(b,1));
					nlist.get(b).add(new Node(a,1));
				}

				ar = new ArrayList<Integer>();

				for (int j = 0; j < stend.length; j++) {
					ar.add(Integer.parseInt(stend[j]) - 1);
				}

				dijkstra(s - 1);

				for (int j = 0; j < stend.length; j++) {
					dijkstra(Integer.parseInt(stend[j]) - 1);
				}
				
				boolean[] bb = new boolean[ar.size()];
				for (int j = 0; j < ar.size(); j++) {
					bb[j] = true;
					cal(ar.get(j), result[s - 1][ar.get(j)], 1, bb);
					bb[j] = false;
				}

				System.out.printf("#%d %d\n", i, ans);
			}

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

