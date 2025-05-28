package model.entities.DTOs;

import java.util.Date;
import model.entities.Expulsados;

/**
 * DTO para la entidad Expulsados
 */
public class ExpulsadoDTO {
    private Long id;
    private String razon;
    private Date fechaFin;
    private Long usuarioId;
    private String usuarioNombre;
    private Long comunidadId;
    private String comunidadNombre;

    // Constructores
    public ExpulsadoDTO() {
    }

    public ExpulsadoDTO(Long id, String razon, Date fechaFin, 
                        Long usuarioId, String usuarioNombre,
                        Long comunidadId, String comunidadNombre) {
        this.id = id;
        this.razon = razon;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.comunidadId = comunidadId;
        this.comunidadNombre = comunidadNombre;
    }
    
    public ExpulsadoDTO(Expulsados expulsado) {
        this.id = expulsado.getId();
        this.razon = expulsado.getRazon();
        this.fechaFin = expulsado.getFechaFin();

        if (expulsado.getUsuario() != null) {
            this.usuarioId = expulsado.getUsuario().getId();
            this.usuarioNombre = expulsado.getUsuario().getNombre();
        }

        if (expulsado.getComunidad() != null) {
            this.comunidadId = expulsado.getComunidad().getId();
            this.comunidadNombre = expulsado.getComunidad().getNombre();
        }
    }

    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public Long getComunidadId() {
        return comunidadId;
    }

    public void setComunidadId(Long comunidadId) {
        this.comunidadId = comunidadId;
    }

    public String getComunidadNombre() {
        return comunidadNombre;
    }

    public void setComunidadNombre(String comunidadNombre) {
        this.comunidadNombre = comunidadNombre;
    }
}