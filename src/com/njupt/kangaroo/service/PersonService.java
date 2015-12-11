package com.njupt.kangaroo.service;

import java.util.List;
import java.util.Map;

public interface PersonService {
	public boolean addPerson(Object [] params);
	
	public boolean deletePerson(Object [] params);
	
	public boolean updataPerson(Object [] params);
	
	public Map<String, String> viewPerson(String [] selectionArgs);
	
	public List<Map<String, String>> listPersonMaps(String [] selectionArgs);
}
