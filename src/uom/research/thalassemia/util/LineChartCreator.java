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

    /**
     * Create a Line Chart.
     *
     * @param dataSet data set
     * @param chartTitle chart title
     * @param subTitle sub title
     * @param xTitle x axis title
     * @param yTitle y axis title
     * @return
     */
    public JPanel createPanel(final DefaultCategoryDataset dataSet,
            final String chartTitle, final String subTitle, final String xTitle,
            final String yTitle) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                xTitle, yTitle,
                dataSet,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        return chartPanel;
    }

}
