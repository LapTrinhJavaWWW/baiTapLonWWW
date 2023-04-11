package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mdoel.SanPham;
import com.example.repositories.SanPhamRepsitories;

@Service
public class SanPhamServiceImpl implements SanPhamService {

	@Autowired
	SanPhamRepsitories repsitories;

	@Override
	public SanPham save(SanPham sanPham) {
		return repsitories.save(sanPham);
	}

	@Override
	public List<SanPham> saveAll(List<SanPham> entities) {
		return (List<SanPham>) repsitories.saveAll(entities);
	}

	@Override
	public SanPham findById(Integer id) {
		return repsitories.findById(id).get();
	}

	@Override
	public boolean existsById(Integer id) {
		return repsitories.existsById(id);
	}

	@Override
	public List<SanPham> findAll() {
		return (List<SanPham>) repsitories.findAll();
	}

	@Override
	public Iterable<SanPham> findAllById(Iterable<Integer> ids) {
		return repsitories.findAllById(ids);
	}

	@Override
	public long count() {
		return repsitories.count();
	}

	@Override
	public void deleteById(Integer id) {
		repsitories.deleteById(id);
	}

	@Override
	public void delete(SanPham entity) {
		repsitories.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		repsitories.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends SanPham> entities) {
		repsitories.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repsitories.deleteAll();
	}

	@Override
	public List<SanPham> findBytenSanpham(String tenSanpham) {
		// TODO Auto-generated method stub
		return repsitories.findByTenSanpham(tenSanpham);
	}

	@Override
	public List<SanPham> findBySlug(String brand, String slug) {
		// TODO Auto-generated method stub
		return repsitories.findBySlug(brand, slug);
	}

	@Override
	public List<SanPham> findByLoaiSanpham(String loaiSanpham) {
		// TODO Auto-generated method stub
		return repsitories.findByLoaiSanpham(loaiSanpham);
	}

	@Override
	public List<SanPham> findByBestsellerProduct(String loaiSanpham, String bestseller) {
		// TODO Auto-generated method stub
		return repsitories.findByBestsellerProduct(loaiSanpham, bestseller);
	}

	@Override
	public List<SanPham> findMutiProducts(String loaiSanpham, String slug, double minprice, double maxprice) {
		// TODO Auto-generated method stub
		return repsitories.findMutiProducts(loaiSanpham, slug, minprice, maxprice);
	}

	@Override
	public List<SanPham> findByPrice(String loaiSanpham, double minprice, double maxprice) {
		// TODO Auto-generated method stub
		return repsitories.findByPrice(loaiSanpham, minprice, maxprice);
	}

}
