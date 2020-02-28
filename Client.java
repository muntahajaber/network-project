import java.net.DatagramPacket;
import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;// import SocketTimeoutException
import java.util.*;


	
	
public class Client {
   public static void main (String[]args) throws SocketTimeoutException,IOException
   {
    	//  Scanner read from user
      Scanner input = new Scanner (System.in); 
      
      try {
      // Welcoming message
         System.out.println("******* Welcome to my socket program :) ********\n");
         System.out.print("Enter your name: ");
         // read user name
         String userName = input.nextLine();
         String userInput="";
        
       // loop to print 10 ping
         for(  int i=0;i<10;i++){
         
            System.out.print(" \nEnter a messag or E for exist: "); 
            
            userInput=input.nextLine();// read from the user
            if(!userInput.equalsIgnoreCase("e")){// press e for exist 
            
            
               //  convert the data to byte format by using getBytes()
               byte[] sendData =(userInput).getBytes();//[1] refrence
               
            //creating a socket to send ping through the socket
               DatagramSocket clintSocket = new DatagramSocket(); //[2]
            
               InetAddress clintAddress = InetAddress.getLocalHost();// IP address[2]
               
            // creat an obj of datagram  to send the data with packet info such as  ip adress and port# lengtgh of data an data it self [2]
               DatagramPacket clintSentPackt = new DatagramPacket(sendData,sendData.length,clintAddress,9999); 
               // starting a timer 
               long  startTimer =  System.nanoTime();//[2]
              
              
               try { // to catch  setSoTimeout excption
               
               // sending the packet to the specifid port# by using function send()[2] 
                  clintSocket.send(clintSentPackt);
                  clintSocket. setSoTimeout(10000);// setting a time out for 1000 ms CEN 
                  
               // set up the socket buffer for receiving data.
                  byte[] receiveData = new byte[1024];
                
                // to get Received packet. [2]
                  DatagramPacket clintRcvPackt = new DatagramPacket(receiveData,receiveData.length);
               
               // rcv a data by using a recive function recive()
                  clintSocket.receive(clintRcvPackt);// wait for the packt to be rcv from port 9999 socket [2]
                
                  long endTimer =  System.nanoTime();// finish time [2]
                
               
                 
                 
               
                  double RTT =(endTimer-startTimer)/ 1000000.0;// Calculate RTT 
               
               
               
                  String printData= new String (clintRcvPackt.getData());// to get the data from the server
                  printData=printData.substring(0,3);//substring the rcv packet
                  System.out.println("\n"+userName +" send "+userInput +" (ping"+ (i+1) +")"+ " To" + " Server\n");// print mesage
                  System.out.println("Server Rcv: ping"+(i+1)+" ----> send pong"+ (i+1) +" "+ printData );
                  System.out.println("RTT= "+RTT +" ns\n");
                  System.out.println("------------------------");
               
               
               
               
               }  catch(SocketTimeoutException e) {// to catch an excption thrown by setSotime()
               	
                  System.out.println("Time out!! " );
               	
               }clintSocket.close();
            
            
            }else{// if press e  print messag and exit the loop 
            
               System.out.println("Bye Bye :)");
               break;}
         }}
      catch(IOException e){
         System.out.println(e);
      } 
        
   }}