import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static OutputStream allFileOutputStream;
    static ArrayList<MyFile> allFiles = new ArrayList<>();
    static ArrayList<MyFile> myFiles = new ArrayList<>();
    public static int serverPortNumber;
    public static int showButtonPress = 0;
    public static ServerSocket serverSocket = null;

    public static void main(String[] args) throws IOException {

        int fileId = 1;
        FrameElement serverFrame = new FrameElement();
        JFrame jFrame = serverFrame.getJFrame("Server GUI");
        JPanel jPanel = serverFrame.getJPanel();
        JScrollPane jScrollPane = serverFrame.getJScrollPane();

        // Title label
        JLabel jlTitle = serverFrame.getJLabel("Server File GUI");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 20));
        jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        jPanel.add(jlTitle);

        // Status label
        JLabel waitClient = serverFrame.getJLabel("Waiting for the Client to Connect");
        waitClient.setFont(new Font("Arial", Font.PLAIN, 14));
        waitClient.setHorizontalAlignment(SwingConstants.CENTER);
        waitClient.setBorder(new EmptyBorder(0, 0, 10, 0));
        jPanel.add(waitClient);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
        
        // Show files button
        JButton jshowFiles = serverFrame.getButton("Show All Server Files");
        jshowFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showButtonPress = 1;
                ServerFiles.showAvailableFiles();
            }
        });
        buttonPanel.add(jshowFiles);

        // Delete files button
        JButton jDeleteFiles = serverFrame.getButton("Delete Server Files");
        jDeleteFiles.setBackground(Color.RED);
        jDeleteFiles.setForeground(Color.BLACK);
        jDeleteFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showButtonPress = 0;
                ServerFiles.showAvailableFiles();
            }
        });
        buttonPanel.add(jDeleteFiles);
        
        jPanel.add(buttonPanel);
        
        // Port information label
        JLabel jPort = serverFrame.getJLabel("Your Port number is 1212");
        jPort.setFont(new Font("Arial", Font.PLAIN, 14));
        jPort.setHorizontalAlignment(SwingConstants.CENTER);
        jPort.setBorder(new EmptyBorder(10, 0, 20, 0));
        jPanel.add(jPort);

        // Add components to the frame
        jFrame.add(jPanel, BorderLayout.NORTH);
        jFrame.add(jScrollPane, BorderLayout.CENTER);
        jFrame.setVisible(true);

        // Server Socket initialization
        serverPortNumber = 1212;
        if (serverSocket == null) {
            try {
                serverSocket = new ServerSocket(serverPortNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Accept client connection
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            waitClient.setText("Client is Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sending file list to client
        allFileOutputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(allFileOutputStream);
        objectOutputStream.writeObject(allFiles);

        // Initialize server controller
        ServerController serverController = new ServerController();

        // Continuously listen for client data
        while (true) {
            serverController.getClientData(socket, jFrame, jPanel, myFiles, fileId);
        }
    }

    public static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel jPanel = (JPanel) e.getSource();
                int fileId = Integer.parseInt(jPanel.getName());
                for (MyFile myFile : allFiles) {
                    if (myFile.getId() == fileId) {
                        System.out.println(myFile.id);
                        JFrame jfPreview = FileDeleteByServer.createFrame(myFile.getId(), myFile.getName(), myFile.getData(), myFile.getFileExtension());
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
