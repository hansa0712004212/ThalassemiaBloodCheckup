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

    /**
     * Chart Main, Header Title.
     */
    private String mainTitle = "";

    /**
     * Chart Sub Title.
     */
    private String subTitle = "";

    /**
     * Chart X Axis Title.
     */
    private String xTitle = "";

    /**
     * Chart Y Axis Title.
     */
    private String yTitle = "";

    /**
     * Chart Category Dataset.
     */
    private CategoryDataset categoryDataset = null;

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @param pCategoryDataset dataset
     * @param pTitle main title
     * @param psubTitle sub title
     * @param pxTitle x Axis Title
     * @param pyTitle y axis title
     * @return A panel.
     */
    public JPanel createPanel(final CategoryDataset pCategoryDataset,
            final String pTitle, final String psubTitle, final String pxTitle,
            final String pyTitle) {
        mainTitle = pTitle;
        subTitle = psubTitle;
        xTitle = pxTitle;
        yTitle = pyTitle;
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
    private JFreeChart createChart(final CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                mainTitle, xTitle, yTitle, dataset);
        chart.addSubtitle(new TextTitle(subTitle));
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
