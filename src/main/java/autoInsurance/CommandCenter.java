package autoInsurance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebService
public class CommandCenter {
	//int insuranceCompany = 0;
	IAutoInsurance[] _ai = new IAutoInsurance[999];
	Map<String, Object> aiMap = new HashMap<String, Object>();
	
	static Map<String,Map<String,String>> tmpMap = new HashMap<String, Map<String,String>>();
	
	public String queryBaoxgsList() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("北京人保", 1);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("北京平安", 2);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("北京太平洋", 3);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("北京国寿", 4);
		list.add(map);
		
		return JSONArray.fromObject(list).toString();
	}
	
	int aiIndex = 0;
	public String login(String in) {
		String out = "";
		System.out.println("login_in: " + in);
		JSONObject jsonObject = JSONObject.fromObject(in);
		String insuranceId = jsonObject.getString("insuranceId");
		
		/*
		String ukey = jsonObject.getString("ukey");
		
		JSONObject jsonObject2 = JSONObject.fromObject(ukey);
		String userList = jsonObject2.getString("userList");
		String[] whoareyou = userList.split("\\|\\|");
		if(whoareyou[0].equals("中国人民财产保险股份有限公司北京分公司"))
			insuranceCompany = 1;
		if(whoareyou[0].equals("中国平安财产保险股份有限公司北京分公司"))
			insuranceCompany = 2;
		if(whoareyou[0].equals("中国太平洋财产保险股份有限公司北京分公司"))
			insuranceCompany = 3;
		if(whoareyou[0].equals("中国人寿财产保险股份有限公司北京分公司"))
			insuranceCompany = 4;
		
		if(insuranceCompany == 1) {
			ai = new BeiJPiccImpl();
			out = ai.login(in);
		}
		*/
		
		if(insuranceId.equals("1")) {
			aiIndex = 1;
			if(_ai[aiIndex] == null)
				_ai[aiIndex] = new BeiJPiccImpl();
		}
			
		if(insuranceId.equals("2")) {
			aiIndex = 2;
			if(_ai[aiIndex] == null)
				_ai[aiIndex] = new BeiJPingAnImpl();
		}
		
		if(insuranceId.equals("3")) {
			aiIndex = 3;
			if(_ai[aiIndex] == null)
				_ai[aiIndex] = new BeiJTaiPYImpl();
		}
		
		aiMap.put(insuranceId, _ai[aiIndex]);
		out = _ai[aiIndex].login(in);
		
		System.out.println("login_out: " + out);
		
		return out;
	}
	
	
	public String queryBaseData(String in) {
		String out = "";
		System.out.println("queryBaseData_in: " + in);
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String insuranceId = jsonObject.getString("insuranceId");
		
		IAutoInsurance ai = (IAutoInsurance) aiMap.get(insuranceId);
		Map<String, String> myData = new HashMap<String, String>();
		String uuid = UUID.randomUUID().toString();
		tmpMap.put(uuid, myData);
		myData.put("uuid", uuid);
		
		if(ai != null) {
			out = ai.queryBaseData(in, myData);
		}
		else
			out = "{\"success\": false, \"msg\": \"好像还没登录\"}";
		
		System.out.println("queryBaseData_out: " + out);
		return out;
	}
	
	public String suanF(String in) {
		String out = "";
		System.out.println("suanF_in: " + in);
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(in);
			
		} catch(Exception e) {
			return "{\"success\": false, \"msg\": \"解析输入参数异常\"}";
		}
		
//		JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("carBaseInfo"));
//		JSONObject jsonObject_carBaseInfo = JSONObject.fromObject(jsonArray.get(0));
//		String uuid = jsonObject_carBaseInfo.getString("uuid");
//		
//		if(tmpMap.get(uuid) == null)
//			return "{\"success\": false, \"msg\": \"算费设置车辆基本信息失败\"}";
		
		String insuranceId = jsonObject.getString("insuranceId");
		IAutoInsurance ai = (IAutoInsurance) aiMap.get(insuranceId);
		if(ai != null) {
			out = ai.suanF(in, null);
		}
		else
			out = "{\"success\": false, \"msg\": \"好像还没登录\"}";
		
		System.out.println("suanF_out: " + out);
		return out;
	}
	
	public String saveAndHeB(String in) {
		String out = "";
		System.out.println("saveAndHeB_in: " + in);
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String insuranceId = jsonObject.getString("insuranceId");
		String uuid = jsonObject.getString("saveHebId");
		
		IAutoInsurance ai = (IAutoInsurance) aiMap.get(insuranceId);
		
		if(tmpMap.get(uuid) == null)
			return "{\"success\": false, \"msg\": \"核保设置车辆基本信息失败\"}";
		
		if(ai != null) {
			out = ai.saveAndHeB(in, tmpMap.get(uuid));
		}
		else
			out = "{\"success\": false, \"msg\": \"好像还没登录\"}";
		
		System.out.println("saveAndHeB_out: " + out);
		return out;
	}
	
	public String queryBaodhByToubdh(String in) {
		String out = "";
		System.out.println("queryBaodhByToubdh_in: " + in);
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String insuranceId = jsonObject.getString("insuranceId");
		
		IAutoInsurance ai = (IAutoInsurance) aiMap.get(insuranceId);
		
		if(ai != null) {
			out = ai.queryBaodhByToubdh(in);
		}
		else
			out = "{\"success\": false, \"msg\": \"好像还没登录\"}";
		
		System.out.println("queryBaodhByToubdh_out: " + out);
		return out;
	}
	
	public String queryRelationData(String in) {
		String out = "";
		System.out.println("queryRelationData_in: " + out);
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String insuranceId = jsonObject.getString("insuranceId");
		
		IAutoInsurance ai = (IAutoInsurance) aiMap.get(insuranceId);
		
		if(ai != null) {
			out = ai.queryRelationData(in);
		}
		else
			out = "{\"success\": false, \"msg\": \"好像还没登录\"}";
		
		System.out.println("queryRelationData_out: " + out);
		return out;
	}
	
	public String queryHebJg(String in) {
		String out = "";
		System.out.println("queryHebJg_in: " + out);
		
		JSONObject jsonObject = JSONObject.fromObject(in);
		String insuranceId = jsonObject.getString("insuranceId");
		
		IAutoInsurance ai = (IAutoInsurance) aiMap.get(insuranceId);
		
		if(ai != null) {
			out = ai.queryHebJg(in);
		}
		else
			out = "{\"success\": false, \"msg\": \"好像还没登录\"}";
		
		System.out.println("queryHebJg_out: " + out);
		return out;
	}
	
	public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:9080/autoInsurance", new CommandCenter());
        System.out.println("启动成功, http://0.0.0.0:9080/autoInsurance?wsdl");
    }
}
