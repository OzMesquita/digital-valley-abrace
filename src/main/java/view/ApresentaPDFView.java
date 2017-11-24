package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class ApresentaPDFView {
	public static void main(String diretorio) {
	
		String filePath = diretorio;
	
	    // build a component controller
	    SwingController controller = new SwingController();
	
	    SwingViewBuilder factory = new SwingViewBuilder(controller);
	
	    JPanel viewerComponentPanel = factory.buildViewerPanel();
	
	    // add interactive mouse link annotation support via callback
	    controller.getDocumentViewController().setAnnotationCallback(new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
	
	    JFrame applicationFrame = new JFrame();
	    applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    applicationFrame.getContentPane().add(viewerComponentPanel);
	
	    // Now that the GUI is all in place, we can try openning a PDF
	    controller.openDocument(filePath);
	
	    // show the component
	    applicationFrame.pack();
	    applicationFrame.setVisible(true);
	    applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    applicationFrame.setLocationRelativeTo(null);
	}
}
