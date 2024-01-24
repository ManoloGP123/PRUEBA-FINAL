package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.ilib.DAOLibrosImpl;
import com.mycompany.models.Libros;

public class DAOLibrosTest {

    private DAOLibrosImpl daoLibros;

    @BeforeEach
    public void setUp() throws Exception {
        // Configuración antes de cada prueba
        daoLibros = new DAOLibrosImpl();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Limpiar recursos después de cada prueba
        daoLibros = null;
    }

    @Test
    public void testListar() {
        try {
            // Poner título de libro a buscar
            List<Libros> libros = daoLibros.listar("Libro Prueba");

            // Verificar que la lista no sea nula
            assertNotNull(libros);

            // Verificar que al menos hay un libro en la lista
            assert libros.size() > 0;

            // Imprime información sobre los libros recuperados
            System.out.println("Libros Recuperados:");
            for (Libros libro : libros) {
                System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitle());
            }

            // Volver a colocar el mismo título del libro de arriba para verificar
            assertTrue(libros.stream().anyMatch(libro -> libro.getTitle().equalsIgnoreCase("Libro Prueba")));

        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            fail("Excepción durante la prueba: " + e.getMessage());
        }
    }

}