import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private Point[] pointsArr;
    private ArrayList<LineSegment> segmentsArr;

    public BruteCollinearPoints(Point[] points)
    {
        pointsArr = points.clone();
        segmentsArr = new ArrayList<LineSegment>();

        Arrays.sort(pointsArr);
        for (int p = 0; p < pointsArr.length - 3; p++)
        {
            for (int q = p + 1; q < pointsArr.length - 2; q++)
            {
                for (int r = q + 1; r < pointsArr.length - 1; r++)
                {
                    for (int s = r + 1; s < pointsArr.length; s++)
                    {
                        if (pointsArr[p].slopeTo(pointsArr[q]) == pointsArr[p].slopeTo(pointsArr[r]) && pointsArr[p].slopeTo(pointsArr[r]) == pointsArr[p].slopeTo(pointsArr[s]))
                        {
                            segmentsArr.add(new LineSegment(pointsArr[p], pointsArr[s]));
                        }
                    }   
                }   
            }
        }
    } // finds all line segments containing 4 points

    public int numberOfSegments()
    {
        return segmentsArr.size();
    } // the number of line segments
    public LineSegment[] segments()
    {
        return segmentsArr.toArray(new LineSegment[0]);
    } // the line segments

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
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