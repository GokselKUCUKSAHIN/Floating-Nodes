import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Edge extends Group
{

    public static ArrayList<Edge> edges = new ArrayList<>();
    Knot[] parents = new Knot[2]; // ?
    private Vec2D str;
    private Vec2D end;
    private DoubleProperty width = new SimpleDoubleProperty(1);
    private Line body = new Line();

    //
    public Edge(Vec2D start, Vec2D finish)
    {
        // Create New Line Object
        // Set Color
        // Bind Start and End
        // update Once in Constructor
        // Add 'this' object to ArrayList
        str = start;
        end = finish;
        //
        body.startXProperty().bind(str.x);
        body.startYProperty().bind(str.y);
        body.endXProperty().bind(end.x);
        body.endYProperty().bind(end.y);
        body.strokeWidthProperty().bind(width);
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
            width.setValue(Utils.map(dist, 0, 100, 0.1, 3));
        } else
        {
            //Disconnect
            //selfDestruct();
        }
    }

    public void selfDestruct()
    {
        // DESTROY YOURSELF
        Main.child.remove(this); // remove from screen
        edges.remove(this); // remove from main memory
        // a Ghost Object waiting for Garbage Collector.
    }
}