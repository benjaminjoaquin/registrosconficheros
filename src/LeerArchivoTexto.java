import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.ArrayList;

public class LeerArchivoTexto
{
   private Scanner entrada; // permite al usuario abrir el archivo
ArrayList<Pelicula> misPeliculas;

   

public LeerArchivoTexto(){

misPeliculas = new ArrayList();
}
   public void abrirArchivo()
   {
      try
      {
       
         entrada = new Scanner( new File("peliculas.txt") );
    } // fin de try
    catch ( FileNotFoundException fileNotFoundException )
      {
         System.err.println( "Error al abrir el archivo." );
         System.exit( 1 );
      } // fin de catch
   } // fin del método abrirArchivo

   // lee registro del archivo
   public void leerRegistros()
   {
    
   

      try // lee registros del archivo, usando el objeto Scanner
      {
             ArrayList<Pelicula> temp = new ArrayList();
         while (entrada.hasNext() )
         {
              Pelicula p = new Pelicula();
      p.setId(entrada.nextInt());
     entrada.nextLine();
      p.setNombre(entrada.nextLine());
      p.setGenero(entrada.nextLine());
      p.setAnio(entrada.nextInt());
     entrada.nextLine();
      p.setPoster(entrada.nextLine());
      
      
   
            temp.add(p);
         } // fin de while
         
         setPeliculas(temp);
      } // fin de try
      catch ( NoSuchElementException elementException )
      {
         System.err.println( "El archivo no esta bien formado." );
        entrada.close();
         System.exit( 1 );
      } // fin de catch
      catch ( IllegalStateException stateException )
      {
         System.err.println( "Error al leer del archivo." );
         System.exit( 1 );
      } // fin de catch
      finally{ entrada.close();}
   } // fin del método leerRegistros

   public void setPeliculas(ArrayList<Pelicula> temp){
   misPeliculas=temp;
   }
   public ArrayList<Pelicula> getPeliculas(){
   return misPeliculas;
   }
   
   // cierra el archivo y termina la aplicación
   public void cerrarArchivo()
   {
      if ( entrada != null )
         entrada.close(); // cierra el archivo
   } // fin del método cerrarArchivo
} // fin de la clase LeerArchivoTexto
