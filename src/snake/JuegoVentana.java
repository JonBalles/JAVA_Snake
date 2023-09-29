package snake;

import javax.swing.JFrame;


public class JuegoVentana extends JFrame{
    JuegoVentana(){
        this.setTitle("Snake");
        this.add(new JuegoContenido());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    // Cuando se llama a pack() en un JFrame, el sistema ajusta el tamaño
    // de la ventana para que se ajuste exactamente a los componentes que
    // contiene sin espacios vacíos innecesarios.
    // Esto es útil porque asegura que todos los componentes sean visibles y
    // estén dispuestos correctamente sin necesidad de establecer un tamaño fijo manualmente.
        
        this.pack();
        
    // Cuando se llama a este método en el contexto de una ventana (por ejemplo, una
    // instancia de JFrame), establece la posición de la ventana en el centro de la pantalla,
    // en lugar de aparecer en una ubicación específica en la pantalla.
    // Esto es útil para centrar una ventana en la pantalla para una mejor experiencia del usuario,
    // ya que facilita la visualización y la interacción con la ventana sin que el usuario 
    // tenga que moverla manualmente.
        
        this.setLocationRelativeTo(null);
        
        
        this.setVisible(true);
    }
    
     public static void main(String[] args) {
      new JuegoVentana();
    }
}
