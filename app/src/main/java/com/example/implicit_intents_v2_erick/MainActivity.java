package com.example.implicit_intents_v2_erick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //variables que contrendrán el URI del editText
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtener la referencia del correspondiente editText
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    /**
     * Implementacion de abrir sitio
     * @param view
     */
    public void openWebsite(View view) {
        //asignar el valor del editText capturado
        String url = mWebsiteEditText.getText().toString();

        //se convierte en un objeto URI
        Uri webpage = Uri.parse(url);

        //se crea un intent para realizar la accion y el URI son los datos
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //si hay una app para controlar este intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    /**
     * Implementación de abrir localización
     * @param view
     */
    public void openLocation(View view) {
        //asignar el valor del editText capturado
        String loc = mLocationEditText.getText().toString();

        //se convierte en un objeto URI con ubicación geografica
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        //se crea un intent para realizar la accion y el URI son los datos
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //si hay una app para controlar este intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    /**
     * Implementación de compartir texto
     * @param view
     */
    public void shareText(View view) {
        //asignar el valor del editText capturado
        String txt = mShareTextEditText.getText().toString();

        //definir el mime del texto
        String mimeType = "text/plain";

        //se llama con estos métodos
        ShareCompat.IntentBuilder
                //El que lanza este recurso compartido
                .from(this)
                //El tipo MIME del elemento que se va a compartir.
                .setType(mimeType)
                //El título que aparece en el selector de la aplicación del sistema.
                .setChooserTitle("Share this text with: ")
                //El texto real que se va a compartir
                .setText(txt)
                //Mostrar el selector de la aplicación del sistema y enviar el archivo
                .startChooser();
    }

    /**
     * Metodo para abrir la camara
     * @param view
     */
    public void openCamera(View view){
        //nueva intent que lleve a la camara
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //checar si hay una app de camara
        if (intentCamera.resolveActivity(getPackageManager()) != null) {
            startActivity(intentCamera);
        }
        else {
            //caso contario, se muestra un mensaje en el log
            Log.d("Implicit Intents", "Can't open the camera");
        }
    }
}