import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.TimeZone;

public class Drawer extends JPanel {
    public String zona;
    Color color1, color2, color3, color4, color5, color6, color7, color8;
    GradientPaint de1, de2, de3, de4;

    private int horas, minutos, segundos;
    private static final int esp = 10;
    private static final float tresp = (float) (3.0 * Math.PI);
    private static final float rad = (float) (Math.PI / 30.0);
    private int tam, centrox, centroy;
    private BufferedImage muestra;
    private javax.swing.Timer t;

    public Image img;

    public Drawer(String zonaHoraria) {
        zona = zonaHoraria;
        t = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        });
    }

    public void mreloj() {
        t.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        tam = ((ancho < alto) ? ancho : alto) - 2 * 192;
        centrox = this.getWidth() / 2;
        centroy = this.getWidth() / 2;
        disreloj(g);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(zona));

        horas = calendar.get(Calendar.HOUR);
        minutos = calendar.get(Calendar.MINUTE);
        segundos = calendar.get(Calendar.SECOND);
        man(g);

    }

    public void disreloj(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        color1 = (new Color(232, 218, 211));
        color2 = (new Color(129, 78, 37));
        color3 = (new Color(185, 140, 101));
        color4 = (new Color(76, 59, 20));
        color5 = (new Color(132, 21, 21));
        color6 = (new Color(225, 172, 0));
        color7 = (new Color(69, 179, 157));
        color8 = (new Color(14, 102, 85));
        de1 = new GradientPaint(0, 100, color5, 0, 225, color4, true);
        de2 = new GradientPaint(0, 200, color3, 0, 225, Color.white, true);
        de3 = new GradientPaint(0, 155, color3, 0, 225, color4, true);
        de4 = new GradientPaint(0, 155, color7, 0, 225, color8, true);

        g2.setPaint(color8);
        for (int seg = 0; seg < 60; seg++) {
            int inicio;
            if (seg % 5 == 0) {
                g2.setStroke(new BasicStroke(2));
                inicio = tam / 2 - 10;
                dis(g, centrox, centroy, rad * seg, inicio, tam / 2);

            } else {
                g2.setStroke(new BasicStroke(1));
                inicio = tam / 2 - 5;
            }
            dis(g, centrox, centroy, rad * seg, inicio, tam / 2);
        }

        g.setColor(color8);
        Font font = new Font("Arial", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("12", centrox - 8, centroy - 75);
        g.drawString("1", centrox + 32, centroy - 65);
        g.drawString("2", centrox + 63, centroy - 35);
        g.drawString("3", centrox + 80, centroy + 5);
        g.drawString("4", centrox + 65, centroy + 45);
        g.drawString("5", centrox + 38, centroy + 75);
        g.drawString("6", centrox - 2, centroy + 88);
        g.drawString("7", centrox - 42, centroy + 75);
        g.drawString("8", centrox - 72, centroy + 45);
        g.drawString("9", centrox - 85, centroy + 5);
        g.drawString("10", centrox - 75, centroy - 35);
        g.drawString("11", centrox - 47, centroy - 63);
        Font font2 = new Font("Arial", Font.BOLD, 12);
        g.setFont(font2);
    }

    private void man(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(color8);//color de manecillas
        g2.setStroke(new BasicStroke(1));//grosor de segundero
        float fsegundos = segundos;
        float anguloSegundero = tresp - (rad * fsegundos);
        dis(g, centrox, centroy, anguloSegundero, 0, 100);

        g2.setStroke(new BasicStroke(2));//grosor de minutero
        float fminutos = (float) (minutos + fsegundos / 60.0);
        float anguloMinutero = tresp - (rad * fminutos);
        dis(g, centrox, centroy, anguloMinutero, 0, 68);

        g2.setStroke(new BasicStroke(3));//grosor de hora
        float fhours = (float) (horas + fminutos / 60.0);
        float anguloHora = tresp - (5 * rad * fhours);
        dis(g, centrox, centroy, anguloHora, 0, 34);
    }

    private void dis(Graphics g, int x, int y, double angulo, int minrad, int maxrad) {
        float sine = (float) Math.sin(angulo);
        float cosine = (float) Math.cos(angulo);

        int dxmin = (int) (minrad * sine);
        int dymin = (int) (minrad * cosine);

        int dxmax = (int) (maxrad * sine);
        int dymax = (int) (maxrad * cosine);
        g.drawLine(x + dxmin, y + dymin, x + dxmax, y + dymax);

    }
}
