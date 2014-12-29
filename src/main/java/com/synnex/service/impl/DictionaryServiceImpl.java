package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.DictionaryDao;
import com.synnex.model.Dictionary;
import com.synnex.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Resource
	private DictionaryDao dictionaryDaoImpl;

	@Override
	public List<Dictionary> listAll() {
		return dictionaryDaoImpl.listAll();
	}

	@Override
	public void add(Dictionary dictionary) {
		dictionaryDaoImpl.save(dictionary);
	}

	@Override
	public void delete(int dictionaryid) {
		dictionaryDaoImpl.delete(dictionaryDaoImpl.get(dictionaryid));
	}

	@Override
	public void update(Dictionary dictionary) {
		dictionaryDaoImpl.update(dictionary);
	}

	@Override
	public Dictionary get(int id) {
		return dictionaryDaoImpl.get(id);
	}

}
