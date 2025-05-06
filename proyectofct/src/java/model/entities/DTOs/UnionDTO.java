/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.DTOs;

/**
 *
 * @author mangel
 */
public class UnionDTO {
    public Long comunidadId;
    public Long usuarioId;

    public UnionDTO() {}

    public Long getComunidadId() {
        return comunidadId;
    }

    public void setComunidadId(Long comunidadId) {
        this.comunidadId = comunidadId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
}