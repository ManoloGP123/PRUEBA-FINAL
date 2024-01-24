package com.mycompany.ilib;

import com.mycompany.db.Conexion;
import com.mycompany.models.Libros;
import com.mycompany.models.Prestamos;
import com.mycompany.models.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.interfaces.DAOPrestamos;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOPrestamoImpl extends Conexion implements DAOPrestamos {

    @Override
    public void registrar(Prestamos lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO lendings(user_id, book_id, date_out) VALUES(?,?,?);");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Prestamos lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE lendings SET user_id = ?, book_id = ?, date_out = ?, date_return = ? WHERE id = ?");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.setString(4, lending.getDate_return());
            st.setInt(5, lending.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public Prestamos getLending(Usuarios user, Libros book) throws Exception {
        Prestamos lending = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings WHERE user_id = ? AND book_id = ? AND date_return IS NULL ORDER BY id DESC LIMIT 1");
            st.setInt(1, user.getId());
            st.setInt(2, book.getId());
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lending = new Prestamos();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
            }
            
            st.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        
        return lending;
    }

    @Override
    public List<Prestamos> listar() throws Exception {
        List<Prestamos> lista = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings ORDER BY id DESC");
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Prestamos lending = new Prestamos();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
                lista.add(lending);
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
    @Override
public void eliminar(Usuarios usuario, Libros libro) {
    try {
        this.Conectar();
        PreparedStatement st = this.conexion.prepareStatement("DELETE FROM lendings WHERE user_id = ? AND book_id = ?");
        st.setInt(1, usuario.getId());
        st.setInt(2, libro.getId());
        st.executeUpdate();
        st.close();
    } catch (Exception e) {
        try {
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(DAOPrestamoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    } finally {
        try {
            this.Cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPrestamoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    

}