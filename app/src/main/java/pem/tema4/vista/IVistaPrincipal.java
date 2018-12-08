package pem.tema4.vista;

public interface IVistaPrincipal {
	
	// TODO A�adir el m�todo actualizarMaestro(Object[] datos) que actualiza la lista maestro con los datos
	// recibidos por par�metros. En cada entrada del vector, est� el nombre de una receta.
    void actualizarMaestro(Object[] datos);

	// TODO A�adir el m�todo actualizarDetalle(Object[] datos) que actualiza la lista detalle con los datos
	// de una receta recibidos por par�metros. As�:
	// datos[0] = almacena el nombre de la receta y en qu� se usa para realizarla (String).
	// datos[1] = almacena una imagen de la receta (Bitmap).
	// datos[2] = almacena la descripci�n de la receta (String).
    void actualizarDetalle(Object[] datos);

    // TODO A�adir el metodo presentarAlerta(int posicion) que prsenta una alerta al elimanar la receta que se encuentra en la posicion pasa por parametro
    void presentarAlerta(int posicion);

}
