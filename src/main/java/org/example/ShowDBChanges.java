package org.example;

import com.google.firebase.database.*;
import java.awt.*;
import java.io.IOException;

public class ShowDBChanges implements Runnable {

    Controller controller;

    public ShowDBChanges() throws AWTException {

        controller = new Controller();
    }

    public void run() {

        FireBaseService fbs = null;

        try {

            fbs = new FireBaseService();

        } catch (IOException e) {

            e.printStackTrace();

        }
        DatabaseReference ref = fbs.getDb()
                .getReference("/");
        ref.addValueEventListener(new ValueEventListener() {

            // Al actualizar algún dato de Firebase se ejecuta el método automaticamente
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Llamamos a los métodos de "clickL" y "clickR" al tener el valor en "true" en el Firebase
                controller.clikL(dataSnapshot.child("usuario").child("clicks").child("clickL").getValue().toString().equals("true"));
                controller.clickR(dataSnapshot.child("usuario").child("clicks").child("clickR").getValue().toString().equals("true"));

                // LLamamos al método move al cambiar de valor y le pasamos el valor del Firebase.
                controller.move(
                        (int) Double.parseDouble(dataSnapshot.child("usuario").child("pos").child("x_pos").getValue().toString()),
                        (int) Double.parseDouble(dataSnapshot.child("usuario").child("pos").child("y_pos").getValue().toString())
                );

                // Llamamos a los métodos "borrar" y "intro" al tener el valor en "true" en el Firebase
                if (dataSnapshot.child("usuario").child("texto").child("borrar").getValue().toString().equals("true")){
                    controller.borrar();
                }
                if (dataSnapshot.child("usuario").child("texto").child("intro").getValue().toString().equals("true")){
                    controller.intro();
                }

                // Llamamos al metod "flechasDireccion" y le pasamos el valor en función de que botón se ha pulsado
                if(dataSnapshot.child("usuario").child("clicks").child("up").getValue().toString().equals("true")){

                    controller.flechasDireccion("up");

                } else if(dataSnapshot.child("usuario").child("clicks").child("left").getValue().toString().equals("true")){

                    controller.flechasDireccion("left");

                }else if(dataSnapshot.child("usuario").child("clicks").child("down").getValue().toString().equals("true")){

                    controller.flechasDireccion("down");

                }else if(dataSnapshot.child("usuario").child("clicks").child("right").getValue().toString().equals("true")){

                    controller.flechasDireccion("right");

                }

                // LLamamos al metodo "write" y le pasamos el valor del Firebase
                controller.write(dataSnapshot.child("usuario").child("texto").child("texto").getValue().toString());

            }

            public void onCancelled(DatabaseError error) {
                System.out.print("Error: " + error.getMessage());
            }
        });
    }
}