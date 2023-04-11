package com.example.mdoel;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {
	@Id
	@Column(name = "maKH", nullable = false)
	private String maKH;

	@Column(columnDefinition = "nvarchar(255)")
	private String tenKH;

	@Column(columnDefinition = "nvarchar(25)")
	private String sdt;

	@Column(columnDefinition = "bit")
	private Boolean gioiTinh;

	@Column(columnDefinition = "nvarchar(255)")
	private String diaChi;

	@Column(name = "email", columnDefinition = "nvarchar(255)", nullable = false)
	private String email;	

	@OneToOne
	@PrimaryKeyJoinColumn
	private TaiKhoan taiKhoan;

	public String getmaKH() {
		return maKH;
	}

	public void setmaKH(String maKH) {
		this.maKH = maKH;
	}

	public String gettenKH() {
		return tenKH;
	}

	public void settenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	
	public KhachHang() {
		super();
	}

	


	public KhachHang(String maKH, String tenKH, String sdt, Boolean gioiTinh, String diaChi, String email) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.email = email;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", gioiTinh=" + gioiTinh + ", diaChi="
				+ diaChi + ", email=" + email + "]";
	}

	

}
