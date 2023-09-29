package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JuegoContenido extends JPanel implements ActionListener {

    //pantalla
    static final int PANTALLA = 600;
    static final int CUADRITO_SIZE = 25;
    static final int CUADRITOS_EN_PARALELO = (int) PANTALLA / CUADRITO_SIZE;

    //serpiente
    static final int TOTAL_CUERPO_SERPIENTE = (PANTALLA * PANTALLA) / CUADRITO_SIZE;
    int[] serpienteX = new int[TOTAL_CUERPO_SERPIENTE];
    int[] serpienteY = new int[TOTAL_CUERPO_SERPIENTE];
    int cuerpoSerpiente = 3;
    char direccion = 'd';
    Color serpienteColor = Color.green;

    //comida
    int comidaX;
    int comidaY;
    Color comidaColor = Color.red;

    //timer
    static final int DELAY = 100; // milisegundos
    Timer timer; // Se utiliza para programar tareas para que se ejecuten a intervalos regulares
    boolean running = true;

    //otros
    Random random = new Random();  // Genera un numero random para la posicion de la comida

    JuegoContenido() {
        //Seteo de tamaño de pantalla y fondo negro.
        this.setPreferredSize(new Dimension(PANTALLA, PANTALLA));
        this.setBackground(Color.black);

       
        this.setFocusable(true); // Permitir que el panel reciba el enfoque del teclado

        // Esto es útil cuando deseas que un componente 
        // reaccione a entradas de teclado,
        // como juegos que requieren que el jugador utilice
        // el teclado para controlar un personaje o una nave espacial.
        
        this.addKeyListener(new Controles()); // Escuchador de eventos de teclado

        iniciarJuego();
    }

    public void iniciarJuego() {
        generarComida();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void generarComida() {
        comidaX = random.nextInt(CUADRITOS_EN_PARALELO) * CUADRITO_SIZE;
        comidaY = random.nextInt(CUADRITOS_EN_PARALELO) * CUADRITO_SIZE;
    }

    public void verificarColisiones() {
        if (serpienteX[0] < 0) {
            running = false;
        }
        if (serpienteY[0] < 0) {
            running = false;
        }
        if (serpienteX[0] > PANTALLA - CUADRITO_SIZE) {
            running = false;
        }
        if (serpienteY[0] > PANTALLA - CUADRITO_SIZE) {
            running = false;
        }
    }

    public void moverSerpiente() {
        for (int i = cuerpoSerpiente; i > 0; i--) {
            serpienteX[i] = serpienteX[i - 1];
            serpienteY[i] = serpienteY[i - 1];
        }
        switch (direccion) {
            case 'd':
                serpienteX[0] = serpienteX[0] + CUADRITO_SIZE;
                break;
            case 'a':
                serpienteX[0] = serpienteX[0] - CUADRITO_SIZE;
                break;
            case 'w':
                serpienteY[0] = serpienteY[0] - CUADRITO_SIZE;
                break;
            case 's':
                serpienteY[0] = serpienteY[0] + CUADRITO_SIZE;
                break;
        }
    }

    public void verificarComida() {
        if (serpienteX[0] == comidaX && serpienteY[0] == comidaY) {
            cuerpoSerpiente++;
            generarComida();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moverSerpiente();
            verificarColisiones();
            verificarComida();
        } else {
            //Mensaje de Game Over
            int opcion = JOptionPane.showConfirmDialog(this, "Desea reiniciar?", "GAME OVER", JOptionPane.YES_NO_OPTION);

            // Verificar la respuesta del usuario
            if (opcion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "No se como reiniciarlo bro xD");
              
            } else {
                System.exit(0);
            }
        }

        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        // Dibuja los elementos del juego aquí
        super.paintComponent(g);

        for (int i = 0; i < CUADRITOS_EN_PARALELO; i++) {
            g.drawLine(0, CUADRITO_SIZE * i, PANTALLA, CUADRITO_SIZE * i);
            g.drawLine(CUADRITO_SIZE * i, 0, CUADRITO_SIZE * i, PANTALLA);
        }
        //Color y diseño comida
        g.setColor(comidaColor);
        g.fillOval(comidaX, comidaY, CUADRITO_SIZE, CUADRITO_SIZE);

        //Color y diseño serpiente serpiente
        g.setColor(serpienteColor);
        for (int i = 0; i < cuerpoSerpiente; i++) {
            g.fillRect(serpienteX[i], serpienteY[i], CUADRITO_SIZE, CUADRITO_SIZE);
        }

    }

    public class Controles extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyChar()) {
                case 'd':
                    if (direccion == 'a') {
                        break;
                    } else {
                        direccion = 'd';
                    }
                    break;
                case 'a':
                    if (direccion == 'd') {
                        break;
                    } else {
                        direccion = 'a';
                    }
                    break;
                case 'w':
                    if (direccion == 's') {
                        break;
                    } else {
                        direccion = 'w';
                    }
                    break;
                case 's':
                    if (direccion == 'w') {
                        break;
                    } else {
                        direccion = 's';
                    }
                    break;
            }
        }
    }

}
