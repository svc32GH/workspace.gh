package com.svc32.hws.client;

// �����, ����� �������� wsdl �������� � ����� ����
// ���������� �� ������ ���-�������
import java.net.URL;
// ����� ������� ��������� ��� ������ � �������� URL
import java.net.MalformedURLException;

// ������, ����� ���������� xml-�� c wsdl ���������
// � ���������� �� ���� service � ���
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// ��������� ������ ���-������� (��� ������ � �����)
import com.svc32.hws.HelloWebServiceServer;

public class HelloWebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        // ������� ������ �� wsdl ��������
        URL url = new URL("http://localhost:1986/svc32/hws/hello?wsdl");
        System.out.println("\n" + "url = \n" + url + "\n");

        // ��������� ���������� ������������ ������� � ����� ������ ���� WSDL �������� - definitions
        // 1-�� �������� ������� � �������� targetNamespace
        // 2-�� �������� ������� � �������� name
        QName qname = new QName("http://hws.svc32.com/", "HelloWebServiceServerImplService");

        // ������ �� ����� ���������� �� ���� service � wsdl ��������,
        Service service = Service.create(url, qname);

        // � ����� � �� ���������� � ���� ���� port, �����
        // �������� ������ �� ��������� �� ��� ������ ���-�������
        HelloWebServiceServer hello = service.getPort(HelloWebServiceServer.class);

        // ���! ������ ����� �������� ��������� �����
        System.out.println(hello.getHelloString("JavaRush"));
    }

}
