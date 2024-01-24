package com.mycompany.interfaces;

import com.mycompany.models.Libros;
import com.mycompany.models.Prestamos;
import com.mycompany.models.Usuarios;
import java.util.List;

public interface DAOPrestamos {
    // Método para registrar un nuevo préstamo en la base de datos
    public void registrar(Prestamos lending) throws Exception;

    // Método para modificar los detalles de un préstamo existente en la base de datos
    public void modificar(Prestamos lending) throws Exception;

    // Método para obtener un préstamo específico de la base de datos basado en el usuario y el libro
    public Prestamos getLending(Usuarios user, Libros book) throws Exception;

    // Método para listar todos los préstamos existentes en la base de datos
    public List<Prestamos> listar() throws Exception;

    // Nota: Este método está comentado, por lo que no está activo en la interfaz
    // public void eliminar(Lendings user) throws Exception;
    
      public void eliminar(Usuarios usuario, Libros libro);
}