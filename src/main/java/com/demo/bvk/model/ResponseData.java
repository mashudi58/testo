package com.demo.bvk.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class ResponseData implements Serializable{

	private static final long serialVersionUID = 1L;
	private Boolean error;
	private String message;
	private Map<String, Object> data;

}
