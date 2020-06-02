import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Knot extends Group
{

    public static ArrayList<Knot> knots = new ArrayList<>();

    public static double RANGE = 250;
    private DoubleProperty r = new SimpleDoubleProperty(10);
    public Vec2D pos; // Position Vector Variable
    public Vec2D vel = new Vec2D();
    public Vec2D acc = new Vec2D();
    private Circle body = new Circle();
    private Circle ring = new Circle();
    private double xOff = Utils.getRandom(100000, 999999);
    private double yOff = Utils.getRandom(100000, 999999);
    private double zOff = Utils.getRandom(10000, 999999);
    private double offset = 0.01;

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
        ring.setRadius(RANGE);
        ring.setFill(Color.TRANSPARENT);
        ring.setStroke(Color.YELLOW);
        ring.setStrokeWidth(0.4);
        ring.setVisible(false);
        //
        body.setFill(Color.SNOW);
        r.setValue(Utils.getRandom(1, 10));
        this.getChildren().addAll(ring, body);
    }

    public void update()
    {
        // PERLIN NOISE
        double pX = SimpleNoise.noise(xOff, 0, -0.1, 0.1, true);
        double pY = SimpleNoise.noise(yOff, 0, -0.1, 0.1, true);
        double pZ = SimpleNoise.noise(zOff, 0, 2, 8, true);
        xOff += 0.005;
        yOff += 0.005;
        zOff += 0.0005;
        // RADIUS
        r.setValue(pZ);
        // PHYSICS STUFF
        acc = new Vec2D(pX, pY);
        vel = vel.add(acc);
        pos = pos.add(vel); // Vector sum.
        vel = vel.multiply(0.9);
        // RELOCATE
        if (pos.x.get() > Main.W_ + 5)
        {
            pos.x.setValue(-5);
        } else if (pos.x.get() < -5)
        {
            pos.x.setValue(Main.W_ + 5);
        }
        //
        if (pos.y.get() > Main.H_ + 5)
        {
            pos.y.setValue(-5);
        } else if (pos.y.get() < -5)
        {
            pos.y.setValue(Main.H_ + 5);
        }
    }

    public void toggleRingVisiblity()
    {
        this.ring.setVisible(!ring.isVisible());
    }

    static void makeConnection()
    {
        int len = Knot.knots.size();
        for (int j = 1; j < len; j++)
        {
            for (int i = 0; i < j; i++)
            {
                //System.out.printf("  %c%c", list[j], list[i]);

            }
            System.out.println();
        }
    }
}