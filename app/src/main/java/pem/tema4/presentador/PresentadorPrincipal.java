package pem.tema4.presentador;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import pem.tema4.AppMediador;
import pem.tema4.modelo.IModelo;
import pem.tema4.modelo.Item;
import pem.tema4.modelo.Modelo;

public class PresentadorPrincipal implements IPresentadorPrincipal {

    private AppMediador appMediador;

	// TODO Declarar una variable modelo para acceder al Modelo
    private IModelo modelo;

	// TODO Declarar e implementar el receptor broadcast que espera por la notificaci�n del modelo. 
	// El modelo notificar� cuando los datos de los tel�fonos a notificar est�n disponibles.
    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_DATOS_LISTOS)){
                ArrayList<Item> infoReceta = (ArrayList<Item>) intent.getSerializableExtra(AppMediador.CLAVE_LISTA_RECETAS);
                String[] datos = new String[infoReceta.size()];
                for (int i = 0; i < infoReceta.size(); i++){
                    datos[i] = infoReceta.get(i).getNombreReceta();
                }
                appMediador.getVistaPrincipal().actualizarMaestro(datos);
            } else if (intent.getAction().equals(AppMediador.AVISO_DETALLE_LISTO)){
                String[] datosDetalle = intent.getStringArrayExtra(AppMediador.CLAVE_DETALLE_RECETA);
                Object[] datos = new Object[3];
                datos[0] = datosDetalle[0] + "(" + datosDetalle[1] + ")";
                datos[1] = BitmapFactory.decodeFile(datosDetalle[2]);
                datos[2] = datosDetalle[3];
                appMediador.getVistaPrincipal().actualizarDetalle(datos);
            } else if (intent.getAction().equals(AppMediador.AVISO_DATOS_ELIMINADOS)){
                ArrayList<Item> infoReceta = (ArrayList<Item>) intent.getSerializableExtra(AppMediador.CLAVE_LISTA_RECETAS);
                String[] datos = new String[infoReceta.size()];
                for (int i = 0; i < infoReceta.size(); i++){
                    datos[i] = infoReceta.get(i).getNombreReceta();
                }
                appMediador.getVistaPrincipal().actualizarMaestro(datos);
            }
            appMediador.unRegisterReceiver(this);
        }
    };

	// TODO Implementar un constructor que crea el modelo.
    public PresentadorPrincipal(){
        appMediador = AppMediador.getInstance();
        modelo = Modelo.getInstance();
    }


    // TODO Implementar el m�todo obtenerDatos() que registra el receptor para recibir notificaciones y
	// solicita al modelo que recupere los datos de la lista maestro.
    @Override
    public void obtenerDatos() {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DATOS_LISTOS);
        modelo.obtenerDatos();

    }

    // TODO Implementar el m�todo obtenerDetalle(int posicion) que registra el receptor para recibir
	// notificaciones y solicita al modelo que recupere los datos de la lista detalle para una receta dada su posici�n.
    @Override
    public void obtenerDetalle(int posicion) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DETALLE_LISTO);
        modelo.obtenerDetalle(posicion);
    }


    // TODO Implementar el m�todo tratarAgregar() que lanza la vista de agregaci�n por medio del mediador.
    @Override
    public void tratarAgregar() {
        appMediador.launchActivity(appMediador.getVistaParaAgregacion(), appMediador.getPresentadorPrincipal(), null);
    }

    // TODO A�adir el metodo eliminarReceta(int posicion) que elimina la receta que se encuentra en la posicion pasa por parametro
    @Override
    public void eliminarReceta(int posicion) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DATOS_ELIMINADOS);
        modelo.eliminarReceta(posicion);
    }

}
