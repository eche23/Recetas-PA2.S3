package pem.tema4.presentador;

public interface IPresentadorPrincipal {
	
	// TODO Añadir el método obtenerDatos() de tipo void que solicita los datos de la lista maestro al modelo.
    void obtenerDatos();

	// TODO Añadir el método obtenerDetalles() que recupera los datos de una receta dada su posición
	// en la lista maestro.
    void obtenerDetalle(int posicion);

	// TODO Añadir el método tratarAgregar() que lanza la vista de agregación por medio del mediador.
    void tratarAgregar();

    // TODO Añadir el metodo eliminarReceta(int posicion) que elimina la receta que se encuentra en la posicion pasa por parametro
    void eliminarReceta(int posicion);
}
