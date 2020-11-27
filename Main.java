import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Threads implements Runnable {
    enum Status
    {
        Positive,
        Negative,
        Infected
    }
    private int x;
    private int y;
    private Pane Root;
    private boolean stop;
    private Circle Node ;
    private Boolean PossiblyInfected = false;
    private Timeline T;
    private Timeline T1;
    Status S;
    Threads(Pane Root) {
        this.stop = false;
        Main.ListOfThreads.add(this);
        this.Root = Root;
        Node = new Circle(5);
        if (Main.PositiveCovid > 0)
        {
            Node.setFill(Color.RED);
            this.SetPosition(2);
            Main.PositiveCovid--;
            this.S = Status.Positive;
        }
        else
        {
            Node.setFill(Color.BLUE);
            this.SetPosition(1);
            this.S = Status.Negative;
        }

    }

    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SetPositionGUI();
                } catch (Exception e) {
                    System.out.println(e);
                }
                int r = (int) (Math.random() * ((int)Main.MaxTime - (int)Main.MinTime)) + (int) Main.MinTime;

                 T = new Timeline(new KeyFrame(Duration.millis(r) , actionEvent -> {

                    MovementInGrid();
                    if (stop)
                        T.stop();
                }));
                T.setCycleCount(Animation.INDEFINITE);
                T.play();

                T1 = new Timeline(new KeyFrame(Duration.seconds((Main.ContactTime /2)) , actionEvent -> {
                    CheckDistance();
                    if (stop)
                        T1.stop();
                }));
                T1.setCycleCount(Animation.INDEFINITE);
                T1.play();
            }
        });
    }
    public void MovementInGrid() {
        boolean Temp = true;
        while (Temp) {
            int R = (int) (Math.random() * (9 - 1) +1);
            if (R == 1) {
                if (this.x + 1 < (int)Main.Dimension)// 1-Right
                    if (Main.Positions[this.x + 1][this.y] == 0){
                        int temp = Main.Positions[this.x][this.y];
                        Main.Positions[this.x][this.y] = Main.Positions[this.x+1][this.y];
                        Main.Positions[this.x+1][this.y] = temp;
                        this.Node.setCenterX(this.Node.getCenterX() + ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                        this.x+=1;
                        Temp = false;
                    }
            } else if (R == 2) // 2-left
            {
                if (this.x-1 >= 0)
                    if (Main.Positions[this.x - 1][this.y] == 0) {
                        int temp = Main.Positions[this.x][this.y];
                        Main.Positions[this.x][this.y] = Main.Positions[this.x-1][this.y];
                        Main.Positions[this.x-1][this.y] = temp;
                        this.x-=1;
                        this.Node.setCenterX(this.Node.getCenterX() - ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                        Temp = false;
                    }
            } else if (R == 3)         // 3-down
            {
                if ((this.y + 1) < (int)Main.Dimension)
                    if (Main.Positions[(this.x)][(this.y + 1)] == 0) {
                        int temp = Main.Positions[this.x][this.y];
                        Main.Positions[this.x][this.y] = Main.Positions[this.x][this.y+1];
                        Main.Positions[this.x][this.y+1] = temp;
                        this.y+=1;
                        this.Node.setCenterY(this.Node.getCenterY() + ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                        Temp = false;
                    }
            } else if (R == 4)         // 4-up
            {
                if ((this.y - 1) >= 0)
                    if (Main.Positions[(this.x)][(this.y - 1)] == 0) {
                        int temp = Main.Positions[this.x][this.y];
                        Main.Positions[this.x][this.y] = Main.Positions[this.x][this.y-1];
                        Main.Positions[this.x][this.y-1] = temp;
                        this.y-=1;
                        this.Node.setCenterY(this.Node.getCenterY() - ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                        Temp = false;
                    }
            }
            else if (R == 5)         // 5-rightdiagup
            {
                if ((this.y - 1) >= 0 && (this.x + 1) < (int)Main.Dimension)
                {   if (Main.Positions[(this.x+1)][(this.y - 1)] == 0) {
                    int temp = Main.Positions[this.x][this.y];
                    Main.Positions[this.x][this.y] = Main.Positions[this.x+1][this.y-1];
                    Main.Positions[this.x+1][this.y-1] = temp;
                    this.x+=1;
                    this.y-=1;
                    this.Node.setCenterX(this.Node.getCenterX() + ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    this.Node.setCenterY(this.Node.getCenterY() - ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    Temp = false;
                }
                }
            }
            else if (R == 6)         // 6-leftdiagup
            {
                if ((this.y - 1) >= 0 && (this.x - 1) >= 0)
                {   if (Main.Positions[(this.x-1)][(this.y - 1)] == 0) {
                    int temp = Main.Positions[this.x][this.y];
                    Main.Positions[this.x][this.y] = Main.Positions[this.x-1][this.y-1];
                    Main.Positions[this.x-1][this.y-1] = temp;
                    this.x-=1;
                    this.y-=1;
                    this.Node.setCenterX(this.Node.getCenterX() - ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    this.Node.setCenterY(this.Node.getCenterY() - ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    Temp = false;
                }
                }
            }
            else if (R == 7)         // 7-rightdiadown
            {
                if ((this.y + 1) < (int)Main.Dimension && (this.x + 1) <(int)Main.Dimension)
                {   if (Main.Positions[(this.x+1)][(this.y+1)] == 0) {
                    int temp = Main.Positions[this.x][this.y];
                    Main.Positions[this.x][this.y] = Main.Positions[this.x+1][this.y+1];
                    Main.Positions[this.x+1][this.y+1] = temp;
                    this.x+=1;
                    this.y+=1;
                    this.Node.setCenterX(this.Node.getCenterX() + ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    this.Node.setCenterY(this.Node.getCenterY() + ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    Temp = false;
                }
                }
            }
            else if (R == 8)         // 8-leftdiagdown
            {
                if ((this.y + 1) < (int)Main.Dimension && (this.x - 1) >= 0)
                {   if (Main.Positions[(this.x-1)][(this.y+1)] == 0) {
                    int temp = Main.Positions[this.x][this.y];
                    Main.Positions[this.x][this.y] = Main.Positions[this.x-1][this.y+1];
                    Main.Positions[this.x-1][this.y+1] = temp;
                    this.x-=1;
                    this.y+=1;
                    this.Node.setCenterX(this.Node.getCenterX() - ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    this.Node.setCenterY(this.Node.getCenterY() + ((800 *Main.DistanceToMove)/ (int) Main.OriginalDimension));
                    Temp = false;
                }
                }
            }
        }
    }
    public synchronized void CheckDistance()
    {
        
        for (int i = 0 ; i < (int)Main.Dimension; i++)
            for (int j = 0 ; j < (int)Main.Dimension; j++)
            {
                if (Main.Positions[i][j] == 2 && this.S == Status.Negative)
            {
                double Distance = Math.sqrt((this.x - i)  * (this.x - i) + (this.y - j)  * (this.y - j));
                Distance *= Main.DistanceToMove;
                if (Distance < Main.SocialDistance && !this.PossiblyInfected)
                {
                    this.PossiblyInfected = true;
                    
                }
                else if (Distance < Main.SocialDistance && this.PossiblyInfected)
                {
                    this.S = Status.Infected;
                    this.Node.setFill(Color.ORANGE);
            
                }
                else if (Distance >= Main.SocialDistance && this.PossiblyInfected)
                {
                    this.PossiblyInfected = false;
                }
            }
            }
    }
    public synchronized void SetPosition(int temp) {
        this.x = (int) (Math.random() * (int) Main.Dimension -1);
        this.y = (int) (Math.random() * (int) Main.Dimension -1);
        while (Main.Positions[this.x][this.y] != 0) {
            this.x = (int) (Math.random() * (int) Main.Dimension-1);
            this.y = (int) (Math.random() * (int) Main.Dimension-1);
        }
        Main.Positions[this.x][this.y] = temp;
    }

    public void SetPositionGUI() {
        this.Node.setCenterX((800 *Main.DistanceToMove/ Main.OriginalDimension) * this.x);
        this.Node.setCenterY((800 *Main.DistanceToMove/ Main.OriginalDimension) * this.y);
        Root.getChildren().add(Node);
    }
    public void Stop()
    {
        this.stop = true;
    }

}
public class Main extends Application {
    Pane Root = new Pane();
    static int[][] Positions;
    static double Nodes;
    static double Percentage;
    static double WalkDuration;
    static double MinTime;
    static double MaxTime;
    static double DistanceToMove;
    static double SocialDistance;
    static double ContactTime;
    static double OriginalDimension;
    static double Dimension;
    static double PositiveCovid ;
    static ArrayList<Threads> ListOfThreads = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        ArrayList<Thread> LT = new ArrayList<>();
        MainMenu Menu = new MainMenu(Root);
        Menu.SceneElements(Root);
        Button StartButton = Menu.GetButton();
        StartButton.setOnMouseClicked(mouseEvent -> {
            Menu.RemoveElements(Root);
            Nodes = Menu.GetS1().getValue();
            Percentage = Menu.GetS2().getValue();
            WalkDuration = Menu.GetS3().getValue();
            MinTime = Menu.GetS4().getValue();
            MaxTime = Menu.GetS5().getValue();
            DistanceToMove = Menu.GetS6().getValue();
            SocialDistance = Menu.GetS7().getValue();
            ContactTime = Menu.GetS8().getValue();
            OriginalDimension = (int) Menu.GetS9().getValue();

            Dimension = (int) OriginalDimension / DistanceToMove;
            PositiveCovid = (int) Nodes * (Percentage/100);
            Positions = new int[(int)Dimension][(int)Dimension];

            for (int i = 0; i < Nodes; i++) {
                Thread object = new Thread(new Threads(Root));
                LT.add(object);
                object.start();
            }
            PauseTransition delay = new PauseTransition(Duration.seconds(WalkDuration));
            delay.setOnFinished(event ->
            {
                int PositiveCases =0;
                int NegativeCases =0;
                int InfectedCases =0;
                FileWriter CovidLog = null;
                String s; 
                try {
                    CovidLog = new FileWriter("CovidLog.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i =0 ; i < ListOfThreads.size() ; i++) {
                    ListOfThreads.get(i).Stop();
                    if (ListOfThreads.get(i).S == Threads.Status.Positive)
                        PositiveCases++;
                    else if (ListOfThreads.get(i).S == Threads.Status.Negative)
                        NegativeCases++;
                    else
                        InfectedCases++;

                    s = "Person Number " + i + " is " + ListOfThreads.get(i).S;
                    try {
                            CovidLog.write(s);
                            CovidLog.write('\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Root.getChildren().clear();
                
                String Positive = "Number of Positive Cases: " + PositiveCases;
                String Negative = "Number of Negative Cases: " + NegativeCases;
                String Infected = "Number of Infected Cases: " + InfectedCases;
                Label L1 = new Label(Positive);
                Label L2 = new Label(Negative);
                Label L3 = new Label(Infected);
                Label L4 = new Label("Detailed Report is saved to file CovidLog.txt");
                L1.setLayoutX(50);
                L1.setLayoutY(200);
                L2.setLayoutX(50);
                L2.setLayoutY(250);
                L3.setLayoutX(50);
                L3.setLayoutY(300);
                L4.setLayoutX(50);
                L4.setLayoutY(350);
                L1.setStyle("-fx-font: 22 Sans-serif;");
                L2.setStyle("-fx-font: 22 Sans-serif;");
                L3.setStyle("-fx-font: 22 Sans-serif;");
                L4.setStyle("-fx-font: 22 Sans-serif;");

                Root.getChildren().add(L1);
                Root.getChildren().add(L2);
                Root.getChildren().add(L3);
                Root.getChildren().add(L4);
                try {
                    CovidLog.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            delay.play();
        });


        primaryStage.setScene(Menu.GetScene());
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setTitle("Covid-19 Tracer");
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}