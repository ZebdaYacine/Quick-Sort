/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package faststor;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ZedYacine
 */
public class FastStor extends Application {

    public static ArrayList<Node> list = new ArrayList<>();
    int indexI = 0;
    int indexJ = 0;
    Collection<Node> listRec = null;



    private ArrayList<Node> createRec(int nbrRecs) {
        ArrayList<Node> arrayRec = new ArrayList();
        int b ;
        for (int i = 0; i <= nbrRecs; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setId("rec" + i);
            rectangle.setWidth(40.0f);
            rectangle.setHeight(40.0f);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.BLACK);
            StackPane stack = new StackPane();
            b = (int) (Math.random() * (100 - 0 + 1) + 0);
            Text text = new Text("" + b);
            text.setId("txt" + i);
            text.setFont(Font.font(20));
            stack.setLayoutX(10.0f + (40.0f * (i)));
            stack.setLayoutY(120.0f);
            stack.setMaxWidth(40.0f);
            stack.setMaxHeight(40.0f);
            stack.setId("stc" + i);
            stack.getChildren().addAll(rectangle, text);
            arrayRec.add(stack);
            //Thread.sleep(1000);
        }
        return arrayRec;
    }


    @Override
    public void start(Stage stage) throws InterruptedException {
        Text t =new Text("Quick Sort  "+ ToDoCategory.CODE.getEmoji());
        t.setFont(Font.font(60));
        TextFlow t0 = new TextFlow (t);
        t0.setLayoutX(50);
        t0.setLayoutY(10);
        t0.setMaxWidth(800);
        Text t1 = new Text("number of boxes");
        t1.setLayoutX(500);
        t1.setLayoutY(75);
        TextField text = new TextField();
        text.setLayoutX(600);
        text.setLayoutY(60);
        Button run = new Button("Create");
        run.setId("Create Table");
        run.setLayoutX(750);
        run.setLayoutY(60);
        Button runBtn = new Button("Start");
        runBtn.setMinHeight(20);
        runBtn.setMinWidth(100);
        ImageView icon= new ImageView("faststor/start.jpg");
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        runBtn.setGraphic(icon);
        runBtn.setId("run");
        runBtn.setLayoutX(250);
        runBtn.setLayoutY(250);
        //Creating a Group object
        Group root = new Group();
        root.getChildren().add(runBtn);
        root.getChildren().add(run);
        root.getChildren().add(text);
        root.getChildren().add(t1);
        root.getChildren().add(t0);
        run.setOnAction(event -> {
            if (!list.isEmpty()) {
                list.forEach(node -> {
                    root.getChildren().remove(node);
                });
            }
            try {
                int i = Integer.parseInt(text.getText());
                if (i < 24) {
                    Collection<Node> rectangles = createRec(i);
                    listRec=rectangles;
                    rectangles.forEach(node -> {
                        list.add(node);
                        root.getChildren().add(node);
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "nbt must less 24");
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "is not number value");
                alert.show();
            }

        });

        //Creating a scene object
        Scene scene = new Scene(root);

        //Setting title to the Stage
        stage.setTitle("Fast store simulation");
        stage.setMinWidth(1000);
        stage.setMaxWidth(1000);
        stage.setMinHeight(350);
        stage.setMaxHeight(350);

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

        runBtn.setOnAction(event -> {
            new Thread(() -> {
                int[] tab = new int[listRec.size()];
                root.getChildren().forEach(node -> {
                    if (node.getClass().getName().contains("StackPane")) {
                        StackPane stc = (StackPane) node;
                        Rectangle rec = (Rectangle) stc.getChildren().get(0);
                        Text txt = (Text) stc.getChildren().get(1);
                        tab[indexI] = Integer.parseInt(txt.getText());
                        indexI++;
                    }
                });
                indexI = 0;
                //Test.triRapide(tab, 5, 8);
                triRapide(tab,0,tab.length-1,listRec);
                listRec.clear();
                //list.clear();
            }).start();
        });
    }

    static void échangerÉléments(int[] t, int m, int n,Collection<Node> listRec )  {
        int temp = t[m];
        t[m] = t[n];
        t[n] = temp;
        String str="";
        StackPane stackPane1= (StackPane) listRec.toArray()[m];
        StackPane stackPane2= (StackPane) listRec.toArray()[n];
        Rectangle rectangleI = (Rectangle) stackPane1.getChildren().get(0);
        Rectangle rectangleJ = (Rectangle) stackPane2.getChildren().get(0);
        rectangleI.setFill(Color.GREEN);
        rectangleJ.setFill(Color.GREEN);
        Text text1 = (Text) stackPane1.getChildren().get(1);
        Text text2 = (Text) stackPane2.getChildren().get(1);
        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        str=text1.getText();
        text1.setText(text2.getText());
        text2.setText(str);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rectangleI.setFill(Color.TRANSPARENT);
        rectangleJ.setFill(Color.TRANSPARENT);
        text1.setFill(Color.BLACK);
        text2.setFill(Color.BLACK);
    }


    static int partition(int[] t, int m, int n,Collection<Node> list)  {
        int v = t[m];                 // valeur pivot
        int i = m - 1;
        int j = n + 1;                  // indice final du pivot
        while (true) {
            do {
                j--;
            } while (t[j] > v);
            do {
                i++;
            } while (t[i] < v);
            if (i < j) {
                échangerÉléments(t, i, j,list);
            } else {
                return j;
            }
        }
    }

    static void triRapide( int[] t, int m, int n,Collection<Node> listRec)  {
        if (m < n) {
            int p = partition(t, m, n,listRec);
            triRapide(t, m, p,listRec);
            triRapide(t, p + 1, n,listRec);
        }
    }




    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        /*int[] tab = {100,7,12,15,6,9,88};
        Test.triRapide(tab,0,6);
        for (int i : tab) {
            System.out.println(i);
        }*/
    }

}