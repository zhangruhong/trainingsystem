package com.synnex.dao;

import java.util.List;

import com.synnex.model.Dictionary;

public interface DictionaryDao extends GenericDao<Dictionary, Integer> {
	public List<Dictionary> listAll();
}
