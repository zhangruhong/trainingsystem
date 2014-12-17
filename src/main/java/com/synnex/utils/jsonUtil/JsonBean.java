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
	private Map termmap = new TreeMap();

	public JsonBean(boolean trueOrfalse, String msg, Object obj) {
		termmap.put("success", trueOrfalse);
		termmap.put("msg", msg);
		termmap.put("terms", obj);
	}

	public Map getTermmap() {
		return termmap;
	}

	public void setTermmap(Map termmap) {
		this.termmap = termmap;
	}

}
