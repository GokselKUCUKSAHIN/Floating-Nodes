import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{

    public static ObservableList<Node> child;
    //
    private static final String title = "JellyBeanci";
    public static final int W_ = 800;
    public static final int H_ = 800;
    private static Color backcolor = Color.rgb(51, 51, 51);

    private static Timeline update;

    @Override
    public void start(Stage stage)
    {
        Pane root = new Pane();
        child = root.getChildren();
        //
        for (int i = 0; i < 20; i++)
        {
            new Knot(Utils.getRandomInt(W_),Utils.getRandomInt(H_));
        }
        for(Knot knot: Knot.knots)
        {
            child.addAll(knot);
        }

        //
        root.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case F1:
                {
                    //PLAY
                    update.play();
                    break;
                }
                case F2:
                {
                    //PAUSE
                    update.pause();
                    break;
                }
                case F3:
                {
                    //Show Child Count
                    System.out.println("Child Count: " + child.size());
                    break;
                }
                case F4:
                {
                    // Hide/Show Ring Visible
                    for(Knot knot: Knot.knots)
                    {
                        knot.toggleRingVisiblity();
                    }
                    break;
                }
            }
        });
        update = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            //60 fps
            for(Knot knot: Knot.knots)
            {
                knot.update();
            }

        }));
        update.setCycleCount(Timeline.INDEFINITE);
        update.setRate(1);
        update.setAutoReverse(false);
        //update.play(); //uncomment for play when start
        //
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, W_ - 10, H_ - 10, backcolor));
        stage.show();
        root.requestFocus();
    }




    public static void main(String[] args)
    {
        launch(args);
    }
}