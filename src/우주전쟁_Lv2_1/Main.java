package øÏ¡÷¿¸¿Ô_Lv2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
	
	static class Missile implements Comparable<Missile>{
		int power;
		int quantity;
		
		public Missile(int power, int quantity) {
			this.power = power;
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return "Missile [power=" + power + ", quantity=" + quantity + "]";
		}

		@Override
		public int compareTo(Missile o) {
			if(this.power < o.power) {
				return -1;
			}
			else if(this.power > o.power) {
				return 1;
			}
			return 0;
		}
		
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1; i <= test; i++) {
				String[] nmb = br.readLine().split(" ");
				int n = Integer.parseInt(nmb[0]);
				int m = Integer.parseInt(nmb[1]);
				int b = Integer.parseInt(nmb[2]);
				
				LinkedList<Missile> li = new LinkedList<Missile>();
				for(int j = 0; j < m; j++) {
					String[] mStr = br.readLine().split(" ");
					li.add(new Missile(Integer.parseInt(mStr[0]),Integer.parseInt(mStr[1])));
				}
				
				Collections.sort(li);
				for(int j = 0; j < m; j++) {
					System.out.println(li.get(j));
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
