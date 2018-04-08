package share.filter;

import java.util.HashSet;
import java.util.Set;

public class WhiteListFilter {
	
	//private static Set<String> whiteList = new HashSet<>(PropertiesUtil.loadWhiteListProperties("whitelist.properties"));

	private static Set<String> whiteList = new HashSet<>();

	public static Set<String> getWhiltListConfig() {
		return whiteList;
	}
}
