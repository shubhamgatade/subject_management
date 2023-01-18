package com.subject.service;

import com.subject.entity.Subject;

public interface SubjectService {

	public Subject create(Subject subject);

	public Subject getById(Integer id);

	public String deleteById(Integer id);

	public Subject updateById(Integer id, Subject subject);

}
