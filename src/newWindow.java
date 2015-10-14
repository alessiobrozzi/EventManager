import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class newWindow {

	protected static Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			newWindow window = new newWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public static void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected static void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");

	}

}
