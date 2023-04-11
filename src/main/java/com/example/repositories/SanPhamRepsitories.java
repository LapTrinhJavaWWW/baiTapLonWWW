package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.mdoel.SanPham;

public interface SanPhamRepsitories extends CrudRepository<SanPham, Integer>{
	public List<SanPham> findByTenSanpham(String tenSanpham);
	
	public List<SanPham> findByLoaiSanpham(String loaiSanpham);
	
	@Query(nativeQuery = true,value = "SELECT * FROM san_pham  WHERE loai_sanpham=?1 and best_seller=?2")
	public List<SanPham> findByBestsellerProduct(String loaiSanpham,String bestseller);
	
	@Query(nativeQuery = true,value = "SELECT * FROM san_pham  WHERE loai_sanpham=?1 and slug=?2")
	public List<SanPham> findBySlug(String brand,String slug);
	
	@Query(nativeQuery = true,value = "SELECT * FROM san_pham  WHERE loai_sanpham=?1 and gia > ?2 and gia < ?3")
	public List<SanPham> findByPrice(String loaiSanpham, double minprice,double maxprice);
	
	@Query(nativeQuery = true,value = "select * from san_pham where loai_sanpham=?1 and slug= ?2 and gia > ?3 and gia < ?4")
	public List<SanPham> findMutiProducts(String loaiSanpham, String slug,double minprice,double maxprice);
}
