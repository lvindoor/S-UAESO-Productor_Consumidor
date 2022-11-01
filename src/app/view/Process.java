package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import app.Main;
import app.model.Configuration;

public class Process extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel profiles;
	private BufferedImage imgProd;
	private JLabel producer;
	private JLabel prod;	
	private BufferedImage imgCon;
	private JLabel consumer;
	private JLabel con;
	private JPanel buffer;
	private DefaultTableModel model;
	private JTable table;
	private JPanel configuration;
	private JLabel qtyToProd;
	private JLabel qtyToCon;
	private JLabel delay;
	private JTextField answToProd;
	private JTextField answToCon;
	private JTextField answDelay;
	private JButton send;
	private JButton reset;
	
	public void create(Configuration conf) throws IOException {

		// General
		setTitle("Productor - Consumidor");
		ImageIcon img = new ImageIcon("images/logo.png");
		setIconImage(img.getImage());
		setBounds(0, 0, 600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Productor - Consumidor
		profiles = new JPanel();
		imgProd = ImageIO.read(new File("images/producer.png"));
		producer = new JLabel(new ImageIcon(imgProd));
		prod = new JLabel("Productor \n [O]");
		prod.setForeground(Color.gray);
		imgCon = ImageIO.read(new File("images/consumer.png"));
		consumer = new JLabel(new ImageIcon(imgCon));
		con = new JLabel("Consumidor \n [X]");
		con.setForeground(Color.orange);

		profiles.add(BorderLayout.LINE_END, prod);
		profiles.add(BorderLayout.EAST, producer);
		profiles.add(BorderLayout.WEST, consumer);
		profiles.add(BorderLayout.LINE_END, con);

		// Vista de Buffer
		buffer = new JPanel();
		buffer.setBounds(0, 0, getWidth() - 5, getHeight() - 5);
		setLocationRelativeTo(null);
		model = new DefaultTableModel(conf.getQuantityToProduce() / 5, 5);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				((JLabel) comp).setHorizontalAlignment(JLabel.CENTER);
				return comp;
			}
		};
		table.setRowHeight(30);
		buffer.add(table);

		// Creamos la entrada de configuración
		configuration = new JPanel(); // Contenedor de configuracion
		qtyToProd = new JLabel("Producir");
		qtyToCon = new JLabel("Consumir");
		delay = new JLabel("Velocidad");
		answToProd = new JTextField(5); // Acepta 5 caracteres
		answToCon = new JTextField(5); // Acepta 5 caracteres
		answDelay = new JTextField(5); // Acepta 5 caracteres

		send = new JButton("Comenzar");
		send.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	conf.setQuantityToProduce(Integer.parseInt(answToProd.getText()));
		    	conf.setQuantityToConsume(Integer.parseInt(answToCon.getText()));
		    	conf.setDelayTime(Integer.parseInt(answDelay.getText()));
		    	
		    	model = new DefaultTableModel(conf.getQuantityToProduce()/5, 5);
		    	table.setModel(model);

		    	Main.run(conf); // Arracamos el backend		    	
		    }
		});
		
		reset = new JButton("Reiniciar");
		reset.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String empty = "";
				answToProd.setText(empty);
				answToCon.setText(empty);
				answDelay.setText(empty);
		    }
		});
		
		configuration.add(qtyToProd); 
		configuration.add(answToProd);
		configuration.add(qtyToCon); 
		configuration.add(answToCon);
		configuration.add(delay); 
		configuration.add(answDelay);
		configuration.add(send);
		configuration.add(reset);

		// Agregar componenents a la ventana
		getContentPane().add(BorderLayout.NORTH, profiles);
		getContentPane().add(BorderLayout.CENTER, buffer);
		getContentPane().add(BorderLayout.SOUTH, configuration);

		// Mostrar ventana
		setVisible(true);

		// ESC para cerrar
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); 
        getRootPane().getActionMap().put("Cancel", new AbstractAction() { 

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
       });		
	}

	public void paintRow(Color color, int position) {
		
		int	x = position/5;
		int y = position%5;
		
		String fill = (color == Color.orange)? "O" : "X";
		
		table.setValueAt(fill, x, y);
	}

}
