package com.eficacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eficacia.dao.RolDao;
import com.eficacia.model.Rol;

@Service
@Transactional
public class RolServiceImpl implements RolService {

	@Autowired
	private RolDao rolDao;
	
	@Override
	public List<Rol> obtenerRoles() {
		return rolDao.obtenerRoles();
	}

	@Override
	public Rol obtenerRol(int id) {
		return rolDao.obtenerRol(id);
	}

}
