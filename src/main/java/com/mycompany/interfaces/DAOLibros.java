package com.mycompany.interfaces;

import com.mycompany.models.Libros;
import java.util.List;

public interface DAOLibros {
    // Método para registrar un nuevo libro en la base de datos
    public void registrar(Libros book) throws Exception;

    // Método para modificar los detalles de un libro existente en la base de datos
    public void modificar(Libros book) throws Exception;

    // Método para eliminar un libro de la base de datos basándose en su ID
    public void eliminar(int bookId) throws Exception;

    // Método para listar libros de la base de datos. Puede buscar por título si se proporciona
    public List<Libros> listar(String title) throws Exception;

    // Método para obtener los detalles de un libro específico de la base de datos utilizando su ID
    public Libros getBookById(int bookId) throws Exception; 
}
