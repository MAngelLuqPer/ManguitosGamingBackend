package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Expulsados;
import model.entities.Publicacion;
import model.entities.Reportes;
import model.entities.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-12T20:08:38")
@StaticMetamodel(Comunidad.class)
public class Comunidad_ { 

    public static volatile SingularAttribute<Comunidad, String> descripcion;
    public static volatile SingularAttribute<Comunidad, String> reglas;
    public static volatile SingularAttribute<Comunidad, String> banner;
    public static volatile ListAttribute<Comunidad, Reportes> reportes;
    public static volatile ListAttribute<Comunidad, Usuario> usuarios;
    public static volatile SingularAttribute<Comunidad, String> nombre;
    public static volatile ListAttribute<Comunidad, Expulsados> expulsados;
    public static volatile ListAttribute<Comunidad, Publicacion> publicaciones;
    public static volatile SingularAttribute<Comunidad, String> foto;
    public static volatile SingularAttribute<Comunidad, Integer> numMiembros;
    public static volatile SingularAttribute<Comunidad, Usuario> usuAdmin;
    public static volatile SingularAttribute<Comunidad, Date> fechaCreacion;
    public static volatile SingularAttribute<Comunidad, Long> id;

}