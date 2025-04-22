/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.DTOs;

import java.util.Date;

/**
 *
 * @author mangel
 */

public class UsuarioDTO {
  private Long id;
    private String nombre;
    private String email;
    private String fotoPerfil;
    private String bannerPerfil;
    private String descripcion;
    private Date fechaCreacion;
    private boolean privacidad;
    private int numPublicaciones;
    private int numComentarios;
    private String pwd;

    public UsuarioDTO() {}

    public UsuarioDTO(model.entities.Usuario u) {
        this.id = u.getId();
        this.nombre = u.getNombre();
        this.email = u.getEmail();
        this.fotoPerfil = u.getFotoPerfil();
        this.bannerPerfil = u.getBannerPerfil();
        this.descripcion = u.getDescripcion();
        this.fechaCreacion = u.getFechaCreacion();
        this.privacidad = u.isPrivacidad();
        this.numPublicaciones = u.getNumPublicaciones();
        this.numComentarios = u.getNumComentarios();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBannerPerfil() {
        return bannerPerfil;
    }

    public void setBannerPerfil(String bannerPerfil) {
        this.bannerPerfil = bannerPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(boolean privacidad) {
        this.privacidad = privacidad;
    }

    public int getNumPublicaciones() {
        return numPublicaciones;
    }

    public void setNumPublicaciones(int numPublicaciones) {
        this.numPublicaciones = numPublicaciones;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
}