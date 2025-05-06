/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entities.Comunidad;
import model.entities.Publicacion;
import model.entities.Usuario;
import model.services.ComunidadService;
import model.services.PublicacionService;
import model.services.UsuarioService;


/**
 *
 * @author jose
 */
@WebServlet(name = "CrearDatos", urlPatterns = {"/CrearDatos"})
public class CrearDatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");

        UsuarioService su = new UsuarioService(emf);
        ComunidadService sc = new ComunidadService(emf);
        PublicacionService sp = new PublicacionService(emf);

        // Crear Usuario
        Usuario u1 = new Usuario();
        u1.setNombre("Marta González");
        u1.setEmail("marta@manguitos.com");
        u1.setPwd("mango123");
        u1.setFotoPerfil("marta_foto.jpg");
        u1.setBannerPerfil("marta_banner.jpg");
        u1.setDescripcion("Fanática de los manguitos tropicales");
        u1.setFechaCreacion(new Date());
        u1.setPrivacidad(false);
        u1.setNumPublicaciones(0);
        u1.setNumComentarios(0);
        su.create(u1);

        // Crear Comunidad
        Comunidad c1 = new Comunidad();
        c1.setNombre("Manguitos World");
        c1.setDescripcion("Comunidad para compartir memes y recetas con mangos");
        c1.setReglas("No spoilers de manguitos. Respetar al prójimo.");
        c1.setFoto("foto_comunidad.jpg");
        c1.setBanner("banner_comunidad.jpg");
        c1.setFechaCreacion(new Date());
        c1.setNumMiembros(1);
        c1.setUsuAdmin(u1);
        sc.create(c1);

        // Crear Publicación
        Publicacion pub1 = new Publicacion();
        pub1.setTitulo("Receta de manguitos con chile");
        pub1.setContenido("¡Deliciosa y picante! Solo para valientes.");
        pub1.setFechaPublicacion(new Date());
        pub1.setUsuario(u1);
        pub1.setComunidad(c1);
        sp.create(pub1);

        emf.close();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Datos Creados</title></head>");
            out.println("<body>");
            out.println("<h1>Se han creado los datos de prueba correctamente</h1>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Crea datos de prueba para usuarios, comunidades y publicaciones";
    }
}