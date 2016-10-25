package aufgabe1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class Fenster extends JFrame implements ActionListener {
	JMenuBar menubar;
	JMenu files;
	JMenuItem bSave, bLoad;
	BasicVisualizationServer<Knoten,Kante> vv;
	public Fenster() {
		
		setTitle("Graphen");
		
		menubar = new JMenuBar();
		files = new JMenu ("files");
		bSave = new JMenuItem("save");
		bSave.addActionListener(this);
		bLoad = new JMenuItem("load");
		bLoad.addActionListener(this);
		
		files.add(bSave);
		files.add(bLoad);
		menubar.add(files);
		
		Layout<Knoten,Kante> layout = new CircleLayout<Knoten,Kante>(new OGraph());
	    vv =  new BasicVisualizationServer<Knoten,Kante>(layout);
	    vv.setPreferredSize(new Dimension(350,350));
		setContentPane(vv);
		
		setJMenuBar(menubar);
		setSize (800,600);
		pack();
	    setVisible (true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bLoad) {
			JFileChooser lade;

			lade = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter (null, "gka");
	        lade.setFileFilter(filter);
	        lade.setDialogTitle("Lade Graph");
	        lade.setVisible(true); 
	        lade.showOpenDialog(this); 
	        if(lade.getSelectedFile()!=null) {
	        	OGraph graph;
	        	try {
					graph = SaveLoad.load(lade.getSelectedFile());
					Layout<Knoten,Kante> layout = new CircleLayout<Knoten,Kante>(graph);
				    vv =  new BasicVisualizationServer<Knoten,Kante>(layout);
				    vv.setPreferredSize(new Dimension(350,350));
					setContentPane(vv);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
		}
		
	}
}