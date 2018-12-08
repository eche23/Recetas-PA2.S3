package pem.tema4.modelo;

import android.os.Bundle;
import android.os.Environment;

import pem.tema4.AppMediador;

public class Modelo implements IModelo {

    private static Modelo singleton = null;
    private ConjuntoDeRecetas conjuntoDeRecetas;
    private AppMediador appMediador;

    private Modelo() {
        appMediador = AppMediador.getInstance();
        conjuntoDeRecetas =	ConjuntoDeRecetas.getInstance();
    }

    public static Modelo getInstance() {
        if (singleton == null)
            singleton = new Modelo();
        return singleton;
    }



    // TODO Implementar el m�todo obtenerDatos() que recupera los datos de la lista de recetas del
	// conjunto de recetas y envia una notificaci�n del tipo AVISO_DATOS_LISTOS al presentador.
    @Override
    public void obtenerDatos() {
        Bundle extras = new Bundle();
        extras.putSerializable(AppMediador.CLAVE_LISTA_RECETAS, conjuntoDeRecetas.getListaDeRecetas());
        appMediador.sendBroadcast(AppMediador.AVISO_DATOS_LISTOS, extras);
    }

    // TODO Implementar el m�todo obtenerDetalle(int posicion) que recupera los datos del detalle de una receta del
	// conjunto de recetas y envia una notificaci�n del tipo AVISO_DETALLE_LISTO al presentador.
    @Override
    public void obtenerDetalle(int posicion) {
        Item receta = conjuntoDeRecetas.getListaDeRecetas().get(posicion);
        String[] datos = new String[4];
        datos[0] = receta.getNombreReceta();
        datos[1] = receta.getAparatoReceta();
        datos[2] = Environment.getExternalStorageDirectory().getAbsolutePath() + "/imagenes/" + receta.getIdReceta() + ".png";
        datos[3] = AccesoArchivo.leerReceta(receta.getIdReceta() + ".txt");

        Bundle extras = new Bundle();
        extras.putStringArray(AppMediador.CLAVE_DETALLE_RECETA, datos);
        appMediador.sendBroadcast(AppMediador.AVISO_DETALLE_LISTO, extras);
    }

    // TODO A�adir el m�todo agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas. En la posici�n 0 se almacena el nombre del archivo de imagen, en la posici�n 1 se
	// almacena el nombre de la receta y en la posici�n 2 se almacena la descripci�n de la receta.
    @Override
    public void agregarReceta(Object[] datos) {
        conjuntoDeRecetas.agregarItem(new Item((String) datos[1],(String) datos[0],(String) datos[2]));
        appMediador.sendBroadcast(AppMediador.AVISO_DATOS_AGREGADOS, null);
    }
    // TODO A�adir el metodo eliminarReceta(int posicion) que elimina la receta que se encuentra en la posicion pasa por parametro
    @Override
    public void eliminarReceta(int posicion) {
        Item receta = conjuntoDeRecetas.getListaDeRecetas().remove(posicion);
        Bundle extras = new Bundle();
        extras.putSerializable(AppMediador.CLAVE_LISTA_RECETAS, conjuntoDeRecetas.getListaDeRecetas());
        appMediador.sendBroadcast(AppMediador.AVISO_DATOS_ELIMINADOS, extras);
    }

}

