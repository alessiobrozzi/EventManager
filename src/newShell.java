import java.awt.Component;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;


public class newShell {
	private static Shell shell;
	
	public static void open()	{
		shell.open();
	}
	public newShell() {
		Display display = Display.getCurrent();
		shell = new Shell(display);
		shell.setSize(801, 520);
		
		String fileName = "/Users/ProjectSoft/Downloads/image.png";
		Image image = new Image(display, fileName);
		Rectangle bounds = image.getBounds();
		int width = bounds.width;
		int height = bounds.height;
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(60, 29, 60 + width, 29 + height);
		lblNewLabel.setText("New Label");
		lblNewLabel.setImage(image);
		
		Picture pic = new Picture(fileName);
		Picture grey = ImageEditor1.makeGreyscale(pic);
		ImageData id = Utilities.convertToSWT(grey.getImage());
		
	}
}
