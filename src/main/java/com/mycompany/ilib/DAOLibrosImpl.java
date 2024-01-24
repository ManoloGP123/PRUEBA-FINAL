package com.mycompany.ilib;

import com.mycompany.db.Conexion;
import com.mycompany.models.Libros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.interfaces.DAOLibros;

public class DAOLibrosImpl extends Conexion implements DAOLibros {

  // Método privado para configurar y ejecutar una consulta SQL
private void ejecutarConsulta(String query, Libros book) throws Exception {
    try {
        PreparedStatement st = this.conexion.prepareStatement(query);
        st.setString(1, book.getTitle());
        st.setString(2, book.getDate());
        st.setString(3, book.getAuthor());
        st.setString(4, book.getCategory());
        st.setString(5, book.getEdit());
        st.setString(6, book.getLang());
        st.setString(7, book.getPages());
        st.setString(8, book.getDescription());
        st.setString(9, book.getEjemplares());
        st.setInt(10, book.getStock());
        st.setInt(11, book.getAvailable());
        st.executeUpdate(); // Ejecuta la consulta SQL
        st.close(); // Cierra el PreparedStatement
    } catch(Exception e) {
        throw e;
    }
}

// Método para registrar un nuevo libro
@Override
public void registrar(Libros book) throws Exception {
    try {
        this.Conectar(); // Abre la conexión a la base de datos
        String query = "INSERT INTO books(title, date, author, category, edit, lang, pages, description, ejemplares, stock, available) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        ejecutarConsulta(query, book);
    } finally {
        this.Cerrar(); // Cierra la conexión a la base de datos en caso de error o éxito
    }
}
//Metodo la modificar libro
    @Override
    public void modificar(Libros book) throws Exception {
        try {
            this.Conectar(); // Abre la conexión a la base de datos
            PreparedStatement st = this.conexion.prepareStatement("UPDATE books SET title = ?, date = ?, author = ?, category = ?, edit = ?, lang = ?, pages = ?, description = ?, ejemplares = ?, stock = ?, available = ? WHERE id = ?");
            // Configura los valores en la consulta SQL
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.setInt(12, book.getId());
            st.executeUpdate(); // Ejecuta la consulta SQL
            st.close(); // Cierra el PreparedStatement
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar(); // Cierra la conexión a la base de datos en caso de error o éxito
        }
    }
//Metodo para eliminar libro
    @Override
    public void eliminar(int bookId) throws Exception {
        try {
            this.Conectar(); // Abre la conexión a la base de datos
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM books WHERE id = ?;");
            st.setInt(1, bookId);
            st.executeUpdate(); // Ejecuta la consulta SQL
            st.close(); // Cierra el PreparedStatement
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar(); // Cierra la conexión a la base de datos en caso de error o éxito
        }
    }
//Metodo para la lista de 
    @Override
    public List<Libros> listar(String title) throws Exception {
        List<Libros> lista = null;
        try {
            this.Conectar(); // Abre la conexión a la base de datos
            String Query = title.isEmpty() ? "SELECT * FROM books;" : "SELECT * FROM books WHERE title LIKE '%" + title + "%';";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            
            lista = new ArrayList(); // Inicializa la lista de libros
            ResultSet rs = st.executeQuery(); // Ejecuta la consulta SQL
            while(rs.next()) {
                Libros book = new Libros(); // Crea un nuevo objeto Libros
                // Configura los valores del objeto con los datos de la consulta
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("date"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
                lista.add(book); // Agrega el libro a la lista
            }
            rs.close(); // Cierra el ResultSet
            st.close(); // Cierra el PreparedStatement
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar(); // Cierra la conexión a la base de datos en caso de error o éxito
        }
        return lista; // Devuelve la lista de libros
    }

    @Override
    public Libros getBookById(int bookId) throws Exception {
        Libros book = null;
        
        try {
            this.Conectar(); // Abre la conexión a la base de datos
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM books WHERE id = ? LIMIT 1;");
            st.setInt(1, bookId);
            
            ResultSet rs = st.executeQuery(); // Ejecuta la consulta SQL
            while(rs.next()) {
                book = new Libros(); // Crea un nuevo objeto Libros
                // Configura los valores del objeto con los datos de la consulta
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("date"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
            }
            rs.close(); // Cierra el ResultSet
            st.close(); // Cierra el PreparedStatement
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar(); // Cierra la conexión a la base de datos en caso de error o éxito
        }
        return book; // Devuelve el objeto Libros
    }   
}
