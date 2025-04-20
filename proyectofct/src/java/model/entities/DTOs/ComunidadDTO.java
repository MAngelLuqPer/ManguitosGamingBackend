/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.DTOs;

import model.entities.*;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mangel
 */
/**
 * DTO para la entidad Comunidad
 */
public class ComunidadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String reglas;
    private String banner;
    private String foto;
    private Date fechaCreacion;
    private int numMiembros;

    private Long idUsuAdmin;

    public ComunidadDTO() {}

    public ComunidadDTO(Comunidad comunidad) {
        this.id = comunidad.getId();
        this.nombre = comunidad.getNombre();
        this.descripcion = comunidad.getDescripcion();
        this.reglas = comunidad.getReglas();
        this.banner = comunidad.getBanner();
        this.foto = comunidad.getFoto();
        this.fechaCreacion = comunidad.getFechaCreacion();
        this.numMiembros = comunidad.getNumMiembros();
        this.idUsuAdmin = comunidad.getUsuAdmin() != null ? comunidad.getUsuAdmin().getId() : null;

    }

    // Getters y setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReglas() {
        return reglas;
    }

    public void setReglas(String reglas) {
        this.reglas = reglas;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getNumMiembros() {
        return numMiembros;
    }

    public void setNumMiembros(int numMiembros) {
        this.numMiembros = numMiembros;
    }

    public Long getIdUsuAdmin() {
        return idUsuAdmin;
    }

    public void setIdUsuAdmin(Long idUsuAdmin) {
        this.idUsuAdmin = idUsuAdmin;
    }
}
