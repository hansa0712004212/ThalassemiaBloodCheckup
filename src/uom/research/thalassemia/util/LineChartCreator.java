/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author nadeera
 */
public final class LineChartCreator {

    public JPanel createPanel(DefaultCategoryDataset dataSet, String chartTitle) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Years", "Number of Schools",
                dataSet,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        return chartPanel;
    }

}
