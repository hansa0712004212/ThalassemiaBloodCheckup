/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

/**
 *
 * @author hansa
 */
public final class ActualValueTransformer {

    
    private static final double HB = 1;

    private static final double RED_CELL_THICKNESS = 2 * Math.pow(10, -6);

    /**
     * get Actual Red Blood Cell count.
     *
     * @param imageBloodCount blood count
     * @return rbc count
     */
    public double getActualRBCCount(final int imageBloodCount) {
        return imageBloodCount * 0.051 * Math.pow(10, 6);
    }

    /**
     * MCV count.
     *
     * @param redBloodCellArea red blood cell area
     * @return rbc count
     */
    public double getMCVCount(final double redBloodCellArea) {
        return (HB / redBloodCellArea * RED_CELL_THICKNESS) * 35.10;
    }

}
