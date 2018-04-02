package stock;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;
import javax.swing.WindowConstants;
import org.apache.derby.drda.NetworkServerControl;

public class Master extends JFrame implements ActionListener {
    JButton check = new JButton("Check Stock");
    JButton purchase = new JButton("Purchase Item");
    JButton stock = new JButton("Update Stock");
    JButton quit = new JButton("Exit");
    static DecimalFormat pounds = new DecimalFormat("‎£###,##0.00");
    static JLabel image;
    //Map<String, StockData.Item> stock = StockData.getStock();
    static JPanel items = new JPanel();
    static JScrollPane bottom; 
    public static boolean loggedIn = false;
    public static void main(String[] args) {
        Master master = new Master();
    }
    public Master() {
        try{
            copyImages();
        }catch(Exception ex)
         {
            System.out.println(ex);
            }
        /*Database connection*/
        String DB_URL="jdbc:derby://localhost:1527/" +
        new File("StoreDB").getAbsolutePath() + ";";
        try{
        NetworkServerControl server = new NetworkServerControl();
        server.start(null);
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
        Statement stmt = conn.createStatement();
        String sqlStatement = "SELECT StockID, Name, Quantity, Price, Filename FROM Stock";
        ResultSet result = stmt.executeQuery(sqlStatement); 
        while(result.next()){
             StockData.stock.put(result.getString("StockID"),
                     new StockData.Item(result.getString("Name"),
                             result.getDouble("Price"),
                             result.getInt("Quantity"),
                             result.getString("Filename")));
        } 
       stmt.close();
       }catch(SQLException sqle){
           System.out.println(sqle);
       }catch(Exception e){
           System.out.println(e);
       }
        /*Database connection*/
        setLayout(new BorderLayout());
        setSize(450, 180);
        setTitle("Master");
        // close application only by clicking the quit button
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(new JLabel("Select an option by clicking one of the buttons below"));
        add("North", top);
        JPanel middle = new JPanel();
        middle.add(check);
        check.addActionListener(this);
        middle.add(purchase);
        purchase.addActionListener(this);
        middle.add(stock);
        stock.addActionListener(this);
        middle.add(quit);
        quit.addActionListener(this);
        add("Center", middle);
        for(String key : StockData.getStock().keySet())  {
            image = new JLabel(StockDropdown.setImageSize(StockData.getFilename(key),60));
            image.setToolTipText(StockData.getName(key)+" "+pounds.format(StockData.getPrice(key)));
            items.add(image);
            items.repaint();
            items.revalidate();
        }
        bottom= new JScrollPane(items);
        bottom.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        bottom.setPreferredSize(new Dimension(20,85));
        add("South", bottom);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check) {
            CheckStock checkStock = new CheckStock();
        } else if (e.getSource() == purchase){
            PurchaseStock purchaseStock = new PurchaseStock();
        } else if (e.getSource() == stock){
            if(loggedIn){
                UpdateStock updateStock = new UpdateStock();
            }else{
                Login login = new Login();
            }
        } else if (e.getSource() == quit) {
            StockData.close();
            System.exit(0);
        }
    }
    public static void addImage(String key){
        image = new JLabel(StockDropdown.setImageSize(StockData.getFilename(key),60));
        image.setToolTipText(StockData.getName(key)+" "+pounds.format(StockData.getPrice(key)));
        items.add(image);
        items.repaint();
        items.revalidate();
    }
    public static void deleteImage(){
        items.removeAll();
        for(String key : StockData.getStock().keySet())  {
            image = new JLabel(StockDropdown.setImageSize(StockData.getFilename(key),60));
            image.setToolTipText(StockData.getName(key)+" "+pounds.format(StockData.getPrice(key)));
            items.add(image);
            items.repaint();
            items.revalidate();
        }
    }
    public static void copyImages() throws URISyntaxException, IOException {
        File imagesCopy = new File("images");
        URI uri = Master.class.getResource("/images").toURI();
        if (!uri.toString().startsWith("file:")) {
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            FileSystems.newFileSystem(uri, env);
        }
        Path imagesOrg = Paths.get(uri);
        if (!imagesCopy.exists()) {
            imagesCopy.mkdir();
            try(DirectoryStream<Path> paths = Files.newDirectoryStream(imagesOrg)) {
                for (final Path child : paths) {
                    try {
                        String targetPath = imagesCopy.getAbsolutePath() + File.separator + child.getFileName().toString();
                        Files.copy(child, Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
