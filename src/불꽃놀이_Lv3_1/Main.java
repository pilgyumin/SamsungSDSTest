package ∫“≤…≥Ó¿Ã_Lv3_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {
	
	static class Node implements Comparable<Node>{
		int end;
		BigInteger weight;
		
		public Node(int end, BigInteger weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node arg0) {
			if(this.weight.compareTo(arg0.weight) == 1) {
				return 1;
			}
			else if(this.weight.compareTo(arg0.weight) == -1) {
				return -1;
			}
			return 0;
		}
	}
	
	private static void dijkstra(int start) {
		ArrayList<BigInteger> list = new ArrayList<BigInteger>();
		list.addAll(Collections.nCopies(2 * n, new BigInteger("9999999999999999")));
		list.set(start, new BigInteger("0"));
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		pq.add(new Node(start,new BigInteger("0")));
		
		while(!pq.isEmpty()) {
			Node no = pq.poll();	
			int node = no.end;
			BigInteger weight = no.weight;
			
			if( weight.compareTo(list.get(node)) == 1 ) {
				continue;
			}
			
			Iterator<Node> it = nlist.get(node).iterator();
			while(it.hasNext()) {
				Node nInfo = it.next();
				int nNode = nInfo.end;
				BigInteger nWeight = nInfo.weight;
				
				BigInteger weight_node = list.get(node).add(nWeight);
				if( list.get(nNode).compareTo(weight_node) == 1) {
					list.set(nNode, weight_node);
					pq.add(new Node(nNode, list.get(nNode)));
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}

	}
	
	private static ArrayList<ArrayList<Node>> nlist;
	private static int n;
	private static BigInteger[] result;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1 ; i <= test; i++) {
				n = Integer.parseInt(br.readLine());
				nlist = new ArrayList<ArrayList<Node>>();
				result = new BigInteger[2*n];
				for(int  j = 0; j < 2 * n; j++) {
					nlist.add(new ArrayList<Node>());
				}
				
				for(int j = 0; j < 2 * n - 1; j++) {
					String[] s = br.readLine().split(" ");
					int a = Integer.parseInt(s[0]) -1;
					int b = Integer.parseInt(s[1]) -1;
					int c = Integer.parseInt(s[2]);
					nlist.get(a).add(new Node(b,new BigInteger(String.valueOf(c))));
					nlist.get(b).add(new Node(a,new BigInteger(String.valueOf(c))));
				}
				
				dijkstra(2*n -1);
				
				BigInteger max = new BigInteger("0");
				for(int j = 0; j < n; j++) {
					if(result[j].compareTo(max) == 1) {
						max = result[j];
					}
				}

				BigInteger sum = new BigInteger("0");
				for(int j = 0; j < n; j++) {
					if(result[j].compareTo(max) == -1) {
						sum = sum.add(max.subtract(result[j]));
					}
				}
				
				System.out.printf("#%d %s\n",i,sum.toString());
				
//				for(int j = 0; j < result.length; j++) {
//					System.out.print(result[j] + " ");
//				}
				
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
