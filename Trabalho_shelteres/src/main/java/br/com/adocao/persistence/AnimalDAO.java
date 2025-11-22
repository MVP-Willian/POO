package br.com.adocao.persistence;

import br.com.adocao.model.Animal;

import java.sql.ResultSet;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;


public class AnimalDAO {

    public void inserir(Animal animal) {
        String sql = "INSERT INTO Animal(nome, idade, especie, sexo, porte, peso, personalidade, historico, localEncontrado, situacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try( Connection cnn = ConnectionFactory.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)
        ){
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getIdade());
            stmt.setString(3, animal.getEspecie());
            stmt.setString(4, animal.getSexo());
            stmt.setString(5, animal.getPorte());
            stmt.setFloat(6, animal.getPeso());
            stmt.setString(7, animal.getPersonalidade());
            stmt.setString(8, animal.getHistorico());
            stmt.setString(9, animal.getLocalEncontrado());
            stmt.setString(10, animal.getSituacao());

            stmt.execute();
            System.out.println("Animal inserido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir animal: " + ex.getMessage());
        }
    }

    public List<Animal> listarTodos() throws SQLException {
        List<Animal> animais = new ArrayList<>();

        try (Connection cnn = ConnectionFactory.getConnection();
        PreparedStatement stmt = cnn.prepareStatement("SELECT * FROM Animal");
        ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Animal a = new Animal(
                        rs.getInt("id_animal"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("especie"),
                        rs.getString("sexo"),
                        rs.getString("porte"),
                        rs.getString("situacao"),
                        rs.getString("personalidade"),
                        rs.getString("historico"),
                        rs.getString("localEncontrado"),
                        rs.getFloat("peso")
                );
                animais.add(a);

            }
        }
    return animais;
    }
    public void deletarPorId(int id) throws SQLException {
        String sql = "DELETE FROM Animal WHERE id_animal = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void limparTabela() throws SQLException {
        String sql = "DELETE FROM Animal";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM Animal");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='Animal'");
        }
    }

}
