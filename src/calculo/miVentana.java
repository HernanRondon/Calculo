import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.text.NumberFormat;


public class miVentana extends JFrame implements ActionListener {
	//declarcion de variables
	private JLabel lcodigo;
	private JLabel lnombre;
	private JLabel ltipo;
	private JLabel lTotal;

	private JTextField txtcodigo;
	private JTextField txtnombre;
	private JComboBox<String> cbtipo;
	
	private JLabel lEncabezados;
	private JTextField txtid;
	private JTextField txtdescripcion;
	private JTextField txtcantidad;
	private JTextField txtvunit;
	private JTextField txtsubtotal;
	private JTextField txtTotal;


	private JLabel labelImagen;
	private JButton botonBuscar;
	private JButton botonAgregar;

	private JTextArea textarea;
	private JScrollPane scroll;

	private String producto1[] = {"100","Portatil HP", "453200"};
	private String producto2[] = {"101", "Teclado HP", "68700"};
	private String producto3[] = {"102", "Mouse Microsoft", "43590"};

	private int totalFactura = 0;


	//Constructor
	public miVentana(){

		//Configuracion de la ventana
		setLayout(null);
		setBounds(500, 200, 600, 400);
		setTitle("Dashboard");
		
		//para terminar toda la aplicacion
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//para ocultar sola la ventana que cerramos
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		//Agregar los componentes
		
		lcodigo = new JLabel("Codigo: ");
		lcodigo.setBounds(10, 5, 150, 20);
		add(lcodigo);

		lnombre = new JLabel("Nombre: ");
		lnombre.setBounds(10, 25, 150, 20);
		add(lnombre);

		ltipo = new JLabel("Tipo Venta: ");
		ltipo.setBounds(10, 45, 200, 20);
		add(ltipo);

		lTotal = new JLabel("Total a pagar");
		lTotal.setBounds(340,300,100,20);
		add(lTotal);


		//Caja boton
		txtcodigo = new JTextField();
		txtcodigo.setBounds(100, 5, 100, 20);
		add(txtcodigo);

		txtnombre = new JTextField();
		txtnombre.setBounds(100, 25, 250, 20);
		add(txtnombre);

		//componente combo box o lista despegable
		cbtipo = new JComboBox<String>();
		cbtipo.addItem("");
		cbtipo.addItem("Debito");
		cbtipo.addItem("Credito");
		cbtipo.setBounds(100, 45, 100, 20);
		add(cbtipo);

		lEncabezados = new JLabel("Id                            Descripcion                                         Cantidad     Valor Unitario      SubTotal");
		lEncabezados.setBounds(10,68,500,20);
		add(lEncabezados);

		txtid = new JTextField();
		txtid.setBounds(10, 85, 25, 20);
		add(txtid);

		txtdescripcion = new JTextField();
		txtdescripcion.setBounds(62, 85, 230, 20);
		add(txtdescripcion);

		txtcantidad = new JTextField();
		txtcantidad.setBounds(294, 85, 50, 20);
		add(txtcantidad);

		txtvunit = new JTextField();
		txtvunit.setBounds(346, 85, 100, 20);
		add(txtvunit);

		txtsubtotal = new JTextField();
		txtsubtotal.setBounds(448, 85, 100, 20);
		add(txtsubtotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(448, 300, 100, 20);
		add(txtTotal);

		//Area de productos cargados
		textarea = new JTextArea();
		scroll = new JScrollPane(textarea);
		scroll.setBounds(10, 110, 538, 180);
		add(scroll);

		//cargar imagen
		Icon miImagen = new ImageIcon(getClass().getResource("mario (1).gif"));
		labelImagen = new JLabel("", miImagen, SwingConstants.LEFT);
		labelImagen.setBounds(500, 3, 80, 80);
		add(labelImagen);

		//Boton
		botonBuscar = new JButton("...");
		botonBuscar.setBounds(36, 85, 24, 19);
		add(botonBuscar);
		//Definir las acciones del boton
		botonBuscar.addActionListener(this);

		botonAgregar = new JButton("Add");
		botonAgregar.setBounds(550,85,30,19);
		add(botonAgregar);
		botonAgregar.addActionListener(this);
		

		//Hacer visible la ventana
		setVisible(true);	
}

	public void actionPerformed(ActionEvent e){

		NumberFormat fn = NumberFormat.getNumberInstance();

		if (e.getSource() == botonBuscar){

			txtcantidad.setText("");
			txtvunit.setText("");
			txtsubtotal.setText("");

			if (txtid.getText().equals("100")){
				txtdescripcion.setText(producto1[1]);
				txtvunit.setText(producto1[2]);
				

			}else if (txtid.getText().equals("101")){
				txtdescripcion.setText(producto2[1]);
				txtvunit.setText(producto2[2]);

			}else if (txtid.getText().equals("102")){
				txtdescripcion.setText(producto3[1]);
				txtvunit.setText(producto3[2]);	
			}else{
				txtdescripcion.setText("");
				txtvunit.setText("");
				JOptionPane.showMessageDialog(null, "vuleve a intentar");
			}

			txtcantidad.requestFocus();
			txtcantidad.setHorizontalAlignment(JTextField.CENTER);
			txtvunit.setHorizontalAlignment(JTextField.RIGHT);
		
		}else{
			if (e.getSource() == botonAgregar) {
				Calculo calculo = new Calculo();

				int subTotal = calculo.getSubTotal(Integer.parseInt(txtcantidad.getText()), Integer.parseInt(txtvunit.getText()));
				
				txtsubtotal.setText(String.valueOf(fn.format(subTotal)));
				txtsubtotal.setHorizontalAlignment(JTextField.RIGHT);

				//llenamos el area de texto
				textarea.append(txtid.getText() + "   " + txtdescripcion.getText() + "   " + txtcantidad.getText() + "   " + txtvunit.getText() + "    " + txtsubtotal.getText() + "\n");

				//total factura
				totalFactura += subTotal;
				txtTotal.setText(String.valueOf(fn.format(totalFactura)));
				txtTotal.setHorizontalAlignment(JTextField.RIGHT);
			}
		}

	}
	
}
