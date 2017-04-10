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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    JButton openFileButton;
    JButton newFileButton;
    JButton saveFileButton;
    JButton saveAsFileButton;
    JButton renameFileButton;
    JButton deleteFileButton;
    boolean isSaved;
    TextDocument openedDoc;

    public MainFrame() {
        super("Editor de Texto");
        this.setSize(650, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
    }

    public void setupView() {
        JPanel controlsPanel = new JPanel(new FlowLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Opciones");
        controlsPanel.setBorder(titledBorder);

        openFileButton = new JButton("Abrir...");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Texto (.txt)", "txt");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(MainFrame.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    openedDoc = new TextDocument(fileChooser.getSelectedFile());
                    try {
                        openedDoc.openFile();
                        setVisibilitiesOnFileManipulation(true);
                        setSaveButtonsVisibility(false);
                        textArea.setText(openedDoc.getContents());
                        refreshTitle();
                    } catch (IOException ex) {
                        showError("Error al abrir el archivo");
                    }
                }
            }
        });

        newFileButton = new JButton("Nuevo");
        newFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (openedDoc != null && isSaved == false) {
                    int answer = JOptionPane.showConfirmDialog(
                            MainFrame.this,
                            "Se perderan los cambios no gaurdados\n"
                            + "¿Desea continuar ?",
                            "Se perderan los cambios",
                            JOptionPane.YES_NO_OPTION);

                    if (answer == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                openedDoc = null;
                textArea.setText("");
                MainFrame.this.setTitle("Nuevo Documento");
                setVisibilitiesOnFileManipulation(true);
                deleteFileButton.setVisible(false);
            }
        });
        saveFileButton = new JButton("Guardar");
        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (openedDoc == null) {
                    saveAsFileButton.doClick();
                    return;
                }
                isSaved = true;
                setSaveButtonsVisibility(false);
                openedDoc.setContents(textArea.getText());
                try {
                    openedDoc.saveFile();
                    refreshTitle();
                    deleteFileButton.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    showError("Error al salvar el archivo");
                }
            }
        });
        saveAsFileButton = new JButton("Guardar como...");
        saveAsFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(MainFrame.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    openedDoc = new TextDocument(fileChooser.getSelectedFile());
                    saveFileButton.doClick();
                }
            }
        });
        renameFileButton = new JButton("Renombrar");
        renameFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nextName = (String) JOptionPane.showInputDialog(
                        MainFrame.this,
                        "Nuevo nombre:",
                        "Renombrar",
                        JOptionPane.PLAIN_MESSAGE);

                if ((nextName != null) && (nextName.length() > 0)) {
                    openedDoc.renameFile(nextName);
                    refreshTitle();
                }
            }
        });
        deleteFileButton = new JButton("Eliminar");
        deleteFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(openedDoc.deleteFile()){
                    showMessage("Eliminado", "Archivo Eliminado");
                    openedDoc = null;
                    textArea.setText("");
                    MainFrame.this.setTitle("Editor de Texto");
                    setVisibilitiesOnFileManipulation(false);
                }
            }
        });

        controlsPanel.add(openFileButton);
        controlsPanel.add(newFileButton);
        controlsPanel.add(saveFileButton);
        controlsPanel.add(saveAsFileButton);
        controlsPanel.add(renameFileButton);
        controlsPanel.add(deleteFileButton);

        textArea = new JTextArea(25, 25);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                isSaved = false;
                setSaveButtonsVisibility(true);
            }

        });
        JScrollPane editorPanel = new JScrollPane(textArea);
        EmptyBorder emptyBorder = new EmptyBorder(5, 10, 5, 10);
        Border loweredBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        editorPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, loweredBorder));
        setVisibilitiesOnFileManipulation(false);

        this.add(controlsPanel);
        this.add(editorPanel);
    }

    private void setSaveButtonsVisibility(boolean areVisibles) {
        saveFileButton.setVisible(areVisibles);
        saveAsFileButton.setVisible(areVisibles);
    }

    private void setVisibilitiesOnFileManipulation(boolean isFileIn) {
        textArea.setVisible(isFileIn);
        setSaveButtonsVisibility(isFileIn);
        renameFileButton.setVisible(isFileIn);
        deleteFileButton.setVisible(isFileIn);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
    
    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(this,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }

    private void refreshTitle() {
        this.setTitle(openedDoc.getFileName());
    }

}
