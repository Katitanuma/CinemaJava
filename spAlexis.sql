delimiter $$
CREATE PROCEDURE sp_mostrarGenero()
begin
		select generopelicula
        from genero;
    end $$
delimiter ;

delimiter $$
CREATE PROCEDURE sp_mostrarClasificacion()
begin
		select clasificacion
        from clasificacion;
    end $$
delimiter ;

delimiter $$
CREATE PROCEDURE sp_obtenerIdGenero(
	in pGenero nvarchar(50)
)
begin
		select idgenero
        from genero
         where generopelicula=pGenero;
    end $$
delimiter ;

delimiter $$
CREATE PROCEDURE sp_obtenerIdClasificacion(
	in pClasificacion nvarchar(50)
)
begin
		select idclasificacion
        from clasificacion
        where clasificacion=pClasificacion;
    end $$
delimiter ;

delimiter $$
CREATE PROCEDURE sp_mostrarPelicula()
begin
		
		select p.idpelicula, p.nombrepelicula, p.sinopsis, p.lanzamiento, p.duracion,p.imagenpelicula, p.url, g.generopelicula,c.clasificacion
        from pelicula p inner join genero g
						 on p.idgenero=g.idgenero
                         inner join clasificacion c
                         on p.idclasificacion=c.idclasificacion;
   end $$
delimiter ;

delimiter $$
CREATE PROCEDURE sp_mostrarPeliculaporCod(
	in pFiltro nvarchar(50)
)
begin
		select p.idpelicula, p.nombrepelicula, p.sinopsis, p.lanzamiento, p.duracion,p.imagenpelicula, p.url, g.generopelicula,c.clasificacion
        from pelicula p inner join genero g
						 on p.idgenero=g.idgenero
                         inner join clasificacion c
                         on p.idclasificacion=c.idclasificacion
        where p.idpelicula like concat('%', pFiltro, '%');
        
    end $$
delimiter ;

delimiter $$
CREATE PROCEDURE sp_mostrarPeliculaporNombre(
	in pFiltro nvarchar(50)
)
begin
		select p.idpelicula, p.nombrepelicula, p.sinopsis, p.lanzamiento, p.duracion,p.imagenpelicula, p.url, g.generopelicula,c.clasificacion
        from pelicula p inner join genero g
						 on p.idgenero=g.idgenero
                         inner join clasificacion c
                         on p.idclasificacion=c.idclasificacion
        where p.nombrepelicula like concat('%', pFiltro, '%');
        
    end $$
delimiter ;

delimiter $$
create procedure sp_autoIncrementarPelicula()
	begin
		select max(LAST_INSERT_ID(idpelicula)) + 1 as idpelicula
        from pelicula;
    end $$
delimiter ;



delimiter $$
create procedure sp_insertarPelicula(
	in pIdPelicula int,
	in pNpelicula varchar(50),
    in pSinopsis varchar(400),
    in pLanzamiento varchar(50),
    in pDuracion varchar(50),
    in pImagen Blob,
    in pUrl varchar(300),
    in pIdGenero int,
    in pIdClasificacion int,
    in pIdUsuario int
)
	begin
		insert into pelicula(nombrepelicula, sinopsis, lanzamiento, duracion, imagenpelicula, url, idgenero, idclasificacion,idusuario)
        value(pNpelicula, pSinopsis, pLanzamiento, pDuracion, pImagen,pUrl,pIdGenero,pIdClasificacion,pIdUsuario);
    end $$
delimiter ;	


delimiter $$
create procedure sp_actualizarPelicula(
	in pIdPelicula int,
	in pNpelicula varchar(50),
    in pSinopsis varchar(400),
    in pLanzamiento varchar(50),
    in pDuracion varchar(50),
    in pImagen Blob,
    in pUrl varchar(300),
    in pIdGenero int,
    in pIdClasificacion int,
    in pIdUsuario int
)
	begin
		update pelicula
        set nombrepelicula = pNPelicula,
			sinopsis = pSinopsis,
            lanzamiento = pLanzamiento,
            duracion = pDuracion,
            imagenpelicula=pImagen,
            url=pUrl,
            idgenero=pIdGenero,
            idclasificacion=pIdClasificacion,
            idusuario=pIdUsuario
		where idpelicula = pIdPelicula;
    end $$
delimiter ;

delimiter $$
create procedure sp_eliminarPelicula(
	in pIdPelicula int
)
	begin
		delete
        from pelicula
		where idpelicula = pIdPelicula;
    end $$
delimiter ;