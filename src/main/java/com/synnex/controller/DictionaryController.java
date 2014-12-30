package com.synnex.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.Dictionary;
import com.synnex.utils.jsonUtil.JsonBean;

@Controller
@RequestMapping("/admin")
public class DictionaryController extends GenericController {

	@ResponseBody
	@RequestMapping(value = "/dictionary/add")
	public JsonBean addDictionary(@RequestBody @Valid Dictionary dictionary, BindingResult brt) {
		JsonBean jsonBean = null;
		if (brt.hasErrors()) {
			List<FieldError> errors = brt.getFieldErrors();
			Map<String, String> mapErrors = new LinkedHashMap<String, String>();
			for (FieldError fieldError : errors) {
				mapErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			jsonBean = new JsonBean(false, "添加失败！", mapErrors);
			return jsonBean;
		}
		try {
			dictionaryServiceImpl.add(dictionary);
		} catch (Exception e) {
			jsonBean = new JsonBean(false, "添加失败，请稍候再试！", null);
			logger.error(e.getMessage());
			return jsonBean;
		}
		jsonBean = new JsonBean(true, "添加成功", null);
		return jsonBean;
	}
}
