package view;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ContatoDAO;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import model.Contato;
import model.TipoEnum;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContatoView extends JInternalFrame {
	private Contato contato;
	private List<Contato> listaContatos;
	private ContatoDAO contatoDAO;
	
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfTelefone;
	private JTextField tfCelular;
	private JTextField tfEmail;

	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnGravar;
	
	private JComboBox<String> cbxTipo;
	private JTextArea taObservacao;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContatoView frame = new ContatoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContatoView() {
		contatoDAO = new ContatoDAO();
		setClosable(true);
		setBounds(100, 100, 924, 558);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		
		JLabel lblNome = new JLabel("Nome");
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		
		JLabel lblTelefone = new JLabel("Telefone");
		
		JLabel lblCelular = new JLabel("Celular");
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JLabel lblObservacao = new JLabel("Observa\u00E7\u00E3o");
		
		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setEnabled(false);
		tfNome.setColumns(10);
		
		tfEndereco = new JTextField();
		tfEndereco.setEnabled(false);
		tfEndereco.setColumns(10);
		
		tfTelefone = new JTextField();
		tfTelefone.setEnabled(false);
		tfTelefone.setColumns(10);
		
		tfCelular = new JTextField();
		tfCelular.setEnabled(false);
		tfCelular.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		
		cbxTipo = new JComboBox<String>();
		for(TipoEnum tipo:TipoEnum.values()){
            cbxTipo.addItem(tipo.getDescricao());
        }
		
		taObservacao = new JTextArea();
		taObservacao.setEnabled(false);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
				preparaNovo();
				ativaCampos();
			}
		});
		
		btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNome.getText().isEmpty() ||
						tfEndereco.getText().isEmpty() ||
						tfTelefone.getText().isEmpty() ||
						tfCelular.getText().isEmpty() ||
						tfEmail.getText().isEmpty() ||
						taObservacao.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", null, JOptionPane.ERROR_MESSAGE);
				}
				else if(tfCodigo.getText().isEmpty()){
					contato = new Contato();
					contato.setNome(tfNome.getText());
					contato.setEndereco(tfEndereco.getText());
					contato.setTelefone(tfTelefone.getText());
					contato.setCelular(tfCelular.getText());
					contato.setEmail(tfEmail.getText());
					contato.setTipo(TipoEnum.values()[cbxTipo.getSelectedIndex()]);
					contato.setObservacao(taObservacao.getText());
					try{
						contatoDAO.salvar(contato);
					}
					catch(SQLException ex){
						
					}
					JOptionPane.showMessageDialog(null, "Gravado com sucesso", null, JOptionPane.INFORMATION_MESSAGE);
					atualizarTabela();
					preparaSalvarECancelar();
					desativaCampos();
					limpaCampos();
				}
				else{
					contato = new Contato();
					contato.setCodigo(Integer.parseInt(tfCodigo.getText()));
					contato.setNome(tfNome.getText());
					contato.setEndereco(tfEndereco.getText());
					contato.setTelefone(tfTelefone.getText());
					contato.setCelular(tfCelular.getText());
					contato.setEmail(tfEmail.getText());
					contato.setTipo(TipoEnum.values()[cbxTipo.getSelectedIndex()]);
					contato.setObservacao(taObservacao.getText());
					try{
						contatoDAO.alterar(contato);
					}
					catch(SQLException ex){
						
					}
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso", null, JOptionPane.INFORMATION_MESSAGE);
					atualizarTabela();
					preparaSalvarECancelar();
					desativaCampos();
					limpaCampos();
				}
			}
		});
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfCodigo.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Selecione um cliente", null, JOptionPane.ERROR_MESSAGE);
				}
				else{
					contato = new Contato();
					contato.setCodigo(Integer.parseInt(tfCodigo.getText()));
					int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir "+tfNome.getText()+" da sua lista de contatos?", null, JOptionPane.YES_NO_OPTION);
					if(confirma == 0){
						try{
							contatoDAO.excluir(contato);
						}
						catch(SQLException ex){
							
						}
						limpaCampos();
						atualizarTabela();
						preparaExcluir();
					}
				}
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preparaAlterar();
				ativaCampos();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				preparaSalvarECancelar();
				desativaCampos();
			}
		});
		btnCancelar.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblObservacao)
								.addComponent(lblCodigo)
								.addComponent(lblNome)
								.addComponent(lblEndereco)
								.addComponent(lblTelefone)
								.addComponent(lblCelular)
								.addComponent(lblEmail)
								.addComponent(lblTipo))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tfCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(cbxTipo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tfEmail, Alignment.LEADING))
								.addComponent(taObservacao, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGravar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereco)
						.addComponent(tfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCelular)
						.addComponent(tfCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(cbxTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObservacao)
						.addComponent(taObservacao, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnGravar)
						.addComponent(btnExcluir)
						.addComponent(btnAlterar)
						.addComponent(btnCancelar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(114, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfEndereco.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				tfTelefone.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				tfCelular.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				tfEmail.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				cbxTipo.setSelectedItem(table.getValueAt(table.getSelectedRow(), 6).toString());
				taObservacao.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
				preparaSelecaoTabela();
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		atualizarTabela();
	}

	public void limpaCampos(){
        tfCodigo.setText("");
        tfNome.setText("");
        tfEndereco.setText("");
        tfTelefone.setText("");
        tfCelular.setText("");
        tfEmail.setText("");
        taObservacao.setText("");
    }
    
    private void ativaCampos(){
        tfNome.setEnabled(true);
        tfEndereco.setEnabled(true);
        tfTelefone.setEnabled(true);
        tfCelular.setEnabled(true);
        tfEmail.setEnabled(true);
        cbxTipo.setEnabled(true);
        taObservacao.setEnabled(true);
    }
    
    private void desativaCampos(){
    	tfNome.setEnabled(false);
        tfEndereco.setEnabled(false);
        tfTelefone.setEnabled(false);
        tfCelular.setEnabled(false);
        tfEmail.setEnabled(false);
        cbxTipo.setEnabled(false);
        taObservacao.setEnabled(false);
    }
    
    private void preparaNovo(){
        btnNovo.setEnabled(false);
        btnGravar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        table.setEnabled(false);
        table.clearSelection();
    }
    
    private void preparaSalvarECancelar(){
        btnNovo.setEnabled(true);
        btnGravar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAlterar.setEnabled(false);
        table.setEnabled(true);
    }
    
    private void preparaAlterar(){
        btnNovo.setEnabled(false);
        btnGravar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        table.setEnabled(false);
        table.clearSelection();
    }
    
    private void preparaExcluir(){
    	btnNovo.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
    
    private void preparaSelecaoTabela(){
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    public void atualizarTabela(){
    	contato = new Contato();
        try {
            listaContatos = contatoDAO.listarContatos();
        } catch (SQLException ex) {
            Logger.getLogger(ContatoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[listaContatos.size()][9];
        int i = 0;
        for (Contato contato : listaContatos) {
            dados[i][0] = String.valueOf(contato.getCodigo());
            dados[i][1] = contato.getNome();
            dados[i][2] = contato.getEndereco();
            dados[i][3] = contato.getTelefone();
            dados[i][4] = contato.getCelular();
            dados[i][5] = contato.getEmail();
            dados[i][6] = contato.getTipo().getDescricao();
            dados[i][7] = contato.getObservacao();
            i++;
        }
        String tituloColuna[] = {"Código", "Nome", "Endereço", "Telefone", "Celular", "Email", "Tipo", "Observação"};
        DefaultTableModel modeloTabela = new DefaultTableModel();
        modeloTabela.setDataVector(dados, tituloColuna);
        table.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();

        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        table.setRowHeight(25);
        table.updateUI();
    }
}
