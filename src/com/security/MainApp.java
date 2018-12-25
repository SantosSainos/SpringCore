package com.security;

import java.sql.Timestamp;
import java.util.ArrayList;
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

		

		try {
			List<Admin> admins = new ArrayList<Admin>();
			admins.add(new Admin(10,"Maria","Desarrollador", ts));
			admins.add(new Admin(11,"Carlos","Tester", ts));
			admins.add(new Admin(33,"Josue","Lider técnico", ts));
			
			int[] vals = adminDao.saveAll(admins);
			
			for (int i : vals) {
				System.out.println("Filas afectadas para la opereción: " + i);
			}
			
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		((ClassPathXmlApplicationContext) applicationContext).close();
	}

}
