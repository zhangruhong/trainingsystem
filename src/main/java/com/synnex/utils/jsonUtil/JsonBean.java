package com.synnex.utils.jsonUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * view object
 * 
 * @author wzy
 *
 */
public class JsonBean {
	Map termmap = new TreeMap();

	public JsonBean(boolean success, String msg, Object obj) {
		termmap.put("success", true);
		termmap.put("msg", msg);
		termmap.put("terms", obj);
	}
}
