package org.example;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Controller {

    int tamanoTexto = 0;

    Robot robot = new Robot();

    public Controller() throws AWTException {}

    public void move(int x, int y){

        robot.mouseMove(MouseInfo.getPointerInfo().getLocation().x + x, MouseInfo.getPointerInfo().getLocation().y + y);

    }
    public void clikL(boolean clickL){

        if (clickL){

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        }
    }

    public void clickR(boolean clickR){

        if (clickR) {

            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

        }
    }

    public void write(String s){

        char caracter = s.charAt(s.length() - 1);
        int ascii = caracter;

        System.out.println(caracter);
        System.out.println(ascii);

        // En función del tamaño de la String, ejecutamos introducimos el último caracter de la String recibida por el Firebase, o ejecutamos el boton "BORRAR".
        // Si el caracter es una mayuscula, tenemos que ejecutar el boton "MAYUS"
        if (s.length() == 1 && tamanoTexto > 2 || tamanoTexto <s.length()){

            if (Character.isUpperCase(caracter)){

                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(ascii));
                robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(ascii));
                robot.keyRelease(KeyEvent.VK_SHIFT);

            } else {

                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(ascii));
                robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(ascii));

            }
        } else if (s.length() < tamanoTexto) {

            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        }

        // Guardamos en una variable elñ valor del tamaño del String
        tamanoTexto = s.length();

    }

    public void borrar(){

        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);

    }

    public void intro(){

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void flechasDireccion(String direccion){

        if (direccion.equals("up")){

            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_UP);

        } else if (direccion.equals("left")){

            robot.keyPress(KeyEvent.VK_LEFT);
            robot.keyRelease(KeyEvent.VK_LEFT);

        }else if (direccion.equals("right")){

            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);

        } else {

            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);

        }
    }
}
