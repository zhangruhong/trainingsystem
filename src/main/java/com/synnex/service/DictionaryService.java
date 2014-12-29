package com.synnex.service;

import java.util.List;

import com.synnex.model.Dictionary;

public interface DictionaryService {
	public List<Dictionary> listAll();

	public void add(Dictionary dictionary);

	public void delete(int dictionaryid);

	public void update(Dictionary dictionary);

	public Dictionary get(int id);
}
