package model.entities;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comentario;
import model.entities.Comunidad;
import model.entities.Expulsados;
import model.entities.Publicacion;
import model.entities.Reportes;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-11T20:40:22", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> descripcion;
    public static volatile SingularAttribute<Usuario, String> fotoPerfil;
    public static volatile SingularAttribute<Usuario, Integer> numComentarios;
    public static volatile ListAttribute<Usuario, Reportes> reportes;
    public static volatile ListAttribute<Usuario, Expulsados> expulsiones;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Boolean> privacidad;
    public static volatile ListAttribute<Usuario, Publicacion> publicaciones;
    public static volatile SingularAttribute<Usuario, Date> fechaCreacion;
    public static volatile SingularAttribute<Usuario, Integer> numPublicaciones;
    public static volatile ListAttribute<Usuario, Comunidad> comunidades;
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> pwd;
    public static volatile SingularAttribute<Usuario, String> bannerPerfil;
    public static volatile ListAttribute<Usuario, Comentario> comentarios;
    public static volatile SingularAttribute<Usuario, String> email;

}