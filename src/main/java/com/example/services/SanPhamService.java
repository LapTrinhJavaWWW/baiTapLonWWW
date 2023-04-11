package com.example.services;

import java.util.List;

import com.example.mdoel.SanPham;

public interface SanPhamService {

	void deleteAll();

	void deleteAll(Iterable<? extends SanPham> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(SanPham entity);

	void deleteById(Integer id);

	long count();

	Iterable<SanPham> findAllById(Iterable<Integer> ids);

	List<SanPham> findAll();
	

	boolean existsById(Integer id);

	SanPham findById(Integer id);

	List<SanPham> saveAll(List<SanPham> entities);

	SanPham save(SanPham sanPham);

	List<SanPham> findBytenSanpham(String tenSanpham);
	
	List<SanPham> findByLoaiSanpham(String loaiSanpham);
	
	List<SanPham> findBySlug(String brand,String slug);
	
	List<SanPham> findMutiProducts(String loaiSanpham, String slug,double minprice,double maxprice);
	
	List<SanPham> findByPrice(String loaiSanpham, double minprice,double maxprice);
	
	List<SanPham> findByBestsellerProduct(String loaiSanpham,String bestseller);

}
