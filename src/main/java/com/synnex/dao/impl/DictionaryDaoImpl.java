package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.DictionaryDao;
import com.synnex.model.Dictionary;

@Repository
public class DictionaryDaoImpl extends GenericDaoImpl<Dictionary, Integer> implements DictionaryDao {

	@Override
	public List<Dictionary> listAll() {
		List<Dictionary> dictionaries = this.findByHql("from Dictionary", null);
		return dictionaries;
	}
}
