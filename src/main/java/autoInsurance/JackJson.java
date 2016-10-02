package autoInsurance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * jackjson涓�浜涜浆鎹㈡柟娉�
 * 
 * @author chenxin
 * @date 2010-6-28 涓嬪�?04:07:33
 */
public class JackJson {
	static Logger logger = new Logger();
	/** 鏍煎紡鍖栨椂闂寸殑string */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * jackjson鎶妀son瀛楃涓茶浆鎹负Java瀵硅薄鐨勫疄鐜版柟娉�?
	 * 
	 * <pre>
	 * return JackJson.fromJsonToObject(this.answersJson, new TypeReference&lt;List&lt;StanzaAnswer&gt;&gt;() {
	 * });
	 * </pre>
	 * 
	 * @param <T>
	 *            杞崲涓虹殑java瀵硅�?
	 * @param json
	 *            json瀛楃涓�?
	 * @param typeReference
	 *            jackjson鑷畾涔夌殑绫诲�?
	 * @return 杩斿洖Java瀵硅�?
	 */
	public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * json杞崲涓�?ava瀵硅�?
	 * 
	 * <pre>
	 * return JackJson.fromJsonToObject(this.answersJson, JackJson.class);
	 * </pre>
	 * 
	 * @param <T>
	 *            瑕佽浆鎹㈢殑瀵硅�?
	 * @param json
	 *            瀛楃涓�?
	 * @param valueType
	 *            瀵硅薄鐨刢lass
	 * @return 杩斿洖�?�硅�?
	 */
	public static <T> T fromJsonToObject(String json, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			logger.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java瀵硅薄杞崲涓簀son瀛楃涓�?
	 * 
	 * @param object
	 *            Java瀵硅�?
	 * @return 杩斿洖�?�楃涓�?
	 */
	public static String fromObjectToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java瀵硅薄杞崲涓簀son瀛楃涓�?
	 * 
	 * @param object
	 *            瑕佽浆鎹㈢殑瀵硅�?
	 * @param filterName
	 *            杩囨护鍣ㄧ殑鍚嶇О
	 * @param properties
	 *            瑕佽繃婊ゅ摢浜涘瓧娈�?
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String fromObjectToJson(Object object, String filterName, Set<String> properties) {
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(properties));
		try {
			return mapper.filteredWriter(filters).writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java瀵硅薄杞崲涓簀son瀛楃涓�?
	 * 
	 * @param object
	 *            瑕佽浆鎹㈢殑瀵硅�?
	 * @param filterName
	 *            杩囨护鍣ㄧ殑鍚嶇О
	 * @param properties
	 *            瑕佽繃婊ょ殑瀛楁鍚嶇�?
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String fromObjectToJson(Object object, String filterName, String property) {
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(property));
		try {
			return mapper.filteredWriter(filters).writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java瀵硅�?(鍖呭惈鏃ユ湡瀛楁鎴栧睘鎬�)杞崲涓�?son瀛楃涓�?
	 * 
	 * @param object
	 *            Java瀵硅�?
	 * @return 杩斿洖�?�楃涓�?
	 */
	public static String fromObjectHasDateToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().withDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java瀵硅�?(鍖呭惈鏃ユ湡瀛楁鎴栧睘鎬�)杞崲涓�?son瀛楃涓�?
	 * 
	 * @param object
	 *            Java瀵硅�?
	 * @param dateTimeFormatString
	 *            鑷畾涔夌殑鏃ユ�?/鏃堕棿鏍煎紡銆傝灞炴�х殑鍊奸伒寰猨ava鏍囧噯鐨刣ate/time鏍煎紡瑙勮寖銆傚锛歽yyy-MM-dd
	 * @return 杩斿洖�?�楃涓�?
	 */
	public static String fromObjectHasDateToJson(Object object, String dateTimeFormatString) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().withDateFormat(new SimpleDateFormat(dateTimeFormatString));
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}
}
class Logger {
	public void error(String a,Exception b){
		System.out.println(a );
		b.printStackTrace();
	}
}
