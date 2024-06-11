import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDownloadByClient extends Client {
    static FrameElement showFrame = new FrameElement();

    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
        JFrame jFrame = showFrame.getJFrame("Download file");
        JPanel jPanel = showFrame.getJPanel();
        JLabel jlTitle = showFrame.getJLabel("File Downloader");
        JLabel jlPrompt = showFrame.getJLabel("Are you sure you want to download " + fileName + "?");
        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel jpButtons = showFrame.getJPanelType1(20, 0, 10, 0);
        JButton jbYes = showFrame.getButton1("Yes");
        JButton jbNo = showFrame.getButton1("No");
        jpButtons.add(jbYes);
        jpButtons.add(jbNo);

        if (fileExtension.equalsIgnoreCase("txt")) {
            jlFileContent.setText("<html>" + new String(fileData) + "</html>");
        } else {
            jlFileContent.setIcon(new ImageIcon(fileData));
        }

        jbYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File directory = new File("/Users/apple/Downloads/File-Server-System-main/File_Server_System_using_Socket Programming/Client File/");
                if (!directory.exists()) {
                    if (!directory.mkdirs()) {
                        JOptionPane.showMessageDialog(jFrame, "Failed to create directory.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                File fileToDownload = new File(directory, fileName);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                    fileOutputStream.write(fileData);
                    fileOutputStream.close();

                    JOptionPane.showMessageDialog(jFrame, "File downloaded successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    jFrame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(jFrame, "Failed to download file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jbNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

        jPanel.add(jlTitle);
        jPanel.add(jlPrompt);
        jPanel.add(jlFileContent);
        jPanel.add(jpButtons);
        jFrame.add(jPanel);

        return jFrame;
    }

}
