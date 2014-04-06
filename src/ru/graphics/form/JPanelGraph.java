package ru.graphics.form;

import ru.graphics.model.Cone;
import ru.graphics.model.Cylinder;
import ru.graphics.model.Intersection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class JPanelGraph extends JPanel implements MouseWheelListener {


    private int p;
    private int w;
    private int h;
    private int countMX;
    private int countMY;

    private int x0;
    private int y0;
    private int dX;
    private int dY;

    private Cone cone;
    private Cylinder cylinder;

    public JPanelGraph() {
        this.addMouseWheelListener(this);
        countMX = 15;
        countMY = 15;
    }

    public void draw(Cone cone, Cylinder cylinder){
        this.cone = cone;
        this.cylinder = cylinder;

        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        this.paint(g);

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        init();


        g2d.setColor(Color.BLACK);
        drawSystemOfAxes(g2d);

        g2d.setColor(Color.BLUE);
        drawCone(cone, g2d);

        g2d.setColor(Color.BLUE);
        drawCylinder(cylinder, g2d);

        g2d.setColor(Color.RED);
        drawIntersectionConeAndCylinder(cone, cylinder, g2d);
    }

    private void init(){
        p = 10;
        w = this.getWidth();
        h = this.getHeight();
        x0 = w-p;
        y0 = h/2;
        dX = (w - 2*p)/countMX;
        dY = (h - 2*p)/countMY/2;
    }

    private void drawSystemOfAxes(Graphics2D g2d) {
        g2d.drawLine(x0, p, x0, h-p);
        g2d.drawLine(p, y0, x0, y0);

        int size = 2;

        for (int x = x0; x >= p; x -= dX){
            int y = y0;
            g2d.drawLine(x, y-size, x, y+size);
        }

        for (int y = y0; y <= h-p; y += dY){
            int x = x0;
            g2d.drawLine(x+size, y, x-size, y);
        }
        for (int y = y0; y >= p; y -= dY){
            int x = x0;
            g2d.drawLine(x+size, y, x-size, y);
        }
    }

    private void drawCone(Cone cone, Graphics2D g2d){
        int x1 = (int)(x0 - dX*cone.getX() - dX*cone.getR());
        int y1 = y0;
        int x2 = (int)(x0 - dX*cone.getX());
        int y2 = (int)(y0 - dY*cone.getC());

        g2d.drawLine(x1, y1, x2, y2);

        x1 = (int)(x0 - dX*cone.getX() + dX*cone.getR());
        y1 = y0;
        x2 = (int)(x0 - dX*cone.getX());
        y2 = (int)(y0 - dY*cone.getC());

        g2d.drawLine(x1, y1, x2, y2);

        // ---------------------------------------- \\

        int x  = (int) (x0 - dX*cone.getX() - dX*cone.getR());
        int y  = (int) (y0 + dY*cone.getY() - dY*cone.getR());
        int r1 = (int) (cone.getR()*dX*2);
        int r2 = (int) (cone.getR()*dY*2);
        g2d.drawOval(x, y, r1, r2);
    }

    private void drawCylinder(Cylinder cylinder, Graphics2D g2d) {
        int x = (int)(x0 - dX*cylinder.getX() - dX*cylinder.getR());
        int y = (int)(y0 - dY*cylinder.getC()) ;
        int w = (int)(dX*cylinder.getR()*2);
        int h = (int)(dY*cylinder.getC());

        g2d.drawRect(x, y, w, h);

        // ---------------------------------------- \\

        x = (int) (x0 - dX*cylinder.getX() - dX*cylinder.getR());
        y = (int) (y0 + dY*cylinder.getY() - dY*cylinder.getR());;
        int r1 = (int) (cylinder.getR()*dX*2);
        int r2 = (int) (cylinder.getR()*dY*2);
        g2d.drawOval(x, y, r1, r2);
    }

    private void drawIntersectionConeAndCylinder(Cone cone, Cylinder cylinder, Graphics2D g2d) {
        double[][] intersection = Intersection.getTableZX(cone, cylinder, 10);
        int intersectionLength = intersection.length;
        int[] xPoint = new int[intersectionLength];
        int[] yPoint = new int[intersectionLength];

        for (int i = 0; i < intersectionLength; i++){
            xPoint[i] = (int) (x0 - dX*intersection[i][1]);
            yPoint[i] = (int) (y0 - dY*intersection[i][0]);
        }

        g2d.drawPolyline(xPoint, yPoint, intersectionLength);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (countMY < 5 || countMY < 5){
            countMY = countMX = 5;
            return;
        }

        countMY += e.getWheelRotation();
        countMX += e.getWheelRotation();
        init();
        draw(cone, cylinder);
    }
}
