package core.database;

import java.sql.Connection;

public interface ConnectionManager {
	
	void lockConnection();
	
	Connection returnConnection();
	
	void closeAllConnection();
	

}
