package com.facci.tareageolocalizaconca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LocationManager locManager; //es una clase, administra la lista de los proveedores que tiene el celular para acceder a las coordenadas
    private double latitud;     // declarar variable, obtener
    private double longitud; // declarar variave
    double altitud = location.getAltitud();
    float velocidad = location.getSepeed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializar LocManager
        locManager = (LocationManager)getSystemService(LOCATION_SERVICE); // sirve

        //Obtener la lista de proveedores

        List<String> listaProviders = locManager.getAllProviders();

        //Obtener el proveedor de la lista

        LocationProvider provider = locManager.getProvider(listaProviders.get(0));

    }

    //Funcionalidad al boton

    public void ActualizarLatLongClick(View v){


        if(ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){

        }
        //LLAMADO AL LISTENER
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);

    }


    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            longitud = location.getLongitude();
            latitud = location.getLongitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText) findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText) findViewById(R.id.txtLatitud);

                    //aqui se ponen la altitud y la velocidad



                    txtLatitud.setText(latitud+"");
                    txtLongitud.setText(longitud+"");
                }
            });

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

}

