package com.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.security.pojo.Admin;
import com.security.pojo.AdminRowMapper;

/**
 * 
 * @author Santos Sainos Hdz
 *
 * @version 1.0
 */
@Component("adminDao")
public class AdminDaoImpl implements AdminDao{
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private void SetDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Admin admin) {
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);
		
		return jdbcTemplate.
				update("insert into Admin (nombre, cargo, fechaCreacion) values (:nombre, :cargo, :fechaCreacion)", paramMap) == 1;
	}

	@Override
	public List<Admin> findAll() {
		return jdbcTemplate.query("select * from admin", new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				admin.setIdAd(rs.getInt("idAd"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				admin.setNombre(rs.getString("nombre"));
				
				return admin;
			}
		});
	}

	@Override
	public Admin findById(int id) {
//		return (Admin) jdbcTemplate.query("select * from admin where idAd = :idAd", 
//				new MapSqlParameterSource("idAd", id), 
//				new AdminRowMapper());
		
		return jdbcTemplate.queryForObject("select * from admin where idAd = :idAd", 
				new MapSqlParameterSource("idAd", id), 
				new AdminRowMapper());
	}

	@Override
	public List<Admin> findByNombre(String nombre) {
		return jdbcTemplate.query("select * from admin where nombre like :nombre",
				new MapSqlParameterSource("nombre", "%" + nombre + "%"),
				new AdminRowMapper());
	}

	@Override
	public boolean update(Admin admin) {
		return jdbcTemplate.update("update admin"
				+ "set nombre:nombre,"
				+ "cargo=:cargo,"
				+ "fechaCreacion=:fechaCreacion"
				+ "where idAd=:idAd",
				new BeanPropertySqlParameterSource(admin)) == 1;
	}

	@Override
	public boolean delete(int idAd) {
		return jdbcTemplate.update("delete from admin where idAd = :idAd",
				new MapSqlParameterSource("idAd", idAd)) ==  1;
	}

	@Override
	public int[] saveAll(List<Admin> admins) {
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());
		
		return jdbcTemplate.batchUpdate("insert into Admin (nombre, cargo, fechaCreacion) values (:nombre, :cargo, :fechaCreacion)", 
				batchArgs);
	}

}
