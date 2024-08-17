# Mutation testing Ejemplo

## Contexto 

Proyecto de ejemplo para ver temas de TDD y Mutation Testing.

Como ingesta de datos se usa un archivo csv dentro de resources llamado publicaciones.csv que contiene la base de las publicaciones 
con la estructura siguiente:

Id,IdPublicacionPadre,IdTipoPropiedad,Anunciante,Ubicacion,Precio,IdNivel,XFactor,FechaPublicacion

### Definiciones 
    Id -> Id de la publicacion , tipo Numerico , Obligatorio
    IdPublicacionPadre -> Id de la publicacion Padre , Tipo Numerico , Opcional
    IdTipoPropiedad -> Id del tipo de Propiedad , Tipo Numerico [1,2,10] Valores posibles , Obligatorio
    Anunciante -> Nombre del anunciante, Tipo Texto , Obligatorio
    Ubicacion -> Ubicacion del lugar , Tipo Texto , Obligatorio
    Precio -> Valor de la Publicacion , Tipp Numerico , Opcional (Para las de tipo 10 IdTipoPropiedad) Obligatorio (Para las de tipo 1-2 IdTipoPropiedad)
    IdNivel -> Id del nivel de la publicacion , Tipo Numerico [1,2,3,4,5] valores posibles , Obligatorio
    XFactor -> Una ponderacion que se da de la publicacion, Tipo Numerio [0-100] valores posibles , Obligatorio
    FechaPublicacion -> Fecha de cuando se publico , Tipo Texto formato dd-MM-YYYY , Obligatorio 

### APP

La aplicacion brinda un servicio unico para obtener el listado de publicaciones, pudiendo excluir propiedades (pasando sus ids), pudiendo excluir anunciantes (pasando sus nombres) y ordenando el listado por 4 Criterios [Default,Precio,Nivel,Xfactor]  

Criterio Ordenamiento explicacion:
- Default -> Como viene.
- Precio -> De mayor a menor por el precio.
- Nivel -> De mayor nivel a menor.
- Xfactor -> De mayor nivel a menor


## Swagger 

   localhost:http://localhost:8080/swagger-ui/index.html


## Requerimiento


