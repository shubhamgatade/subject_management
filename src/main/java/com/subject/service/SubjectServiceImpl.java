package com.subject.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.subject.entity.Subject;
import com.subject.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements com.subject.service.SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Subject create(Subject subject) {
		if (Objects.nonNull(subject)) {
			subject = subjectRepository.save(subject);
			return subject;
		}
		return null;
	}

	@Override
	public Subject getById(Integer id) {
		Optional<Subject> subjectDetail = subjectRepository.findById(id);
		if (subjectDetail.isPresent()) {
			Subject subject = subjectDetail.get();
			return subject;
		}
		return null;
	}

	@Override
	public String deleteById(Integer id) {
		Optional<Subject> subjectDetails = subjectRepository.findById(id);
		if (subjectDetails.isPresent()) {
			Subject subject = subjectDetails.get();
			subject.setStatus(false);
			subjectRepository.save(subject);
		}
		return null;
	}

	@Override
	public Subject updateById(Integer id, Subject subject) {
		if (id != null && id > 0) {
			Subject existing = getById(id);
			if (Objects.nonNull(existing)) {
				if (!StringUtils.isEmpty(subject.getName())) {
					existing.setName(subject.getName());
				}
//				if (subject.getTeacher() != null && subject.getTeacher()){
//				existing.setTeacher(subject.getTeacher());
//				}
//				if (subject.getStdLevel() != null && subject.getStdLevel()) {
//				existing.setStdLevel(subject.getStdLevel());
//				}

				subjectRepository.save(existing);
				return existing;
			}
		}
		return subject;
	}
}