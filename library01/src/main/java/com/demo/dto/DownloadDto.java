package com.demo.dto;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import lombok.Data;

@Data
public class DownloadDto {

	private Resource resouce;
	private HttpHeaders headers;
	
	public void saveData(Resource resource, HttpHeaders headers) {
		this.resouce = resource;
		this.headers = headers;
	}
}
