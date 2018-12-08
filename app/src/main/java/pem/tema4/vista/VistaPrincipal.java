package pem.tema4.vista;

import pem.tema4.AppMediador;
import pem.tema4.presentador.IPresentadorPrincipal;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class VistaPrincipal extends AppCompatActivity implements IVistaPrincipal,
		FragmentoMaestro.EscuchaFragmento {
	
	private AppMediador appMediador;
	private IPresentadorPrincipal presentadorPrincipal;
	private FragmentoMaestro fragmentoMaestro;
	private FragmentoDetalle fragmentoDetalle;
    //Declaraci�n de un objeto llamado fab, que corresponda con un bot�n flotante
    private FloatingActionButton fab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_principal);
		appMediador = (AppMediador)this.getApplication();
		presentadorPrincipal = appMediador.getPresentadorPrincipal();
		appMediador.setVistaPrincipal(this);
		// Se comprueba si la actividad est� usando una versi�n de layout con un contenedor de fragmentos
		// de tipo FrameLayout (si es as�, es un smartphone y no permite m�s de un fragmento en pantalla),
		// por tanto, s�lo se a�ade el primero
		if (findViewById(R.id.contenedorDeFragmentos) != null) {
			// se crea el fragmento maestro y se a�ade al contenedor de fragmentos
			fragmentoMaestro = new FragmentoMaestro();
			getSupportFragmentManager().beginTransaction()
				.add(R.id.contenedorDeFragmentos, fragmentoMaestro)
				.commit();
		} else {
			// Si el layout no es de panel �nico (es una tableta) se permiten m�s de un fragmento
			// por tanto, se crean los dos fragmentos y se a�aden a sus layouts seg�n el xml sw600dp
			fragmentoMaestro = new FragmentoMaestro();
			getSupportFragmentManager().beginTransaction()
				.add(R.id.contenedor_lista, fragmentoMaestro)
				.commit();
			
			fragmentoDetalle = new FragmentoDetalle();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_detalle, fragmentoDetalle)
                    .commit();
		}
		// Creaci�n de un bot�n flotante para que, cuando se seleccione, solicite al presentador principal que trate
		// la opci�n de agregar una nueva receta
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //TODO Solicitar al presentador que agregue una nueva receta
				presentadorPrincipal.tratarAgregar();
            }
        });
	}

	
	@Override
	protected void onStart() {
		super.onStart();
		// TODO Solicitar al presentador que recupere los datos desde el modelo.
		presentadorPrincipal.obtenerDatos();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		appMediador.removePresentadorPrincipal();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.vista_principal, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_salir:
		    int id = android.os.Process.myPid();
		    android.os.Process.killProcess(id);
		}
	    return super.onOptionsItemSelected(item);
	}

    @SuppressLint("RestrictedApi")
	@Override
    public void alSeleccionarItem(int posicion) {
        // Si no hay fragmento detalle, se crea la vista detalle (esto ocurre si es panel �nico)
        if (fragmentoDetalle == null)
            fragmentoDetalle = new FragmentoDetalle();

        if (findViewById(R.id.contenedorDeFragmentos) != null) {
            // si es de panel �nico, se reemplaza, en el contenedor de fragmentos
            // el fragmento que est� visible por el de la vista detalle
            FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedorDeFragmentos, fragmentoDetalle);
            transaccion.addToBackStack(null);
            transaccion.commit();
            // Quita la visibilidad al bot�n flotante (para que no aparezca en el detalle)
			fab.setVisibility(View.GONE);
            // realiza la transacci�n
            getSupportFragmentManager().executePendingTransactions();
        }
        // TODO Solicitar al presentador que trate el item seleccionado.
		presentadorPrincipal.obtenerDetalle(posicion);

    }


    // Redefinici�n del m�todo onBackPressed para que si se tiene un dispositivo de panel �nico, y el bot�n
    // flotante no est� visible (est� el fragmento detalle en pantalla), reemplace el fragmento detalle por el
    // fragmento maestro. En cualquier otro caso, la actividad debe finalizar (porque se quiere salir de ella)
    @SuppressLint("RestrictedApi")
	@Override
    public void onBackPressed(){
	    if (findViewById(R.id.contenedorDeFragmentos)!=null){
	        //es panel �nico
            if (fab.getVisibility() != View.VISIBLE){
                //est� en la vista del detalle
                FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.contenedorDeFragmentos,fragmentoMaestro);
                transaccion.addToBackStack(null);
                transaccion.commit();
                fab.setVisibility(View.VISIBLE);
                presentadorPrincipal.obtenerDatos();

            }else{
                //no est� en la vista del detalle
                finish();
            }
        }else
	        //no es panel �nico
            finish();
    }



	// TODO A�adir el m�todo actualizarMaestro(Object[] datos) que actualiza la lista maestro con los datos
	// recibidos por par�metros. En cada entrada del vector, est� el nombre de una receta.	
	@Override
	public void actualizarMaestro(Object[] datos) {
		// TODO Dentro del m�todo actualizarMaestro(Object[] datos), crear la lista maestro con los nombres
		// de las recetas que entran por par�metros.
		fragmentoMaestro.crearLista((String[]) datos);
		// TODO Dentro del m�todo actualizarMaestro(Object[] datos), si es una pantalla multi-panel, presentar
		// el detalle de la primera receta.
		if (findViewById(R.id.contenedorDeFragmentos) == null){
			presentadorPrincipal.obtenerDetalle(0);
		}


	}

	// TODO A�adir el m�todo actualizarDetalle(Object[] datos) que actualiza los valores del detalle, 
	// teniendo en cuenta que en la posici�n 0 del vector est� el nombre de la receta y en qu� se usa 
	// para realizarla, en la posici�n 1 del vector est� la imagen como un Bitmap y en en la posici�n 3 
	// del vector est� la descripci�n de la receta.
	@Override
	public void actualizarDetalle(Object[] datos) {
		fragmentoDetalle.actualizarNombreReceta((String) datos[0]);
		fragmentoDetalle.actualizarImagenReceta((Bitmap) datos[1]);
		fragmentoDetalle.actualizarDescripcion((String) datos[2]);
	}

	// TODO A�adir el metodo presentarAlerta(int posicion) que prsenta una alerta al elimanar la receta que se encuentra en la posicion pasa por parametro
	@Override
	public void presentarAlerta(final int posicion) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Aviso");
		alerta.setMessage("�Quiere eliminar este item de la lista?");
		alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				presentadorPrincipal.eliminarReceta(posicion);
			}
		});
		alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				presentadorPrincipal.obtenerDatos();
			}
		});
		alerta.show();
	}


}
