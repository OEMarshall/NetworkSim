/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

/**
 * Animation Class authored by teknikindustries and can be found here
 * http://www.teknikindustries.com/youtube/
 */
import AppPackage.AnimationClass;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Point;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Ochaun Marshall
 */
public class ContactEditorUI extends javax.swing.JFrame {

    private Random rand = new Random();
    public static Message messageOne, messageTwo;
    Integer[] cost = new Integer[11];
    ArrayList shortcut;
    AnimationClass AC;
    edgeDisplay one, two, three, four, five, six, seven, eight, nine, ten, eleven;

    /**
     * Creates new form ContactEditorUI
     */
    public ContactEditorUI() {
        graphMath();
        displayEdgeInit();
        initComponents();
        String messageA = JOptionPane.showInputDialog("Enter a string for message 1:");
        String messageB = JOptionPane.showInputDialog("Enter a string fofr message 2:");
        message1.text = messageA;
        message2.text = messageB;
        messageOne = message1;
        messageTwo = message2;
    }

    public void weightInit() {
        for (int i = 0; i < cost.length; i++) { //initialize 
            cost[i] = (rand.nextInt(7) + 1);
        }
    }

    public void graphMath() {
        final WeightedGraph net = new WeightedGraph(11);
        weightInit();
        net.setLabel(0, "r1");
        net.setLabel(1, "r2");
        net.setLabel(2, "r3");
        net.setLabel(3, "r4");
        net.setLabel(4, "r5");
        net.setLabel(5, "r6");
        net.setLabel(6, "r7");
        net.setLabel(7, "r8");
        net.setLabel(8, "r9");
        net.setLabel(9, "r10");
        net.setLabel(10, "r11");
        net.addEdge(0, 1, cost[0]);
        net.addEdge(0, 6, cost[1]);
        net.addEdge(1, 4, cost[2]);
        net.addEdge(6, 4, cost[3]);
        net.addEdge(1, 2, cost[4]);
        net.addEdge(4, 5, cost[5]);
        net.addEdge(6, 7, cost[6]);
        net.addEdge(5, 2, cost[7]);
        net.addEdge(5, 7, cost[8]);
        net.addEdge(2, 3, cost[9]);
        net.addEdge(7, 3, cost[10]);

        final int[] pred = Dijkstra.dijkstra(net, 0);
        shortcut = Dijkstra.Path(net, pred, 0, 3);
        System.out.println("Shortcut = " + shortcut.toString());
    }

    private void displayEdgeInit() {
        Point r1 = new Point(210, 310);
        Point r2 = new Point(270, 200);
        Point r3 = new Point(610, 200);
        Point r4 = new Point(680, 310);
        Point r5 = new Point(340, 320);
        Point r6 = new Point(540, 310);
        Point r7 = new Point(280, 430);
        Point r8 = new Point(610, 430);

        one = new edgeDisplay(r1, r2);
        two = new edgeDisplay(r1, r7);
        three = new edgeDisplay(r2, r5);
        four = new edgeDisplay(r7, r5);
        five = new edgeDisplay(r2, r3);
        six = new edgeDisplay(r5, r6);
        seven = new edgeDisplay(r7, r8);
        eight = new edgeDisplay(r6, r3);
        nine = new edgeDisplay(r6, r8);
        ten = new edgeDisplay(r3, r4);
        eleven = new edgeDisplay(r8, r4);
    }

    public static int delayCalc(int cost) {
        int delay = cost;
        if (cost > 3) {
            delay = cost * 15;
        }
        return delay;
    }

    public void edgeRefresh() {
        e12.setText(Integer.toString(cost[0]));
        e17.setText(Integer.toString(cost[1]));
        e23.setText(Integer.toString(cost[2]));
        e25.setText(Integer.toString(cost[3]));
        e34.setText(Integer.toString(cost[4]));
        e56.setText(Integer.toString(cost[5]));
        e57.setText(Integer.toString(cost[6]));
        e63.setText(Integer.toString(cost[7]));
        e68.setText(Integer.toString(cost[8]));
        e78.setText(Integer.toString(cost[9]));
        e84.setText(Integer.toString(cost[10]));
    }

    private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Group root = new Group();
        //Scene  scene  =  new  Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        //stage.setTitle("Bianary FSK chart");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Bit of Message");
        //creating the chart
        final LineChart<Number, Number> lineChart
                = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Hello");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Bianary Values for -");
        //populating the series with data
//        byte[] m1 = messageOne.text.getBytes();
//        Byte[] mOne = null;
//        for (int i = 0; i < m1.length; i++) {
//            mOne[i] = m1[i];
//        }
//        for (int i = 0; i < mOne.length; i++) {
//            System.out.println(mOne[i]);
//        }
        series.getData().add(new XYChart.Data(1, 0));
        series.getData().add(new XYChart.Data(2, 1));
        series.getData().add(new XYChart.Data(3, 0));
        series.getData().add(new XYChart.Data(4, 1));
        series.getData().add(new XYChart.Data(5, 0));
        series.getData().add(new XYChart.Data(6, -1));
        series.getData().add(new XYChart.Data(7, 0));
        series.getData().add(new XYChart.Data(8, -1));

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        //stage.setScene(scene);
        return (scene);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runRedButton = new javax.swing.JButton();
        runBlueButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        appLayerH1 = new javax.swing.JButton();
        transLayerH1 = new javax.swing.JButton();
        netLayerH1 = new javax.swing.JButton();
        dlLayerH1 = new javax.swing.JButton();
        physLayerH1 = new javax.swing.JButton();
        appLayerH2 = new javax.swing.JButton();
        transLayerH2 = new javax.swing.JButton();
        netLayerH2 = new javax.swing.JButton();
        dlLayerH2 = new javax.swing.JButton();
        physLayerH2 = new javax.swing.JButton();
        message2 = new my.contacteditor.Message();
        message1 = new my.contacteditor.Message();
        e12 = new javax.swing.JTextField();
        e17 = new javax.swing.JTextField();
        e23 = new javax.swing.JTextField();
        e25 = new javax.swing.JTextField();
        e78 = new javax.swing.JTextField();
        e56 = new javax.swing.JTextField();
        e63 = new javax.swing.JTextField();
        e68 = new javax.swing.JTextField();
        e84 = new javax.swing.JTextField();
        e34 = new javax.swing.JTextField();
        e57 = new javax.swing.JTextField();
        backgroundImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(926, 493));
        setMinimumSize(new java.awt.Dimension(923, 493));
        setPreferredSize(new java.awt.Dimension(926, 493));
        setResizable(false);
        getContentPane().setLayout(null);

        runRedButton.setText("Step Red");
        runRedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runRedButtonActionPerformed(evt);
            }
        });
        getContentPane().add(runRedButton);
        runRedButton.setBounds(350, 50, 100, 30);

        runBlueButton.setText("Run Blue");
        runBlueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runBlueButtonActionPerformed(evt);
            }
        });
        getContentPane().add(runBlueButton);
        runBlueButton.setBounds(470, 50, 100, 30);

        resetButton.setText("Reset");
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                resetButtonMouseReleased(evt);
            }
        });
        getContentPane().add(resetButton);
        resetButton.setBounds(720, 10, 80, 40);

        appLayerH1.setBorderPainted(false);
        appLayerH1.setContentAreaFilled(false);
        appLayerH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                appLayerH1MouseReleased(evt);
            }
        });
        appLayerH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appLayerH1ActionPerformed(evt);
            }
        });
        getContentPane().add(appLayerH1);
        appLayerH1.setBounds(73, 60, 110, 90);

        transLayerH1.setToolTipText("");
        transLayerH1.setBorder(null);
        transLayerH1.setBorderPainted(false);
        transLayerH1.setContentAreaFilled(false);
        transLayerH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                transLayerH1MouseReleased(evt);
            }
        });
        getContentPane().add(transLayerH1);
        transLayerH1.setBounds(73, 150, 110, 30);

        netLayerH1.setToolTipText("");
        netLayerH1.setBorder(null);
        netLayerH1.setBorderPainted(false);
        netLayerH1.setContentAreaFilled(false);
        netLayerH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                netLayerH1MouseReleased(evt);
            }
        });
        getContentPane().add(netLayerH1);
        netLayerH1.setBounds(73, 175, 110, 30);

        dlLayerH1.setToolTipText("");
        dlLayerH1.setBorder(null);
        dlLayerH1.setBorderPainted(false);
        dlLayerH1.setContentAreaFilled(false);
        dlLayerH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dlLayerH1MouseReleased(evt);
            }
        });
        getContentPane().add(dlLayerH1);
        dlLayerH1.setBounds(73, 203, 110, 30);

        physLayerH1.setToolTipText("");
        physLayerH1.setBorder(null);
        physLayerH1.setBorderPainted(false);
        physLayerH1.setContentAreaFilled(false);
        physLayerH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                physLayerH1MouseReleased(evt);
            }
        });
        getContentPane().add(physLayerH1);
        physLayerH1.setBounds(70, 240, 110, 30);

        appLayerH2.setToolTipText("");
        appLayerH2.setBorderPainted(false);
        appLayerH2.setContentAreaFilled(false);
        appLayerH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                appLayerH2MouseReleased(evt);
            }
        });
        getContentPane().add(appLayerH2);
        appLayerH2.setBounds(703, 70, 113, 80);

        transLayerH2.setToolTipText("");
        transLayerH2.setBorderPainted(false);
        transLayerH2.setContentAreaFilled(false);
        transLayerH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                transLayerH2MouseReleased(evt);
            }
        });
        getContentPane().add(transLayerH2);
        transLayerH2.setBounds(703, 150, 113, 30);

        netLayerH2.setToolTipText("");
        netLayerH2.setBorderPainted(false);
        netLayerH2.setContentAreaFilled(false);
        netLayerH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                netLayerH2MouseReleased(evt);
            }
        });
        getContentPane().add(netLayerH2);
        netLayerH2.setBounds(703, 180, 113, 30);

        dlLayerH2.setToolTipText("");
        dlLayerH2.setBorderPainted(false);
        dlLayerH2.setContentAreaFilled(false);
        dlLayerH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dlLayerH2MouseReleased(evt);
            }
        });
        getContentPane().add(dlLayerH2);
        dlLayerH2.setBounds(700, 210, 113, 30);

        physLayerH2.setToolTipText("");
        physLayerH2.setBorderPainted(false);
        physLayerH2.setContentAreaFilled(false);
        physLayerH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                physLayerH2MouseReleased(evt);
            }
        });
        getContentPane().add(physLayerH2);
        physLayerH2.setBounds(700, 240, 113, 30);

        message2.c = Color.blue;
        getContentPane().add(message2);
        message2.setBounds(190, 70, 35, 35);
        getContentPane().add(message1);
        message1.setBounds(20, 70, 35, 35);

        e12.setEditable(false);
        e12.setText(Integer.toString(cost[0]));
        e12.setMinimumSize(new java.awt.Dimension(25, 25));
        e12.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e12);
        e12.setBounds(198, 210, 30, 30);

        e17.setEditable(false);
        e17.setText(Integer.toString(cost[1]));
        e17.setMinimumSize(new java.awt.Dimension(25, 25));
        e17.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e17);
        e17.setBounds(190, 360, 25, 25);

        e23.setEditable(false);
        e23.setText(Integer.toString(cost[4]));
        e23.setMinimumSize(new java.awt.Dimension(25, 25));
        e23.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e23);
        e23.setBounds(420, 170, 25, 25);

        e25.setEditable(false);
        e25.setText(Integer.toString(cost[2]));
        e25.setMinimumSize(new java.awt.Dimension(25, 25));
        e25.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e25);
        e25.setBounds(310, 240, 25, 25);

        e78.setEditable(false);
        e78.setText(Integer.toString(cost[6]));
        e78.setMinimumSize(new java.awt.Dimension(25, 25));
        e78.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e78);
        e78.setBounds(420, 400, 25, 25);

        e56.setEditable(false);
        e56.setText(Integer.toString(cost[5]));
        e56.setMinimumSize(new java.awt.Dimension(25, 25));
        e56.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e56);
        e56.setBounds(420, 280, 25, 30);

        e63.setEditable(false);
        e63.setText(Integer.toString(cost[7]));
        e63.setMinimumSize(new java.awt.Dimension(25, 25));
        e63.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e63);
        e63.setBounds(540, 240, 25, 25);

        e68.setEditable(false);
        e68.setText(Integer.toString(cost[8]));
        e68.setMinimumSize(new java.awt.Dimension(25, 25));
        e68.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e68);
        e68.setBounds(540, 360, 25, 25);

        e84.setEditable(false);
        e84.setText(Integer.toString(cost[10]));
        e84.setMinimumSize(new java.awt.Dimension(25, 25));
        e84.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e84);
        e84.setBounds(660, 370, 25, 25);

        e34.setEditable(false);
        e34.setText(Integer.toString(cost[9]));
        e34.setMinimumSize(new java.awt.Dimension(25, 25));
        e34.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e34);
        e34.setBounds(650, 210, 25, 25);

        e57.setEditable(false);
        e57.setText(Integer.toString(cost[3]));
        e57.setMinimumSize(new java.awt.Dimension(25, 25));
        e57.setPreferredSize(new java.awt.Dimension(25, 25));
        getContentPane().add(e57);
        e57.setBounds(300, 370, 25, 25);

        backgroundImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/contacteditor/pic3.png"))); // NOI18N
        backgroundImg.setToolTipText("");
        getContentPane().add(backgroundImg);
        backgroundImg.setBounds(0, 0, 926, 493);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runRedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runRedButtonActionPerformed
        AC = new AnimationClass();
        message1.moved = true;
        if (message1.getLocation().getX() == 20 && message1.getLocation().getY() == 70) { // Red has passed through layers
            AC.jLabelYDown(70, one.lhs.y, 10, 10, message1);
        } else if (message1.getLocation().getX() == 20 && message1.getLocation().getY() == 310) { // Red has reched H1
            shortcut.remove(0);
            AC.jLabelXRight(20, one.lhs.x, 10, 10, message1);
        } else if (message1.getLocation().getX() == one.lhs.x && message1.getLocation().getY() == one.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r2")) {
            shortcut.remove(0);
            AC.jLabelXRight(message1.getLocation().x, one.rhs.x, delayCalc(cost[0]), 5, message1);
            AC.jLabelYUp(message1.getLocation().y, one.rhs.y, delayCalc(cost[0]), 5, message1);
        } else if (message1.getLocation().getX() == one.lhs.x && message1.getLocation().getY() == one.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r7")) {
            shortcut.remove(0);
            AC.jLabelYDown(message1.getLocation().y, two.rhs.y, delayCalc(cost[1]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, two.rhs.x, delayCalc(cost[1]), 5, message1);
        } else if (message1.getLocation().getX() == three.lhs.x && message1.getLocation().getY() == three.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r5")) {
            shortcut.remove(0);
            AC.jLabelYDown(message1.getLocation().y, three.rhs.y, delayCalc(cost[2]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, three.rhs.x, delayCalc(cost[2]), 5, message1);
        } else if (message1.getLocation().getX() == four.lhs.x && message1.getLocation().getY() == four.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r5")) {
            shortcut.remove(0);
            AC.jLabelYUp(message1.getLocation().y, four.rhs.y, delayCalc(cost[3]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, four.rhs.x, delayCalc(cost[3]), 5, message1);
        } else if (message1.getLocation().getX() == four.rhs.x && message1.getLocation().getY() == four.rhs.y && shortcut.get(0).toString().equalsIgnoreCase("r7")) {
            shortcut.remove(0);
            AC.jLabelYDown(message1.getLocation().y, four.lhs.y, delayCalc(cost[3]), 5, message1);
            AC.jLabelXLeft(message1.getLocation().x, four.lhs.x, delayCalc(cost[3]), 5, message1);
        } else if (message1.getLocation().getX() == five.lhs.x && message1.getLocation().getY() == five.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r3")) {
            shortcut.remove(0);
            AC.jLabelXRight(message1.getLocation().x, five.rhs.x, delayCalc(cost[4]), 5, message1);
        } else if (message1.getLocation().x == six.lhs.x && message1.getLocation().y == six.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r6")) {
            shortcut.remove(0);
            AC.jLabelYUp(message1.getLocation().y, six.rhs.y, delayCalc(cost[5]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, six.rhs.x, delayCalc(cost[5]), 5, message1);
        } else if (message1.getLocation().getX() == seven.lhs.x && message1.getLocation().getY() == seven.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r8")) {
            shortcut.remove(0);
            AC.jLabelXRight(message1.getLocation().x, seven.rhs.x, delayCalc(cost[6]), 5, message1);
        } else if (message1.getLocation().getX() == eight.lhs.x && message1.getLocation().getY() == eight.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r3")) {
            shortcut.remove(0);
            AC.jLabelYUp(message1.getLocation().y, eight.rhs.y, delayCalc(cost[7]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, eight.rhs.x, delayCalc(cost[7]), 5, message1);
        } else if (message1.getLocation().getX() == nine.lhs.x && message1.getLocation().getY() == nine.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r8")) {
            shortcut.remove(0);
            AC.jLabelYDown(message1.getLocation().y, nine.rhs.y, delayCalc(cost[8]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, nine.rhs.x, delayCalc(cost[8]), 5, message1);
        } else if (message1.getLocation().getX() == nine.rhs.x && message1.getLocation().getY() == nine.rhs.y && shortcut.get(0).toString().equalsIgnoreCase("r6")) {
            shortcut.remove(0);
            AC.jLabelYUp(message1.getLocation().y, nine.lhs.y, delayCalc(cost[8]), 5, message1);
            AC.jLabelXLeft(message1.getLocation().x, nine.lhs.x, delayCalc(cost[8]), 5, message1);
        } else if (message1.getLocation().getX() == ten.lhs.x && message1.getLocation().getY() == ten.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r4")) {
            shortcut.remove(0);
            AC.jLabelYDown(message1.getLocation().y, ten.rhs.y, delayCalc(cost[9]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, ten.rhs.x, delayCalc(cost[9]), 5, message1);
        } else if (message1.getLocation().getX() == eleven.lhs.x && message1.getLocation().getY() == eleven.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r4")) {
            shortcut.remove(0);
            AC.jLabelYUp(message1.getLocation().y, eleven.rhs.y, delayCalc(cost[10]), 5, message1);
            AC.jLabelXRight(message1.getLocation().x, eleven.rhs.x, delayCalc(cost[10]), 5, message1);
        } else if (message1.getLocation().equals(new Point(680, 310))) {
            AC.jLabelXRight(message1.getLocation().x, 830, 10, 10, message1);
        } else if (message1.getLocation().equals(new Point(830, 310))) {
            AC.jLabelYUp(message1.getLocation().y, 70, 10, 10, message1);
            message1.madeit = true;
        } else {
            System.out.println("OMG! Error! Error!");
        }
        System.out.println("Red location = " + message1.getLocation().toString());
        System.out.println(shortcut);


    }//GEN-LAST:event_runRedButtonActionPerformed

    private void runBlueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runBlueButtonActionPerformed
        AC = new AnimationClass();
        if (message2.getLocation().getX() == 190 && message2.getLocation().getY() == 70) { // Red has passed through layers
            AC.jLabelYDown(70, one.lhs.y, 10, 10, message2);
            message2.moved = true;
        } else if (message2.getLocation().getX() == 190 && message2.getLocation().getY() == 310) { // Red has reched H1
            shortcut.remove(0);
            AC.jLabelXRight(message2.getLocation().x, one.lhs.x, delayCalc(cost[0]), 10, message2);
        } else if (message2.getLocation().getX() == one.lhs.x && message2.getLocation().getY() == one.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r2")) {
            shortcut.remove(0);
            AC.jLabelXRight(message2.getLocation().x, one.rhs.x, delayCalc(cost[0]), 10, message2);
            AC.jLabelYUp(message2.getLocation().y, one.rhs.y, 10, 10, message2);
        } else if (message2.getLocation().getX() == one.lhs.x && message2.getLocation().getY() == one.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r7")) {
            shortcut.remove(0);
            AC.jLabelYDown(message2.getLocation().y, two.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, two.rhs.x, delayCalc(cost[1]), 10, message2);
        } else if (message2.getLocation().getX() == three.lhs.x && message2.getLocation().getY() == three.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r5")) {
            shortcut.remove(0);
            AC.jLabelYDown(message2.getLocation().y, three.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, three.rhs.x, delayCalc(cost[2]), 10, message2);
        } else if (message2.getLocation().getX() == four.lhs.x && message2.getLocation().getY() == four.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r5")) {
            shortcut.remove(0);
            AC.jLabelYUp(message2.getLocation().y, four.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, four.rhs.x, delayCalc(cost[3]), 10, message2);
        } else if (message2.getLocation().getX() == four.rhs.x && message2.getLocation().getY() == four.rhs.y && shortcut.get(0).toString().equalsIgnoreCase("r7")) {
            shortcut.remove(0);
            AC.jLabelYDown(message2.getLocation().y, four.lhs.y, 10, 10, message2);
            AC.jLabelXLeft(message2.getLocation().x, four.lhs.x, delayCalc(cost[3]), 10, message2);
        } else if (message2.getLocation().getX() == five.lhs.x && message2.getLocation().getY() == five.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r3")) {
            shortcut.remove(0);
            AC.jLabelXRight(message2.getLocation().x, five.rhs.x, delayCalc(cost[4]), 10, message2);
        } else if (message2.getLocation().x == six.lhs.x && message2.getLocation().y == six.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r6")) {
            shortcut.remove(0);
            AC.jLabelYUp(message2.getLocation().y, six.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, six.rhs.x, delayCalc(cost[5]), 10, message2);
        } else if (message2.getLocation().getX() == seven.lhs.x && message2.getLocation().getY() == seven.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r8")) {
            shortcut.remove(0);
            AC.jLabelXRight(message2.getLocation().x, seven.rhs.x, delayCalc(cost[6]), 10, message2);
        } else if (message2.getLocation().getX() == eight.lhs.x && message2.getLocation().getY() == eight.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r3")) {
            shortcut.remove(0);
            AC.jLabelYUp(message2.getLocation().y, eight.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, eight.rhs.x, delayCalc(cost[7]), 10, message2);
        } else if (message2.getLocation().getX() == nine.lhs.x && message2.getLocation().getY() == nine.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r8")) {
            shortcut.remove(0);
            AC.jLabelYDown(message2.getLocation().y, nine.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, nine.rhs.x, delayCalc(cost[8]), 10, message2);
        } else if (message2.getLocation().getX() == nine.rhs.x && message2.getLocation().getY() == nine.rhs.y && shortcut.get(0).toString().equalsIgnoreCase("r6")) {
            shortcut.remove(0);
            AC.jLabelYUp(message2.getLocation().y, nine.lhs.y, 10, 10, message2);
            AC.jLabelXLeft(message2.getLocation().x, nine.lhs.x, delayCalc(cost[8]), 10, message2);
        } else if (message2.getLocation().getX() == ten.lhs.x && message2.getLocation().getY() == ten.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r4")) {
            shortcut.remove(0);
            AC.jLabelYDown(message2.getLocation().y, ten.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, ten.rhs.x, delayCalc(cost[9]), 10, message2);
        } else if (message2.getLocation().getX() == eleven.lhs.x && message2.getLocation().getY() == eleven.lhs.y && shortcut.get(0).toString().equalsIgnoreCase("r4")) {
            shortcut.remove(0);
            AC.jLabelYUp(message2.getLocation().y, eleven.rhs.y, 10, 10, message2);
            AC.jLabelXRight(message2.getLocation().x, eleven.rhs.x, delayCalc(cost[10]), 10, message2);
        } else if (message2.getLocation().equals(new Point(680, 310))) {
            AC.jLabelXRight(message2.getLocation().x, 830, 10, 10, message2);
        } else if (message2.getLocation().equals(new Point(830, 310))) {
            AC.jLabelYUp(message2.getLocation().y, 70, 10, 10, message2);
            message2.madeit = true;
        } else {
            System.out.println("OMG! Error! Error!");
        }
        System.out.println("Blue location = " + message2.getLocation().toString());
        System.out.println(shortcut);


    }//GEN-LAST:event_runBlueButtonActionPerformed

    private void resetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseReleased
        // TODO add your handling code here:
        message1.setLocation(20, 70);
        message2.setLocation(190, 70);
        graphMath();
        edgeRefresh();
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_resetButtonMouseReleased

    private void appLayerH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appLayerH1ActionPerformed
        // TODO add your handling code here:
        if (message1.moved) {
            
        }
        if (message2.moved) {
            
        }
    }//GEN-LAST:event_appLayerH1ActionPerformed

    private void dlLayerH1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dlLayerH1MouseReleased
        if (message1.moved) {
            initAndShowGUI();
        }

    }//GEN-LAST:event_dlLayerH1MouseReleased

    private void appLayerH1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appLayerH1MouseReleased
        if (message1.moved) {
            JOptionPane.showMessageDialog(null, "Message 1: " + message1.text);
        }
        if (message2.moved) {
            JOptionPane.showMessageDialog(null, "Message 2: " + message2.text);
        }
    }//GEN-LAST:event_appLayerH1MouseReleased

    private void appLayerH2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appLayerH2MouseReleased
        if (message1.moved) {
            JOptionPane.showMessageDialog(null, "Message 1: " + message1.text);
        }
        if (message2.moved) {
            JOptionPane.showMessageDialog(null, "Message 2: " + message2.text);
        }
    }//GEN-LAST:event_appLayerH2MouseReleased

    private void transLayerH1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transLayerH1MouseReleased
        if (message1.moved) {
            String[] columnNames = {"Header1", "Data"};
        Object[][] data = {{"H4", message1.text}};

        JTable j = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(null, new JScrollPane(j));
        }
        if (message2.moved) {
            String[] columnNames = {"Header2", "Data"};
        Object[][] data = {{"H4", message2.text}};

        JTable j = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(null, new JScrollPane(j));
        }
        
    }//GEN-LAST:event_transLayerH1MouseReleased

    private void netLayerH1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_netLayerH1MouseReleased
        if (message1.moved) {
            String[] columnNames = {"Header1", "Data"};
        Object[][] data = {{"H4", message1.text}};

        JTable j = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(null, new JScrollPane(j));
        }
        if (message2.moved) {
            String[] columnNames = {"Header2", "Data"};
        Object[][] data = {{"H4", message2.text}};

        JTable j = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(null, new JScrollPane(j));
        }
    }//GEN-LAST:event_netLayerH1MouseReleased

    private void physLayerH1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_physLayerH1MouseReleased
        // TODO add your handling code here:
        if (message1.moved) {
            
        }
        if (message2.moved) {
            
        }
    }//GEN-LAST:event_physLayerH1MouseReleased

    private void physLayerH2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_physLayerH2MouseReleased
        if (message1.madeit) {
            
        }
        if (message2.madeit) {
            
        }
    }//GEN-LAST:event_physLayerH2MouseReleased

    private void dlLayerH2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dlLayerH2MouseReleased
        // TODO add your handling code here:
        if (message1.madeit) {
            
        }
        if (message2.madeit) {
            
        }
    }//GEN-LAST:event_dlLayerH2MouseReleased

    private void netLayerH2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_netLayerH2MouseReleased
        // TODO add your handling code here:
        if (message1.madeit) {
            
        }
        if (message2.madeit) {
            
        }
    }//GEN-LAST:event_netLayerH2MouseReleased

    private void transLayerH2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transLayerH2MouseReleased
        // TODO add your handling code here:
        if (message1.madeit) {
            
        }
        if (message2.madeit) {
            
        }
    }//GEN-LAST:event_transLayerH2MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactEditorUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appLayerH1;
    private javax.swing.JButton appLayerH2;
    private javax.swing.JLabel backgroundImg;
    private javax.swing.JButton dlLayerH1;
    private javax.swing.JButton dlLayerH2;
    private javax.swing.JTextField e12;
    private javax.swing.JTextField e17;
    private javax.swing.JTextField e23;
    private javax.swing.JTextField e25;
    private javax.swing.JTextField e34;
    private javax.swing.JTextField e56;
    private javax.swing.JTextField e57;
    private javax.swing.JTextField e63;
    private javax.swing.JTextField e68;
    private javax.swing.JTextField e78;
    private javax.swing.JTextField e84;
    private my.contacteditor.Message message1;
    private my.contacteditor.Message message2;
    private javax.swing.JButton netLayerH1;
    private javax.swing.JButton netLayerH2;
    private javax.swing.JButton physLayerH1;
    private javax.swing.JButton physLayerH2;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton runBlueButton;
    private javax.swing.JButton runRedButton;
    private javax.swing.JButton transLayerH1;
    private javax.swing.JButton transLayerH2;
    // End of variables declaration//GEN-END:variables

}
