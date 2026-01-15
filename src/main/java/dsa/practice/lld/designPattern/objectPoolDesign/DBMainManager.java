package dsa.practice.lld.designPattern.objectPoolDesign;

public class DBMainManager {
    public static void main(String[] args) {
        DBConnectionPoolManager dbConnectionPoolManager = DBConnectionPoolManager.getInstance();
        dbConnectionPoolManager.createConnectionPool();
        DBConnection dbConnection = dbConnectionPoolManager.getDBConnection();
        dbConnectionPoolManager.releaseDBConnection(dbConnection);
    }
}
