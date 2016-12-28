package com.eficacia.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eficacia.model.Rol;

@Repository
public class RolDaoImpl implements RolDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private Query query;
	
	@Override
	public List<Rol> obtenerRoles() {
		session = sessionFactory.getCurrentSession();
		List<Rol> roles = new ArrayList<Rol>();
		query = session.createQuery("FROM Rol");
		roles = query.list();
		Rol select = new Rol(0,"---Selecciona---");
		roles.add(0, select);

		
		return roles;
	}

	@Override
	public Rol obtenerRol(int id) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Rol r WHERE r.id = :id");
		query.setParameter("id", id);
		Rol rol = (Rol) query.uniqueResult();
		return rol;
	}
	
}
