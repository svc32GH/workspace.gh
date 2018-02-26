package com.svc32.hws.client;

// ���������� ������-��������
import com.svc32.hws.*;

public class HelloWebServiceClient {
    public static void main(String[] args) {
        // ������������ � ���� service � wsdl ��������
        HelloWebServiceServerImplService helloService = new HelloWebServiceServerImplService();
        // ������� ���������� �� ���� service ������������ � ������ ���-�������
        HelloWebServiceServer hello = helloService.getHelloWebServiceServerImplPort();

        // ���������� � ���-������� � ������� ��������� � �������
        System.out.println( hello.getHelloString("JavaRush Community by svc32 ") );
    }
}
