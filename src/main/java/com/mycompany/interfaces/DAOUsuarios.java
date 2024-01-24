package com.mycompany.interfaces;

import com.mycompany.models.Usuarios;
import java.util.List;

public interface DAOUsuarios {
    // Método para registrar un nuevo usuario en la base de datos
    public void registrar(Usuarios user) throws Exception;

    // Método para modificar los detalles de un usuario existente en la base de datos
    public void modificar(Usuarios user) throws Exception;

    // Método para aplicar sanciones a un usuario en la base de datos
    public void sancionar(Usuarios user) throws Exception;

    // Método para eliminar un usuario de la base de datos utilizando su ID
    public void eliminar(int userId) throws Exception;

    // Método para listar usuarios de la base de datos. Puede buscar por nombre si se proporciona
    public List<Usuarios> listar(String name) throws Exception;

    // Método para obtener los detalles de un usuario específico de la base de datos utilizando su ID
    public Usuarios getUserById(int userId) throws Exception;
}
