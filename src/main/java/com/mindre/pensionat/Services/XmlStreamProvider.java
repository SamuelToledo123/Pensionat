package com.mindre.pensionat.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class XmlStreamProvider {

    String path = "https://javaintegration.systementor.se/customers";
    public InputStream getDataStream() throws IOException {
        URL url = new URL(path);
        return  url.openStream();
    }
}
