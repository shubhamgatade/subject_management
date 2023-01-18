package com.subject.network.service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.subject.entity.Teacher;

@Service
public class NetworkServiceImpl implements NetworkService {

	@Value("${teacher.url}")
	private String teacherUrl;

	@Value("${teacher.create.url}")
	private String createTeacherUrl;

	@Override
	public Teacher createTeacherUsingRT(Teacher teacher) throws Exception {

		String url = teacherUrl + createTeacherUrl;
		RestTemplate restTemplate = new RestTemplate();
		Teacher created = restTemplate.postForObject(url, teacher, Teacher.class);
		return created;
	}

	@Override
	public Teacher createTeacherUsingHTTPClient(Teacher teacher) throws Exception {

		String inputPW = teacher.getPassword();
		teacher.setPassword(Base64.getEncoder().encodeToString(inputPW.getBytes()));

		String url = teacherUrl + createTeacherUrl;

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(teacher);
		java.net.http.HttpClient httpClient = java.net.http.HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).header("Content-Type", "application/json").build();

		java.net.http.HttpResponse<String> response = httpClient.send(request,
				java.net.http.HttpResponse.BodyHandlers.ofString());

		String responseString = response.body();
		ObjectMapper objectMapper = new ObjectMapper();
		Teacher newTeacher = objectMapper.readValue(responseString, Teacher.class);
		return newTeacher;
	}

	@Override
	public Teacher createTeacher(Teacher teacher) throws Exception {

		String url = teacherUrl + createTeacherUrl;

		HttpClient httpClient = HttpClients.createDefault();
		HttpPost postRequest = new HttpPost(url);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(teacher);
		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		postRequest.setEntity(input);

		HttpResponse httpResponse = httpClient.execute(postRequest);

		if (httpResponse.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
		}

		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		TypeReference<Teacher> typeReference = new TypeReference<Teacher>() {
		};
		return objectMapper.readValue(response, typeReference);
	}

}
