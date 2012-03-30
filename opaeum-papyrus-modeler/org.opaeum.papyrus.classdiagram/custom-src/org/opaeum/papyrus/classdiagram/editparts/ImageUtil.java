package org.opaeum.papyrus.classdiagram.editparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLDiagramEditorPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ImageUtil{
	private static class Fader{
		private static Image original;
		private int width;
		private int height;
		void apply(Image image){
			original = new Image(Display.getCurrent(), image, SWT.IMAGE_COPY);
			width = original.getBounds().width;
			height = original.getBounds().height;
			for(int y = 0;y < height;y++){
				for(int x = 0;x < width;x++){
					// image.getImageData().setPixel(ImageUtilities. x, y, smooth(x, y).getRGB().);
				}
			}
		}
		/**
		 * Return a new color that is the smoothed color of a given position. The "smoothed color" is the color value that is the average of
		 * this pixel and all the adjacent pixels.
		 */
		private Color smooth(int xpos,int ypos){
			List<Color> pixels = new ArrayList<Color>(9);
			for(int y = ypos - 1;y <= ypos + 1;y++){
				for(int x = xpos - 1;x <= xpos + 1;x++){
					if(x >= 0 && x < width && y >= 0 && y < height){
						ImageData imageData = original.getImageData();
						// pixels.add(new RGB(imageData.getPixel(x, y)));
					}
				}
			}
			return null;
			// return new Color(Display.getCurrent(), avgRed(pixels), avgGreen(pixels), avgBlue(pixels)).getRGB());
		}
		/**
		 * Return the average of all the red values in the given list of pixels.
		 */
		private int avgRed(List<Color> pixels){
			int total = 0;
			for(Iterator it = pixels.iterator();it.hasNext();){
				total += ((Color) it.next()).getRed();
			}
			return total / pixels.size();
		}
		/**
		 * Return the average of all the green values in the given list of pixels.
		 */
		private int avgGreen(List<Color> pixels){
			int total = 0;
			for(Iterator it = pixels.iterator();it.hasNext();){
				total += ((Color) it.next()).getGreen();
			}
			return total / pixels.size();
		}
		/**
		 * Return the average of all the blue values in the given list of pixels.
		 */
		private int avgBlue(List<Color> pixels){
			int total = 0;
			for(Iterator it = pixels.iterator();it.hasNext();){
				total += ((Color) it.next()).getBlue();
			}
			return total / pixels.size();
		}
	}
	public static void paintBackgroundImage(Graphics graphics,Figure f,String imagePath){
		if(imagePath != null){
			Image img = UMLDiagramEditorPlugin.getInstance().getBundledImage(imagePath);
			if(img != null){
				double amount = 0.6 * f.getBounds().height / (double) img.getBounds().height;
				graphics.scale(amount);
				int actualImageWidth = (int) (img.getBounds().width * amount);
				int actualImageHeight = (int) (img.getBounds().height * amount);
				int x = f.getLocation().x + ((f.getBounds().width - actualImageWidth) / 2);
				int y = f.getLocation().y + (f.getBounds().height - actualImageHeight);
				Point location = new Point(x, y);
				int brightest = 255;
				// Pattern pt = new Pattern(Display.getCurrent(), location.x, location.y, location.x + actualImageWidth, location.y
				// + actualImageHeight, ColorConstants.white, ColorConstants.lightGray);
				// graphics.setBackgroundPattern(pt);
//				img = new Image(Display.getCurrent(), createShadedImage(img, new Color(Display.getCurrent(), brightest, brightest,
//						brightest)));
				graphics.drawImage(img, location.scale(1d / amount));
				graphics.setBackgroundPattern(null);
				// pt.dispose();
				graphics.scale(1d / amount);
			}
		}
	}
	public static ImageData createShadedImage(Image fromImage, Color shade) {
		org.eclipse.swt.graphics.Rectangle r = fromImage.getBounds();
		ImageData data = fromImage.getImageData();
		PaletteData palette = data.palette;
		if (!palette.isDirect) {
			/* Convert the palette entries */
			RGB[] rgbs = palette.getRGBs();
			for (int i = 0; i < rgbs.length; i++) {
				if (data.transparentPixel != i) {
					RGB color = rgbs[i];
					color.red = determineShading(color.red, shade.getRed());
					color.blue = determineShading(color.blue, shade.getBlue());
					color.green = determineShading(color.green,
							shade.getGreen());
				}
			}
			data.palette = new PaletteData(rgbs);
		} else {
			/* Convert the pixels. */
			int[] scanline = new int[r.width];
			int redMask = palette.redMask;
			int greenMask = palette.greenMask;
			int blueMask = palette.blueMask;
			int redShift = palette.redShift;
			int greenShift = palette.greenShift;
			int blueShift = palette.blueShift;
			for (int y = 0; y < r.height; y++) {
				data.getPixels(0, y, r.width, scanline, 0);
				for (int x = 0; x < r.width; x++) {
					int pixel = scanline[x];
					int red = pixel & redMask;
					red = (redShift < 0) ? red >>> -redShift : red << redShift;
					int green = pixel & greenMask;
					green = (greenShift < 0) ? green >>> -greenShift
							: green << greenShift;
					int blue = pixel & blueMask;
					blue = (blueShift < 0) ? blue >>> -blueShift
							: blue << blueShift;
					red = determineShading(red, shade.getRed());
					blue = determineShading(blue, shade.getBlue());
					green = determineShading(green, shade.getGreen());
					red = (redShift < 0) ? red << -redShift : red >> redShift;
					red &= redMask;
					green = (greenShift < 0) ? green << -greenShift
							: green >> greenShift;
					green &= greenMask;
					blue = (blueShift < 0) ? blue << -blueShift
							: blue >> blueShift;
					blue &= blueMask;
					scanline[x] = red | blue | green;
				}
				data.setPixels(0, y, r.width, scanline, 0);
			}
		}
		return data;
	}

	private static int determineShading(int origColor, int shadeColor) {
		return Math.min(255,15+(origColor + shadeColor)/2);
	}

}