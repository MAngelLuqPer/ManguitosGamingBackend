package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comunidad;
import model.entities.Publicacion;
import model.entities.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-12T20:08:38")
@StaticMetamodel(Reportes.class)
public class Reportes_ { 

    public static volatile SingularAttribute<Reportes, String> motivo;
    public static volatile SingularAttribute<Reportes, Usuario> usuario;
    public static volatile SingularAttribute<Reportes, Long> id;
    public static volatile SingularAttribute<Reportes, Date> fechaReporte;
    public static volatile SingularAttribute<Reportes, Publicacion> publicacion;
    public static volatile SingularAttribute<Reportes, Comunidad> comunidad;

}