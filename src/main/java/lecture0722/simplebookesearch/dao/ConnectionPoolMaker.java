package lecture0722.simplebookesearch.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

public interface ConnectionPoolMaker {
    public BasicDataSource makePool() throws IOException;
}
