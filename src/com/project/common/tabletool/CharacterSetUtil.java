package com.project.common.tabletool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.project.util.Toolkits;


/**
 * 
 * @description 字符转换工具类
 * @version1.0
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CharacterSetUtil {
	
	private static Logger log = Logger.getLogger(CharacterSetUtil.class);

	/**
	 * ISO8859-1字符类型
	 */
	private static final String ISO8859 = "iso-8859-1";
	/**
	 * GBK 字符类型
	 */
	private static final String GBK = "gbk";

	@SuppressWarnings("unused")
	private static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将ISO字符转换为UTF-8
	 * 
	 * @param str
	 * @return
	 */
	public static String getUTF8String(String str) {
		return transformString(str, ISO8859, GBK);
	}

	/**
	 * 将UTF-8字符转换为ISO
	 * 
	 * @param str
	 * @return
	 */
	public static String getISOString(String str) {
		return transformString(str, GBK, ISO8859);
	}

	/**
	 * map 中String 转成UTF-8
	 * 
	 * @param map
	 * @return
	 */
	public static Map getUTF8Map(Map map) {
		Map newMap = new HashMap();
		if (map == null) {
			return newMap;
		}
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getValue() instanceof java.lang.String) {
				newMap.put(pairs.getKey(), getUTF8String(pairs.getValue() + ""));
			} else if (pairs.getValue() instanceof Collection) {
				newMap.put(pairs.getKey(), getUTF8Object(pairs.getValue()));
			} else {
				newMap.put(pairs.getKey(), pairs.getValue());
			}
		}
		return newMap;
	}

	/**
	 * map 中String 转成ISO
	 * 
	 * @param map
	 * @return
	 */
	public static Map getISOMap(Map map) {
		Map newMap = new HashMap();
		if (map == null) {
			return newMap;
		}
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getValue() instanceof java.lang.String) {
				newMap.put(pairs.getKey(), getISOString(pairs.getValue() + ""));
			} else if (pairs.getValue() instanceof Collection) {
				newMap.put(pairs.getKey(), getISOObject(pairs.getValue()));
			} else {
				newMap.put(pairs.getKey(), pairs.getValue());
			}
		}
		return newMap;

	}

	/**
	 * list 中的map 的String 转成UTF-8
	 * 
	 * @param map
	 * @return
	 */
	public static List getUTF8List(List list) {
		List listUTF8 = new ArrayList();
		if (list == null) {
			return listUTF8;
		}
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object o = list.get(i);
			o = getUTF8Object(o);
			listUTF8.add(o);
		}
		return listUTF8;
	}

	/**
	 * list 中的map 的String 转成ISO
	 * 
	 * @param map
	 * @return
	 */
	public static List getISOList(List list) {
		List listISO = new ArrayList();
		if (list == null) {
			return listISO;
		}
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object o = list.get(i);
			o = getISOObject(o);
			listISO.add(o);
		}
		return listISO;
	}

	/**
	 * 将utf-8字符集对象转换成ISO字符集对象，sql入参字符集转换
	 * 
	 * @param object
	 * @return
	 */
	public static Object getISOObject(Object object) {
		if (object != null) {
			if (object instanceof Map) {
				object = getISOMap((Map) object);
			} else if (object instanceof java.lang.String) {
				object = getISOString((String) object);
			} else if (object instanceof Collection) {
				object = getISOList((List) object);
			} else {
				transformObject(object, GBK, ISO8859);
			}
		}
		return object;
	}

	/**
	 * 将ISO字符集对象转换成utf-8字符集对象
	 * 
	 * @param object
	 * @return
	 */
	public static Object getUTF8Object(Object object) {
		if (object != null) {
			if (object instanceof Map) {
				object = getUTF8Map((Map) object);
			} else if (object instanceof java.lang.String) {
				object = getUTF8String((String) object);
			} else if (object instanceof Collection) {
				object = getUTF8List((List) object);
			} else {
				transformObject(object, ISO8859, GBK);
			}
		}
		return object;
	}

	/**
	 * 字符串转换
	 * 
	 * @param str
	 * @param fromEncoding
	 * @param toEncoding
	 * @return
	 */
	private static String transformString(String str, String fromEncoding, String toEncoding) {
		return str;
	}

	/**
	 * 将对象类型进行转换
	 * 
	 * @param object
	 * @param fromEncoding
	 * @param toEncoding
	 */
	private static void transformObject(Object object, String fromEncoding, String toEncoding) {
		try {
			Class c = object.getClass();
			// Field[] fields = c.getFields();
			Method[] methods = c.getMethods();
			Map<String, Method> hMap = new HashMap();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("get")) {
					hMap.put(methods[i].getName(), methods[i]);
				}
			}
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("set")) {
					String columnName = methodName.substring(3, methodName.length());
					Class[] parmts = method.getParameterTypes();
					if (parmts[0] == String.class && parmts.length == 1) {
						if (hMap.get("get" + columnName) != null) {
							Method getMethod = hMap.get("get" + columnName);
							String svalue = (String) getMethod.invoke(object, null);
							if (!StringUtils.isEmpty(svalue)) {
								method.invoke(object, transformString(svalue, fromEncoding, toEncoding));
							}
						}
					} else if (parmts[0].getName().indexOf("com.hotent.platform.model.") > -1) {// 为BaseModel类型
						if (hMap.get("get" + columnName) != null) {
							Method getMethod = hMap.get("get" + columnName);
							Object svalue = getMethod.invoke(object, null);
							if (svalue != null) {
								transformObject(svalue, fromEncoding, toEncoding);
								method.invoke(object, svalue);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(Toolkits.getExceptionInfo(e));
		}
	}

}
