/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.DTOs;

import java.util.Date;
import java.util.List;
import model.entities.Comentario;

/**
 *
 * @author mangel
 */
public class ComentarioDTO {

    private Long id;
    private String contenido;
    private Date fechaComentario;
    private Long usuarioId;
    private Long publicacionId;
    private Long comentarioPadreId; 
    private List<Long> respuestasIds; 

    public ComentarioDTO() {
    }

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.contenido = comentario.getContenido();
        this.fechaComentario = comentario.getFechaComentario();
        this.usuarioId = comentario.getUsuario() != null ? comentario.getUsuario().getId() : null;
        this.publicacionId = comentario.getPublicacion() != null ? comentario.getPublicacion().getId() : null;
        this.comentarioPadreId = comentario.getComentarioPadre() != null ? comentario.getComentarioPadre().getId() : null;
        this.respuestasIds = comentario.getRespuestas() != null
                ? comentario.getRespuestas().stream().map(Comentario::getId).toList()
                : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Long getComentarioPadreId() {
        return comentarioPadreId;
    }

    public void setComentarioPadreId(Long comentarioPadreId) {
        this.comentarioPadreId = comentarioPadreId;
    }

    public List<Long> getRespuestasIds() {
        return respuestasIds;
    }

    public void setRespuestasIds(List<Long> respuestasIds) {
        this.respuestasIds = respuestasIds;
    }
    
    
}
