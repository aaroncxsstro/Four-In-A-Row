package CuatroEnRaya;

import CuatroEnRaya.Configuracion;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Juego
extends JFrame {
    private String Turno;
    static String nombre = "Cuatro En Raya";
    private JPanel panprincipal;
    private JPanel panbotones;
    private JButton botonbase;
    boolean turnoDeJugador1 = true;
    URL soundEffect = this.getClass().getResource("/recursos/button.wav");

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    Configuracion frame = new Configuracion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void playSoundEffect() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.soundEffect);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public Juego(final String jugador1, final String jugador2, final String Letra1, final String Letra2, Color color) {
        super(nombre);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        Image icon = new ImageIcon(this.getClass().getResource("/recursos/logo.png")).getImage();
        this.setIconImage(icon);
        this.setBounds(100, 100, 765, 541);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
        this.panprincipal = new JPanel();
        this.panprincipal.setBackground(color);
        this.panprincipal.setBounds(0, 66, 757, 445);
        contentPane.add(this.panprincipal);
        this.panprincipal.setLayout(new GridLayout(6, 7, 0, 0));
        this.panbotones = new JPanel();
        this.panbotones.setBounds(0, 65, 757, 587);
        this.panprincipal.setLayout(new GridLayout(0, 7, 0, 0));
        contentPane.add(this.panbotones);
        this.panbotones.setLayout(new GridLayout(0, 7, 0, 0));
        this.panbotones.setFocusable(false);
        this.panbotones.setBackground(new Color(0, 0, 0, 0));
        this.panbotones.setOpaque(false);
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(0, 0, 757, 67);
        contentPane.add(panel);
        panel.setLayout(null);
        JLabel MostradorTurno = new JLabel("Turno:");
        MostradorTurno.setForeground(new Color(255, 255, 255));
        MostradorTurno.setFont(new Font("Tahoma", 1, 39));
        MostradorTurno.setBounds(21, 11, 282, 45);
        panel.add(MostradorTurno);
        final JLabel MostrarTurno = new JLabel(jugador1);
        MostrarTurno.setForeground(new Color(255, 255, 255));
        MostrarTurno.setFont(new Font("Tahoma", 1, 38));
        MostrarTurno.setBounds(180, 11, 239, 45);
        panel.add(MostrarTurno);
        int i = 0;
        while (i < 42) {
            JLabel Lb = new JLabel();
            Lb.setHorizontalAlignment(0);
            Lb.setFont(new Font("Tahoma", 1, 60));
            Lb.setForeground(Color.WHITE);
            this.panprincipal.add(Lb);
            Lb.setName("JLb" + i);
            Lb.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            this.Turno = Letra1;
            ++i;
        }
        i = 0;
        while (i < 7) {
            this.botonbase = new JButton("");
            this.panbotones.add(this.botonbase);
            this.botonbase.setOpaque(false);
            this.botonbase.setSize(110, 130);
            this.botonbase.setName("Boton" + i);
            this.botonbase.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombreBoton = ((JButton)e.getSource()).getName();
                    int numBoton = Integer.parseInt(nombreBoton.substring(5));
                    boolean colocado = false;
                    int i = 35 + numBoton;
                    while (i >= numBoton) {
                        JLabel Jbl = (JLabel)Juego.this.panprincipal.getComponent(i);
                        if (Jbl.getText().isEmpty()) {
                            Jbl.setText(Juego.this.Turno);
                            Juego.this.playSoundEffect();
                            colocado = true;
                            break;
                        }
                        i -= 7;
                    }
                    if (colocado) {
                        if (Juego.this.hayGanador() && Juego.this.Turno.equals(Letra1)) {
                            Juego.this.reiniciarJuego(jugador1);
                        } else if (Juego.this.hayGanador() && Juego.this.Turno.equals(Letra2)) {
                            Juego.this.reiniciarJuego(jugador2);
                        }
                        Juego.this.turnoDeJugador1 = !Juego.this.turnoDeJugador1;
                        String string = Juego.this.Turno = Juego.this.turnoDeJugador1 ? Letra1 : Letra2;
                        if (Juego.this.turnoDeJugador1) {
                            MostrarTurno.setText(jugador1);
                        } else {
                            MostrarTurno.setText(jugador2);
                        }
                    }
                }
            });
            ++i;
        }
    }

    private boolean hayGanador() {
        JLabel jbl4;
        JLabel jbl3;
        JLabel jbl2;
        JLabel jbl1;
        int j;
        int i = 0;
        while (i < 42) {
            j = i;
            while (j < i + 4) {
                jbl1 = (JLabel)this.panprincipal.getComponent(j);
                jbl2 = (JLabel)this.panprincipal.getComponent(j + 1);
                jbl3 = (JLabel)this.panprincipal.getComponent(j + 2);
                jbl4 = (JLabel)this.panprincipal.getComponent(j + 3);
                if (!jbl1.getText().isEmpty() && jbl1.getText().equals(jbl2.getText()) && jbl1.getText().equals(jbl3.getText()) && jbl1.getText().equals(jbl4.getText())) {
                    jbl1.setForeground(new Color(255, 157, 191));
                    jbl2.setForeground(new Color(255, 157, 191));
                    jbl3.setForeground(new Color(255, 157, 191));
                    jbl4.setForeground(new Color(255, 157, 191));
                    return true;
                }
                ++j;
            }
            i += 7;
        }
        i = 0;
        while (i < 7) {
            j = 0;
            while (j < 3) {
                jbl1 = (JLabel)this.panprincipal.getComponent(i + j * 7);
                jbl2 = (JLabel)this.panprincipal.getComponent(i + (j + 1) * 7);
                jbl3 = (JLabel)this.panprincipal.getComponent(i + (j + 2) * 7);
                jbl4 = (JLabel)this.panprincipal.getComponent(i + (j + 3) * 7);
                if (!jbl1.getText().isEmpty() && jbl1.getText().equals(jbl2.getText()) && jbl1.getText().equals(jbl3.getText()) && jbl1.getText().equals(jbl4.getText())) {
                    jbl1.setForeground(new Color(255, 157, 191));
                    jbl2.setForeground(new Color(255, 157, 191));
                    jbl3.setForeground(new Color(255, 157, 191));
                    jbl4.setForeground(new Color(255, 157, 191));
                    return true;
                }
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i < 15) {
            j = i;
            while (j < i + 4) {
                jbl1 = (JLabel)this.panprincipal.getComponent(j);
                jbl2 = (JLabel)this.panprincipal.getComponent(j + 8);
                jbl3 = (JLabel)this.panprincipal.getComponent(j + 16);
                jbl4 = (JLabel)this.panprincipal.getComponent(j + 24);
                if (!jbl1.getText().isEmpty() && jbl1.getText().equals(jbl2.getText()) && jbl1.getText().equals(jbl3.getText()) && jbl1.getText().equals(jbl4.getText())) {
                    jbl1.setForeground(new Color(255, 157, 191));
                    jbl2.setForeground(new Color(255, 157, 191));
                    jbl3.setForeground(new Color(255, 157, 191));
                    jbl4.setForeground(new Color(255, 157, 191));
                    return true;
                }
                ++j;
            }
            i += 7;
        }
        i = 3;
        while (i < 18) {
            j = i;
            while (j < i + 4) {
                jbl1 = (JLabel)this.panprincipal.getComponent(j);
                jbl2 = (JLabel)this.panprincipal.getComponent(j + 6);
                jbl3 = (JLabel)this.panprincipal.getComponent(j + 12);
                jbl4 = (JLabel)this.panprincipal.getComponent(j + 18);
                if (!jbl1.getText().isEmpty() && jbl1.getText().equals(jbl2.getText()) && jbl1.getText().equals(jbl3.getText()) && jbl1.getText().equals(jbl4.getText())) {
                    jbl1.setForeground(new Color(255, 157, 191));
                    jbl2.setForeground(new Color(255, 157, 191));
                    jbl3.setForeground(new Color(255, 157, 191));
                    jbl4.setForeground(new Color(255, 157, 191));
                    return true;
                }
                ++j;
            }
            i += 7;
        }
        boolean empate = true;
        int i2 = 0;
        while (i2 < 42) {
            jbl1 = (JLabel)this.panprincipal.getComponent(i2);
            if (jbl1.getText().isEmpty()) {
                empate = false;
            }
            ++i2;
        }
        if (empate) {
            this.reiniciarJuego("empate");
        }
        return false;
    }

    private void reiniciarJuego(String Ganador) {
        JFrame jFrame = new JFrame();
        if (Ganador.equals("empate")) {
            JOptionPane.showMessageDialog(jFrame, "El juego ha finalizado con un empate");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(jFrame, "El juego ha finalizado. Ha ganado el jugador " + Ganador);
        }
        this.dispose();
        try {
            Configuracion frame = new Configuracion();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}