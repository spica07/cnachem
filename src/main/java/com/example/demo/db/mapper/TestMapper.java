package com.example.demo.db.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.db.dto.Test;

public interface TestMapper {
	 
    public List<Test> getAll() throws Exception;
    
    public List<Test> getUserList(String userName) throws Exception;
    
    List<LinkedHashMap<String, Object>> retrieveSmileData();
    
   
}