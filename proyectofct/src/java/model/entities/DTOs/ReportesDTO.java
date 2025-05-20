package model.entities.DTOs;

import java.util.Date;
import model.entities.Reportes;

public class ReportesDTO {
    private Long id;
    private String motivo;
    private Date fechaReporte;
    private Long usuarioId;
    private String usuarioNombre;
    private Long publicacionId;
    private String publicacionTitulo;
    private Long comunidadId;
    private String comunidadNombre;

    // Constructores
    public ReportesDTO() {
    }

    public ReportesDTO(Reportes reporte) {
        this.id = reporte.getId();
        this.motivo = reporte.getMotivo();
        this.fechaReporte = reporte.getFechaReporte();
        this.usuarioId = reporte.getUsuario() != null ? reporte.getUsuario().getId() : null;
        this.usuarioNombre = reporte.getUsuario() != null ? reporte.getUsuario().getNombre(): null;
        this.publicacionId = reporte.getPublicacion()!= null ? reporte.getPublicacion().getId(): null;
        this.publicacionTitulo = reporte.getPublicacion()!= null ? reporte.getPublicacion().getTitulo(): null;
        this.comunidadId = reporte.getComunidad()!= null ? reporte.getComunidad().getId(): null;
        this.comunidadNombre = reporte.getComunidad()!= null ? reporte.getComunidad().getNombre(): null;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
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

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public String getPublicacionTitulo() {
        return publicacionTitulo;
    }

    public void setPublicacionTitulo(String publicacionTitulo) {
        this.publicacionTitulo = publicacionTitulo;
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