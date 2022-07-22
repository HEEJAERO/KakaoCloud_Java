package lecture0721.simplebookesearch;

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
import lecture0721.simplebookesearch.service.BookService;
import lecture0721.simplebookesearch.vo.BookVo;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class SimpleBookSearch extends Application {

    private static BasicDataSource basicDs;
    TextArea textarea;
    Button keyBtn, isbnBtn;
    TextField textField;
    static{

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
            BookService service = new BookService();
            textarea.clear();
            String keyword = textField.getText();
            ArrayList<BookVo> list = service.bookSearchByKeyword(keyword);
            for (BookVo book : list) {
                textarea.appendText(book.toString()+"\n");
            }
        });




        isbnBtn = new Button("isbn 삭제");
        isbnBtn.setPrefSize(150, 40);

        isbnBtn.setOnAction(e->{
            String isbn = textField.getText();
            BookService service = new BookService();
            try{
                String s = service.bookDeleteByISBN(isbn);
                textarea.appendText(s);
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
    }// JavaFX 를 이용한 View
}
