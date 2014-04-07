package ru.graphics.form;

import ru.graphics.model.Cone;
import ru.graphics.model.Cylinder;
import ru.graphics.model.Intersection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class MainForm extends JFrame implements ChangeListener{

    private JTable tableZX;

    private JSpinner spConeX;
    private JSpinner spCylinderX;
    private JSpinner spConeAndCylinderY;
    private JSpinner spConeC;
    private JSpinner spCylinderC;
    private JSpinner spConeR;
    private JSpinner spCylinderR;

    private JLabel lblFormula;

    private JPanelGraph jPanelGraph;

    public MainForm() {

        // Главная..
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 666, 674);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        // Панели
        jPanelGraph = new JPanelGraph();
        jPanelGraph.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        jPanelGraph.setLayout(null);
        jPanelGraph.setBounds(334, 12, 320, 620);
        contentPane.add(jPanelGraph);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        panel_1.setBounds(12, 12, 310, 211);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(12, 235, 310, 397);
        contentPane.add(panel_2);
        panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        panel_2.setLayout(null);


        // Панель_2
        double spinnerMin  = 1;
        double spinnerMax  = 100;
        double spinnerStep = 0.1;

        spConeX = new JSpinner();
        spConeX.setModel(new SpinnerNumberModel(8, spinnerMin, spinnerMax, spinnerStep));
        spConeX.setBounds(43, 31, 100, 20);
        spConeX.addChangeListener(this);
        panel_1.add(spConeX);

        spCylinderX = new JSpinner();
        spCylinderX.setBounds(197, 31, 100, 20);
        spCylinderX.setModel(new SpinnerNumberModel(4, spinnerMin, spinnerMax, spinnerStep));
        spCylinderX.addChangeListener(this);
        panel_1.add(spCylinderX);

        spConeAndCylinderY = new JSpinner();
        spConeAndCylinderY.setBounds(43, 63, 254, 20);
        spConeAndCylinderY.setModel(new SpinnerNumberModel(6, spinnerMin, spinnerMax, spinnerStep));
        spConeAndCylinderY.addChangeListener(this);
        panel_1.add(spConeAndCylinderY);

        spConeC = new JSpinner();
        spConeC.setBounds(43, 95, 100, 20);
        spConeC.setModel(new SpinnerNumberModel(3, spinnerMin, spinnerMax, spinnerStep));
        spConeC.addChangeListener(this);
        panel_1.add(spConeC);

        spCylinderC = new JSpinner();
        spCylinderC.setBounds(197, 95, 100, 20);
        spCylinderC.setModel(new SpinnerNumberModel(6, spinnerMin, spinnerMax, spinnerStep));
        spCylinderC.addChangeListener(this);
        panel_1.add(spCylinderC);

        spConeR = new JSpinner();
        spConeR.setBounds(43, 127, 100, 20);
        spConeR.setModel(new SpinnerNumberModel(6, spinnerMin, spinnerMax, spinnerStep));
        spConeR.addChangeListener(this);
        panel_1.add(spConeR);

        spCylinderR = new JSpinner();
        spCylinderR.setBounds(197, 127, 100, 20);
        spCylinderR.setModel(new SpinnerNumberModel(4, spinnerMin, spinnerMax, spinnerStep));
        spCylinderR.addChangeListener(this);
        panel_1.add(spCylinderR);

        JLabel lblX = new JLabel("X1");
        lblX.setBounds(12, 33, 28, 15);
        panel_1.add(lblX);

        JLabel lblY = new JLabel("Y");
        lblY.setBounds(12, 65, 28, 15);
        panel_1.add(lblY);

        JLabel lblC = new JLabel("C1");
        lblC.setBounds(12, 97, 28, 15);
        panel_1.add(lblC);

        JLabel lblNewLabel = new JLabel("Конус");
        lblNewLabel.setBounds(43, 12, 70, 15);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Цилиндр");
        lblNewLabel_1.setBounds(197, 12, 70, 15);
        panel_1.add(lblNewLabel_1);

        JLabel lblX_1 = new JLabel("X2");
        lblX_1.setBounds(165, 33, 28, 15);
        panel_1.add(lblX_1);

        JLabel lblC_1 = new JLabel("C2");
        lblC_1.setBounds(165, 97, 28, 15);
        panel_1.add(lblC_1);

        JLabel lblR = new JLabel("R1");
        lblR.setBounds(12, 129, 28, 15);
        panel_1.add(lblR);

        JLabel lblR_1 = new JLabel("R2");
        lblR_1.setBounds(165, 129, 28, 15);
        panel_1.add(lblR_1);

        JLabel lblxX = new JLabel("(x -  x2)^2 +(y - y2)^2 = r^2");
        lblxX.setHorizontalAlignment(SwingConstants.CENTER);
        lblxX.setFont(new Font("Dialog", Font.PLAIN, 11));
        lblxX.setBounds(12, 159, 285, 15);
        panel_1.add(lblxX);

        JLabel lblNewLabel_2 = new JLabel("(x - x1)^2 + (y - y1)^2 - ((r1/c1)^2)*(z - c1)^2 = 0");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 11));
        lblNewLabel_2.setBounds(0, 186, 310, 15);
        panel_1.add(lblNewLabel_2);


        // Панель_3
        lblFormula = new JLabel("Формула");
        lblFormula.setHorizontalAlignment(SwingConstants.CENTER);
        lblFormula.setBounds(12, 12, 286, 15);
        panel_2.add(lblFormula);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 35, 286, 350);
        panel_2.add(scrollPane);

        tableZX = new JTable();
        scrollPane.setViewportView(tableZX);

        /*JButton btnDraw = new JButton("Построить");
        btnDraw.setBounds(99, 187, 117, 25);
        btnDraw.addActionListener(this);
        panel_2.add(btnDraw);*/
    }


    private String[][] doubleToString(double[][] value) {
        int n = value.length;
        String[][] result = new String[n][];

        for(int i = 0; i < n; i++){
            int m = value[i].length;
            result[i] = new String[m];

            for (int j = 0; j < m; j++)
                result[i][j] = String.format("%.2f", value[i][j]);
        }

        return result;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        double coneX            = Double.valueOf(spConeX.getValue().toString());
        double cylinderX        = Double.valueOf(spCylinderX.getValue().toString());
        double coneAndCylinderY = Double.valueOf(spConeAndCylinderY.getValue().toString());
        double coneC            = Double.valueOf(spConeC.getValue().toString());
        double cylinderC        = Double.valueOf(spCylinderC.getValue().toString());
        double coneR            = Double.valueOf(spConeR.getValue().toString());
        double cylinderR        = Double.valueOf(spCylinderR.getValue().toString());

        // TODO: Добавить проверку даных

        Cone cone = new Cone(coneX, coneAndCylinderY, coneC, coneR);
        Cylinder cylinder = new Cylinder(cylinderX, coneAndCylinderY, cylinderC, cylinderR);

        lblFormula.setText(Intersection.getLineXY(cone, cylinder));

        String[][] data = doubleToString(Intersection.getTableZX(cone, cylinder, 15));
        String[] columnNames = new String[]{"Z", "X"};
        tableZX.setModel(new DefaultTableModel(data, columnNames));

        jPanelGraph.draw(cone, cylinder);
    }
}
