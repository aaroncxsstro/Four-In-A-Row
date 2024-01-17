package CuatroEnRaya;

import CuatroEnRaya.Juego;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Configuracion
extends JFrame {
    static String nombre = "Cuatro En Raya";
    private int cambiocolor = 1;
    private Color color = new Color(0, 120, 215, 255);
    private JButton btnJugar;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
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

    public Configuracion() {
        super(nombre);
        this.setResizable(false);
        this.setSize(559, 406);
        this.setDefaultCloseOperation(3);
        Image icon = new ImageIcon(this.getClass().getResource("/recursos/logo.png")).getImage();
        this.setIconImage(icon);
        this.setLocationRelativeTo(null);
        this.btnJugar = new JButton("JUGAR");
        this.btnJugar.setBounds(90, 242, 352, 106);
        this.btnJugar.setFont(new Font("Tahoma", 1, 45));
        this.btnJugar.setBackground(this.color);
        this.btnJugar.setBorder(null);
        this.btnJugar.setFocusable(false);
        this.btnJugar.setForeground(Color.WHITE);
        JButton noche = new JButton("\u263c");
        noche.setBounds(10, 11, 50, 42);
        noche.setBackground(Color.WHITE);
        noche.setBorder(null);
        noche.setFocusable(false);
        final JPanel panel = new JPanel();
        panel.setBackground(this.color);
        panel.setLayout(null);
        panel.add(this.btnJugar);
        this.getContentPane().add(panel);
        panel.add(noche);
        JLabel lblIntroduceElNombre = new JLabel("Introduce el nombre del jugador 1");
        lblIntroduceElNombre.setForeground(Color.WHITE);
        lblIntroduceElNombre.setBounds(44, 115, 247, 14);
        panel.add(lblIntroduceElNombre);
        this.textField = new JTextField();
        this.textField.setBounds(83, 140, 96, 20);
        panel.add(this.textField);
        this.textField.setColumns(10);
        JLabel lblIntroduceElNombre_2 = new JLabel("Introduce el nombre del jugador 2");
        lblIntroduceElNombre_2.setForeground(Color.WHITE);
        lblIntroduceElNombre_2.setBounds(288, 115, 247, 14);
        panel.add(lblIntroduceElNombre_2);
        this.textField_1 = new JTextField();
        this.textField_1.setColumns(10);
        this.textField_1.setBounds(327, 140, 96, 20);
        panel.add(this.textField_1);
        JLabel lblNewLabel = new JLabel("4 EN RAYA");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", 1, 54));
        lblNewLabel.setBounds(114, 23, 335, 66);
        panel.add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("4 EN RAYA");
        lblNewLabel_1.setForeground(Color.GRAY);
        lblNewLabel_1.setFont(new Font("Tahoma", 1, 54));
        lblNewLabel_1.setBounds(114, 27, 335, 66);
        panel.add(lblNewLabel_1);
        JLabel lblFichaCon = new JLabel("Ficha del jugador 1");
        lblFichaCon.setForeground(Color.WHITE);
        lblFichaCon.setBounds(74, 186, 247, 14);
        panel.add(lblFichaCon);
        this.textField_2 = new JTextField();
        this.textField_2.addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent e) {
                if (Configuracion.this.textField_2.getText().length() >= 1) {
                    e.consume();
                }
            }
        });
        this.textField_2.setColumns(10);
        this.textField_2.setBounds(83, 211, 96, 20);
        panel.add(this.textField_2);
        this.textField_3 = new JTextField();
        this.textField_3.addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent e) {
                if (Configuracion.this.textField_3.getText().length() >= 1) {
                    e.consume();
                }
            }
        });
        this.textField_3.setColumns(10);
        this.textField_3.setBounds(327, 211, 96, 20);
        panel.add(this.textField_3);
        JLabel lblFichaDelJugador = new JLabel("Ficha del jugador 2");
        lblFichaDelJugador.setForeground(Color.WHITE);
        lblFichaDelJugador.setBounds(319, 186, 247, 14);
        panel.add(lblFichaDelJugador);
        this.btnJugar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String Letra2;
                Configuracion.this.playSoundEffect();
                String jugador1 = Configuracion.this.textField.getText();
                String jugador2 = Configuracion.this.textField_1.getText();
                String Letra1 = Configuracion.this.textField_2.getText();
                if (Letra1.isEmpty()) {
                    Letra1 = "X";
                }
                if ((Letra2 = Configuracion.this.textField_3.getText()).isEmpty()) {
                    Letra2 = "O";
                }
                if (jugador1.isEmpty()) {
                    jugador1 = "X";
                }
                if (jugador2.isEmpty()) {
                    jugador2 = "O";
                }
                if (Letra1.equals(Letra2)) {
                    Letra1 = "X";
                    Letra2 = "O";
                }
                Juego juego = new Juego(jugador1, jugador2, Letra1, Letra2, Configuracion.this.color);
                juego.setVisible(true);
                Configuracion.this.dispose();
            }
        });
        noche.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Configuracion.this.playSoundEffect();
                Configuracion.this.cambiocolor = Configuracion.this.cambiocolor < 8 ? ++Configuracion.this.cambiocolor : 1;
                switch (Configuracion.this.cambiocolor) {
                    case 1: {
                        Configuracion.this.color = new Color(0, 120, 215, 255);
                        break;
                    }
                    case 2: {
                        Configuracion.this.color = new Color(229, 57, 53);
                        break;
                    }
                    case 3: {
                        Configuracion.this.color = new Color(216, 27, 96);
                        break;
                    }
                    case 4: {
                        Configuracion.this.color = new Color(142, 36, 170);
                        break;
                    }
                    case 5: {
                        Configuracion.this.color = new Color(67, 160, 71);
                        break;
                    }
                    case 6: {
                        Configuracion.this.color = new Color(251, 192, 45);
                        break;
                    }
                    case 7: {
                        Configuracion.this.color = new Color(244, 81, 30);
                        break;
                    }
                    case 8: {
                        Configuracion.this.color = new Color(75, 75, 75);
                    }
                }
                panel.setBackground(Configuracion.this.color);
                Configuracion.this.btnJugar.setBackground(Configuracion.this.color);
            }
        });
    }
}