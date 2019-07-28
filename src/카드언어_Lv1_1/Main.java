package 카드언어_Lv1_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1; i <= test; i++) {
				int num = Integer.parseInt(br.readLine());
				String s = br.readLine();
				ArrayList<Integer> ar = new ArrayList<Integer>();
				LinkedList<Integer> li = new LinkedList<Integer>();
				LinkedList<Integer> liF = new LinkedList<Integer>();
				LinkedList<Integer> liB = new LinkedList<Integer>();
				for(int j = 0; j < num; j++) {
					ar.add((int)s.charAt((2 * j)));
				}
				
				for(int j = 0; j < num; j++) {
					if(li.isEmpty()) {
						li.add(ar.get(j));
						continue;
					}
					else {
						if(ar.get(j) >= li.getFirst()) {
							li.addFirst(ar.get(j));
						}
						else {
							li.addLast(ar.get(j));
						}
					}
				}
				
				System.out.printf("#%d ",i);
				for(int j = 0; j < num; j++) {
					System.out.printf("%c",li.get(j));
				}
				System.out.println();
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
