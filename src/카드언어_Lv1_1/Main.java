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
						int size = li.size();
						liF = new LinkedList<Integer>();
						liB = new LinkedList<Integer>();
						for(int k = 0; k < size; k++) {
							liF.add(li.get(k));
							liB.add(li.get(k));
						}
						liF.addFirst(ar.get(j));
						liB.addLast(ar.get(j));
						
				
						for(int k = 0; k < size+1; k++) {
							if(liF.get(k) > liB.get(k)) {
								li = liF;
								break;
							}
							else if(liF.get(k) < liB.get(k)) {
								li = liB;
								break;
							}
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
