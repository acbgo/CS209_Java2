package lab6;

import org.junit.Assert;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class practice {
    public static void main(String[] args) throws IOException {
        List<String> zipList = readZip("E:\\OneDrive - 南方科技大学\\code\\CS209_Java2\\labs\\src\\main\\resources\\src.zip");
        List<String> jarList = readJar("E:\\OneDrive - 南方科技大学\\code\\CS209_Java2\\labs\\src\\main\\resources\\rt.jar");
        System.out.println("of .java files in java.io/java.nio: " + zipList.size());
        for (String s : zipList)
            System.out.println(s);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("of .class files in java.io/java.nio: " + jarList.size());
        for (String s : jarList)
            System.out.println(s);
    }

    public static List<String> readZip(String path){
        List<String> zipList = new ArrayList<>();
        try{
            ZipFile zipFile = new ZipFile(path);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()){
                String name = entries.nextElement().getName();
                if ((name.substring(0,7).equals("java/io") || name.substring(0,8).equals("java/nio")) && name.substring(name.length()-5,name.length()).equals(".java"))
                    zipList.add(name);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return zipList;
    }

    public static List<String> readJar(String path) throws IOException {
        List<String> jarList = new ArrayList<>();
        JarFile jarFile = new JarFile(path);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()){
            JarEntry entry = jarEntries.nextElement();
            String name = entry.getName();
            if ((name.substring(0,8).equals("java/nio") || name.substring(0,7).equals("java/io")
                    && name.substring(name.length()-6).equals(".class")))
                jarList.add(name);
        }
        return jarList;
    }
}
