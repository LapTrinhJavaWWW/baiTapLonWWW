package com.example.mdoel;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {

	@Id
	@Column(name = "maTaiKhoan", nullable = false)
	private String maTaiKhoan;
	@Column(name = "email", columnDefinition = "varchar(255)", nullable = false)
	private String email;
	@Column(name = "matKhau", columnDefinition = "varchar(55)")
	private String matKhau;

	@OneToOne
	@MapsId
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String email, String matKhau) {
		super();
		this.email = email;
		this.matKhau = matKhau;
	}

	

	public TaiKhoan(String maTaiKhoan, String email, String matKhau) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.email = email;
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", email=" + email + ", matKhau=" + matKhau + "]";
	}

	
	
}
