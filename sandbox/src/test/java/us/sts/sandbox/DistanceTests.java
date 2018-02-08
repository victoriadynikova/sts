package us.sts.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void zeroDistanceTest(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void roundDistanceTest(){
        Point p1 = new Point(10,10);
        Point p2 = new Point(-2,2);

        Assert.assertEquals(Math.round(p1.distance(p2)), 14);
    }

    @Test
    public void accurateDistanceTest(){
        Point p1 = new Point(-3,-4);
        Point p2 = new Point(3,7.5);

        Assert.assertEquals(p1.distance(p2), 12.971121771072847);
    }

    @Test
    public void negativeDistanceTest(){
        Point p1 = new Point(-3,-4);
        Point p2 = new Point(-10,-0.5);

        Assert.assertNotEquals(p1.distance(p2), 7.83);
    }
}
