package lab5;

import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileTypeParser {
    public static final HashMap<String, String> file_types = new HashMap<>();
    static {
        file_types.put("89504e47", "png");
        file_types.put("cafebabe","class");
        file_types.put("504b0304","zip or jar");
    }
    public static void main(String[] args) throws URISyntaxException {
        String name = "1";
        System.out.println("Filename: " + name);
        URI uri = ByteReader.class.getClassLoader().getResource(name).toURI();
        String filePath = Paths.get(uri).toString();

        FileInputStream is;
        String header = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            is.read(b,0,b.length);
            System.out.print("File Header(Hex): [");
            for (int i = 0; i < b.length; i++) {
                System.out.printf("%02x", b[i]);
                if (i != 3)
                System.out.print(",");
            }
            System.out.println("]");
            String hv;
            for (int i = 0; i < b.length; i++) {
                hv = Integer.toHexString(b[i] & 0xff).toLowerCase();
                if (hv.length() < 2) {
                    sb.append(0);
                }
                sb.append(hv);
            }
            header = sb.toString();
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("File Type: " + file_types.get(header));
    }
}
