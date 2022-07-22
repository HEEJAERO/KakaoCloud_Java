package lecture0722.simplebookesearch;

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
import lecture0722.simplebookesearch.dao.ConnectionPoolMakerImpl;
import lecture0722.simplebookesearch.dao.ConnectionPoolMaker;
import lecture0722.simplebookesearch.service.BookService;
import lecture0722.simplebookesearch.vo.BookVo;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleBookSearch extends Application {
    private static final ConnectionPoolMaker connectionPool = new ConnectionPoolMakerImpl();
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
            BookService service = null;
            try {
                service = new BookService(connectionPool);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
            try{
                BookService service = new BookService(connectionPool);
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
