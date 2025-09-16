import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private Point[] pointsArr;
    private ArrayList<LineSegment> segmentsArr;
    
    public FastCollinearPoints(Point[] points)
    {
        pointsArr = points.clone();
        segmentsArr = new ArrayList<LineSegment>();

        Arrays.sort(pointsArr);
        for (Point p : pointsArr) // p is the test point
        {
            int count = 0;
            double slopeTo = Double.NEGATIVE_INFINITY;
            Point [] slopeOrderArr = pointsArr.clone();
            Arrays.sort(slopeOrderArr, p.slopeOrder());
            for (int i = 1; i < slopeOrderArr.length; i++)
            {
                if (slopeTo == p.slopeTo(slopeOrderArr[i]))
                {
                    count++;
                    //System.out.println(count);
                }
                else
                {
                    if (count >= 4 && p.compareTo(slopeOrderArr[i + 1 - count]) < 0)
                    {
                        //System.out.println(i + ", " + (slopeOrderArr.length - 1) + ", " + p.compareTo(slopeOrderArr[i + 1 - count]));
                        //System.out.println("run:" + p + " to " + slopeOrderArr[i - 1]);
                        segmentsArr.add(new LineSegment(p, slopeOrderArr[i - 1]));
                    }
                    count = 2;
                    slopeTo = p.slopeTo(slopeOrderArr[i]);
                }
                
            }
            if (count >= 4 && p.compareTo(slopeOrderArr[slopeOrderArr.length - count + 1]) < 0)
            {
                segmentsArr.add(new LineSegment(p, slopeOrderArr[slopeOrderArr.length - 1]));
            }
        }
    } // finds all line segments containing 4 or more points
    
    public int numberOfSegments()
    {
        return segmentsArr.size();
    } // the number of line segments
    public LineSegment[] segments()
    {
        LineSegment[] toReturn = new LineSegment[segmentsArr.size()];
        for (int i = 0; i < segmentsArr.size(); i++)
        {
            toReturn[i] = segmentsArr.get(i);
        }
        return toReturn;
    } // the line segments

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("collinear (1)/input40.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
 }
