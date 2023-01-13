package com.example.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mdoel.Student;
import com.example.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository repository;

	@Override
	public Student save(Student entity) {
		return repository.save(entity);
	}

	@Override
	public List<Student> saveAll(List<Student> entities) {
		return (List<Student>) repository.saveAll(entities);
	}

	@Override
	public Optional<Student> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public List<Student> findAll() {
		return (List<Student>) repository.findAll();
	}

	@Override
	public Iterable<Student> findAllById(Iterable<Integer> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Student entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		repository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Student> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
	
	
}
