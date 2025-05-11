package model.entities;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comentario;
import model.entities.Publicacion;
import model.entities.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-11T20:40:22", comments="EclipseLink-2.7.12.v20230209-rNA")
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