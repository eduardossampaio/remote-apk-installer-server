package br.com.esampaio.remote_apk_installer_server.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static void createDirIfNotExists(String directory) throws IOException {
        createDirIfNotExists(new File(directory));
    }
    public static void createDirIfNotExists(File directory) throws IOException {
       Path path = directory.toPath();
       Files.createDirectories(path);
    }

    public static void createFileIfNotExists(String file) throws IOException {
        createFileIfNotExists(new File(file));
    }
    public static void createFileIfNotExists(File file) throws IOException {
        Path path = file.toPath();
        if(Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    public static void saveBytesInFile(String file,byte[] bytes,int size) throws IOException {
        if(bytes.length==size){
            saveBytesInFile(file,bytes);
        }else{
            byte[] newByteArray = new byte[size];
            for(int i=0;i<size;i++){
                newByteArray[i] = bytes[i];
            }
            saveBytesInFile(file,newByteArray);
        }
    }
    public static void saveBytesInFile(String file,byte[] bytes) throws IOException {
        Path path = Paths.get(file);
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        Files.write(path, bytes);
    }
    public static void saveStringInFile(String file,String content) throws IOException {
        saveBytesInFile(file,content.getBytes());
    }

    public static byte[] readBytes(File file) throws IOException {
        return readBytes(file.getAbsolutePath());
    }
    public static byte[] readBytes(String file) throws IOException {
        Path path = Paths.get(file);
        byte[] data = Files.readAllBytes(path);
        return data;
    }
    public static void copyFile(String source,String destinationPath,String fileName) throws IOException {
        File destinationFile = new File(destinationPath,fileName);
        if(!destinationFile.exists()){
            destinationFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
        Files.copy(Paths.get(source),fileOutputStream);
        fileOutputStream.close();
    }
}
