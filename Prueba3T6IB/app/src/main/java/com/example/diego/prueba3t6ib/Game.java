package com.example.diego.prueba3t6ib;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.RotateBy;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.ScaleTo;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Diego on 18/11/2017.
 */

public class Game {
    CCGLSurfaceView _VistaJuego;
    CCSize PantallaDispositivo;
    Sprite spriteVerde;
    Sprite spriteRojo;
    Sprite spriteAzul;
    Sprite spriteBlanco;

    public Game(CCGLSurfaceView Vistadeljuego)
    {
        _VistaJuego = Vistadeljuego;
    }
    public void startgame()
    {
        Director.sharedDirector().attachInView(_VistaJuego);
        PantallaDispositivo = Director.sharedDirector().displaySize();
        Director.sharedDirector().runWithScene(Gamescene());
    }
    private Scene Gamescene()
    {
        Scene scene = Scene.node();

        Capa C = new Capa();
        scene.addChild(C);

        return scene;

    }
    class Capa extends Layer {
        public Capa()

        {
            creaSprite();
            this.setIsTouchEnabled(true);
        }

       @Override
        public boolean ccTouchesBegan(MotionEvent event) {

           Random random = new Random();
           int spriteElegido = random.nextInt(4);
           RotateBy rotateByDerecha = RotateBy.action(1f, 360f);
           RotateBy rotateByIzquierda = RotateBy.action(1f, -360f);
           RotateBy rotateByIzquierda2 = RotateBy.action(1f, -360f);
           RotateBy rotateByIzquierda3 = RotateBy.action(1f, -360f);
            Log.d("Random " , "El num es " + spriteElegido);

           switch (spriteElegido){
               case 0:
                   spriteVerde.runAction(rotateByDerecha);
                   spriteRojo.runAction(rotateByIzquierda);
                   spriteAzul.runAction(rotateByIzquierda2);
                   spriteBlanco.runAction(rotateByIzquierda3);
                   Log.d("Opuesto", "El sprite VERDE gira para la derecha y todo el resto para la izquierda");
                   break;
               case 1:
                   spriteRojo.runAction(rotateByDerecha);
                   spriteVerde.runAction(rotateByIzquierda);
                   spriteAzul.runAction(rotateByIzquierda2);
                   spriteBlanco.runAction(rotateByIzquierda3);
                   Log.d("Opuesto", "El sprite ROJO gira para la derecha y todo el resto para la izquierda");
                   break;
               case 2:
                   spriteAzul.runAction(rotateByDerecha);
                   spriteRojo.runAction(rotateByIzquierda);
                   spriteVerde.runAction(rotateByIzquierda2);
                   spriteBlanco.runAction(rotateByIzquierda3);
                   Log.d("Opuesto", "El sprite AZUL gira para la derecha y todo el resto para la izquierda");
                   break;
               case 3:
                   spriteBlanco.runAction(rotateByDerecha);
                   spriteRojo.runAction(rotateByIzquierda);
                   spriteAzul.runAction(rotateByIzquierda);
                   spriteVerde.runAction(rotateByIzquierda);
                   Log.d("Opuesto", "El sprite BLANCO gira para la derecha y todo el resto para la izquierda");
                   break;
           }

            return true;
        }

        @Override
        public boolean ccTouchesMoved(MotionEvent event) {

            MoveBy mover = MoveBy.action(4f,30,0);
            Log.d("Moved", "entro");
            /*
            if ((spriteVerde.getPositionX()+spriteVerde.getWidth()/2) < PantallaDispositivo.getWidth())
            {
                spriteVerde.runAction(mover);
            }

            if (((spriteRojo.getPositionX()+spriteRojo.getWidth()/2)+30) < PantallaDispositivo.getWidth())
            {
                spriteRojo.runAction(mover);
            }

            if (((spriteAzul.getPositionX()+spriteAzul.getWidth()/2)+30) < PantallaDispositivo.getWidth())
            {
                spriteAzul.runAction(mover);
            }

            if (((spriteBlanco.getPositionX()+spriteBlanco.getWidth()/2)+30) < PantallaDispositivo.getWidth())
            {
                spriteBlanco.runAction(mover);
            }*/


            do {
                spriteVerde.runAction(mover);
            }while (((spriteVerde.getPositionX() + spriteVerde.getWidth()/2)+30) < PantallaDispositivo.getWidth());

            do {
                spriteRojo.runAction(mover);
            }while (((spriteRojo.getPositionX() + spriteRojo.getWidth()/2)+30) < PantallaDispositivo.getWidth());


            do {
                    spriteAzul.runAction(mover);
            }while (((spriteAzul.getPositionX() + spriteAzul.getWidth()/2) + 30) < PantallaDispositivo.getWidth());


            do {
                spriteBlanco.runAction(mover);
            }while (((spriteBlanco.getPositionX() + spriteBlanco.getWidth()/2) + 30) < PantallaDispositivo.getWidth());
            return true;
        }
        @Override
        public boolean ccTouchesEnded(MotionEvent event) {
            IntervalAction Secuencia;
            ScaleBy scale = ScaleBy.action(1f,1.3f);
            ScaleTo scale2 = ScaleTo.action(1f,1f);
            Secuencia = Sequence.actions(scale,scale2,scale,scale2,scale,scale2);
            spriteVerde.runAction(Secuencia);
            return true;
        }



        public void creaSprite(){
            spriteVerde = Sprite.sprite("cuadradoverde.png");
            spriteRojo = Sprite.sprite("cuadradorojo.png");
            spriteAzul = Sprite.sprite("cuadradoazul.png");
            spriteBlanco = Sprite.sprite("cuadradoblanco.png");

            spriteVerde.setPosition(PantallaDispositivo.getWidth()/4 - spriteVerde.getWidth()/4, PantallaDispositivo.getHeight()/4 + spriteVerde.getHeight()/4);
            spriteRojo.setPosition(PantallaDispositivo.getWidth() - spriteRojo.getWidth(), PantallaDispositivo.getHeight()/4 + spriteRojo.getHeight()/4);


            spriteAzul.setPosition(PantallaDispositivo.getWidth() - spriteAzul.getWidth(), PantallaDispositivo.getHeight() - spriteAzul.getHeight()*2);
            spriteBlanco.setPosition(PantallaDispositivo.getWidth()/4 - spriteBlanco.getWidth()/4, PantallaDispositivo.getHeight() - spriteBlanco.getHeight()*2);

            Log.d("Verde position", " x " + spriteVerde.getPositionX() + " y " + spriteVerde.getPositionY());
            Log.d("Rojo position", " x " + spriteRojo.getPositionX() + " y " + spriteRojo.getPositionY());
            Log.d("Azul position", " x " + spriteAzul.getPositionX() + " y " + spriteAzul.getPositionY());
            Log.d("Blanco position", " x " + spriteBlanco.getPositionX() + " y " + spriteBlanco.getPositionY());



            super.addChild(spriteVerde);
            super.addChild(spriteRojo);
            super.addChild(spriteAzul);
            super.addChild(spriteBlanco);
        }

    }

}
