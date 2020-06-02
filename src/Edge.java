import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Edge extends Group
{

    public static ArrayList<Edge> edges = new ArrayList<>();
    private Vec2D str;
    private Vec2D end;
    private DoubleProperty width = new SimpleDoubleProperty(1);
    private Line body = new Line();

    //
    public Edge(Knot start, Knot finish)
    {
        // Create New Line Object
        // Set Color
        // Bind Start and End
        // update Once in Constructor
        // Add 'this' object to ArrayList
        str = start.pos;
        end = finish.pos;
        //
        body.startXProperty().bind(str.x);
        body.startYProperty().bind(str.y);
        body.endXProperty().bind(end.x);
        body.endYProperty().bind(end.y);
        body.strokeWidthProperty().bind(width);
        body.setStroke(Color.RED);
        //
        this.getChildren().addAll(body);
        //
        update();
        edges.add(this);
        Main.child.addAll(this);
    }

    public void update()
    {

        // Calculate Distance Between End and Start and re-arrange Width value
        double dist = str.distance(end);

        if (dist <= Knot.RANGE)
        {
            //Stay Connected
            width.setValue(Utils.map(dist, 0, Knot.RANGE, 4, 0.1));
            this.setVisible(true);
        } else
        {
            //Disconnect
            this.setVisible(false);
        }
    }


    static double getDistance(Knot a, Knot b)
    {
        return a.pos.distance(b.pos);
    }
}