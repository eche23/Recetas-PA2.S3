package pem.tema4.modelo;

public interface IModelo {
	
	// TODO A�adir el m�todo obtenerDatos() que recupera los datos a mostrar en la lista maestro.
    void obtenerDatos();

	// TODO A�adir el m�todo obtenerDetalles() que recupera los datos de una receta dada su posici�n
	// en la lista maestro.
    void obtenerDetalle(int posicion);

	// TODO A�adir el m�todo agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas.
    void agregarReceta(Object[] datos);

    // TODO A�adir el metodo eliminarReceta(int posicion) que elimina la receta que se encuentra en la posicion pasa por parametro
    void eliminarReceta(int posicion);

}


