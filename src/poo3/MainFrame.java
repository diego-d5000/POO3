/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author diego-d
 */
public class MainFrame extends JFrame {

    JTextArea textArea;

    public MainFrame() {
        super("Editor de Texto");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
    }

    public void setupView() {
        JPanel controlsPanel = new JPanel(new FlowLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Opciones");
        controlsPanel.setBorder(titledBorder);

        JButton openFileButton = new JButton("Abrir...");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Texto (.txt)", "txt");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(MainFrame.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    TextDocument openedDoc = new TextDocument(fileChooser.getSelectedFile());
                    try {
                        openedDoc.openFile();
                        textArea.setText(openedDoc.getContents());
                        MainFrame.this.setTitle(openedDoc.getFileName());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Error al abrir el archivo",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        controlsPanel.add(openFileButton);

        textArea = new JTextArea(25, 25);
        JScrollPane editorPanel = new JScrollPane(textArea);
        EmptyBorder emptyBorder = new EmptyBorder(5, 10, 5, 10);
        Border loweredBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        editorPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, loweredBorder));

        this.add(controlsPanel);
        this.add(editorPanel);
    }

}
