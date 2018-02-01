package com.svc32.hws.endpoint;

// �����, ��� ������� ���-������� � ���-���������
import javax.xml.ws.Endpoint;
// ����� ������ ���-�������
import com.svc32.hws.HelloWebServiceServerImpl;

public class HelloWebServicePublisher {

    public static void main(String[] args) {
        // ��������� ���-������ �� ����� 1986
        // � �� ������, ���������� � ������ ���������,
        // ��������� ���-������, ������������ �� ������ ���������
        Endpoint.publish("http://0.0.0.0:1986/svc32/hws/hello", new HelloWebServiceServerImpl());
        System.out.println("***");
    }
}
