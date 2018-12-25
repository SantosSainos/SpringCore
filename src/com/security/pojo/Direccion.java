package com.security.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Santos Sainos Hdz
 * @version 1.0
 */

@Component
public class Direccion {

	private String calle;
	private String cp;

	public Direccion() {

	}

	public Direccion(String calle, String cp) {
		this.calle = calle;
		this.cp = cp;
	}

	/**
	 * @param calle
	 *            the calle to set
	 */
	@Autowired
	public void setCalle(@Value("Chihuahua") String calle) {
		this.calle = calle;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	@Autowired
	public void setCp(@Value("9999") String cp) {
		this.cp = cp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", cp=" + cp + "]";
	}

}
