import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
//		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			char[] ss = fs.next().toCharArray();
			int n = ss.length;
			//convert input string into 1-based char array
			char[] s = new char[n+1];
			s[0] = '?'; //dummy character (since 1st character will be ignored)
			for (int i = 1; i <= n; i++) {
				s[i] = ss[i-1];
			}
			long[][] freq = new long[n+1][26];
			for (int i = 0; i <= n; i++) {
				Arrays.fill(freq[i], 0);
			}
			for (int i = 1; i <= n; i++) {
//				for (int j = 0; j < 26; j++) {
//					freq[i][j] += freq[i-1][j];
//				}
				freq[i][s[i]-'a'] += 1;
			}
			BIT bt = new BIT(freq);
//			System.out.println(bt.query(1, 1));
			int q =  fs.nextInt();
			for (int i = 0; i < q; i++) {
				int choice = fs.nextInt();
				if (choice == 1) {
					int pos = fs.nextInt();
					char c = fs.next().charAt(0);
					//assign new value
//					for (int j = pos - 1; j < n; j++) {
//						freq[j][s[pos-1]-'a']--;
//						freq[j][c-'a']++;
//					}
					bt.update(pos, -1, s[pos]);
					bt.update(pos, 1, c);
					s[pos] = c; //don't forget to update the character
				} else {
					int l = fs.nextInt(), r = fs.nextInt();
					System.out.println(bt.query(l, r));
				}
			}
		}
		out.close();
	}
	
	static class BIT {
		int n;
		long[][] tree;
		
		public BIT(long[][] values) {
			n = values.length;
			tree = new long[n][26];
			values[0][0] = 0L;
			for (int i = 0; i < n; i++) {
				tree[i] = values[i].clone();
			}
			for (int i = 1; i < n; i++) {
				int parent = i + lsb(i);
				if (parent < n) {
					for (int j = 0; j < 26; j++) {
						tree[parent][j] += tree[i][j];
					}
				}
			}
//			for (int i = 1; i < n; i++) {
//				System.out.println(i + ":");
//				for (int j = 0; j < 26; j++) {
//					System.out.print(tree[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
		
		static int lsb(int i) {
			return i & -i;
		}
		
		long[] prefixSum(int i) {
			long[] sum = new long[26];
			Arrays.fill(sum, 0);
			while (i != 0) {
				for (int j = 0; j < 26; j++) {
					sum[j] += tree[i][j];
				}
				i &= ~lsb(i); //same as i -= lsb(i)
			}
			return sum;
		}
		
		int query(int l, int r) {
			long[] pr = prefixSum(r);
			long[] pl = prefixSum(l - 1);
			int counter = 0;
			for (int i = 0; i < 26; i++) {
				if (pr[i] - pl[i] > 0) {
					counter++;
				}
			}
			return counter;
		}
		
		void add(int i, long value, char letter) {
			while (i < n) {
				if (letter - 'a' < 0) {
					throw null;
				}
				tree[i][letter-'a'] += value;
				i += lsb(i);
			}
		}
		
		void update(int i, long value, char letter) {
			add(i, value, letter);
		}
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
