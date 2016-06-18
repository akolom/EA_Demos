package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Member;
import edu.mum.domain.Product;
import edu.mum.rest.RemoteApi;
import edu.mum.rest.service.MemberRestService;

@Component
public class MemberRestServiceImpl implements MemberRestService {

	@Autowired
	RemoteApi remoteApi;
	
	public List<Member> findAll() {
		
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/MemberRest/members/", HttpMethod.GET, remoteApi.getHttpEntity(), Member[].class).getBody());
//		return Arrays.asList(restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/members/", HttpMethod.GET, remoteApi.getHttpEntity(), Member[].class).getBody());
	}

	public Member findOne(Long index) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
//		return (restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/members/"+ index, HttpMethod.GET, remoteApi.getHttpEntity(), Member.class).getBody());
		return (restTemplate.exchange("http://localhost:8080/MemberRest/members/"+ index, HttpMethod.GET, remoteApi.getHttpEntity(), Member.class).getBody());
	}

	public Member save(Member member) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		HttpEntity<Member> httpEntity = new HttpEntity<Member>(member, remoteApi.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/MemberRest/members/add/", httpEntity, Member.class);
		return null;
	}

}
