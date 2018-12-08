package pem.tema4.modelo;

public interface IModelo {
	
	// TODO Añadir el método obtenerDatos() que recupera los datos a mostrar en la lista maestro.
    void obtenerDatos();

	// TODO Añadir el método obtenerDetalles() que recupera los datos de una receta dada su posición
	// en la lista maestro.
    void obtenerDetalle(int posicion);

	// TODO Añadir el método agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas.
    void agregarReceta(Object[] datos);

    // TODO Añadir el metodo eliminarReceta(int posicion) que elimina la receta que se encuentra en la posicion pasa por parametro
    void eliminarReceta(int posicion);

}


