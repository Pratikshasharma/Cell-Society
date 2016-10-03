package simulations.sugarscape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates the 51x51 array of sugar capacity values for sugarscape
 * @author Ryan Anders
 *
 */
public class SugarMap {

	private int[][] sugarCapacity;
	
	public SugarMap() {
		int[] a = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1};
		int[] b = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1};
		int[] c = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1};
		int[] d = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1};
		int[] e = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1};
		int[] f = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1};
		int[] g = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,1};
		int[] h = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2};
		int[] i = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2};
		int[] j = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2};
		int[] k = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2};
		int[] l = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2};
		int[] m = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2};
		int[] n = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2,2,2};
		int[] o = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2,2};
		int[] p = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2};
		int[] q = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2};
		int[] r = new int[]{1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2};
		int[] s = new int[]{1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2};
		int[] t = new int[]{1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2};
		int[] u = new int[]{1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2,1};
		int[] v = new int[]{1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,2,2,2,2,2,1,1};
		int[] w = new int[]{1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,2,2,2,2,2,1,1,1};
		int[] x = new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,3,3,3,3,3,3,3,2,2,2,2,2,2,1,1,1};
		int[] y = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,1,1,1,1};
		int[] z = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,1,1,1,1,1,1};
		int[] aa = reverse(z);
		int[] ba = reverse(y);
		int[] ca = reverse(x);
		int[] da = reverse(w);
		int[] ea = reverse(v);
		int[] fa = reverse(u);
		int[] ga = reverse(t);
		int[] ha = reverse(s);
		int[] ia = reverse(r);
		int[] ja = reverse(q);
		int[] ka = reverse(p);
		int[] la = reverse(o);
		int[] ma = reverse(n);
		int[] na = reverse(m);
		int[] oa = reverse(l);
		int[] pa = reverse(k);
		int[] qa = reverse(j);
		int[] ra = reverse(i);
		int[] sa = reverse(h);
		int[] ta = reverse(g);
		int[] ua = reverse(f);
		int[] va = reverse(e);
		int[] wa = reverse(d);
		int[] xa = reverse(c);
		int[] ya = reverse(b);
		sugarCapacity =  new int[][]{a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,ba,ca,da,ea,fa,ga,ha,ia,ja,ka,la,ma,na,oa,pa,qa,ra,sa,ta,ua,va,wa,xa,ya};
		
		

	}
	
	private int[] reverse(int[] a) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i<a.length; i++) {
			list.add(a[i]);
		}
		Collections.reverse(list);
		for (int i = 0; i<list.size(); i++) {
			a[i] = list.get(i).intValue();
		}
		return a;
	}
	
	public int getSugarCapacity(int i, int j) {
		return sugarCapacity[i][j];
	}
}
