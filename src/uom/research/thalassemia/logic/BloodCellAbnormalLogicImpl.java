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
 * @author hansa
 */
public final class BloodCellAbnormalLogicImpl {

    public Map<String, Integer> classes = new HashMap<>();
    private double sgf, dv, diameter, cpArea, ap;
    private boolean tf;

    public BloodCellAbnormalLogicImpl(double psgf, double pdv, double pdiameter,
            double pcpArea, double pap, boolean ptf) {
        sgf = psgf;
        dv = pdv;
        diameter = pdiameter;
        cpArea = pcpArea;
        ap = pap;
        tf = ptf;
    }

    private void fillClasses(final String key) {
        if (classes.containsKey(key)) {
            int x = classes.get(key);
            classes.replace(key, x, x++);
        } else {
            classes.put(key, 1);
        }
    }

    public Map<String, Integer> getAbnormalCellTypes() {
        if (sgf > 1.2) {
            //sgf true
            if (dv > 0.2) {
                //sgf true and dv true
                System.out.println("class 12");
                fillClasses("Class 12");
            } else {
                //sgf true and dv false
                System.out.println("class 5");
            }
        } else {
            //sgf false
            if ((6.5 < diameter) & (diameter < 8.5)) {
                //diameter true
                if (cpArea > 0) {
                    //cpArea true
                    if (tf == true) {
                        //tf true

                        //diameter true and cpArea true and tf true
                        System.out.println("class 6");
                    } else {
                        //tf false

                        //diameter true and cpArea true and tf false
                        if (ap > 0.2) {
                            //ap true
                            System.out.println("class 9");
                        } else {
                            //ap false
                            System.out.println("class 1");
                        }
                    }
                } else {
                    //cp area false
                    System.out.println("class 2");
                }
            } else {
                //diameter false
                if (dv > 8.5) {
                    //dv true
                    if (cpArea > 0) {
                        //cpArea true
                        if (ap > 0.2) {
                            //ap true
                            System.out.println("class 8");
                        } else {
                            //ap false
                            System.out.println("class 9");
                        }
                    } else {
                        //cpArea false
                        System.out.println("class 3");

                    }
                } else {
                    //dv false
                    if (cpArea > 0) {
                        //cpArea true
                        if (ap > 0.2) {
                            System.out.println("class 7");
                        } else {
                            //cpArea false
                            System.out.println("class 10");
                        }
                    } else {
                        //cpArea false
                        System.out.println("class 4");
                    }
                }
            }
        }
        return classes;
    }

}
