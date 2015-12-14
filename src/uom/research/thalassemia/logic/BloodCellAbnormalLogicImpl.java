/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.logic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anupama
 */
public final class BloodCellAbnormalLogicImpl {

    /**
     * keeps list of blood related classes.
     */
    private final Map<String, Integer> classes;

    /**
     * set of double values.
     */
    private final double sgf, dv, diameter, cpArea, ap;

    /**
     *
     */
    private final boolean tf;

    /**
     * Creates new form BloodCellAbnormalLogicImpl.
     *
     * @param psgf shape geometric factor
     * @param pdv deviation value
     * @param pdiameter diameter
     * @param pcpArea central pallor area
     * @param pap area proportion
     * @param ptf target flag
     */
    public BloodCellAbnormalLogicImpl(final double psgf, final double pdv,
            final double pdiameter, final double pcpArea, final double pap,
            final boolean ptf) {
        sgf = psgf;
        dv = pdv;
        diameter = pdiameter;
        cpArea = pcpArea;
        ap = pap;
        tf = ptf;
        classes = new HashMap<>();
    }

    /**
     * get cell type count.
     *
     * @param key key
     */
    private void fillClasses(final String key) {
        if (classes.containsKey(key)) {
            int x = classes.get(key);
            classes.replace(key, x, x++);
        } else {
            classes.put(key, 1);
        }
    }

    /**
     * identify abnormal cell types.
     *
     * @return cell type
     */
    public Map<String, Integer> getAbnormalCellTypes() {
        if (sgf > 1.2) {
            //sgf true
            if (dv > 0.2) {
                //dv true
                fillClasses("Class 12");
            } else {
                //dv false
                fillClasses("class 5");
            }
        } else //sgf false
        {
            if ((6.5 < diameter) & (diameter < 8.5)) {
                //diameter true
                if (cpArea > 0) {
                    //cpArea true
                    if (tf == true) {
                        //tf true
                        fillClasses("class 6");
                    } else //tf false
                    {
                        if (ap > 0.2) {
                            //ap true
                            fillClasses("class 9");
                        } else {
                            //ap false
                            fillClasses("class 1");
                        }
                    }
                } else {
                    //cp area false
                    fillClasses("class 2");
                }
            } else //diameter false
            {
                if (dv > 8.5) {
                    //dv true
                    if (cpArea > 0) {
                        //cpArea true
                        if (ap > 0.2) {
                            //ap true
                            fillClasses("class 8");
                        } else {
                            //ap false
                            fillClasses("class 9");
                        }
                    } else {
                        //cpArea false
                        fillClasses("class 3");
                    }
                } else //dv false
                {
                    if (cpArea > 0) {
                        //cpArea true
                        if (ap > 0.2) {
                            fillClasses("class 7");
                        } else {
                            //cpArea false
                            fillClasses("class 10");
                        }
                    } else {
                        //cpArea false
                        fillClasses("class 4");
                    }
                }
            }
        }
        return classes;
    }

}
