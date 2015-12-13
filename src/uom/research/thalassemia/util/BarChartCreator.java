/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author hansa
 */
public final class BarChartCreator {

    private String title = "";
    private CategoryDataset categoryDataset = null;

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @param pCategoryDataset dataset
     * @param pTitle title
     * @return A panel.
     */
    public JPanel createPanel(final CategoryDataset pCategoryDataset,
            final String pTitle) {
        title = pTitle;
        categoryDataset = pCategoryDataset;
        JFreeChart chart = createChart(categoryDataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(final CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Performance: JFreeSVG vs Batik", null /* x-axis label*/,
                "Milliseconds" /* y-axis label */, dataset);
        chart.addSubtitle(new TextTitle("Time to generate 1000 charts in SVG "
                + "format (lower bars = better performance)"));
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }
}
