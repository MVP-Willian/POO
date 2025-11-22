package br.com.adocao.persistence;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void inicializar() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()){

            String sqlAnimal = """
            CREATE TABLE IF NOT EXISTS Animal (
                id_animal INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                idade   INTEGER,
                especie TEXT NOT NULL,
                sexo TEXT NOT NULL,
                porte TEXT NOT NULL,
                peso FLOAT NOT NULL,
                personalidade TEXT NOT NULL,
                historico TEXT NOT NULL,
                localEncontrado  TEXT NOT NULL,
                situacao TEXT NOT NULL
            );
            """;

            stmt.execute(sqlAnimal);

            System.out.println("LOG: Banco de dados inicializado com sucesso!");
        } catch (SQLException e) {
            System.out.println("X LOG Error: erro ao inicializar o banco de dados.");
        }
    }
}
