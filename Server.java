
 
 
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
public class Server {
 
 
 
    public static void main(String args[]) throws Exception {
       // create a server socket.
        DatagramSocket serverSocket = new DatagramSocket(9999); // you can use any port number that is not publicly reserved 
       // set up the socket buffer for receiving data. 
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
         
        // communication loop. 
        while (true) {
        // assign the socket buffers as Datagram Packet.
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
             
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
        // to send and ACK of receiving data back to the client. 
            sendData = "ACK".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
             
        }
         
    }
}