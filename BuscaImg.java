
package CopiaImatgesDesDunDiectori;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class BuscaImg {
    
    public static void main(String[] args) {
        
        Path dir = Paths.get("C:\\Users\\Josep Serra\\Desktop\\Proba");

        System.out.println("Fitxers del directori " + dir);

        if (Files.isDirectory(dir)) {
            Buscar(dir);

        } else {
            System.err.println("Ús: java LlistarDirectori <directori>");
        }
    }

    public static void Buscar(Path dir){

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
            for (Path fitxer : stream) {

                if (Files.isDirectory(fitxer)) {
                    Buscar(fitxer);
                }else{
                    
                    String extension = "";
                    
                    String nomFitxer = fitxer.getFileName().toString();
                    if(nomFitxer.endsWith(".jpg")){
                        extension = ".jpg";
                    }else if(nomFitxer.endsWith(".png")){
                        extension = ".png";
                    }else if(nomFitxer.endsWith(".gif")){
                        extension = ".gif";
                    }else if(nomFitxer.endsWith(".bmp")){
                        extension = ".bmp";
                    }
                    
                    if(!extension.equals("")){
                        Copiar(fitxer.toFile(), extension);
                    }
                }

            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }
    }
    
    static int numFoto = 0;
    public static void Copiar(File ruta, String extension) throws FileNotFoundException, IOException{
         
        FileInputStream fis = new FileInputStream(ruta);
        
        File fitxerDesti = new File("C:\\Users\\Josep Serra\\Desktop\\Fotos\\"+numFoto+extension);
        FileOutputStream out = new FileOutputStream(fitxerDesti);
        
        byte[] blockBytes = new byte[100];

        int character = fis.read();
        int i=0;
        while (character!=-1) {
            if (i < 100) {
                blockBytes[i] = (byte) character;

                character = fis.read();
                i++;
            } else {
                out.write(blockBytes);
                i = 0;
            }
        }

        if (i>0) {
            out.write(blockBytes,0,i);
        }
        
        numFoto++;
    }
}
