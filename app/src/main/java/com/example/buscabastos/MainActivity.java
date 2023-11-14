package com.example.buscabastos;


import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MiApp";
    boolean visible[][] = new boolean[10][10];
    private int buttonCount = 0;

    public Button botoncito;

    public ImageButton imagenMina;

    int contadorNueves ;

    int[][] matriz = new int[10][10];

    boolean revelar = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout tablero = findViewById(R.id.tablero);

        CeldasAmigasAlo(matriz);

        ColocarMinas(matriz, 12, tablero);

        Imprimir(matriz);

        System.out.println("Tabla con valores calculados");
        System.out.println();

        ContarBarajas(matriz);

    }

    public void CeldasAmigasAlo (int [][] matriz) {
        for (int filas = 0; filas < 10; filas++) {
            for (int columnas = 0; columnas < 10; columnas++) {
                matriz[filas][columnas] = -1;

                botoncito = new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();

                params.width = 100;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;


                params.rowSpec = GridLayout.spec(filas);
                params.columnSpec = GridLayout.spec(columnas);


                botoncito.setLayoutParams(params);


                botoncito.setText("");

             //   matriz.addView(botoncito);

            }
        }

    }

    public void ColocarMinas(int[][] matriz, int cantidadMinas, GridLayout gridLayout) {
        Random random = new Random();

        for (int i = 0; i < cantidadMinas; i++) {
            int fila = random.nextInt(10);
            int columna = random.nextInt(10);

            while (matriz[fila][columna] == -9) {
                fila = random.nextInt(10);
                columna = random.nextInt(10);

            }
            matriz[fila][columna] = -9;
            imagenMina = new ImageButton(this);
            imagenMina.setBackgroundColor(Color.RED);
            AsignarFotoBarajaBomba(imagenMina);
            // imageButton.setVisibility(View.INVISIBLE);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();


            params.width = 100;
            params.height = 132;


            params.rowSpec = GridLayout.spec(fila);
            params.columnSpec = GridLayout.spec(columna);


            imagenMina.setLayoutParams(params);


            gridLayout.addView(imagenMina);
        }
    }

    public void Imprimir(int[][] matriz) {
        for (int filas = 0; filas < 10; filas++) {
            for (int columnas = 0; columnas < 10; columnas++) {
                System.out.print(matriz[filas][columnas] + " : ");

            }
            System.out.println();
        }


    }

    public int ContarBarajas(int[][] matriz) {

        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] resultado = new int[filas][columnas];

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {


                if (matriz[fila][columna] == -1) {
                    int[] filaVecina = {-1, 1, 0, 0, -1, -1, 1, 1};
                    int[] columnaVecina = {0, 0, -1, 1, -1, 1, -1, 1};

                    contadorNueves = 0 ;

                    for (int i = 0; i < 8; i++) {
                        int RowVecinas = fila + filaVecina[i];
                        int ColumnVecinas = columna + columnaVecina[i];

                        if (RowVecinas >= 0 && RowVecinas < filas && ColumnVecinas >= 0 && ColumnVecinas < columnas) {
                            if (matriz[RowVecinas][ColumnVecinas] == -9) {
                                contadorNueves++;
                                //    Log.d(TAG, "Nueves " + contadorNueves );
                            }
                        }
                    }
                    resultado[fila][columna] = contadorNueves;
                } else {
                    resultado[fila][columna] = matriz[fila][columna];
                }
            }
        }

        Imprimir(resultado);
        return filas;
    }


    public void AsignarFotoBarajaBomba(ImageButton bomba) {

        int[] imagenes = {R.drawable.as, R.drawable.rey, R.drawable.sota};

        Random random = new Random();

        int indiceAleatorio = random.nextInt(imagenes.length);

        int imagenId = imagenes[indiceAleatorio];

        bomba.setImageResource(imagenId);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // sigue sin aparecer el menu

        getMenuInflater().inflate(R.menu.barramenu, menu);

        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int alturaBoton = botoncito.getHeight();
            Log.d(TAG, " la altura es " + alturaBoton);
        }
    }


}
