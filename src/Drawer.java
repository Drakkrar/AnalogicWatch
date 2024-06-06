import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Drawer extends JPanel {
    public final String zona;
    Color colorEmerald;


    private int horas, minutos, segundos;

    private static final float tresp = (float) (3.0 * Math.PI);
    private static final float rad = (float) (Math.PI / 30.0);
    private int tam, centrox, centroy;

    private final javax.swing.Timer t;

    public Drawer(String zonaHoraria) {
        zona = zonaHoraria;
        t = new javax.swing.Timer(1000, ae -> repaint());
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

        tam = (Math.min(ancho, alto)) - 2 * 192;
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
        double radio = 75; // Radio del círculo
        double anguloInicial = -Math.PI / 2; // Ángulo inicial (12 en el reloj)
        double incrementoAngulo = 2 * Math.PI / 12; // Incremento del ángulo para cada número
        Font font = new Font("Arial", Font.BOLD, 16);
        Font font2 = new Font("Arial", Font.BOLD, 12);

        colorEmerald = (new Color(14, 102, 85));

        g2.setPaint(colorEmerald);
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

        g.setColor(colorEmerald);
        g.setFont(font);
        for (int i = 1; i <= 12; i++) {
            double angulo = anguloInicial + i * incrementoAngulo;
            int x = centrox + (int) (radio * Math.cos(angulo));
            int y = centroy + (int) (radio * Math.sin(angulo));
            g.drawString(Integer.toString(i), x, y);
        }
        g.setFont(font2);
    }

    private void man(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(colorEmerald);//color de manecillas
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
