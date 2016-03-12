package bs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Gui extends JFrame {

    public static final int WRONG_NAME = -1;
    public static final int SUCCESS = 0;

    private Picker picker;
    private JPanel panel = new JPanel();
    private JLabel ynameLb, topicLb, fnameLb;
    private JTextField ynameTf, topicTf, fnameTf;
    private JButton runBtn, resetBtn;
    private JTextPane contentTp;

    public static void main(String[] args) throws IOException {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui gui = null;
                try {
                    gui = new Gui();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gui.setVisible(true);
            }
        });
    }

//        if (name1 != null && topic != null && name2 == null) {
//


//        } else if (name1 != null && name2 != null) {
//            picker.setName1("Nhan");
//            picker.setName2("Minh hai");
//            picker.run2();
//        }
//        System.out.println(picker.getRes2());

    public Gui() throws IOException {

        picker = new Picker("list.txt", 8);

        initUI();

        setListener();

//        Picker picker = new Picker("list.txt", 8);
//        picker.setName1("Thai duong");
//            picker.setTopic(1);
//            picker.run1();
//                System.out.println(picker.getRes1());

    }

    private void setListener() {

        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Gui.this.ynameTf.getText().equals("")
                        && !Gui.this.topicTf.getText().equals("")
                        && Gui.this.fnameTf.getText().equals("")) {
                    Gui.this.picker.setName1(Gui.this.ynameTf.getText());
                    Gui.this.picker.setTopic(Integer.parseInt(Gui.this.topicTf.getText()));
                    Gui.this.picker.run1();
                    Gui.this.contentTp.setText(Gui.this.picker.getRes1());
                } else if (!Gui.this.ynameTf.getText().equals("")
                        && !Gui.this.fnameTf.getText().equals("")) {
                    Gui.this.picker.setName1(Gui.this.ynameTf.getText());
                    Gui.this.picker.setName2(Gui.this.fnameTf.getText());
                    Gui.this.picker.run2();
                    Gui.this.contentTp.setText(Gui.this.picker.getRes2());
                } else {
                    Gui.this.contentTp.setText("Wrong input!!!");
                }
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui.this.ynameTf.setText("");
                Gui.this.fnameTf.setText("");
                Gui.this.topicTf.setText("");
                Gui.this.contentTp.setText("");
            }
        });
    }

    private void initUI() {
        setTitle("Bingo Student");
        setSize(500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setLayout(null);

        ynameLb = new JLabel("Your name");
        ynameLb.setBounds(10, 10, 80, 25);
        panel.add(ynameLb);

        ynameTf = new JTextField(20);
        ynameTf.setBounds(100, 10, 160, 25);
        panel.add(ynameTf);

        topicLb = new JLabel("Topic");
        topicLb.setBounds(290, 10, 80, 25);
        panel.add(topicLb);

        topicTf = new JTextField(2);
        topicTf.setBounds(340, 10, 50, 25);
        panel.add(topicTf);

        fnameLb = new JLabel("Friend name");
        fnameLb.setBounds(10, 40, 80, 25);
        panel.add(fnameLb);

        fnameTf = new JTextField(20);
        fnameTf.setBounds(100, 40, 160, 25);
        panel.add(fnameTf);

        runBtn = new JButton("run");
        runBtn.setBounds(10, 80, 80, 25);
        panel.add(runBtn);

        resetBtn = new JButton("reset");
        resetBtn.setBounds(180, 80, 80, 25);
        panel.add(resetBtn);

        contentTp = new JTextPane();
        contentTp.setBounds(10, 110, 480, 660);
        contentTp.setEditable(false);
        JScrollPane spane = new JScrollPane(contentTp);
        spane.setBounds(10, 110, 480, 660);
        panel.add(spane);

        getContentPane().add(panel);
    }
}
