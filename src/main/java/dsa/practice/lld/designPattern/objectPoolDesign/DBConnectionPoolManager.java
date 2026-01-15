package dsa.practice.lld.designPattern.objectPoolDesign;


import java.util.ArrayList;
import java.util.List;
/**
* In this design pattern we are creating a pool of objects and reusing them
* This is used to reduce the creation of objects and to reuse the objects
* This is thread safe
* */
public class DBConnectionPoolManager {

    private static DBConnectionPoolManager dbConnectionPoolManagerInstance;
    private final List<DBConnection> connectionPool = new ArrayList<>();
    private final List<DBConnection> connectionInUsePool = new ArrayList<>();

    private static final  int INITIAL_CONNECTION_POOL_SIZE = 3;
    private static final  int MAX_CONNECTION_POOL_SIZE = 6;

    private DBConnectionPoolManager() {
    }

    public static DBConnectionPoolManager getInstance(){
        if(dbConnectionPoolManagerInstance == null){
            synchronized (DBConnectionPoolManager.class){
                if(dbConnectionPoolManagerInstance == null){
                    dbConnectionPoolManagerInstance = new DBConnectionPoolManager();
                }
            }
        }
        return dbConnectionPoolManagerInstance;
    }

    public void createConnectionPool(){
        for(int i = 0; i < INITIAL_CONNECTION_POOL_SIZE; i++){
            connectionPool.add(new DBConnection());
        }
    }

    public synchronized DBConnection getDBConnection(){
        if(connectionPool.isEmpty() && connectionInUsePool.size() < MAX_CONNECTION_POOL_SIZE){
            connectionPool.add(new DBConnection());
        }else if(connectionPool.isEmpty() && connectionInUsePool.size() >= MAX_CONNECTION_POOL_SIZE){
            return null;
        }
        connectionInUsePool.add(connectionPool.remove(0));
        return connectionInUsePool.get(connectionInUsePool.size() - 1);
    }

    public void releaseDBConnection(DBConnection dbConnection){
        connectionPool.add(dbConnection);
        connectionInUsePool.remove(dbConnection);
    }

}
