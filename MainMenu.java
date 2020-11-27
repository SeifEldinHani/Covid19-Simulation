import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.Slider;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.Pane;

public class MainMenu {
    Scene MainScene;
    ImageView BackGround;
    Slider s1;
    Slider s2;
    Slider s3;
    Slider s4;
    Slider s5;
    Slider s6;
    Slider s7;
    Slider s8;
    Slider s9;

    Label L1;
    Label L2;
    Label L3;
    Label L4;
    Label L5;
    Label L6;
    Label L7;
    Label L8;
    Label L9;

    Button B;

    public MainMenu(Pane Root)
    {
        this.MainScene = new Scene (Root,800,800);
        this.s1 = new Slider(0,200,0);                         //Nodes
        this.s2 = new Slider(0,50,0);                          //Positive
        this.s3 = new Slider(0,120,0);                          //Duration
        this.s4 = new Slider(0,500,0);                         //Min Time
        this.s5 = new Slider(500,1000,500);                          //Movement Distance
        this.s6 = new Slider(0,1,0);
        this.s7 = new Slider(0,5,0);
        this.s8 = new Slider(0,5,0);
        this.s9 = new Slider(0,50,0);

        this.L1 = new Label("Number of nodes");
        this.L2 = new Label("Positive Covid-19 Percentage");
        this.L3 = new Label("Walk Duration");
        this.L4 = new Label("Min Random Wait Time");
        this.L5 = new Label("Max Random Wait Time");
        this.L6 = new Label("Distance to move each time");
        this.L7 = new Label("Safe social distance");
        this.L8 = new Label("Unsafe Contact Time");
        this.L9 = new Label("X and Y Dimension in Meters");

        this.L1.setStyle("-fx-font: 22 Sans-serif;");
        this.L2.setStyle("-fx-font: 22 Sans-serif;");
        this.L3.setStyle("-fx-font: 22 Sans-serif;");
        this.L4.setStyle("-fx-font: 22 Sans-serif;");
        this.L5.setStyle("-fx-font: 22 Sans-serif;");
        this.L6.setStyle("-fx-font: 22 Sans-serif;");
        this.L7.setStyle("-fx-font: 22 Sans-serif;");
        this.L8.setStyle("-fx-font: 22 Sans-serif;");
        this.L9.setStyle("-fx-font: 22 Sans-serif;");

        this.B = new Button("Start Simulation");
        this.B.setStyle("-fx-font: 22 Sans-serif;");


    }

    public void SceneElements(Pane Root)
    {
        this.s1.setLayoutX(400);
        this.s1.setLayoutY(200);
        this.s1.setShowTickMarks(true);
        this.s1.setShowTickLabels(true);
        this.s1.setMajorTickUnit(50);
        this.s1.setBlockIncrement(10);
        this.s1.setPrefWidth(300);

        this.s2.setLayoutX(400);
        this.s2.setLayoutY(250);
        this.s2.setShowTickMarks(true);
        this.s2.setShowTickLabels(true);
        this.s2.setMajorTickUnit(10);
        this.s2.setBlockIncrement(5);
        this.s2.setPrefWidth(300);

        this.s3.setLayoutX(400);
        this.s3.setLayoutY(300);
        this.s3.setShowTickMarks(true);
        this.s3.setShowTickLabels(true);
        this.s3.setMajorTickUnit(10);
        this.s3.setBlockIncrement(10);
        this.s3.setPrefWidth(300);

        this.s4.setLayoutX(400);
        this.s4.setLayoutY(350);
        this.s4.setShowTickMarks(true);
        this.s4.setShowTickLabels(true);
        this.s4.setMajorTickUnit(50);
        this.s4.setBlockIncrement(10);
        this.s4.setPrefWidth(300);

        this.s5.setLayoutX(400);
        this.s5.setLayoutY(400);
        this.s5.setShowTickMarks(true);
        this.s5.setShowTickLabels(true);
        this.s5.setMajorTickUnit(50);
        this.s5.setBlockIncrement(10);
        this.s5.setPrefWidth(300);

        this.s6.setLayoutX(400);
        this.s6.setLayoutY(450);
        this.s6.setShowTickMarks(true);
        this.s6.setShowTickLabels(true);
        this.s6.setMajorTickUnit(0.1);
        this.s6.setBlockIncrement(0.1);
        this.s6.setPrefWidth(300);

        this.s7.setLayoutX(400);
        this.s7.setLayoutY(500);
        this.s7.setShowTickMarks(true);
        this.s7.setShowTickLabels(true);
        this.s7.setMajorTickUnit(1);
        this.s7.setBlockIncrement(1);
        this.s7.setPrefWidth(300);

        this.s8.setLayoutX(400);
        this.s8.setLayoutY(550);
        this.s8.setShowTickMarks(true);
        this.s8.setShowTickLabels(true);
        this.s8.setMajorTickUnit(1);
        this.s8.setBlockIncrement(1);
        this.s8.setPrefWidth(300);

        this.s9.setLayoutX(400);
        this.s9.setLayoutY(600);
        this.s9.setShowTickMarks(true);
        this.s9.setShowTickLabels(true);
        this.s9.setMajorTickUnit(5);
        this.s9.setBlockIncrement(5);
        this.s9.setPrefWidth(300);


        this.L1.setLayoutX(50);
        this.L1.setLayoutY(200);

        this.L2.setLayoutX(50);
        this.L2.setLayoutY(250);

        this.L3.setLayoutX(50);
        this.L3.setLayoutY(300);

        this.L4.setLayoutX(50);
        this.L4.setLayoutY(350);

        this.L5.setLayoutX(50);
        this.L5.setLayoutY(400);

        this.L6.setLayoutX(50);
        this.L6.setLayoutY(450);

        this.L7.setLayoutX(50);
        this.L7.setLayoutY(500);

        this.L8.setLayoutX(50);
        this.L8.setLayoutY(550);

        this.L9.setLayoutX(50);
        this.L9.setLayoutY(600);

        this.B.setLayoutX(400);
        this.B.setLayoutY(100);

        Root.getChildren().add(this.s1);
        Root.getChildren().add(this.s2);
        Root.getChildren().add(this.s3);
        Root.getChildren().add(this.s4);
        Root.getChildren().add(this.s5);
        Root.getChildren().add(this.s6);
        Root.getChildren().add(this.s7);
        Root.getChildren().add(this.s8);
        Root.getChildren().add(this.s9);

        Root.getChildren().add(this.L1);
        Root.getChildren().add(this.L2);
        Root.getChildren().add(this.L3);
        Root.getChildren().add(this.L4);
        Root.getChildren().add(this.L5);
        Root.getChildren().add(this.L6);
        Root.getChildren().add(this.L7);
        Root.getChildren().add(this.L8);
        Root.getChildren().add(this.L9);

        Root.getChildren().add(this.B);


    }

    public Slider GetS1()
    {
        return this.s1;
    }
    public Slider GetS2()
    {
        return this.s2;
    }
    public Slider GetS3()
    {
        return this.s3;
    }
    public Slider GetS4()
    {
        return this.s4;
    }
    public Slider GetS5()
    {
        return this.s5;
    }
    public Slider GetS6()
    {
        return this.s6;
    }
    public Slider GetS7()
    {
        return this.s7;
    }
    public Slider GetS8()
    {
        return this.s8;
    }
    public Slider GetS9()
    {
        return this.s9;
    }
    public Button GetButton()
    {
        return this.B;
    }
    public Scene GetScene()
    {
        return this.MainScene;
    }

    public void RemoveElements(Pane Root)
    {
        Root.getChildren().remove(this.s1);
        Root.getChildren().remove(this.s2);
        Root.getChildren().remove(this.s3);
        Root.getChildren().remove(this.s4);
        Root.getChildren().remove(this.s5);
        Root.getChildren().remove(this.s6);
        Root.getChildren().remove(this.s7);
        Root.getChildren().remove(this.s8);
        Root.getChildren().remove(this.s9);


        Root.getChildren().remove(this.L1);
        Root.getChildren().remove(this.L2);
        Root.getChildren().remove(this.L3);
        Root.getChildren().remove(this.L4);
        Root.getChildren().remove(this.L5);
        Root.getChildren().remove(this.L6);
        Root.getChildren().remove(this.L7);
        Root.getChildren().remove(this.L8);
        Root.getChildren().remove(this.L9);

        Root.getChildren().remove(this.B);
    }
}