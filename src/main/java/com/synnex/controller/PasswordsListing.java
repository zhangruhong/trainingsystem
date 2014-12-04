package com.synnex.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synnex.model.PasswordName;

/*
 *@author Jeniss 2014-3-5 ����9:53:06
 *@tags
 */
@Controller
public class PasswordsListing extends GenericController {
	/**
	 * @tags ��ȡ���ѯ��������Ϣ�б�
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search")
	public String searchPasswords(@RequestParam String searchKeyStr,
			ModelMap modelMap, HttpServletResponse response) throws Exception {
		List<PasswordName> passwordInfosOrderByName = null;
		if (searchKeyStr == null || "".equals(searchKeyStr.trim())
				|| "undefined".equals(searchKeyStr)) {
			passwordInfosOrderByName = passwordInfoService
					.getAllPasswordInfosOrderByName();
		}
		if (passwordInfosOrderByName != null) {
			modelMap.put("list", passwordInfosOrderByName);
		}
		response.setContentType("text/html;charset=UTF-8");
		return "/function/infoListTable";
	}

	@RequestMapping({ "/listing", "/" })
	public String Listing(ModelMap modelMap) {
		return "/ListAllPasswords";
	}
}
