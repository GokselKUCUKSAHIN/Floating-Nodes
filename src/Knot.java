import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Knot extends Group
{

    public static ArrayList<Knot> knots = new ArrayList<>();
    private DoubleProperty r = new SimpleDoubleProperty(10);
    public Vec2D pos; // Position Vector Variable
    public Vec2D vel = new Vec2D();
    private Circle body = new Circle();


    public Knot()
    {
        this.pos = new Vec2D();
        this.layoutXProperty().bind(pos.x);
        this.layoutYProperty().bind(pos.y);
        knots.add(this);
        body.radiusProperty().bind(r);
        draw();
    }

    public Knot(double x, double y)
    {
        this();
        pos.x.setValue(x);
        pos.y.setValue(y);
    }

    public void draw()
    {
        body.setFill(Color.SNOW);
        r.setValue(Utils.getRandom(1, 10));
        this.getChildren().addAll(body);
    }

    public void update()
    {
        pos = pos.add(vel); // Vector sum.
    }
}
