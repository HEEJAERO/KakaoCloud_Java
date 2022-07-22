module com.example.kakaocloud_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.dbcp2;
    requires commons.logging;
    requires org.apache.commons.pool2;
    requires java.sql;
    requires java.management;
    requires java.management.rmi;
    requires mysql.connector.java;

    opens com.example.kakaocloud_javafx to javafx.fxml;
    exports com.example.kakaocloud_javafx;
    exports lecture0718.exam01;
    exports lecture0718.exam02;
    exports lecture0718.exam03;
    exports lecture0718.chatting;
    exports lecture0715;
    exports lecture0721;
    exports lecture0721.simplebookesearch;
    exports lecture0722.step1;
    exports lecture0722.simplebookesearch;
}