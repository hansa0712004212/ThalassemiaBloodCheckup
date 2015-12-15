/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

/**
 *
 * @author anupama
 */
public final class ActualValueTransformer {

    /**
     * Hemoglobin Value.
     */
    private static final double HB = 1;

    /**
     * Red Blood Cell Thickness.
     *
     * private static final double RED_CELL_THICKNESS = 2 * Math.pow(10, -6);
     */
    /**
     * get Actual Red Blood Cell count.
     *
     * @param imageBloodCount blood count
     * @return red blood cell count
     */
    public double getActualRBCCount(final int imageBloodCount) {
        //return imageBloodCount * 0.051 * Math.pow(10, 6);
        double trueCount = (0.6626 * imageBloodCount) + 33.1227;
        return (53179.3769 * trueCount) - 175139.6264;
    }

    /**
     * Mean Corpuscular Volume count.
     *
     * @param redBloodCellArea redBloodCellArea
     * @return mean corpuscular volume
     */
    public double getMCVCount(final double redBloodCellArea) {
        //return (HB / redBloodCellArea * RED_CELL_THICKNESS) * 35.10;
        return (HB * (-0.5814)) + 91.5582;
    }

    /**
     * get RDW count.
     *
     * @param redBloodCellArea redBloodCellArea
     * @return mean corpuscular volume
     */
    public double getRDWCount(final double redBloodCellArea) {
        //return (HB / redBloodCellArea * RED_CELL_THICKNESS);
        return (-0.2713) * getMCVCount(redBloodCellArea) + 37.6378;
    }

}
