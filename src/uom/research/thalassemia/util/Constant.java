/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.util;

import java.io.File;

/**
 *
 * @author nadeera
 */
public final class Constant {

    /**
     * blood image files save location.
     */
    public static final String IMAGES_DB_FOLDER
            = System.getProperty("user.home") + File.separator
            + "bloodCellImagesDB" + File.separator;

    /**
     * profile image files save location.
     */
    public static final String PROFILES_DB_FOLDER
            = System.getProperty("user.home") + File.separator
            + "profileImagesDB" + File.separator;
}
