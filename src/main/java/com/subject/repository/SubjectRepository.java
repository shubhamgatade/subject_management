package com.subject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.subject.entity.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer>{

	@Query(value = "select * from subject sd where sd.id = :id", nativeQuery = true)
	Optional<Subject> findById(Integer id);

	@Query(value = "select * from subject sd where sd.id=:id", nativeQuery = true)
	Subject getByRollNo(@Param("id") Integer id);
}
