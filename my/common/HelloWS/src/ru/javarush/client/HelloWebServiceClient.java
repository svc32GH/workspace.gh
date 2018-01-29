package ru.javarush.client;

// ���������� ������-��������
import ru.javarush.ws.*;

public class HelloWebServiceClient {
    public static void main(String[] args) {
        // ������������ � ���� service � wsdl ��������
        HelloWebServiceImplService helloService = new HelloWebServiceImplService();
        // ������� ���������� �� ���� service ������������ � ������ ���-�������
        HelloWebService hello = helloService.getHelloWebServiceImplPort();

        // ���������� � ���-������� � ������� ��������� � �������
        System.out.println( hello.getHelloString("JavaRush Community") );
    }
}
