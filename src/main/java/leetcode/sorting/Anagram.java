package leetcode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagram
{
	public static void main(String ... args) {
		String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};
//		Arrays.sort(strs);
//		for (int i=0; i<strs.length; i++)
//		{
//			System.out.println(strs[i]);
//		}
		Map<String, List<String>> map = new HashMap<>();
		List<List<String>> result = new ArrayList<>();
		for (int i=0; i<strs.length; i++)
		{
			char[] c = strs[i].toCharArray();
			Arrays.sort(c);
			String key = new String(c);
			if (!map.containsKey(key))
			{
				List<String> inner = new ArrayList<>();
				inner.add(strs[i]);
				map.put(key, inner);
			}
			else
			{
				map.get(key).add(strs[i]);
			}
		}
		map.entrySet().stream().forEach(stringAndListEntry -> {
			result.add(stringAndListEntry.getValue());
		});
		System.out.println(map);//{aet=[eat, tea, ate], abt=[bat], ant=[tan, nat]}

		result.stream().forEach(a -> System.out.println(a));
		//[eat, tea, ate]
		//[bat]
		//[tan, nat]
	}
}
