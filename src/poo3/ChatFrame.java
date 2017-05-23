/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author diego-d
 */
public class ChatFrame extends JFrame {

    private ChatSocket socket;
    private JButton sendButton;
    private JTextField messageField;
    private JTextPane textArea;

    public ChatFrame(String title) throws HeadlessException {
        super(title);
    }

    public void setSocket(ChatSocket socket) {
        this.socket = socket;
    }

    private void setUpSocket() {
        socket.setChatEventListener(new ChatSocket.ChatEventListener() {
            @Override
            public void messageSent(String message) {
                messageField.setText("");
                putMessage(message, "Yo");
            }

            @Override
            public void messageReceived(String message) {
                putMessage(message, "Compañero");
            }
        });
    }

    public void initView() {
        setUpSocket();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setPreferredSize(new Dimension(600, 30));

        messageField = new JTextField();
        messageField.setSize(new Dimension(450, 30));
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendButton.doClick();
                }
            }

        });
        sendButton = new JButton("Enviar");
        sendButton.setPreferredSize(new Dimension(150, 30));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                try {
                    socket.sendMessage(message);
                } catch (IOException ex) {
                    Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        controlPanel.add(messageField);
        controlPanel.add(sendButton);

        textArea = new JTextPane();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(600, 470));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 470));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollPane);
        mainPanel.add(controlPanel);
        this.add(mainPanel);

        this.setVisible(true);
    }

    private void putMessage(String message, String author) {
        StyledDocument doc = textArea.getStyledDocument();

        SimpleAttributeSet authorStyle = new SimpleAttributeSet();
        StyleConstants.setItalic(authorStyle, true);
        StyleConstants.setBold(authorStyle, true);

        try {
            doc.insertString(doc.getLength(), "\n" + author + ":\n", authorStyle);
            doc.insertString(doc.getLength(), message + "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
