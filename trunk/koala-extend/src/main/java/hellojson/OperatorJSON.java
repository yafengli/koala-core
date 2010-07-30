package hellojson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: phoenixup
 * Date: 2010-7-29
 * Time: 14:16:25
 */
public class OperatorJSON {


    public static void main(String args[]) {
        Set set = new HashSet();
        JSONObject obj = new JSONObject();
        Map n1 = new HashMap();
        n1.put("name", "name_1");
        n1.put("age", new int[]{1, 2, 3});
        Map n2 = new HashMap();
        n2.put("name", "name_2");
        n2.put("age", new int[]{4, 5, 6});
        set.add(n1);
        set.add(n2);
        JSONArray jnArray = new JSONArray(set);
        System.out.println(jnArray.toString());
    }
}

class Name {
    private String name;
    private int[] age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getAge() {
        return age;
    }

    public void setAge(int[] age) {
        this.age = age;
    }
}
