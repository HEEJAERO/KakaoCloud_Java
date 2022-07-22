package lecture0718.exam01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam01_DateClient extends Application {
    TextArea textArea;
    Button connBtn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 화면 구성
        BorderPane root = new BorderPane();
        root.setPrefSize(700, 500); // window 크기

        textArea = new TextArea();
        root.setCenter(textArea);//화면 센터에 textarea 를 붙인다.
        connBtn = new Button("Data 서버 접속");
        connBtn.setPrefSize(150, 40); // 버튼크기
        connBtn.setOnAction(e->{
            textArea.clear();
            // 서버 process 에 접속을 시도
            try {
                Socket socket = new Socket("localhost", 5678);
                // 접속성공 => stream 을 열어 데이터를 받는다.
                InputStreamReader ir = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(ir);
                String msg = br.readLine();
                textArea.appendText(msg); // 나중에 문제가 생길 수 있음

                // 사용한 자원 반납
                br.close();
                ir.close();
                socket.close();

            } catch (IOException ex) {
            }
        });
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding((new Insets(10,10,10,10))); // 여백을 줌
        flowPane.setPrefSize(700, 40);
        flowPane.setHgap(10);
        flowPane.getChildren().add(connBtn); // 버튼 부착

        root.setBottom(flowPane);

        Scene scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
