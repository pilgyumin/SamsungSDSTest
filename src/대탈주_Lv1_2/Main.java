package ¥Î≈ª¡÷_Lv1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int test = Integer.parseInt(br.readLine());
			for(int i = 1; i <= test; i++) {
				String[] nm = br.readLine().split(" ");
				int[] n = new int[Integer.parseInt(nm[0])];
				int[] m = new int[Integer.parseInt(nm[1])];
				String[] arM = br.readLine().split(" ");
				String[] arN = br.readLine().split(" ");
				ArrayList<Integer> ar = new ArrayList<Integer>();
				int mCnt = 0;
				for(int j = 0; j < m.length; j++) {
					int num = Integer.parseInt(arM[j]);
					if(Integer.parseInt(arM[j]) != 0) {
						mCnt += num;
						for(int k = 0; k < num; k++) {
							ar.add(j+1);
						}
					}
				}
				int[] comp = new int[mCnt];
				for(int j = 0; j < mCnt; j++) {
					comp[j] = ar.get(j);
				}
				
				int ans = 0;
				for(int j = 0; j <= n.length-mCnt; j++) {
					int[] cutAr = new int[mCnt];
					for(int k = j; k < j+mCnt; k++) {
						cutAr[k - j] = Integer.parseInt(arN[k]);
					}
					Arrays.sort(cutAr);
					boolean check = true;
					for(int k = 0; k < mCnt; k++) {
						if(comp[k] != cutAr[k]) {
							check = false;
							break;
						}
					}
					if(check) {
						ans = j + 1;
						break;
					}
				}
				System.out.printf("#%d %d\n",i,ans);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
