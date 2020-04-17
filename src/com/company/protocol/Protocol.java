package com.company.protocol;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Protocol {
    private URL objectUrl;
    private URLConnection connection;

    public Protocol(String url) throws IOException {
        objectUrl = new URL(url.trim());
        connection = objectUrl.openConnection();
    }

    public void transfer() throws IOException {
        getHeader();
        createFile();
    }

    private void getHeader() throws IOException {
        Map<String, List<String>> header = connection.getHeaderFields();
        saveHeaderToFile(header);
    }

    private void saveHeaderToFile(Map<String, List<String>> header) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("header.txt"));
        writer.write(objectUrl.toString() + "\n");
        for (Map.Entry<String, List<String>> entry : header.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        writer.close();
    }

    private void createFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("received." + getExtension()));

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            inputStream = objectUrl.openStream();
            byte[] bytesBlock = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(bytesBlock)) > 0) {
                byteStream.write(bytesBlock, 0, bytesRead);
            }

            byteStream.writeTo(fos);
        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: %s", objectUrl.toExternalForm(), e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private String getExtension() {
        String contentType = connection.getContentType();
        if (contentType.indexOf(';') > 0) {
            return contentType.substring(contentType.indexOf('/') + 1, contentType.indexOf(';'));
        }
        return contentType.substring(contentType.indexOf('/') + 1);
    }
}
