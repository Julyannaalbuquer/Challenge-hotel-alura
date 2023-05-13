package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import controller.HospedesController;
import controller.ReservaController;

public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservaController reservaController;
	private HospedesController hospedeController;
	int xMouse, yMouse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Buscar() {
		reservaController = new ReservaController();
		hospedeController = new HospedesController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/assets/imagens/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Pagamento");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/assets/imagens/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		popularListaReservas();
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/assets/imagens/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		popularListaHospedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/assets/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparListas();
				buscar();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(panel.getSelectedIndex()==0){
					editarReservas();
				} else{
					editarHospedes();
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				deletar();
			}
		});
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	private void popularListaReservas(){
		reservaController.listarReservas().forEach(reserva -> {
			modelo.addRow(reserva);
		});
	}

	private void popularListaHospedes(){
		hospedeController.listarHospedes().forEach(hospede -> {
			modeloHospedes.addRow(hospede);
		});
	}

	private void limparListas(){
		for(int i=(modeloHospedes.getRowCount()-1); i>=0; i--){ 
			try{
				modelo.removeRow(i);
				modeloHospedes.removeRow(i);
			} catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
			}
		}
	}

	private void buscar(){
		hospedeController.buscar(txtBuscar.getText()).forEach(hospede -> {
			modelo.addRow(hospede.getReserva().toListString());
			modeloHospedes.addRow(hospede.toListString());
		});
	}

	private void deletar(){
		String registroSelecionado;
		Integer linhaSelecionada = tbHospedes.getSelectedRow();
		if(linhaSelecionada < 0){
			linhaSelecionada = tbReservas.getSelectedRow();
			if(linhaSelecionada < 0){
				JOptionPane.showMessageDialog(this, "Selecione uma linha");
				return;
			}
			registroSelecionado = (modelo.getDataVector().elementAt(linhaSelecionada)).elementAt(0).toString();
		} else{
			registroSelecionado = (modeloHospedes.getDataVector().elementAt(linhaSelecionada)).elementAt(6).toString();
		}
		hospedeController.deletar(registroSelecionado);
		JOptionPane.showMessageDialog(this, "Registro deletado");
		limparListas();
		popularListaHospedes();
		popularListaReservas();
	}

	private void editarHospedes(){
		Integer colunaSelecionada = tbHospedes.getEditingColumn();
		if(colunaSelecionada==0 || colunaSelecionada==6){
			JOptionPane.showMessageDialog(this, "Esse campo não pode ser alterado");
			return;
		}
		Integer linhaSelecionada = tbHospedes.getEditingRow();
		if (tbHospedes.isEditing()){
			tbHospedes.getCellEditor().stopCellEditing();
		}
		String nomeColuna = tbHospedes.getColumnName(colunaSelecionada);
		String valorEditado;
		String idSelecionado;
		try{
			valorEditado = tbHospedes.getValueAt(linhaSelecionada, colunaSelecionada).toString();
			idSelecionado = tbHospedes.getValueAt(linhaSelecionada, 0).toString();
		} catch(ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(this, "Dê um duplo clique sobre uma célula e clique em EDITAR");
			return;
		}
		if(hospedeController.editar(
			nomeColuna,
			valorEditado,
			idSelecionado			
		)){
			JOptionPane.showMessageDialog(this, "Coluna " + nomeColuna + " editada");
		} else{
			JOptionPane.showMessageDialog(this, "Cancelado, o formato de data deve ser YYYY-MM-DD");
		}
		limparListas();
		popularListaHospedes();
		popularListaReservas();
	}

	private void editarReservas(){
		Integer colunaSelecionada = tbReservas.getEditingColumn();
		if(colunaSelecionada==0 || colunaSelecionada==3){
			JOptionPane.showMessageDialog(this, "Esse campo não pode ser alterado");
			return;
		}
		Integer linhaSelecionada = tbReservas.getEditingRow();
		if (tbReservas.isEditing()){
			tbReservas.getCellEditor().stopCellEditing();
		}
		if(colunaSelecionada==2 || colunaSelecionada==1){
			if(!validarData(linhaSelecionada)){
				JOptionPane.showMessageDialog(this, "A data de saída deve ser após a data de entrada");
				return;	
			}
		}
		String nomeColuna = tbReservas.getColumnName(colunaSelecionada);
		String valorEditado;
		String idSelecionado;
		try{
			valorEditado = tbReservas.getValueAt(linhaSelecionada, colunaSelecionada).toString();
			idSelecionado = tbReservas.getValueAt(linhaSelecionada, 0).toString();
		} catch(ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(this, "Dê um duplo clique sobre uma célula e clique em EDITAR");
			return;
		}
		if(reservaController.editar(
			nomeColuna,
			valorEditado,
			idSelecionado			
		)){
			JOptionPane.showMessageDialog(this, "Coluna " + nomeColuna + " editada");
		} else{
			JOptionPane.showMessageDialog(this, "Cancelado, o formato de data deve ser YYYY-MM-DD");
		}
		limparListas();
		popularListaHospedes();
		popularListaReservas();
	}

	private Boolean validarData(Integer linha){
		Integer diasEntreDatas = obterDiasEntreDatas(
			java.sql.Date.valueOf(tbReservas.getValueAt(linha, 1).toString()),
			java.sql.Date.valueOf(tbReservas.getValueAt(linha, 2).toString())
		);
		if(diasEntreDatas<0){
			return false;
		}
		recalcularValor(diasEntreDatas, tbReservas.getValueAt(linha, 0).toString());
		return true;
	}

	private Integer obterDiasEntreDatas(java.sql.Date dataEntrada, java.sql.Date dataSaida){
		return (int)((dataSaida.getTime()-dataEntrada.getTime())/86400000); 
	}
		
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
	    xMouse = evt.getX();
	    yMouse = evt.getY();
	}

	private void recalcularValor(Integer dias, String id){
		reservaController.editar((double)(80*dias), id);
	}

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
