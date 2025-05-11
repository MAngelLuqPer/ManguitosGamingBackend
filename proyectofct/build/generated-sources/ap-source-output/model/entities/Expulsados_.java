package model.entities;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Comunidad;
import model.entities.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-11T20:40:22", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Expulsados.class)
public class Expulsados_ { 

    public static volatile SingularAttribute<Expulsados, Usuario> usuario;
    public static volatile SingularAttribute<Expulsados, Long> id;
    public static volatile SingularAttribute<Expulsados, Date> fechaFin;
    public static volatile SingularAttribute<Expulsados, String> razon;
    public static volatile SingularAttribute<Expulsados, Comunidad> comunidad;

}