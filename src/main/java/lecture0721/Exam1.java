package lecture0721;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

import java.io.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Exam1 extends Application {

    private static BasicDataSource basicDs;
    TextArea textarea;
    Button keyBtn, isbnBtn;
    TextField textField;
    static{
        try{
            basicDs = new BasicDataSource();
            Properties properties = new Properties();
            InputStream is = new FileInputStream("/Users/heejae/Desktop/KakaoCloud_JavaFx/src/main/resources/db.properties");
            properties.load(is);

            basicDs.setDriverClassName(properties.getProperty("JDBC_DRIVER"));
            basicDs.setUrl(properties.getProperty("JDBC_URL"));
            basicDs.setUsername(properties.getProperty("DB_USER"));
            basicDs.setPassword(properties.getProperty("DB_PASSWORD"));

            // 어떻게 설정해야 하나? => 서비스에 따라 다르다
            basicDs.setInitialSize(10);
            basicDs.setMaxTotal(10);

        }catch (Exception e){

        }
    }
    public static DataSource getDataSource(){
        return basicDs;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPrefSize(700, 500);

        textarea = new TextArea();
        textarea.setEditable(false);
        root.setCenter(textarea);
        textField = new TextField();
        textField.setPrefSize(400, 40);



        keyBtn = new Button("Keyword 검색");
        keyBtn.setPrefSize(150, 40);
        keyBtn.setOnAction(e->{
            textarea.clear();
            String keyword = textField.getText();
            System.out.println(keyword);
            Connection con = null;
            DataSource ds = getDataSource(); // 만들어진 connection pool 을 가져옴
            try{
                con = ds.getConnection(); // pool 에서 사용가능한 connection 을 하나 리턴해준다.

                String sql = "SELECT bisbn, btitle,bdate,bprice FROM BOOK WHERE btitle LIKE '%"+keyword+"%'";
                System.out.println("select 실행");
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    textarea.appendText(rs.getString(1) +" "+
                            rs.getString(2) +" "+
                            rs.getString(3) +" "+
                            rs.getString(4)+"\n");
                }
                System.out.println(textarea.getText());
                con.close(); // 여기서는 connection 이 닫히는게 아닌 connection pool 로 돌아가게 된다.
            }catch (Exception e1){

            }
        });




        isbnBtn = new Button("isbn 삭제");
        isbnBtn.setPrefSize(150, 40);

        isbnBtn.setOnAction(e->{
            String isbn = textField.getText();
            Connection con = null;
            DataSource ds = getDataSource(); // 만들어진 connection pool 을 가져옴
            try{
                con = ds.getConnection(); // pool 에서 사용가능한 connection 을 하나 리턴해준다.

                String sql = "DELETE FROM BOOK WHERE bisbn=" + "'"+isbn+"'";
                System.out.println(sql);
                PreparedStatement pstmt = con.prepareStatement(sql);
                int i = pstmt.executeUpdate();

                    textarea.appendText(i+"개가 삭제되었습니다.");
                    System.out.println("delete 실행");

                con.close(); // 여기서는 connection 이 닫히는게 아닌 connection pool 로 돌아가게 된다.
            }catch (Exception e1){

            }
        });

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setColumnHalignment(HPos.CENTER);
        flowPane.setPrefSize(700, 40);
        flowPane.setHgap(10);
        flowPane.getChildren().add(keyBtn);
        flowPane.getChildren().add(textField);
        flowPane.getChildren().add(isbnBtn);

        root.setBottom(flowPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setTitle("DB 테스트");

        primaryStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
