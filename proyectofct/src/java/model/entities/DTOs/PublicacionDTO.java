/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.DTOs;

import java.util.Date;
import java.util.List;
import model.entities.Publicacion;

/**
 *
 * @author mangel
 */
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private Date fechaPublicacion;
    private int numVotos;
    private String contenido;
    private List<String> imagenes;
    private Long usuarioId;  // Referencia solo al ID del Usuario
    private Long comunidadId;  // Referencia solo al ID de la Comunidad

     public PublicacionDTO() {
    }

        public PublicacionDTO(Publicacion publicacion) {
        this.id = publicacion.getId();
        this.titulo = publicacion.getTitulo();
        this.fechaPublicacion = publicacion.getFechaPublicacion();
        this.numVotos = publicacion.getNumVotos();
        this.contenido = publicacion.getContenido();
        this.imagenes = publicacion.getImagenes();
        this.usuarioId = publicacion.getUsuario() != null ? publicacion.getUsuario().getId() : null;
        this.comunidadId = publicacion.getComunidad() != null ? publicacion.getComunidad().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getComunidadId() {
        return comunidadId;
    }

    public void setComunidadId(Long comunidadId) {
        this.comunidadId = comunidadId;
    }
        
        
}
