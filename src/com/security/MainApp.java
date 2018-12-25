package com.security;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.security.dao.AdminDao;
import com.security.pojo.Admin;

/**
 * 
 * @author Santos Sainos Hdz
 * @version 1.0
 */
public class MainApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

		Timestamp ts = new Timestamp(new Date().getTime());

		Admin admin = new Admin();
		admin.setCargo("Gerente");
		admin.setNombre("Carlos");
		admin.setFechaCreacion(ts);

		try {
			adminDao.save(admin);
			
//			List<Admin> admins = adminDao.findAll();
//			
//			for (Admin admin2 : admins) {
//				System.out.println(admin2);
//			}
			
			System.out.println(adminDao.findById(1));
			System.out.println(adminDao.findByNombre("j").toString());
			
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		((ClassPathXmlApplicationContext) applicationContext).close();
	}

}
