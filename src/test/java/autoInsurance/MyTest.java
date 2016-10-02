package autoInsurance;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

public class MyTest {

	@Test
	public void test() {
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("ukey", "{\"userList\":\"中国人民财产保险股份有限公司北京分公司||313000100013233&&&\",\"type15\":\"BjcaKey_FT11.dll\",\"type14\":\"EnterSafe CSP (ePass2000-FT11) v1.0 for BJCA\",\"oid\":\"JJ106520BJ\"}");
		loginMap.put("loginName", "HW1001");
		loginMap.put("password", "1102680006");
		
		JSONObject jsonObject = JSONObject.fromObject(loginMap);
		System.out.println(jsonObject.get("ukey"));
		System.out.println(jsonObject.get("loginName"));
		System.out.println(jsonObject.get("password"));
		System.out.println(jsonObject.toString());
	}

}
