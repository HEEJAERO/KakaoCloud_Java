package lecture0718.exam02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Exam02_EchoClient extends Application {

    TextArea textArea;
    Button connBtn;
    TextField textField;
    TextField idField;
    Socket socket;
    PrintWriter pw;
    BufferedReader br;
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 화면 구성
        BorderPane root = new BorderPane();
        root.setPrefSize(700, 500); // window 크기


        textArea = new TextArea();
        root.setCenter(textArea);//화면 센터에 textarea 를 붙인다.
        connBtn = new Button("Data 서버 접속");
        connBtn.setPrefSize(150, 40); // 버튼크기
        connBtn.setOnAction(e->{  // 서버에 연결하면서 소켓생성
            try  {
                socket = new Socket("localhost", 5678);
                pw = new PrintWriter(socket.getOutputStream());
                br = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                textArea.appendText("Echo 서버 접속 완료!!\n");
            } catch (IOException ex) {
            }
        });
        idField = new TextField();
        idField.setPrefSize(200, 40);

        textField = new TextField();
        textField.setPrefSize(200, 40);
        textField.setOnAction(event -> { // 입력상자에 글 입력 후 enter 입력하면 이벤트 처리
            //데이터 전송
            try  {
                String text = idField.getText() + " : " + textField.getText();
                pw.println(text);
                pw.flush();
                if (textField.getText().equals("/exit")) {
                    textArea.appendText("서버와의 연결을 종료합니다.");
                    textField.setDisable(true);
                    //primaryStage.close();
                }else{
                    String msg = br.readLine();
                    //System.out.println(msg);
                    textArea.appendText(msg+"\n"); // 나중에 문제가 생길 수 있음
                }
                textField.clear();
            }catch(Exception e){
            }
        });

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding((new Insets(10,10,10,10))); // 여백을 줌
        flowPane.setPrefSize(700, 40);
        flowPane.setHgap(10);
        flowPane.getChildren().add(connBtn); // 버튼 부착
        flowPane.getChildren().add(idField);
        flowPane.getChildren().add(textField); //입력상자 부착

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
