package effictive.one;

import java.util.List;
import java.util.Map;

/**
 */
public class HelloWorld {
	public void createMap() {
		Map<String, List<String>> map = ConHashMap.newInstance();
	}

	public void build() {
		NutritionFacts nf = new NutritionFacts.Builder(1, 2).creatZ(3)
				.creatU(4).build();
	}
}
