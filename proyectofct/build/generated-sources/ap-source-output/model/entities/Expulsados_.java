package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comunidad;
import model.entities.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T16:15:10")
@StaticMetamodel(Expulsados.class)
public class Expulsados_ { 

    public static volatile SingularAttribute<Expulsados, Usuario> usuario;
    public static volatile SingularAttribute<Expulsados, Long> id;
    public static volatile SingularAttribute<Expulsados, Date> fechaFin;
    public static volatile SingularAttribute<Expulsados, String> razon;
    public static volatile SingularAttribute<Expulsados, Comunidad> comunidad;

}