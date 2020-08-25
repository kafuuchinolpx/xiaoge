package cn.xiaoge.design.util;

import cn.xiaoge.design.entity.vo.ReturnBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

//Jackson工具利用好jackson的注解
public final class JacksonUtil {

	private JacksonUtil(){
	}
	public static ObjectMapper objectMapper;

	/**
	* json字符串转对象
	* @param jsonStr json字符串
	* @param valueType 类型
	* @param <T> 泛型
	* @return 泛型类型
	*/
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	* 高级json字符串转对象
	* @param jsonStr json字符串
	* @param valueTypeRef 类型
	* @param <T> 泛型
	* @return 泛型类型
	*/
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	* 对象转换成json字符串
	* @param object 对象
	* @return json字符串
	*/
	public static String toJsonString(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String str = toJsonString(ReturnBean.of(ReturnBean.AnswerCode.SUCCESS));
		ReturnBean returnBean = readValue(str, ReturnBean.class);
		System.out.println(returnBean);
		List<ReturnBean> returnBeans = Arrays.asList(ReturnBean.of(ReturnBean.AnswerCode.SUCCESS), ReturnBean.of(ReturnBean.AnswerCode.UNKNOWN_ERROR));
		str = toJsonString(returnBeans);
		List<ReturnBean> returnBeans1 = readValue(str, new TypeReference<List<ReturnBean>>() {});
		System.out.println(returnBeans1);
	}
}
