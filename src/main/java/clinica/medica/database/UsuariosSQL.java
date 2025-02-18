package clinica.medica.database;

import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para realizar a consulta das informações referentes aos
 * usuarios no banco de dados.
 */
public class UsuariosSQL {
    /**
     * Método para leitura dos dados, referentes à classe Usuario,
     * presentes no banco de dados.
     * @param userCpf Cpf do usuario procurado no banco de dados.
     * @param conn Conexão com o banco de dados.
     * @return ResultSet com as informações do usuario
     */
    public static ResultSet selectAllFromUsuario(String userCpf, Connection conn) {
        String query = "SELECT * FROM usuarios WHERE cpf = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Usuário não foi encontrado.");
        }

        return rs;
    }

    /**
     * Método para a leitura dos dados, referentes à classe Paciente,
     * presentes no banco de dados.
     * @param pacienteCpf Cpf do paciente procurado no banco de dados.
     * @param conn Conexão com o banco de dados.
     * @return ResultSet com as informações do paciente.
     */
    public static ResultSet selectAllFromPaciente(String pacienteCpf, Connection conn) {
        String query = "SELECT * FROM pacientes WHERE cpf = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pacienteCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Paciente não foi encontrado.");
        }

        return rs;
    }

    /**
     * Método para a leitura dos dados, referentes à classe Medico,
     * presentes no banco de dados.
     * @param medicoCpf Cpf do médico procurado no banco de dados.
     * @param conn Conexão com o banco de dados.
     * @return ResultSet com as informações do médico.
     */
    public static ResultSet selectAllFromMedico(String medicoCpf, Connection conn) {
        String query = "SELECT * FROM medicos WHERE cpf = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, medicoCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Médico não foi encontrado.");
        }

        return rs;
    }

    /**
     * Método para inserção de um novo usuário no banco de dados.
     * @param nome Nome do usuário.
     * @param cpf CPF do usuário.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @param medico Usuário é um médico ou não.
     * @return Valor booleano se foi possível realizar o cadastro.
     */
    public static boolean cadastroUsuario(String nome, String cpf, String email, String senha, boolean medico) {
        boolean cadastro = false;
        String query = "INSERT INTO usuarios (nome, cpf, email, senha, medico) VALUES (?, ?, ?, ?, ?)";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
           PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);
            pstmt.setString(3, email);
            pstmt.setString(4, senha);
            pstmt.setBoolean(5, medico);
            pstmt.executeUpdate();
            cadastro = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }

    /**
     * Método para inserção de um novo médico no banco de dados.
     * @param nome Nome do médico.
     * @param cpf CPF do médico.
     * @param email Email do médico.
     * @param senha Senha do médico.
     * @param areaAtuacao Área de atuação do médico.
     * @param crm CRM do médico.
     * @return Valor booleano se foi possível cadastrar o médico.
     */
    public static boolean cadastroMedico(String nome, String cpf, String email, String senha, String areaAtuacao, String crm){
        boolean cadastro = false;
        //verifica validade dos dados digitados
        if(nome.matches("[0-9]+") || cpf.matches("[A-Z]+") || areaAtuacao.matches("[0-9]+") || crm.matches("[A-Z]+")){
            return false;
        }else if(nome.equals("") || cpf.equals("") || email.equals("") || senha.equals("") || areaAtuacao.equals("") || crm.equals("")){
            return false;
        }

        String queryMedico = "INSERT INTO medicos (cpf, areaAtuacao, crm) VALUES (?, ?, ?)";

        if (!cadastroUsuario(nome, cpf, email, senha, true))
            return false;
        
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
    
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(queryMedico);
            pstmt.setString(1, cpf);
            pstmt.setString(2, areaAtuacao);
            pstmt.setString(3, crm);
            pstmt.executeUpdate();
            cadastro = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }

    /**
     * Método para a inserção de um novo paciente no banco de dados.
     * @param nome Nome do paciente.
     * @param cpf CPF do paciente.
     * @param email Email do paciente.
     * @param senha Senha do paciente.
     * @param endereco Endereço do paciente.
     * @param sexo Sexo do paciente.
     * @param idade Idade do paciente.
     * @param altura Altura do paciente.
     * @param peso Peso do paciente.
     * @return Valor booleano se foi possível realizar o cadastro do paciente.
     */
    public static boolean cadastroPaciente(String nome, String cpf, String email, String senha, String endereco, String sexo, int idade, double altura, double peso){
        boolean cadastro = false;
        //verifica validade dos dados digitados
        if(nome.matches("[0-9]+") || cpf.matches("[A-Z]+") || endereco.matches("[0-9]+") || sexo.matches("[0-9]+")){
            return false;
        }else if(nome.equals("") || cpf.equals("") || email.equals("") || senha.equals("") || endereco.equals("") || sexo.equals("")){
            return false;
        }else if(idade == 0 || peso == 0 || altura == 0){
            return false;
        }

        String queryPaciente = "INSERT INTO pacientes (cpf, endereco, sexo, idade, altura, peso) VALUES (?, ?, ?, ?, ?, ?)";

        if (!cadastroUsuario(nome, cpf, email, senha, false))
            return false;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(queryPaciente);
            pstmt.setString(1, cpf);
            pstmt.setString(2, endereco);
            pstmt.setString(3, sexo);
            pstmt.setInt(4, idade);
            pstmt.setDouble(5,altura);
            pstmt.setDouble(6,peso);
            pstmt.executeUpdate();
            cadastro = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }

    /**
     * Método para realizar a consulta do login de um médico no banco de dados.
     * @param email Email do médico.
     * @param senha Senha do médico.
     * @return Médico encontrado no banco de dados.
     */
    public static Medico loginMedico(String email, String senha){
        Medico medicoLogado = null;
        String query = "SELECT * FROM usuarios U INNER JOIN medicos M ON U.cpf = M.cpf WHERE email = ? and senha = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
        
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                medicoLogado = new Medico(rs.getString("cpf"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return medicoLogado;
    }

    /**
     * Método para realizar a consulta do login de um paciente no banco de dados.
     * @param email Email do paciente.
     * @param senha Senha do paciente.
     * @return Paciente encontrado no banco de dados.
     */
    public static Paciente loginPaciente(String email, String senha){
        Paciente pacienteLogado = null;
        String query = "SELECT * FROM usuarios U INNER JOIN pacientes P ON U.cpf = P.cpf WHERE email = ? and senha = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
        
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                pacienteLogado = new Paciente(rs.getString("cpf"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return pacienteLogado;
    }
}
