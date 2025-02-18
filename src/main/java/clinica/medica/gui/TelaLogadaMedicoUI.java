package clinica.medica.gui;

import clinica.medica.database.MedicosSQL;
import clinica.medica.database.UsuariosSQL;
import clinica.medica.documentos.Exame;
import clinica.medica.usuarios.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class TelaLogadaMedicoUI {


    /**
     * Método privado para a implementação da tela do médico.
     * @param medicoLogado Médico logado no momento.
     */
    protected static JPanel painelMedico(Medico medicoLogado){
        LoginUI.frame.setVisible(false);

        JPanel painelMedico = new JPanel();

        painelMedico.setLayout(new GridBagLayout());
        painelMedico.setVisible(true);
        painelMedico.setSize(800, 600);

        JTextArea infoMedicoLabel = new JTextArea("============Bem Vindo, " + medicoLogado.getNome() + "============!\n\nÁrea de atuação: " + medicoLogado.getAreaAtuacao() + "\nCRM: " + medicoLogado.getCRM());
        infoMedicoLabel.setEditable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelMedico.add(infoMedicoLabel, constraints);

        return painelMedico;
    }

    protected static JPanel telaPrescreverExame(Medico medicoLogado, TelaLogadaUI telaLogada) {
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área dos exames");
        
        JButton novoExameButton = new JButton("Prescrever novo exame");
        JButton verificarExameButton = new JButton("Verificar exames");
        
        novoExameButton.addActionListener(telaLogada);
        verificarExameButton.addActionListener(telaLogada);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel, constraints);
        
        constraints.gridy = 2;
        painelExame.add(novoExameButton, constraints);
        
        constraints.gridy = 3;
        painelExame.add(verificarExameButton, constraints);
        
        
        return painelExame;
    }

    protected static JPanel telaPrescreverLaudo() {
        JPanel painelLaudo = new JPanel();

        painelLaudo.setLayout(new GridBagLayout());
        painelLaudo.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área do Laudo");
        
        JButton novoLaudoButton = new JButton("Prescrever novo laudo");
        JButton verificarLaudoButton = new JButton("Verificar laudos");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelLaudo.add(tempLabel, constraints);
        
        constraints.gridy = 2;
        painelLaudo.add(novoLaudoButton, constraints);
        
        constraints.gridy = 3;
        painelLaudo.add(verificarLaudoButton, constraints);

        return painelLaudo;
    }

    protected static JPanel telaAgendarConsulta() {
        JPanel painelConsulta = new JPanel();

        painelConsulta.setLayout(new GridBagLayout());
        painelConsulta.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Área da Consulta");
        
        JButton novaConsultaButton = new JButton("Agendar nova consulta");
        JButton verificarConsultaButton = new JButton("Verificar consultas");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelConsulta.add(tempLabel, constraints);
        
        constraints.gridy = 2;
        painelConsulta.add(novaConsultaButton, constraints);
        
        constraints.gridy = 3;
        painelConsulta.add(verificarConsultaButton, constraints);

        return painelConsulta;
    }
    
    protected static JPanel telaNovoExame(Medico medicoLogado, TelaLogadaUI telaLogada){
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Novo exame");
        
        JLabel tipoLabel = new JLabel("Tipo de exame");
        JLabel cpfPacienteLabel = new JLabel("CPF do paciente");
        JLabel comentarioLabel = new JLabel("Comentários");
        
        JTextField tipoField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextArea comentarioArea = new JTextArea(20,40);
        comentarioArea.setEditable(true);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);
        
        JButton cadastrarExameButton = new JButton("Cadastrar exame");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;

        constraints.gridy = 1;
        painelExame.add(tempLabel, constraints);
        
        constraints.gridy = 2;
        painelExame.add(tipoLabel, constraints);
        
        constraints.gridy = 3;
        painelExame.add(tipoField, constraints);
        
        constraints.gridy = 4;
        painelExame.add(cpfPacienteLabel, constraints);
        
        constraints.gridy = 5;
        painelExame.add(cpfField, constraints);
        
        constraints.gridy = 6;
        painelExame.add(comentarioLabel, constraints);
        
        constraints.gridy = 7;
        painelExame.add(comentarioArea, constraints);
        
        constraints.gridy = 8;
        painelExame.add(cadastrarExameButton, constraints);
        
        constraints.gridy = 9;
        painelExame.add(voltarButton, constraints);
        
        
        cadastrarExameButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String tipo = tipoField.getText();
                String cpf = cpfField.getText();
                String comentario = comentarioArea.getText();
               if(MedicosSQL.cadastrarNovoExame(tipo, cpf, medicoLogado.getCRM(), new Date(2023,6,4), comentario)){
                   JOptionPane.showMessageDialog(painelExame, "Cadastro de exame realizado com sucesso!");
               }else{
                    JOptionPane.showMessageDialog(painelExame,"Não foi possível cadastrar o exame, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    tipoField.setText("");
                    cpfField.setText("");
                    comentarioArea.setText("");
               }
            }
        });
        return painelExame;
    }
    
    protected static JPanel showExames(Medico medicoLogado, TelaLogadaUI telaLogada){
        ArrayList<Exame> exames = MedicosSQL.verificarExames(medicoLogado.getCRM());
        int i = 0;
        JPanel painelExame = new JPanel();

        painelExame.setLayout(new GridBagLayout());
        painelExame.setSize(800, 600);

        JTextArea tempLabel = new JTextArea("Exames");
        JButton imprimirExameButton = new JButton("Visualizar exame");
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(telaLogada);
       
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        
        String[] listaExame = new String[exames.size()];
        
        for(Exame ex : exames){
            listaExame[i] = "Exame - " + ex.getPaciente().getNome() + " - " + ex.getTipo() + " - " + ex.getId();
            i++;
        }
        
        JList<String> list = new JList<>(listaExame);
        JScrollPane scrollPanel = new JScrollPane(list);
        
        scrollPanel.setPreferredSize(new Dimension(800,600));
        constraints.gridy = 0;
        painelExame.add(tempLabel,constraints);
        constraints.gridy = 1;
        painelExame.add(scrollPanel,constraints);
        constraints.gridy = 2;
        painelExame.add(imprimirExameButton,constraints);
        constraints.gridy = 3;
        painelExame.add(voltarButton,constraints);
        
        return painelExame;
        
    }
}
