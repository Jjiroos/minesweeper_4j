package sc0vil.minsweeper;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Date;
import javax.swing.*;

public class Dialogue extends JFrame implements ActionListener {
   private JButton heure = new JButton("Quel heure est-il ?");
   
   public Dialogue() {
      super("Dialogue...");
      add(heure, BorderLayout.SOUTH);
      heure.addActionListener(this);
      getContentPane().setBackground(Color.ORANGE);
      setSize(300, 200);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public static void main(String[] args) { new Dialogue();  }

   public void actionPerformed(ActionEvent e) {
      SimpleDateFormat heure = new SimpleDateFormat("HH 'h' mm 'mn'");
      JOptionPane.showMessageDialog(this, heure.format(new Date()));
   }
}
