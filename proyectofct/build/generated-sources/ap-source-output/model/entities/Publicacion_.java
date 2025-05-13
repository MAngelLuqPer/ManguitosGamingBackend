package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comentario;
import model.entities.Comunidad;
import model.entities.Reportes;
import model.entities.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-12T20:08:38")
@StaticMetamodel(Publicacion.class)
public class Publicacion_ { 

    public static volatile SingularAttribute<Publicacion, String> contenido;
    public static volatile ListAttribute<Publicacion, String> imagenes;
    public static volatile SingularAttribute<Publicacion, Integer> numVotos;
    public static volatile SingularAttribute<Publicacion, String> titulo;
    public static volatile ListAttribute<Publicacion, Reportes> reportes;
    public static volatile SingularAttribute<Publicacion, Usuario> usuario;
    public static volatile SingularAttribute<Publicacion, Long> id;
    public static volatile SingularAttribute<Publicacion, Date> fechaPublicacion;
    public static volatile ListAttribute<Publicacion, Comentario> comentarios;
    public static volatile SingularAttribute<Publicacion, Comunidad> comunidad;

}