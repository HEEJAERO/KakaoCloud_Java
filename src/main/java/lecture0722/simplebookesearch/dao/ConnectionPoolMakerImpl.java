package lecture0722.simplebookesearch.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolMakerImpl implements ConnectionPoolMaker{

    @Override
    public BasicDataSource makePool() throws IOException {
        BasicDataSource basicDs = new BasicDataSource();
        basicDs = new BasicDataSource();
        Properties properties = new Properties();
        InputStream is = new FileInputStream("/Users/heejae/Desktop/KakaoCloud_JavaFx/src/main/resources/db.properties");
        properties.load(is);
        basicDs.setDriverClassName(properties.getProperty("JDBC_DRIVER"));
        basicDs.setUrl(properties.getProperty("JDBC_URL"));
        basicDs.setUsername(properties.getProperty("DB_USER"));
        basicDs.setPassword(properties.getProperty("DB_PASSWORD"));
        basicDs.setInitialSize(10);
        basicDs.setMaxTotal(10);
        return basicDs;
    }

}
