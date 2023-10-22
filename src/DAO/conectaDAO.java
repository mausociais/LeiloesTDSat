package DAO;


import Controller.ProdutosDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



    public class conectaDAO{

    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    public String url = "jdbc:mysql://localhost:3306/uc11";
    public String user = "root";
    public String pass = "191484M@u";
    
    public boolean conectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user, pass);
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
            
        }
    }
    
    public void desconectar(){
            try {
                conn.close();

            } catch (SQLException ex) {
                //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
            }
        }
    
    
    public static Connection Conexao() throws SQLException{
        
        try{
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "191484M@u");
        } catch (ClassNotFoundException e){
            throw new SQLException(e.getException());
        }
    }
    
    public int salvar (ProdutosDTO produtos){

            int status;

            try {
                st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
                //
                String query = "SELECT MAX(id) FROM produtos";

                rs = st.executeQuery(query);

                int lastId = 0;
                if (rs.next()) {
                    lastId = rs.getInt(1);
                }
                int nextId = lastId + 1;

                st.setInt(1, nextId);
                st.setString(2, produtos.getNome());
                st.setInt(3, produtos.getValor());
                st.setString(4, produtos.getStatus());

                status = st.executeUpdate();
                return status; //retornar 1

            } catch (SQLException ex) {
                System.out.println("Erro ao conectar: " + ex.getMessage());
                return ex.getErrorCode();

            }
        }

    Connection connectDB() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
}
