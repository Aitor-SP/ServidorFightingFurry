package com.company.testclient;


import com.company.model.Jugador;
import com.company.model.Mensaje;
import jakarta.websocket.*;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class Cliente extends JPanel {
    private Jugador jugador;
    private Map<String, Jugador> enemigos = new ConcurrentHashMap<>();

    private Random random = new Random();

    private ClienteEndpoint clienteEndpoint;

    private JLabel etiquetaEstadoConexion;

    public Cliente(int x, int y, int angulo, Color color){
        jugador = new Jugador(new Jugador.Posicion(x, y, angulo), color);

        clienteEndpoint = new ClienteEndpoint(this);
    }

    public void iniciar() {
        JFrame frame = new JFrame();

        JPanel panelDeBotones = new JPanel();

        Button botonEntrar = new Button("ENTER GAME");
        Button botonSalir = new Button("EXIT GAME");
        Button botonConectar = new Button("CONNECT TO SERVER");
        Button botonDesconectar = new Button("DISCONNECT FROM SERVER");
        etiquetaEstadoConexion = new JLabel("Status");

        botonConectar.addActionListener(e -> conectarAlServidor());
        botonDesconectar.addActionListener(e -> desconectarDelServidor());
        botonEntrar.addActionListener(e -> entrarAlJuego());
        botonSalir.addActionListener(e -> salirDelJuego());

        panelDeBotones.setLayout(new BoxLayout(panelDeBotones, BoxLayout.PAGE_AXIS));
        panelDeBotones.add(botonEntrar);
        panelDeBotones.add(botonSalir);
        panelDeBotones.add(botonConectar);
        panelDeBotones.add(botonDesconectar);
        panelDeBotones.add(etiquetaEstadoConexion);

        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.getContentPane().add(panelDeBotones, BorderLayout.PAGE_END);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((int) jugador.posicion.x+200, (int) jugador.posicion.y+200);

        conectarAlServidor();
        entrarAlJuego();

        while(true){
            repaint();
            try { Thread.sleep(10); } catch (Exception e) { }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        moverYDisparar();

        enviarPosicionAlServidor();

        pintarJugador(g);

        pintarEnemigos(g);
    }

    private void pintarEnemigos(Graphics g) {
        enemigos.forEach((id, enemigo) -> {
            g.setColor(enemigo.color);
            g.fillOval((int) enemigo.posicion.x, (int) enemigo.posicion.y, 20, 20);
            enemigo.disparos.forEach(disparo -> {
                g.fillRect((int) disparo.posicion.x, (int) disparo.posicion.y, 4, 4);
            });
        });
    }

    private void pintarJugador(Graphics g) {
        g.setColor(jugador.color);
        g.fillOval((int) jugador.posicion.x, (int) jugador.posicion.y, 20, 20);
        jugador.disparos.forEach(disparo -> {
            g.fillRect((int) disparo.posicion.x, (int) disparo.posicion.y, 4, 4);
        });
    }

    private void enviarPosicionAlServidor() {
        enviarMensajeAlServidor(Mensaje.posicion(jugador));
    }

    private void moverYDisparar() {
        jugador.posicion.x += jugador.vx;
        jugador.posicion.y += jugador.vy;

        jugador.posicion.x = jugador.posicion.x > this.getWidth() ? this.getHeight() : jugador.posicion.x < 0 ? 0 : jugador.posicion.x;
        jugador.posicion.y = jugador.posicion.y > this.getHeight() ? this.getHeight() : jugador.posicion.y < 0 ? 0 : jugador.posicion.y;

        if(random.nextInt()%20 == 0){
            jugador.vx = random.nextInt(4)-2;
            jugador.vy = random.nextInt(4)-2;
        }

        if(random.nextInt()%5 == 0){
            jugador.posicion.angulo += random.nextFloat();
        }

        if(random.nextInt()%4==0) {
            jugador.disparos.add(new Jugador.Disparo(jugador.posicion.x, jugador.posicion.y, jugador.posicion.angulo));
        }

        jugador.disparos.forEach(disparo -> {
            disparo.posicion.x += Math.cos(disparo.posicion.angulo);
            disparo.posicion.y += Math.sin(disparo.posicion.angulo);

            if(disparo.posicion.x < 0 || disparo.posicion.y > this.getWidth() || disparo.posicion.y < 0 || disparo.posicion.y > this.getHeight()){
                jugador.disparos.remove(disparo);
            }
        });
    }

    private void procesarMensajeDelServidor(Mensaje mensaje){
        // TODO:
        switch (mensaje.tipo){
            case ENTRAR_AL_JUEGO:
                ponerEnemigo(mensaje.idDelQueEnvia, mensaje.posicion, mensaje.color);
                enviarMensajeAlServidor(Mensaje.estoyJugando(jugador));
                break;
            case POSICION:
                actualizarEnemigo(mensaje.idDelQueEnvia, mensaje.posicion, mensaje.disparos);
                break;
            case ESTOY_JUGANDO:
                ponerEnemigo(mensaje.idDelQueEnvia, mensaje.posicion, mensaje.color);
                break;
            case SALIR_DEL_JUEGO:
                quitarEnemigo(mensaje.idDelQueEnvia);
                break;
        }
    }

    private void ponerEnemigo(String idEnemigo, Jugador.Posicion posicion, Color color){
        enemigos.put(idEnemigo, new Jugador(posicion, color));
    }

    private void actualizarEnemigo(String idEnemigo, Jugador.Posicion posicion, List<Jugador.Disparo> disparos){
        enemigos.get(idEnemigo).actualizar(posicion, disparos);
    }

    private void quitarEnemigo(String idEnemigo){
        enemigos.remove(idEnemigo);
    }

    private void cuandoSeAbraLaConexionAlServidor(Session sesion){
        etiquetaEstadoConexion.setText("CONNECTED: " + sesion.getId());
        etiquetaEstadoConexion.paintImmediately(getVisibleRect());
    }

    private void cuandoSeCierreLaConexionAlServidor(){
        etiquetaEstadoConexion.setText("DISCONNECTED");
        etiquetaEstadoConexion.paintImmediately(getVisibleRect());
        enemigos.clear();
    }

    private void enviarMensajeAlServidor(Mensaje mensaje){
        clienteEndpoint.enviarMensaje(mensaje);
    }

    private void conectarAlServidor(){
        clienteEndpoint.conectar();
    }

    private void desconectarDelServidor(){
        clienteEndpoint.desconectar();
    }

    private void entrarAlJuego(){
        clienteEndpoint.enviarMensaje(Mensaje.entrarAlJuego(jugador));
    }

    private void salirDelJuego(){
        clienteEndpoint.enviarMensaje(Mensaje.exitGame());
        enemigos.clear();
    }


    @ClientEndpoint(decoders = {Mensaje.Decoder.class}, encoders = {Mensaje.Encoder.class})
    public static class ClienteEndpoint {
        private Cliente cliente;
        private Session sesion;

        private ClienteEndpoint(Cliente cliente){
            this.cliente = cliente;
        }

        private void conectar(){
            try{
                if(conectadoAlServidor()) desconectar();
                ContainerProvider.getWebSocketContainer().connectToServer(this, new URI("ws://localhost:12345/"));
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        private void desconectar(){
            try {
                sesion.close();
            } catch (Exception e) {}
        }

        private boolean conectadoAlServidor(){
            return sesion != null;
        }

        private void enviarMensaje(Mensaje mensaje){
            if(sesion == null) return;

            try {
                sesion.getBasicRemote().sendObject(mensaje);
            } catch (Exception e) {}
        }

        @OnOpen
        public void onOpen(Session session) {
            this.sesion = session;
            cliente.cuandoSeAbraLaConexionAlServidor(session);
        }

        @OnMessage
        public void onMessage(Mensaje mensaje, Session session) {
            cliente.procesarMensajeDelServidor(mensaje);
        }

        @OnClose
        public void onClose(Session session, CloseReason closeReason) {
            cliente.cuandoSeCierreLaConexionAlServidor();
        }
    }
}

