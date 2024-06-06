import javax.swing.*;

public class WatchUI {
    // TODO: Replace this for a method that obtains 30 timezones calling an api and saving inside of timeZones array.
    public static final String[] timeZones = {
            "Europe/Moscow",
            "Europe/Paris",
            "Europe/Berlin",
            "America/New_York",
            "America/Los_Angeles",
            "America/Mazatlan",
            "Asia/Tokyo",
            "Asia/Shanghai",
            "Asia/Dubai",
            "Australia/Sydney",
            "Africa/Cairo",
            "Africa/Johannesburg"
    };
    private JLabel labelTitle;

    public WatchUI(){
        this.labelTitle.setText("Global Analogic Watch");

        JPanel panelButtons = new JPanel();

        for (String zone : timeZones) {
            JButton button = new JButton(zone);
            button.addActionListener(evt -> {
                WatchZone watchZone = new WatchZone(zone);
                watchZone.run();
            });

            panelButtons.add(button);
        }

        JFrame frame = new JFrame("Watch");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(labelTitle);
        frame.add(panelButtons);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WatchUI::new);
    }
}
