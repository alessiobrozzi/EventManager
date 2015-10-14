/* 
 * This class provides methods for editing objects of class Picture
 */

import java.awt.Color;

public class ImageEditor1 {
	
	public static double luminance(Color color)	{
		double red = color.getRed();
		double green = color.getGreen();
		double blue = color.getBlue();
		
		return (0.299*red + 0.587*green + 0.114*blue);
	}
	public static Color toGrey(Color color)	{
		double l = luminance(color);
		int int_l = (int) l;
		Color new_color = new Color(int_l, int_l, int_l);
		return new_color;
	}
	public static Picture makeGreyscale(Picture pic)	{
		int height = pic.height();
		int width = pic.width();
		for (int i = 0; i < height; i++)	{
			for (int e = 0; e < width; e++)		{
				Color color = pic.get(e, i);
				color = toGrey(color);
				pic.set(e, i, color);
			}
		}
		return pic;
	}

}
