package net;

public class ServerDetails {
  public String address;
  public int tcpPort;
  public int udpPort;

  public ServerDetails(String address, int tcpPort, int udpPort) {
    this.address = address;
    this.tcpPort = tcpPort;
    this.udpPort = udpPort;
  }
}