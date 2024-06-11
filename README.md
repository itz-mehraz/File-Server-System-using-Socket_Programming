My apologies for missing that. Here's the updated README.md file with the "How to Run the Project" section included:

```markdown
## Project Overview

This project utilizes TCP/IP Socket Programming in Java to facilitate file transfers between computers connected on the same network. The system comprises a server and client setup, allowing users to share files seamlessly. Below are the instructions for setting up and running the project.

### Installation Instructions:

1. Clone the project repository to your local machine.
   
   ```
   git clone https://github.com/itz-mehraz/File-Server-System-using-Socket-Programming.git
   ```

2. Navigate to the project directory in your terminal.

   ```
   cd File-Server-System-using-Socket-Programming/src/
   ```

### Running the Server:

1. Compile the server-side Java code.

   ```
   javac Server.java
   ```

2. Run the server application.

   ```
   java Server
   ```

### Running the Client:

1. Compile the client-side Java code.

   ```
   javac Client.java
   ```

2. Run the client application.

   ```
   java Client
   ```

### Project Features:

- **Server Initialization:** Upon loading the server system, it automatically sets up the necessary communication infrastructure to enable client connections.
  
- **Client Connectivity:** The client system prompts users to input the IP address and port number of the server to establish a connection.
  
- **Graphical User Interface (GUI):** The system incorporates a user-friendly GUI with directory services that display available directories and files.
  
- **File Operations:** Both uploading and downloading files are facilitated through context-menu operations (right-click mouse actions). Clients can upload files to the server, and download files from it.

### Technical Details:

- **Technology Stack:** Java, TCP/IP Socket Programming.
  
- **User Interface:** Developed using Java AWT and Swing libraries.
  
- **File Compatibility:** Supports various file formats including .txt, .xlsx, .pdf, images, .mp4, and other audio and video formats.
  
- **Functionality:** Allows clients to browse server directories, select files for download, upload files to the server, and enables server-side file deletion.

### Screenshots:

![Screenshot 1](https://github.com/itz-mehraz/File-Server-System-using-Socket_Programming/raw/main/ScreenShots/Screenshot%202024-06-11%20at%206.26.58%E2%80%AFPM.png)

*Screenshot 1: Server Initialization*

---

![Screenshot 2](https://github.com/itz-mehraz/File-Server-System-using-Socket_Programming/raw/main/ScreenShots/Screenshot%202024-06-11%20at%206.28.51%E2%80%AFPM.png)

*Screenshot 2: Client Connectivity*

---

![Screenshot 3](https://github.com/itz-mehraz/File-Server-System-using-Socket_Programming/raw/main/ScreenShots/Screenshot%202024-06-11%20at%206.27.08%E2%80%AFPM.png)

*Screenshot 3: Graphical User Interface (GUI)*

---

![Screenshot 4](https://github.com/itz-mehraz/File-Server-System-using-Socket_Programming/raw/main/ScreenShots/Screenshot%202024-06-11%20at%206.28.38%E2%80%AFPM.png)

*Screenshot 4: File Operations*

![Project GUI](https://github.com/itz-mehraz/File-Server-System-using-Socket_Programming/raw/main/ScreenShots/Your-GUI-Screenshot.png)

*Project GUI: This screenshot showcases the graphical user interface of the project.*

You can include these captions along with the respective screenshots in your project overview. Let me know if you need any further assistance!
```

I've added the "Running the Server" and "Running the Client" sections under the "Installation Instructions" to provide clear guidance on how to run the project.
