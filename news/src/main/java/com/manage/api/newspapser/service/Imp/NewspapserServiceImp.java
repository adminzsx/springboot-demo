package com.manage.api.newspapser.service.Imp;

import com.manage.api.newspapser.dao.NewspapserMapper;
import com.manage.api.newspapser.entity.Newspapser;
import com.manage.api.newspapser.service.NewspapserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scoreService")
public class NewspapserServiceImp implements NewspapserService {
	@Autowired
	NewspapserMapper newspapserMapper;

	@Override
	public List<Newspapser> getNewspapserByType(Integer type) {
		return newspapserMapper.selectByType(type);
	}

}
