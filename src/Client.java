import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private static String serverName;
    private static int portNumber;
    static int ptrue = 0;
    public static Socket socket = null;
    public static ArrayList<MyFile> downloadedFile = new ArrayList<>();
    public static ArrayList<MyFile> allFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final File[] fileToSend = new File[1];
        int[] size = new int[1];
        size[0] = 0;

        JFrame jFrame = new JFrame("Client GUI");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Client");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JPanel connectionPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        JLabel ipLabel = new JLabel("Enter IP:");
        ipLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        JTextField ipTextField = new JTextField("localhost");
        ipTextField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        JLabel portLabel = new JLabel("Enter Port:");
        portLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        JTextField portTextField = new JTextField("1212");
        portTextField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        connectionPanel.add(ipLabel);
        connectionPanel.add(ipTextField);
        connectionPanel.add(portLabel);
        connectionPanel.add(portTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(150, 40));

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setPreferredSize(new Dimension(150, 40));
        JButton uploadButton = new JButton("Upload");
        uploadButton.setPreferredSize(new Dimension(150, 40));
        JButton downloadButton = new JButton("Download");
        downloadButton.setPreferredSize(new Dimension(150, 40));

        topPanel.add(titleLabel);
        centerPanel.add(connectionPanel);
        centerPanel.add(connectButton);
        centerPanel.add(buttonPanel);

        buttonPanel.add(chooseFileButton);
        buttonPanel.add(uploadButton);
        buttonPanel.add(downloadButton);

        jFrame.add(topPanel, BorderLayout.NORTH);
        jFrame.add(centerPanel, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setVisible(true);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverName = ipTextField.getText();
                portNumber = Integer.parseInt(portTextField.getText());
                ClientConnection clientConnection = new ClientConnection();
                socket = clientConnection.getClientConnected(serverName, portNumber, allFiles, ptrue, jFrame);
                ptrue = clientConnection.getPTrue();
            }
        });

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose a file to send");
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToSend[0] = jFileChooser.getSelectedFile();
                    // Handle file selection
                }
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileToSend[0] == null) {
                    // Show error message
                    JOptionPane.showMessageDialog(jFrame, "Please choose a file first", "Choose First", JOptionPane.WARNING_MESSAGE);
                } else {
                    FileUploader fileUploader = new FileUploader();
                    fileUploader.uploadFile(fileToSend[0], socket);
                }
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ptrue == 1) {
                    ShowFiles.showAvailableFiles();
                } else {
                    // Show error message
                    JOptionPane.showMessageDialog(jFrame, "Please Connect to the server, First", "Connect First", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel jPanel = (JPanel) e.getSource();
                int fileId = Integer.parseInt(jPanel.getName());
                for (MyFile myFile : downloadedFile) {
                    if (myFile.getId() == fileId) {
                        JFrame jfPreview = FileDownloadByClient.createFrame(myFile.getName(), myFile.getData(), FileExtension.getExtension(myFile.getName()));
                        jfPreview.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }
}
