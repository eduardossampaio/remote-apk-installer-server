package br.com.esampaio.remote_apk_installer_server.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    public static byte[] readBytes(File file) throws IOException {
        return readBytes(file.getAbsolutePath());
    }
    public static byte[] readBytes(String file) throws IOException {
        Path path = Paths.get(file);
        byte[] data = Files.readAllBytes(path);
        return data;
    }
}
