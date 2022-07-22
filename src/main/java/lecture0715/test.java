package lecture0715;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class test extends Application {

    TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 화면을 구성하는 전체 판
        BorderPane root = new BorderPane();
        // BorderPane 의 가로세로 길이
        root.setPrefSize(700, 500);

        textArea = new TextArea();
        root.setCenter(textArea);

        // Scene 객체 생성
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("메모장");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();

    }
}
