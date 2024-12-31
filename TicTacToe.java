import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int tahtaGenisligi = 600;
    int tahtaYuksekligi = 650; //Üstteki metin paneli için 50px

    JFrame pencere = new JFrame("X-O-X");
    JLabel metinLabel = new JLabel();
    JPanel metinPanel = new JPanel();
    JPanel tahtaPanel = new JPanel();

    JButton[][] tahta = new JButton[3][3];
    String oyuncuX = "X";
    String oyuncuO = "O";
    String mevcutOyuncu = oyuncuX;

    boolean oyunBitti = false;
    int hamleSayisi = 0;

    TicTacToe() {
        pencere.setVisible(true);
        pencere.setSize(tahtaGenisligi, tahtaYuksekligi);
        pencere.setLocationRelativeTo(null);
        pencere.setResizable(false);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(new BorderLayout());

        metinLabel.setBackground(Color.darkGray);
        metinLabel.setForeground(Color.white);
        metinLabel.setFont(new Font("Arial", Font.BOLD, 50));
        metinLabel.setHorizontalAlignment(JLabel.CENTER);
        metinLabel.setText("X-O-X");
        metinLabel.setOpaque(true);

        metinPanel.setLayout(new BorderLayout());
        metinPanel.add(metinLabel);
        pencere.add(metinPanel, BorderLayout.NORTH);

        tahtaPanel.setLayout(new GridLayout(3, 3));
        tahtaPanel.setBackground(Color.darkGray);
        pencere.add(tahtaPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton kutu = new JButton();
                tahta[r][c] = kutu;
                tahtaPanel.add(kutu);

                kutu.setBackground(Color.darkGray);
                kutu.setForeground(Color.white);
                kutu.setFont(new Font("Arial", Font.BOLD, 120));
                kutu.setFocusable(false);
                // kutu.setText(mevcutOyuncu);

                kutu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (oyunBitti) return;
                        JButton kutu = (JButton) e.getSource();
                        if (kutu.getText() == "") {
                            kutu.setText(mevcutOyuncu);
                            hamleSayisi++;
                            kazananKontrolEt();
                            if (!oyunBitti) {
                                mevcutOyuncu = mevcutOyuncu == oyuncuX ? oyuncuO : oyuncuX;
                                metinLabel.setText(mevcutOyuncu + "'nun sırası.");
                            }
                        }

                    }
                });
            }
        }
    }

    void kazananKontrolEt() {
        //yatay
        for (int r = 0; r < 3; r++) {
            if (tahta[r][0].getText() == "") continue;

            if (tahta[r][0].getText() == tahta[r][1].getText() &&
                    tahta[r][1].getText() == tahta[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    kazananBelirle(tahta[r][i]);
                }
                oyunBitti = true;
                return;
            }
        }

        //dikey
        for (int c = 0; c < 3; c++) {
            if (tahta[0][c].getText() == "") continue;

            if (tahta[0][c].getText() == tahta[1][c].getText() &&
                    tahta[1][c].getText() == tahta[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    kazananBelirle(tahta[i][c]);
                }
                oyunBitti = true;
                return;
            }
        }

        //çapraz
        if (tahta[0][0].getText() == tahta[1][1].getText() &&
                tahta[1][1].getText() == tahta[2][2].getText() &&
                tahta[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                kazananBelirle(tahta[i][i]);
            }
            oyunBitti = true;
            return;
        }

        //ters çapraz
        if (tahta[0][2].getText() == tahta[1][1].getText() &&
                tahta[1][1].getText() == tahta[2][0].getText() &&
                tahta[0][2].getText() != "") {
            kazananBelirle(tahta[0][2]);
            kazananBelirle(tahta[1][1]);
            kazananBelirle(tahta[2][0]);
            oyunBitti = true;
            return;
        }

        if (hamleSayisi == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    berabereBelirle(tahta[r][c]);
                }
            }
            oyunBitti = true;
        }
    }

    void kazananBelirle(JButton kutu) {
        kutu.setForeground(Color.green);
        kutu.setBackground(Color.gray);
        metinLabel.setText(mevcutOyuncu + " kazandı!");
    }

    void berabereBelirle(JButton kutu) {
        kutu.setForeground(Color.orange);
        kutu.setBackground(Color.gray);
        metinLabel.setText("Beraberlik!");
    }
}
