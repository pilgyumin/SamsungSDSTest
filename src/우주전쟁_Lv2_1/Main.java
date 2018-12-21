package 우주전쟁_Lv2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

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

				ArrayList<Missile> ar = new ArrayList<Missile>();
				ArrayList<Missile> ar2 = new ArrayList<Missile>();
				for (int j = 0; j < m; j++) {
					String[] mStr = br.readLine().split(" ");
					ar.add(new Missile(Integer.parseInt(mStr[0]), Integer.parseInt(mStr[1])));
					ar2.add(new Missile(Integer.parseInt(mStr[0]), Integer.parseInt(mStr[1])));
				}

				Collections.sort(ar);
				Collections.sort(ar2);
				int cc = 0;
				boolean end = true;
				while(cc < 2) {
					int min3 = 987654321;
					
					if(ar2.get(ar2.size()-1).power >= b) {
						if(ar2.get(ar2.size()-1).quantity > 1) {
							ar2.get(ar2.size()-1).quantity--;
						}
						else if(ar2.get(ar2.size()-1).quantity == 1) {
							ar2.remove(ar2.size()-1);
						}
					}
					else {
						if(ar.size() >= 2) {
							if(ar2.get(ar2.size()-1).power + ar2.get(ar2.size()-2).power < b) {
								end = false;
								break;
							}
							if(ar2.get(ar2.size()-1).quantity > 1) {
								ar2.get(ar2.size()-1).quantity--;
							}
							else if(ar2.get(ar2.size()-1).quantity == 1) {
								ar2.remove(ar2.size()-1);
							}
							if(ar2.get(ar2.size()-2).quantity > 1) {
								ar2.get(ar2.size()-2).quantity--;
							}
							else if(ar2.get(ar2.size()-2).quantity == 1) {
								ar2.remove(ar2.size()-2);
							}
						}
						else {
							if(ar2.get(ar2.size()-1).quantity > 1) {
								ar2.get(ar2.size()-1).quantity--;
							}
							else if(ar2.get(ar2.size()-1).quantity == 1) {
								ar2.remove(ar2.size()-1);
							}
						}
						
					}
					
					
					cc++;
				}
				
				
				//find 0 => 아무것도 못찾음, 1 => 한개짜리로 찾음, 2 => 두개짜리 찾음 
				int find = 0;
				int count = 0;
				int sum = 0;
				while (count < 2 && end) {
					find = 0;
					int min2 = 987654321;
					int min1 = 987654321;
					int first = -1;
					int last = -1;
					for (int j = ar.size() - 1; j >= 0; j--) {
						for (int k = j - 1; k >= 0; k--) {
							int power = ar.get(j).power + ar.get(k).power;
							if (b <= power && power < min2) {
								min2 = power;
								first = k;
								last = j;
							}
						}
					}
//					System.out.println(first + " " + last);
					for (int j = ar.size() - 1; j >= 0; j--) {
						int power = ar.get(j).power;
						if (b <= ar.get(j).power && ar.get(j).power < min1) {
							min1 = power;
							last = j;
						}
					}
					if (min2 == 987654321 || min1 == 987654321) {
						break;
					}
					if(min2 <= min1) {
						sum += min2;
						find = 2;
					}
					else if(min2 > min1) {
						sum += min1;
						find = 1;
					}
					
					if(find == 2) {
						if (ar.get(last).quantity > 1) {
//							System.out.println("a");
							ar.get(last).quantity--;
						} 
						else if (ar.get(last).quantity == 1) {
//							System.out.println("b");
							ar.remove(last);
						}
						
						if (ar.get(first).quantity > 1) {
//							System.out.println("c");
							ar.get(first).quantity--;
						} 
						else if (ar.get(first).quantity == 1) {
//							System.out.println("d");
							ar.remove(first);
						}
					}
					else if(find == 1) {
						if (ar.get(last).quantity > 1) {
//							System.out.println("a");
							ar.get(last).quantity--;
						} else if (ar.get(last).quantity == 1) {
//							System.out.println("b");
							ar.remove(last);
						}
					}
//					for (int j = ar.size() - 1; j >= 0; j--) {
//						System.out.println(ar.get(j));
//					}
//					System.out.println();
					count++;
				}
				if (find != 0) {
					System.out.printf("#%d %d\n", i, sum);
				} else {
					System.out.printf("#%d %d\n", i, -1);
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
