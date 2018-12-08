package pem.tema4.presentador;

public interface IPresentadorPrincipal {
	
	// TODO A�adir el m�todo obtenerDatos() de tipo void que solicita los datos de la lista maestro al modelo.
    void obtenerDatos();

	// TODO A�adir el m�todo obtenerDetalles() que recupera los datos de una receta dada su posici�n
	// en la lista maestro.
    void obtenerDetalle(int posicion);

	// TODO A�adir el m�todo tratarAgregar() que lanza la vista de agregaci�n por medio del mediador.
    void tratarAgregar();

    // TODO A�adir el metodo eliminarReceta(int posicion) que elimina la receta que se encuentra en la posicion pasa por parametro
    void eliminarReceta(int posicion);
}
