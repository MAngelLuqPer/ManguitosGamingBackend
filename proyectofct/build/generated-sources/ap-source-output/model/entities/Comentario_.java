package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comentario;
import model.entities.Publicacion;
import model.entities.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-12T20:08:38")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, String> contenido;
    public static volatile ListAttribute<Comentario, Comentario> respuestas;
    public static volatile SingularAttribute<Comentario, Comentario> comentarioPadre;
    public static volatile SingularAttribute<Comentario, Date> fechaComentario;
    public static volatile SingularAttribute<Comentario, Usuario> usuario;
    public static volatile SingularAttribute<Comentario, Long> id;
    public static volatile SingularAttribute<Comentario, Publicacion> publicacion;

}