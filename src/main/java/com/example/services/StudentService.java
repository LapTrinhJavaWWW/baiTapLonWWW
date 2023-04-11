package com.example.services;

import java.util.List;
import java.util.Optional;


import com.example.mdoel.Student;

public interface StudentService {

	void deleteAll();

	void deleteAll(Iterable<? extends Student> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Student entity);

	void deleteById(Integer id);

	long count();

	Iterable<Student> findAllById(Iterable<Integer> ids);

	List<Student> findAll();

	boolean existsById(Integer id);

	Optional<Student> findById(Integer id);

	List<Student> saveAll(List<Student> entities);

	Student save(Student entity);

}
