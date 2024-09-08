
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author benja
 */
public class MenuMain extends JFrame implements ActionListener {

    JPanel contentPane;
    JComboBox<String> combo1;

    JTextField nombre, anio, genero, busqueda;
    JLabel foto;
    Icon fotoUsuario;
    Image redimensionada;
    String rutaFoto;
    JButton buscar, agregar, listar, find;
    
   
    JPanel panel2, panel3, panelista, panelbusqueda;
    JPanel panel1;
    JTabbedPane panelDePestanas;
    int id;

    LeerArchivoTexto aplicacion;

    public MenuMain() {
        aplicacion = new LeerArchivoTexto();
        aplicacion.abrirArchivo();
        aplicacion.leerRegistros();
        id = aplicacion.misPeliculas.size();
    

        ///////////////////////////////////////
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(200, 100, 360, 520); //ancho,alto
        setSize(300, 350);
        setLocationRelativeTo(null);  //centro en pantalla
        setVisible(true);
        setTitle("Cine");
        setLayout(null);
        setResizable(true);
        // getContentPane().setLayout(new FlowLayout());
        ///////////////////////////////////////

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        ///////////////////////////////////////
        panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
        //panelDePestanas.setBounds(10, 11, 383, 174);
        contentPane.add(panelDePestanas);
        panel1 = new JPanel();
        JPanel panelAgregar = new JPanel();
        panel2 = new JPanel();

        panel3 = new JPanel();

        panelDePestanas.addTab("Agregar pelicula", null, panelAgregar, null);
        panelDePestanas.addTab("Buscar", null, panel2, null);
        panelDePestanas.addTab("Listar", null, panel3, null);
        ////////////////////////////////////////

        panel1.setLayout(new GridLayout(4, 2));
        panelAgregar.setLayout(new BorderLayout());
        JLabel lblnombre = new JLabel("Nombre:");
        nombre = new JTextField();
        JLabel lblgenero = new JLabel("Genero:");
        genero = new JTextField("");
        JLabel lblanio = new JLabel("Anio");
        anio = new JTextField();
        JLabel poster = new JLabel("Poster");
        ////////////////////////////////////////77
        foto = new JLabel();
        foto.setSize(130, 130);
        //rutaFoto = System.getProperty("user.dir") + "/img/" + "usuario.jpg";
        rutaFoto =  "usuario.jpg";
         String photo= System.getProperty("user.dir") + "/img/" + rutaFoto;
        redimensionada = new ImageIcon(photo).getImage();
        redimensionada = redimensionada.getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH);
        fotoUsuario = new ImageIcon(redimensionada);
        foto.setIcon(fotoUsuario);
        buscar = new JButton("Buscar poster");
        buscar.addActionListener(this);
        agregar = new JButton("Agregar pelicula");
        agregar.addActionListener(this);
        combo1 = new JComboBox<String>();
        combo1.addItem("Ciencia ficcion");
        combo1.addItem("Terror");
        combo1.addItem("Super Heroes");
        combo1.addItem("Infantil");
        combo1.addItem("Accion");
        combo1.addItem("Drama");
        combo1.addItem("Comedia");
        combo1.addItem("Fantasia");
        //////////////////////////////////////
        panel1.add(lblnombre);
        panel1.add(nombre);
        panel1.add(lblgenero);
        panel1.add(combo1);
        panel1.add(lblanio);
        panel1.add(anio);
        panel1.add(poster);
        panel1.add(buscar);
//////////////////////////
        panelAgregar.add("North", panel1);
        panelAgregar.add("East", foto);
        panelAgregar.add("South", agregar);
        //////////////////////

        find = new JButton("BUSCAR");
        find.addActionListener(this);
        busqueda = new JTextField(10);
        panel2.setLayout(new FlowLayout());
        panel2.add(new JLabel("ID. a buscar: "));
        panel2.add(busqueda);
        panel2.add(find);
        panelbusqueda = new JPanel();
        panelbusqueda.setLayout(new BoxLayout(panelbusqueda, BoxLayout.Y_AXIS));
        panel2.add(panelbusqueda);

        /////////////////////
        listar = new JButton("Listar/Actualizar");
        listar.addActionListener(this);
        panelista = new JPanel();
        panelista.setLayout(new BoxLayout(panelista, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panelista);
        // scroll.setSize(100,100);
        panel3.setLayout(new BorderLayout());
        panel3.add("North", listar);
        panel3.add("Center", scroll);
        ////////////////
        getContentPane().setVisible(false);
        getContentPane().setVisible(true);
        //////////////////
    }

    public void actionPerformed(ActionEvent mn) {
        if (mn.getSource() == listar) {

            panelista.removeAll();
            panelista.repaint();
            ArrayList<Pelicula> misPeliculas = new ArrayList();
            aplicacion.abrirArchivo();
            aplicacion.leerRegistros();
            aplicacion.cerrarArchivo();
            misPeliculas = aplicacion.getPeliculas();
            String aux2 = "";

            for (int i = 0; i < misPeliculas.size(); i++) {
                aux2 = "";

                aux2 = "\tID: " + misPeliculas.get(i).getId() + "\n";
                panelista.add(new JLabel(aux2));
                aux2 = "\tNombre: " + misPeliculas.get(i).getNombre() + "\n";
                panelista.add(new JLabel(aux2));
                aux2 = "\tGenero: " + misPeliculas.get(i).getGenero() + "\n";
                panelista.add(new JLabel(aux2));
                aux2 = "\tAnio de estreno: " + misPeliculas.get(i).getAnio() + "\n";
                panelista.add(new JLabel(aux2));
                String rutaFoto = misPeliculas.get(i).getPoster();
                String photo= System.getProperty("user.dir") + "/img/" + rutaFoto;
                //Image redimensionada = new ImageIcon(rutaFoto).getImage();
                Image redimensionada = new ImageIcon(photo).getImage();
                redimensionada = redimensionada.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                Icon fotoUsuario = new ImageIcon(redimensionada);
                JLabel foto = new JLabel();
                foto.setIcon(fotoUsuario);
                panelista.add(foto);

                panelista.repaint();
                panel3.setVisible(false);
                panel3.setVisible(true);
            }

        }

        if (mn.getSource() == find) {
            boolean encontrado = false;
            panelbusqueda.removeAll();
            panelbusqueda.repaint();

            ArrayList<Pelicula> p = new ArrayList();
            aplicacion.abrirArchivo();
            aplicacion.leerRegistros();
            aplicacion.cerrarArchivo();
            p = aplicacion.getPeliculas();

            String aux2 = "";
            try {
                for (int i = 0; i < p.size(); i++) {

                    if (p.get(i).getId() == Integer.parseInt(busqueda.getText())) {
                        encontrado = true;
                        aux2 = "";
                        JLabel aux = new JLabel(aux2);

                        aux2 = "\tID: " + p.get(i).getId() + "\n";
                        panelbusqueda.add(new JLabel(aux2));
                        aux2 = "\tNombre: " + p.get(i).getNombre() + "\n";
                        panelbusqueda.add(new JLabel(aux2));
                        aux2 = "\tGenero: " + p.get(i).getGenero() + "\n";
                        panelbusqueda.add(new JLabel(aux2));
                        aux2 = "\tAnio de estreno: " + p.get(i).getAnio() + "\n";
                        panelbusqueda.add(new JLabel(aux2));
                        String rutaFoto = p.get(i).getPoster();
                        
                String photo= System.getProperty("user.dir") + "/img/" + rutaFoto;
                //Image redimensionada = new ImageIcon(rutaFoto).getImage();
                Image redimensionada = new ImageIcon(photo).getImage();
                        
                     
                        redimensionada = redimensionada.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                        Icon fotoUsuario = new ImageIcon(redimensionada);
                        JLabel foto = new JLabel();
                        foto.setIcon(fotoUsuario);
                        panelbusqueda.add(foto);

                        //panelista.repaint();
                        panelbusqueda.setVisible(false);
                        panelbusqueda.setVisible(true);
                        i = p.size();

                    }

                }

                if (encontrado == false) {
                    JOptionPane.showMessageDialog(null, "Registro no encontrado");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Introduzca id en formato numerico");
            }

        }

        if (mn.getSource() == agregar) {
            try {

                CrearArchivoTexto aplicacioncrear = new CrearArchivoTexto();

                id++;
                String name = nombre.getText();
                String gener = (String) combo1.getSelectedItem();
              
                String ruta = rutaFoto;
            

                int year = Integer.parseInt(anio.getText());

                aplicacioncrear.abrirArchivo();
                aplicacioncrear.agregarRegistros(id, name, gener, year, ruta);
                aplicacioncrear.cerrarArchivo();

                nombre.setText("");
                genero.setText("");

                anio.setText("");
                //rutaFoto = System.getProperty("user.dir") + "/img/" + "usuario.jpg";
                rutaFoto = "usuario.jpg";
                String photo= System.getProperty("user.dir") + "/img/" + rutaFoto;
                redimensionada = new ImageIcon(photo).getImage();
                redimensionada = redimensionada.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                fotoUsuario = new ImageIcon(redimensionada);
                foto.setIcon(fotoUsuario);
               
              

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Dejo un campo vacio o introdujo mal el anio");

            }

        }

        if (mn.getSource() == buscar) {

            JFileChooser fileChooser = new JFileChooser();
            //fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
            fileChooser.setFileFilter(imgFilter);

            int result = fileChooser.showOpenDialog(this);
            //int result = fileChooser.showSaveDialog(this);

            if (result != JFileChooser.CANCEL_OPTION) {

                File fileName = fileChooser.getSelectedFile();

                if ((fileName == null) || (fileName.getName().equals(""))) {
                    //txt.setText("...");
                    fotoUsuario = new ImageIcon(redimensionada); // la foto que tengo default
                    foto.setIcon(fotoUsuario);
                } else {
                    try {
                        //txt.setText(fileName.getAbsolutePath());

                        String Dest = System.getProperty("user.dir") + "/img/" + fileName.getName();
                        Path Destino = Paths.get(Dest);
                        String Orig = fileName.getPath();
                        Path Origen = Paths.get(Orig);
                        Files.copy(Origen, Destino, REPLACE_EXISTING);
                        //rutaFoto = Dest;
                        rutaFoto= fileName.getName();

                        /////////////
                        Image redimensionada = new ImageIcon(Dest).getImage();
                        redimensionada = redimensionada.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                        fotoUsuario = new ImageIcon(redimensionada);
                        //////////////
                        foto.setIcon(fotoUsuario);
                    } catch (IOException e) {
System.out.println("Error E/S: "+e);
                    }

                }
            }
        }

    }

}
