package pem.tema4.vista;

import pem.tema4.AppMediador;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class VistaAgregacion extends AppCompatActivity implements IVistaAgregacion {
	
	private AppMediador appMediador;
	private EditText nombre, identificador, aparato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_agregacion);
		appMediador = AppMediador.getInstance();
		// TODO Añadir el codigo necesario para que el AppMediador conozca a este objeto.
		appMediador.setVistaAgregacion(this);

		nombre = (EditText) this.findViewById(R.id.nombre);
		identificador = (EditText) this.findViewById(R.id.identificador);
		aparato = (EditText) this.findViewById(R.id.aparato);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		appMediador.removePresentadorAgregacion();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_agregacion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_guardar) {
			// TODO Solicitar al presentador de agregacion que trate la opcion correspondiente a guardar receta.
			appMediador.getPresentadorAgregacion().tratarGuardar();
		}
		return super.onOptionsItemSelected(item);
	}

	// TODO Implementar el metodo getNombre() que devuelva el nombre almacenado en el objeto EditText
	// con identificador "nombre" de la vista.
	@Override
	public String getNombre() {
		return nombre.getText().toString();
	}

	// TODO Implementar el metodo getIdentificador() que devuelva el nombre almacenado en el objeto EditText
	// con identificador "identificador" de la vista.
	@Override
	public String getIdentificador() {
		return identificador.getText().toString();
	}

	// TODO Implementar el metodo getAparato() que devuelva el nombre almacenado en el objeto EditText
	// con identificador "aparato" de la vista.
	@Override
	public String getAparato() {
		return aparato.getText().toString();
	}

}
