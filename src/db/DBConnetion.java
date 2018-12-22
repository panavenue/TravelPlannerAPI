package db;

import java.util.List;

import entity.Place;

public interface DBConnetion {

	void close();
	
	boolean register(String userId, String password);
	
	boolean verifyLogin(String userId, String password);

	void savePath(List<Place> places);
	
	List<Place> getSavedPath(String userId);
}
