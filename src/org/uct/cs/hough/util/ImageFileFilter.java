package org.uct.cs.hough.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFileFilter extends FileFilter
{
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory()) return true;

        String extension = getExtension(f);
        return extension != null && (extension.equals(tiff) || extension.equals(tif) || extension.equals(gif) || extension.equals(jpeg) || extension.equals(jpg) || extension.equals(png));

    }

    public static String getExtension(File f)
    {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    @Override
    public String getDescription()
    {
        return "Image Files";
    }
}
