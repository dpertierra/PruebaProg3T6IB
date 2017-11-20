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
    Sprite sprite;
    Sprite sprite2;
    Sprite sprite3;
    Sprite sprite4;

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
           if (spriteElegido==0) {
                sprite.runAction(rotateByDerecha);
                sprite2.runAction(rotateByIzquierda);
                sprite3.runAction(rotateByIzquierda2);
                sprite4.runAction(rotateByIzquierda3);
                Log.d("Opuesto", "El sprite 1 gira para la derecha y todo el resto para la izquierda");
            }

           else if (spriteElegido==1) {
                sprite2.runAction(rotateByDerecha);
                sprite.runAction(rotateByIzquierda);
                sprite3.runAction(rotateByIzquierda2);
                sprite4.runAction(rotateByIzquierda3);
                Log.d("Opuesto", "El sprite 2 gira para la derecha y todo el resto para la izquierda");
            }
           else if (spriteElegido == 2) {
                sprite3.runAction(rotateByDerecha);
                sprite2.runAction(rotateByIzquierda);
                sprite.runAction(rotateByIzquierda2);
                sprite4.runAction(rotateByIzquierda3);
                Log.d("Opuesto", "El sprite 3 gira para la derecha y todo el resto para la izquierda");
            }
           else if (spriteElegido==3) {
                sprite4.runAction(rotateByDerecha);
                sprite2.runAction(rotateByIzquierda);
                sprite3.runAction(rotateByIzquierda);
                sprite.runAction(rotateByIzquierda);
                Log.d("Opuesto", "El sprite 4 gira para la derecha y todo el resto para la izquierda");
            }
            return true;
        }

        @Override
        public boolean ccTouchesMoved(MotionEvent event) {

            MoveBy mover = MoveBy.action(1f,30,0);

            if ((sprite.getPositionX() + sprite.getWidth()/2) < PantallaDispositivo.getWidth())
            {
                sprite.runAction(mover);
            }
            if ((sprite2.getPositionX() + sprite2.getWidth()/2) < PantallaDispositivo.getWidth())
            {
                sprite2.runAction(mover);
            }
            if ((sprite3.getPositionX() + sprite3.getWidth()/2) < PantallaDispositivo.getWidth())
            {
                sprite3.runAction(mover);
            }
            if ((sprite4.getPositionX() + sprite4.getWidth()/2) < PantallaDispositivo.getWidth())
            {
                sprite4.runAction(mover);
            }
            return true;
        }

        @Override
        public boolean ccTouchesEnded(MotionEvent event) {
            IntervalAction Secuencia;
            ScaleBy scale = ScaleBy.action(1f,1.3f);
            ScaleTo scale2 = ScaleTo.action(1f,1f);
            Secuencia = Sequence.actions(scale,scale2,scale,scale2,scale,scale2);
            sprite.runAction(Secuencia);
            return true;
        }



        public void creaSprite(){
            sprite = Sprite.sprite("cuadradoverde.png");
            sprite2 = Sprite.sprite("cuadradorojo.png");
            sprite3 = Sprite.sprite("cuadradoverde.png");
            sprite4 = Sprite.sprite("cuadradorojo.png");


            sprite.setPosition(PantallaDispositivo.getWidth()/4 - sprite.getWidth()/4, PantallaDispositivo.getHeight()/4 + sprite.getHeight()/4);
            sprite2.setPosition(PantallaDispositivo.getWidth() - sprite2.getWidth(), PantallaDispositivo.getHeight()/4 + sprite2.getHeight()/4);


            sprite3.setPosition(PantallaDispositivo.getWidth() - sprite2.getWidth(), PantallaDispositivo.getHeight() - sprite2.getHeight()*2);
            sprite4.setPosition(PantallaDispositivo.getWidth()/4 - sprite.getWidth()/4, PantallaDispositivo.getHeight() - sprite.getHeight()*2);

            Log.d("Sprite 1 position", " x " + sprite.getPositionX() + " y " + sprite.getPositionY());
            Log.d("Sprite 2 position", " x " + sprite2.getPositionX() + " y " + sprite2.getPositionY());
            Log.d("Sprite 3 position", " x " + sprite3.getPositionX() + " y " + sprite3.getPositionY());
            Log.d("Sprite 4 position", " x " + sprite4.getPositionX() + " y " + sprite4.getPositionY());



            super.addChild(sprite);
            super.addChild(sprite2);
            super.addChild(sprite3);
            super.addChild(sprite4);
        }

    }

}
