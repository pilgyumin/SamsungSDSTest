package øÏ¡÷¿¸¿Ô_Lv2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

	static class Missile implements Comparable<Missile> {
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
			if (this.power < o.power) {
				return -1;
			} else if (this.power > o.power) {
				return 1;
			}
			return 0;
		}

	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for (int i = 1; i <= test; i++) {
				String[] nmb = br.readLine().split(" ");
				int n = Integer.parseInt(nmb[0]);
				int m = Integer.parseInt(nmb[1]);
				int b = Integer.parseInt(nmb[2]);
				
				PriorityQueue<Missile> pq = new PriorityQueue<Missile>();
				
				ArrayList<Missile> doubl = new ArrayList<Missile>();
				
				for (int j = 0; j < m; j++) {
					String[] mStr = br.readLine().split(" ");
					Missile mi = new Missile(Integer.parseInt(mStr[0]), Integer.parseInt(mStr[1]));
					if (mi.power >= b) {
						pq.add(mi);
					} 
					else {
						doubl.add(mi);
					}
				}
				
				Collections.sort(doubl);
				
				int dsize = doubl.size();
				for (int j = 0; j < dsize; j++) {
					for (int k = 0 ; k < dsize; k++) {
						int jq = doubl.get(j).quantity;
						int jp = doubl.get(j).power;
						int kq = doubl.get(k).quantity;
						int kp = doubl.get(k).power;
						
						if(jq == 0 ||  kq == 0) {
							continue;
						}
						if(j == k && jq <= 1) {
							continue;
						}
						if (jp + kp < b) {
							continue;
						}
						else if(jp + kp >= b) {
							if (jq > kq) {
								pq.add(new Missile(jp + kp,kq));
								jq -= kq;
								doubl.get(j).quantity = jq;
								doubl.get(k).quantity = 0;
							} 
							
							else if (jq < kq) {
								pq.add(new Missile(jp + kp,jq));
								kq -= jq;
								doubl.get(k).quantity = kq;
								doubl.get(j).quantity = 0;
							} 
							
							else {
								if(j == k) {
									if(jq % 2 == 0) {
										pq.add(new Missile(jp + kp, jq / 2));
										doubl.get(j).quantity = 0;
									}
									else {
										pq.add(new Missile(jp + kp, jq / 2));
										doubl.get(j).quantity = (jq % 2);
									}
									
								}
								else {
									pq.add(new Missile(jp + kp,jq));
									doubl.get(k).quantity = 0;
									doubl.get(j).quantity = 0;
								}
							}
						}
					}
				}
				
				int sum = 0;
				Missile attack = null;
				while(!pq.isEmpty()) {
					if(n <= 0) {
						break;
					}
					attack = pq.poll();
					sum += attack.power * attack.quantity;
					n -= attack.quantity;
				}

				if(n > 0) {
					System.out.printf("#%d %d\n",i,-1);
				}
				else {
					if(n == 0) {
						System.out.printf("#%d %d\n",i,sum);
					}
					else {
						sum -= Math.abs(n) * attack.power;
						System.out.printf("#%d %d\n",i,sum);
					}
				}				
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
