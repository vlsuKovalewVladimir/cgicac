package ru.graphics.model;

public class Intersection {
    private Intersection(){}

    public static String getLineXY(Cone cone, Cylinder cylinder){
        double a1 = cylinder.getX();
        double a2 = cone.getX();
        double b1 = cylinder.getY();
        double b2 = cone.getY();
        double c2 = cone.getC();
        double r1 = cylinder.getR();
        double r2 = cone.getR();

        double x,y,z1,z2,s;
        x  = 2*(a2 - a1);
        y  = 2*(b2 - b1);
        z2 = (r2*r2)/(c2*c2);
        z1 = 2*r2*r2/c2;
        s  = r1*r1 - r2*r2 - a1*a1 + a2*a2 - b1*b1 + b2*b2;

        double xy, xz2, xz1, xs;
        xy  = -  y / x;
        xz2 = - z2 / x;
        xz1 =   z1 / x;
        xs  =    s / x;

        return formulaToString("x = ", "y", xz2, xz1, xy, xs);
    }

    public static double[][] getTableZX(Cone cone, Cylinder cylinder, int count){
        double a1 = cylinder.getX();
        double a2 = cone.getX();
        double b1 = cylinder.getY();
        double b2 = cone.getY();
        double c1 = cylinder.getC();
        double c2 = cone.getC();
        double r1 = cylinder.getR();
        double r2 = cone.getR();

        double x,z1,z2,s;
        x  = 2*(a2 - a1);
        z2 = (r2*r2)/(c2*c2);
        z1 = 2*r2*r2/c2;
        s  = r1*r1 - r2*r2 - a1*a1 + a2*a2 - b1*b1 + b2*b2;

        double xz2, xz1, xs;
        xz2 = - z2 / x;
        xz1 =   z1 / x;
        xs  =    s / x;

        double coneLeftX  = a2 + r2;
        double coneRightX = a2 - r2;
        double cylinderLeftX = a1 + r1;
        double cylinderRightX = a1 - r1;

        double leftX = min(coneLeftX, cylinderLeftX);
        double rightX = max(coneRightX, cylinderRightX);

        double z0 = 0;
        double maxZ = 0; //min(c1,c2);

        if (cylinderLeftX >= coneRightX && a2 >= cylinderLeftX){
            maxZ = equationByTwoPoints(leftX, a2, c2, coneRightX, 0); // r
        }

        if (coneLeftX >= cylinderRightX && cylinderRightX >= a2){
            maxZ = equationByTwoPoints(rightX, a2, c2, coneLeftX, 0); // l
        }

        if (coneLeftX >= cylinderLeftX && cylinderLeftX >= a2){
            maxZ = equationByTwoPoints(leftX, a2, c2, coneLeftX, 0); // l
        }

        if (a2 >= cylinderRightX && cylinderRightX >= coneRightX){
            maxZ = equationByTwoPoints(rightX, a2, c2, coneRightX, 0); // r
        }

        if (coneLeftX >= cylinderLeftX && cylinderLeftX >= a2 &&
                a2 > cylinderRightX && cylinderRightX > coneRightX){

            z0 = equationByTwoPoints(rightX, a2, c2, coneRightX, 0); // r
            maxZ = equationByTwoPoints(leftX, a2, c2, coneLeftX, 0); // l
            maxZ -= z0;
        }

        if (a2 >= cylinderLeftX && a2 >= cylinderRightX &&
                cylinderLeftX >= coneRightX && cylinderRightX >= coneRightX){
            z0 = equationByTwoPoints(rightX, a2, c2, coneRightX, 0); // r
            maxZ = equationByTwoPoints(leftX, a2, c2, coneRightX, 0); // r
            maxZ -= z0;
        }

        if (coneLeftX >= cylinderLeftX && coneLeftX >= cylinderRightX &&
                cylinderLeftX >= a2 && cylinderRightX >= a2){
            z0 = equationByTwoPoints(leftX, a2, c2, coneLeftX, 0); // l
            maxZ = equationByTwoPoints(rightX, a2, c2, coneLeftX, 0); // l
            maxZ -= z0;
        }

        double dZ = maxZ/count;
        double z = z0;

        double[][] result = new double[count+1][2];

        for (int i = 0; i < count+1; i++){
            double x1 = xz2*z*z + xz1*z + xs;
            result[i][0] = z;
            result[i][1] = x1;
            z += dZ;
        }

        return result;
    }

    private static double min(double c1, double c2) {
        return  (c1 > c2) ? c2 : c1;
    }

    private static double max(double c1, double c2) {
        return  (c1 < c2) ? c2 : c1;
    }

    private static double equationByTwoPoints(double x, double x0, double y0, double x1, double y1){
        return (x-x0)*(y1-y0)/(x1-x0)+y0;
    }

    private static String formulaToString(String str, String str1, double z2, double z1, double y, double s){
        String res = str;

        res += paramToString(z2, "*z^2 ");
        res += paramToString(z1, "*z ");
        res += paramToString(y, "*" + str1 + " ");
        res += paramToString(s, " \n");

        return res;
    }

    private static String paramToString(double p, String s){
        return (p==0) ? "" : (((p<0) ? "- "+String.format("%.2f", -p) : "+ "+String.format("%.2f", p)) + s);
    }
}
