package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.mdoel.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

}
