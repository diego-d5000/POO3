package poo3;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Created by diego-d on 10/28/15.
 */
public class TextDocument {

    private String fileName;
    private String contents;
    private File file;

    public TextDocument() {
    }

    public TextDocument(File file) {
        this.file = file;
        this.fileName = file.getName();
    }

    public void openFile() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String fileText = "";
        String fileLine = "";
        while (((fileLine = bufferedReader.readLine()) != null)) {
            fileText += fileLine + "\n";
        }
        this.contents = fileText;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
