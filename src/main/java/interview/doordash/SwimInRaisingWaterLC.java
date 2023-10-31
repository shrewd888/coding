package interview.doordash;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SwimInRaisingWaterLC
{
	public int dfs(int[][] grid) {
		int N = grid.length;
		int lo = grid[0][0], hi = N * N;
		while (lo < hi) {
			int mi = lo + (hi - lo) / 2;
			if (!possible(mi, grid)) {
				lo = mi + 1;
			} else {
				hi = mi;
			}
		}
		return lo;
	}

	public boolean possible(int T, int[][] grid) {
		int N = grid.length;
		Set<Integer> seen = new HashSet();
		seen.add(0);
		int[] dr = new int[]{1, -1, 0, 0};
		int[] dc = new int[]{0, 0, 1, -1};

		Stack<Integer> stack = new Stack();
		stack.add(0);

		while (!stack.empty()) {
			int k = stack.pop();
			int r = k / N, c = k % N;
			if (r == N-1 && c == N-1) return true;

			for (int i = 0; i < 4; ++i) {
				int cr = r + dr[i], cc = c + dc[i];
				int ck = cr * N + cc;
				if (0 <= cr && cr < N && 0 <= cc && cc < N
						&& !seen.contains(ck) && grid[cr][cc] <= T) {
					stack.add(ck);
					seen.add(ck);
				}
			}
		}

		return false;
	}

	public static void main(String ... args)
	{
		SwimInRaisingWaterLC s = new SwimInRaisingWaterLC();
		int[][] grid = new int [][]{{0,2}, {1,3}};
		int result = s.dfs(grid);
		System.out.println(result);

		s = new SwimInRaisingWaterLC();
		grid = new int [][]{{10,12,4,6},{ 9,11,3,5 },{1,7,13,8},{2,0,15,14}};
		result = s.dfs(grid);
		System.out.println(result);//10 but should be 14

		s = new SwimInRaisingWaterLC();
		grid = new int [][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
		result = s.dfs(grid);
		System.out.println(result);

		s = new SwimInRaisingWaterLC();
		grid = new int [][]{{0,1,2,3,4},{24,16,22,21,5},{12,13,14,15,23},{11,17,18,19,20},{10,9,8,7,6}};
		result = s.dfs(grid);
		System.out.println(result);
	}
}
