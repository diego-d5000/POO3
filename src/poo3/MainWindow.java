/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author diego-d
 */
public class MainWindow extends JFrame {

    int[] elementsA;
    int[] elementsB;

    public MainWindow(int[] elements) {
        this.elementsA = elements.clone();
        this.elementsB = elements.clone();
        this.setName("Bubble Sort and Threads");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 500);
        drawViews();
    }

    private void drawViews() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        DefaultCategoryDataset categoryDatasetA = createDataset(elementsA);
        JFreeChart chartA = ChartFactory.createBarChart(
                "Proceso 1",
                "Elementos",
                "Numero",
                categoryDatasetA,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        ChartPanel chartPanelA = new ChartPanel(chartA);
        chartPanelA.setPreferredSize(new java.awt.Dimension(560, 367));

        JLabel timeALabel = new JLabel("00:00");

        Timer timerA = new Timer();

        BubbleSortMinToMaxThread processOneThread = new BubbleSortMinToMaxThread(elementsA);
        processOneThread.setOnSwapListener(new ThreadListener() {
            @Override
            public void onSwap(int indexA, int newValueA, int indexB, int newValueB) {
                categoryDatasetA.setValue(newValueA, "e", String.valueOf(indexA));
                categoryDatasetA.setValue(newValueB, "e", String.valueOf(indexB));
            }

            @Override
            public void onStart() {
                timerA.start();
                timeALabel.setText(sdf.format(timerA.getStartDate()));
            }

            @Override
            public void onFinish() {
                timerA.end();
                String labelText = timeALabel.getText();
                timeALabel.setText(
                        labelText
                        + " - "
                        + sdf.format(timerA.getEndDate())
                        + " : "
                        + String.valueOf(timerA.getDuration())
                        + " millis");
            }
        });

        JButton startAButton = new JButton("Comenzar Ord.");
        startAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processOneThread.start();
            }
        });

        leftPanel.add(chartPanelA);
        leftPanel.add(startAButton);
        leftPanel.add(timeALabel);

        DefaultCategoryDataset categoryDatasetB = createDataset(elementsB);
        JFreeChart chartB = ChartFactory.createBarChart(
                "Proceso 2",
                "Elementos",
                "Numero",
                categoryDatasetB,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        ChartPanel chartPanelB = new ChartPanel(chartB);
        chartPanelB.setPreferredSize(new java.awt.Dimension(560, 367));

        JLabel timeBLabel = new JLabel("00:00");

        Timer timerB = new Timer();

        BubbleSortMaxToMinThread processTwoThread = new BubbleSortMaxToMinThread(elementsB);
        processTwoThread.setOnSwapListener(new ThreadListener() {
            @Override
            public void onSwap(int indexA, int newValueA, int indexB, int newValueB) {
                categoryDatasetB.setValue(newValueA, "e", String.valueOf(indexA));
                categoryDatasetB.setValue(newValueB, "e", String.valueOf(indexB));
            }

            @Override
            public void onStart() {
                timerB.start();
                timeBLabel.setText(sdf.format(timerB.getStartDate()));
            }

            @Override
            public void onFinish() {
                timerB.end();
                String labelText = timeBLabel.getText();
                timeBLabel.setText(
                        labelText
                        + " - "
                        + sdf.format(timerB.getEndDate())
                        + " : "
                        + String.valueOf(timerB.getDuration())
                        + " millis");
                
            }
        });

        JButton startBButton = new JButton("Comenzar Ord.");
        startBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processTwoThread.start();
            }
        });

        rightPanel.add(chartPanelB);
        rightPanel.add(startBButton);
        rightPanel.add(timeBLabel);
        
        JButton bothButton = new JButton("Comenzar Ambos");
        bothButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processOneThread.start();
                processTwoThread.start();
            }
        });
        leftPanel.add(bothButton);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

    }

    private DefaultCategoryDataset createDataset(int[] datasetElements) {
        final String elementos = "ELEMENTOS";
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        for (int i = 0; i < datasetElements.length; i++) {
            dataset.addValue(datasetElements[i], "e", String.valueOf(i));
        }

        return dataset;
    }

}
