
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

/**
 *
 * @author benja
 */
public class CrearArchivoTexto {

    private Formatter salida; // objeto usado para enviar texto al archivo
    File archivo;
    FileWriter escribir;

    public void abrirArchivo() {
        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            archivo = new File("peliculas.txt");

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            escribir = new FileWriter(archivo, true);
            // salida = new Formatter( "peliculas.txt" );
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor

        } // fin de try
            catch (IOException e) {
             System.out.println("Error E/S: "+e);
        }

    } // fin del método abrirArchivo

    // agrega registros al archivo
    public void agregarRegistros(int id, String name, String gener, int year, String ruta) {

        // objeto que se va a escribir en el archivo
        Pelicula p = new Pelicula();
        p.setId(id);
        p.setNombre(name);
        p.setGenero(gener);
        p.setAnio(year);
        p.setPoster(ruta);

// escribe el nuevo registro
        try // envía valores al archivo
        {
            //salida.format("%d %s %s %d %s\n",
            //salida.format("%d%n%s%n%s%n%d%n%s%n",

            /*  p.getId(), 
        p.getNombre(), 
        p.getGenero(),
        p.getAnio(),
        p.getPoster()
             );*/
            escribir.write(Integer.toString(p.getId()));
            escribir.write("\n");
            escribir.write(p.getNombre());
            escribir.write("\n");
            escribir.write(p.getGenero());
            escribir.write("\n");
            escribir.write(Integer.toString(p.getAnio()));
            escribir.write("\n");
            escribir.write(p.getPoster());
            escribir.write("\n");
            escribir.close();

        } // fin de try
        catch (IOException e) {
             System.out.println("Error E/S: "+e);
        }/*
          catch ( FormatterClosedException formatterClosedException )
          {
          System.err.println( "Error al escribir en el archivo." );
          return;
          } // fin de catch
          catch ( NoSuchElementException elementException )
          {
          System.err.println( "Entrada invalida. Intente de nuevo." );
      
         
          } // fin de catch*/

    } // fin del método agregarRegistros

    public void cerrarArchivo() {
        //if ( salida != null )
        //  escribir.close();
    } // fin del método cerrarArchivo
}
