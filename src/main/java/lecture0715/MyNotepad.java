package lecture0715;

// notepad 만들기
// JavaFX

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class MyNotepad extends Application{
    TextArea textArea;
    Button openBtn, saveBtn;

    File file;
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 화면을 구성하는 전체 판
        BorderPane root = new BorderPane();
        // BorderPane 의 가로세로 길이
        root.setPrefSize(700, 500);

        textArea = new TextArea();
        root.setCenter(textArea);

        //버튼생성
        openBtn = new Button("파일 열기");
        openBtn.setPrefSize(150, 40);
        openBtn.setOnAction(e ->{
            textArea.clear();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("오픈할 파일을 선택해주세요");
            file = fileChooser.showOpenDialog(primaryStage);
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while ((line = br.readLine() )!= null) {
                    textArea.appendText(line);
                }
                br.close();

            }catch(FileNotFoundException e1){
                e1.printStackTrace();
            }catch(IOException e2){
                e2.printStackTrace();
            }
            System.out.println("버튼이 눌렸어요!");
        } );

        saveBtn = new Button("파일 저장");
        saveBtn.setPrefSize(150, 40);

        saveBtn.setOnAction(e->{
            String text = textArea.getText();
            try{
                FileWriter fw = new FileWriter(file);
                fw.write(text);
                fw.close();

            }catch(Exception e2){

            }
        });

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setColumnHalignment(HPos.CENTER); // 가운데 정렬
        flowPane.setPrefSize(700,40);
        flowPane.setHgap(10);

        flowPane.getChildren().add(openBtn);
        flowPane.getChildren().add(saveBtn);

        root.setBottom(flowPane);

        // Scene 객체 생성
        Scene scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setTitle("메모장");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();

    }
}

