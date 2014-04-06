package ru.graphics.model;

public class Intersection {
    private Intersection(){};

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

        double[][] result = new double[count+1][2];

        double minZ = min(c1,c2);

        double z0 = 0;
        double dZ = minZ/count;
        double z = z0;

        for (int i = 0; i < count+1; i++){
            double x1 = xz2*z*z + xz1*z + xs;
            result[i][0] = z;
            result[i][1] = x1;
            z += dZ;
        }

        /*double[][] result = new double[count+1][2];
        double minC = min(c1,c2);
        int i = 0;
        for (double z = 0; z <= minC; z += minC/count){
            double x1 = xz2*z*z + xz1*z + xs;
            result[i][0] = z;
            result[i][1] = x1;
            i++;
        }*/

        return result;
    }

    private static double min(double c1, double c2) {
        return  (c1 > c2) ? c2 : c1;
    }

    private static double max(double c1, double c2) {
        return  (c1 < c2) ? c2 : c1;
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
