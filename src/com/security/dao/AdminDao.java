package com.security.dao;

import java.util.List;

import com.security.pojo.Admin;

/**
 * 
 * @author Santos Sainos Hdz
 *
 * @version 1.0
 */
public interface AdminDao {
	
	public boolean save(Admin admin);
	
	public List<Admin> findAll();
	
	public Admin findById(int id);
	
	public List<Admin> findByNombre(String nombre);
	
	public boolean update(Admin admin);
	
	public boolean delete(int idAd);
	
	public void saveAll(List<Admin> admins);
}
