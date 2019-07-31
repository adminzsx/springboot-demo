package com.manage.api.newspapser.service;

import com.manage.api.newspapser.entity.Newspapser;

import java.util.List;

public interface NewspapserService {
	

	public List<Newspapser> getNewspapserByType(Integer type);


}
