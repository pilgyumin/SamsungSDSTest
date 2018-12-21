package 우주전쟁_Lv2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

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

				LinkedList<Missile> solo = new LinkedList<Missile>();
				ArrayList<Missile> doubl = new ArrayList<Missile>();
				for (int j = 0; j < m; j++) {
					String[] mStr = br.readLine().split(" ");
					Missile mi = new Missile(Integer.parseInt(mStr[0]), Integer.parseInt(mStr[1]));
					if (mi.power >= b) {
						solo.add(mi);
					} else {
						doubl.add(mi);
					}
				}

//				int cc = 0;
//				boolean end = true;
//				while(cc < 2) {
//					int min3 = 987654321;
//					
//					if(ar2.get(ar2.size()-1).power >= b) {
//						if(ar2.get(ar2.size()-1).quantity > 1) {
//							ar2.get(ar2.size()-1).quantity--;
//						}
//						else if(ar2.get(ar2.size()-1).quantity == 1) {
//							ar2.remove(ar2.size()-1);
//						}
//					}
//					else {
//						if(ar.size() >= 2) {
//							if(ar2.get(ar2.size()-1).power + ar2.get(ar2.size()-2).power < b) {
//								end = false;
//								break;
//							}
//							if(ar2.get(ar2.size()-1).quantity > 1) {
//								ar2.get(ar2.size()-1).quantity--;
//							}
//							else if(ar2.get(ar2.size()-1).quantity == 1) {
//								ar2.remove(ar2.size()-1);
//							}
//							if(ar2.get(ar2.size()-2).quantity > 1) {
//								ar2.get(ar2.size()-2).quantity--;
//							}
//							else if(ar2.get(ar2.size()-2).quantity == 1) {
//								ar2.remove(ar2.size()-2);
//							}
//						}
//						else {
//							if(ar2.get(ar2.size()-1).quantity > 1) {
//								ar2.get(ar2.size()-1).quantity--;
//							}
//							else if(ar2.get(ar2.size()-1).quantity == 1) {
//								ar2.remove(ar2.size()-1);
//							}
//						}
//						
//					}
//					
//					
//					cc++;
//				}

				int shield = b;
				int sum = 0;
				while (n > 0) {
					// 한 개로 부심
					if (solo.getFirst().quantity == shield) {
						n -= solo.getFirst().quantity;
						sum += shield * solo.getFirst().quantity;
						doubl.remove(0);
					}
					// 두 개로 부심(둘 다 방어력보다 낮은 미사일)
					int dsize = doubl.size();
					for (int j = 0; j < dsize; j++) {
						for (int k = dsize - 1; k >= j + 1; k--) {
							if (doubl.get(j).power + doubl.get(k).power < shield) {
								break;
							}
							if (doubl.get(j).power + doubl.get(k).power == shield) {
								if (doubl.get(j).quantity > doubl.get(k).quantity) {
									n -= doubl.get(k).quantity;
									sum += shield * doubl.get(k).quantity;
									doubl.get(j).quantity -= doubl.get(k).quantity;
									doubl.remove(k);
								} else if (doubl.get(j).quantity < doubl.get(k).quantity) {
									n -= doubl.get(j).quantity;
									sum += shield * doubl.get(j).quantity;
									doubl.get(k).quantity -= doubl.get(j).quantity;
									doubl.remove(j);
								} else {
									n -= doubl.get(j).quantity;
									sum += shield * doubl.get(k).quantity;
									doubl.remove(k);
									doubl.remove(j);
								}
							}
						}
					}
					// 두 개로 부심(하나는 솔로 하나는 더블용 미사일)
					dsize = doubl.size();
					int ssize = solo.size();
					for (int j = 0; j < ssize; j++) {
						for (int k = 0; k < dsize; k++) {
							if (solo.get(j).power + doubl.get(k).power > shield) {
								break;
							}
							if (solo.get(j).power + doubl.get(k).power == shield) {
								if (solo.get(j).quantity > doubl.get(k).quantity) {
									n -= doubl.get(k).quantity;
									sum += shield * doubl.get(k).quantity;
									solo.get(j).quantity -= doubl.get(k).quantity;
									doubl.remove(k);
								} else if (solo.get(j).quantity < doubl.get(k).quantity) {
									n -= solo.get(j).quantity;
									sum += shield * solo.get(j).quantity;
									doubl.get(k).quantity -= solo.get(j).quantity;
									solo.remove(j);
								} else {
									n -= solo.get(j).quantity;
									sum += shield * solo.get(j).quantity;
									doubl.remove(k);
									solo.remove(j);
								}
							}
						}
					}
					shield++;
				}
				if (n < 0) {
					sum -= Math.abs(n) * shield;
				}

//				while (count < 2 && end) {
//					find = 0;
//					int min2 = 987654321;
//					int min1 = 987654321;
//					int first = -1;
//					int last = -1;
//					for (int j = ar.size() - 1; j >= 0; j--) {
//						for (int k = j - 1; k >= 0; k--) {
//							int power = ar.get(j).power + ar.get(k).power;
//							if (b <= power && power < min2) {
//								min2 = power;
//								first = k;
//								last = j;
//							}
//						}
//					}
////					System.out.println(first + " " + last);
//					for (int j = ar.size() - 1; j >= 0; j--) {
//						int power = ar.get(j).power;
//						if (b <= ar.get(j).power && ar.get(j).power < min1) {
//							min1 = power;
//							last = j;
//						}
//					}
//					if (min2 == 987654321 || min1 == 987654321) {
//						break;
//					}
//					if(min2 <= min1) {
//						sum += min2;
//						find = 2;
//					}
//					else if(min2 > min1) {
//						sum += min1;
//						find = 1;
//					}
//					
//					if(find == 2) {
//						if (ar.get(last).quantity > 1) {
////							System.out.println("a");
//							ar.get(last).quantity--;
//						} 
//						else if (ar.get(last).quantity == 1) {
////							System.out.println("b");
//							ar.remove(last);
//						}
//						
//						if (ar.get(first).quantity > 1) {
////							System.out.println("c");
//							ar.get(first).quantity--;
//						} 
//						else if (ar.get(first).quantity == 1) {
////							System.out.println("d");
//							ar.remove(first);
//						}
//					}
//					else if(find == 1) {
//						if (ar.get(last).quantity > 1) {
////							System.out.println("a");
//							ar.get(last).quantity--;
//						} else if (ar.get(last).quantity == 1) {
////							System.out.println("b");
//							ar.remove(last);
//						}
//					}
////					for (int j = ar.size() - 1; j >= 0; j--) {
////						System.out.println(ar.get(j));
////					}
////					System.out.println();
//					count++;
//				}

				System.out.printf("#%d %d\n", i, sum);

			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
