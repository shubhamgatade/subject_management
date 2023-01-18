package com.subject.network.service;

import com.subject.entity.Teacher;

public interface NetworkService {

	Teacher createTeacher(Teacher teacher) throws Exception;

	Teacher createTeacherUsingRT(Teacher teacher) throws Exception;

	Teacher createTeacherUsingHTTPClient(Teacher teacher) throws Exception;
}
