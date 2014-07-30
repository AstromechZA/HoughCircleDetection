package org.uct.cs.hough.pipes;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Greyscale
{
    public static ShortImageBuffer Convert(BufferedImage image, IFormula formula)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        ShortImageBuffer output = new ShortImageBuffer(h, w);
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        int index = 0;
        for(int y=0;y<h;y++)
        {
            for(int x=0;x<w;x++)
            {
                output.set(y, x, formula.combine(pixels[index+2], pixels[index+1], pixels[index]));
                index += 3;
            }
        }

        return output;
    }

    private static interface IFormula
    {
        public short combine(byte r, byte g, byte b);
    }

    public static class FormulaAverage implements IFormula
    {
        @Override
        public short combine(byte r, byte g, byte b)
        {
            return (short) (((r & 0xFF) + (g & 0xFF) + (b & 0xFF)) / 3);
        }
    }

    public static class FormulaLightness implements IFormula
    {
        @Override
        public short combine(byte r, byte g, byte b)
        {
            return (short)((
                Math.max(Math.max(r & 0xFF, g & 0xFF), b & 0xFF) +
                Math.min(Math.min(r & 0xFF, g & 0xFF), b & 0xFF)
            )/2);
        }
    }

    public static class FormulaLuminosity implements IFormula
    {
        @Override
        public short combine(byte r, byte g, byte b)
        {
            return (short)(
                0.21f * (r & 0xFF) +
                0.72f * (g & 0xFF) +
                0.07f * (b & 0xFF)
            );
        }
    }

}
