package com.example.demo.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.indigo.Indigo;
import com.epam.indigo.IndigoObject;
import com.example.demo.db.mapper.TestMapper;

@Service
public class SampleService {

	@Autowired
	private TestMapper testmapper;
	
	public List<LinkedHashMap<String,Object>> retrieveSmileData() {
		return testmapper.retrieveSmileData();
	}
	
	public List<LinkedHashMap<String,Object>> findSmileData(String srcSmile) {
		
//		srcSmile = "C1=CN=CC=C1";  //검색구조 1
//		/sample/smiles/filter?key=N1=CC=CN1
//		srcSmile = "N1=CC=CN1"; // 검색구조 2
//      /sample/smiles/filter?key=N1=CC=CN1
		
		
		List <LinkedHashMap<String,Object>> smileArray = new ArrayList<LinkedHashMap<String,Object>>(); 
		
		try {
			Indigo indigo = new Indigo();
		    // 분자식 조회조건 Object 생성
		   	IndigoObject query = indigo.loadQueryMolecule(srcSmile);
		   	query.aromatize();
		   	for(LinkedHashMap<String,Object> tgtSmile :  retrieveSmileData()) {
		   		try {
			   		IndigoObject target = indigo.loadMolecule(tgtSmile.get("smiledata").toString());
			   		IndigoObject matcher = indigo.substructureMatcher(target);
			   		
			   		IndigoObject find = matcher.match(query);
			   		if(find != null) {
			   			smileArray.add(tgtSmile);
					}	
		   		}catch(Exception ex) {}
		   	}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return smileArray;
	}


}
