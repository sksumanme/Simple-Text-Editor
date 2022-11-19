import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea txtArea;
    JMenuBar menuBar;
    TextEditor(){
        //Initialising frame,textarea, and menu bar
        frame=new JFrame("Simple Text Editor");
        txtArea=new JTextArea();
        menuBar=new JMenuBar();
        //Creating Menu
        JMenu file=new JMenu("File");
        JMenu edit=new JMenu("Edit");
        //creating Menu items
        JMenuItem openfile=new JMenuItem("Open File");
        JMenuItem savefile=new JMenuItem("Save File");
        JMenuItem printfile=new JMenuItem("Print File");
        JMenuItem newfile=new JMenuItem("New File");
        //Adding action listener to the menu items that will implement functionality to the item via action performed function
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        printfile.addActionListener(this);
        newfile.addActionListener(this);
        //Adding Menu items to the Menu file
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(printfile);
        // Doing similar things for the "Edit" file
        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");
        JMenuItem close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);
        //Adding Menu to the Menu bar
        menuBar.add(file);
        menuBar.add(edit);
        //Setting Menu bar to the frame
        frame.setJMenuBar(menuBar);
        //Adding textarea to the frame
        frame.add(txtArea);
        //Other stuffs related to the frame
        frame.setBounds(20,10,800,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        TextEditor obj=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call=e.getActionCommand();
        if(call=="Cut"){
            txtArea.cut();
        }
        else if(call=="Copy"){
            txtArea.copy();
        }
        else if(call=="Paste"){
            txtArea.paste();
        }
        else if(call=="Close"){
            frame.setVisible(false);
        }
        else if (call=="New File"){
            txtArea.setText("");
        }
        else if(call=="Save File"){
            JFileChooser selectfile=new JFileChooser("C:");
            int ans=selectfile.showOpenDialog(null);
            if(ans==selectfile.APPROVE_OPTION){
                File files=new File(selectfile.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(files,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(txtArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Open File"){
            JFileChooser selfile=new JFileChooser("C:");
            int ans=selfile.showOpenDialog(null);
            if(ans==selfile.APPROVE_OPTION){
                File files=new File(selfile.getSelectedFile().getAbsolutePath());
                try{
                    String s1="",s2="";
                    BufferedReader reader=new BufferedReader(new FileReader(files));
                    s2=reader.readLine();
                    while ((s1=reader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    txtArea.setText(s2);
                }catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Print File"){
            try {
                txtArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
