package autoInsurance;

import java.util.Map;

public interface IAutoInsurance {
	String login(String in);
	String queryBaseData(String in, Map<String, String> map);
	String suanF(String in, Map<String, String> map);
	String queryRelationData(String in);
	String saveAndHeB(String in, Map<String, String> map);
	String queryBaodhByToubdh(String in);
	String queryHebJg(String in);
	
	
}
