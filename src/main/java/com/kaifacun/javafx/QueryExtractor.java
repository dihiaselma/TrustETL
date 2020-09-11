package com.kaifacun.javafx;

import com.kaifacun.javafx.MDFromQueryLogs.AppJava;
import com.kaifacun.javafx.MDFromQueryLogs.SyntacticalValidation.QueryFixer;
import com.kaifacun.javafx.MDPatternDetection.OntologyFactory;
import com.kaifacun.javafx.Utils.SharedFunctions;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.core.PathBlock;
import org.apache.jena.sparql.core.TriplePath;
import org.apache.jena.sparql.syntax.Element;
import org.apache.jena.sparql.syntax.ElementGroup;
import org.apache.jena.sparql.syntax.ElementPathBlock;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Controller
public class QueryExtractor {
    /* FXML Variables*/
    @FXML Text text1;
    @FXML Text text2;
    @FXML Text text3;
    @FXML Text text4;
    @FXML Text text5;
    @FXML Text text6;
    @FXML Text text7;
    @FXML Text text8;
    @FXML Text text9;
    @FXML Text text10;
    @FXML Text text11;
    @FXML Text text12;
    @FXML Text text13;
    @FXML Text text14;
    @FXML Text text15;
    @FXML Text text16;
    @FXML Text text17;
    @FXML Text title;

    @FXML TextField info1;
    @FXML TextField info2;
    @FXML TextField info3;
    @FXML TextField info4;
    @FXML TextField textfieldconect;
    @FXML TextField fielddepth1;
    @FXML TextField fielddepth2;

    @FXML Rectangle Rectangle1;
    @FXML Rectangle Rectangle2;
    @FXML Rectangle Rectangle3;
    @FXML Rectangle Rectangle4;
    @FXML Rectangle Rectangle5;
    @FXML Rectangle Rectangle6;
    @FXML Rectangle Rectangle7;
    @FXML Rectangle Rectangle8;
    @FXML Rectangle Rectangle9;
    @FXML Rectangle Rectangle10;
    @FXML Rectangle Rectangle11;
    @FXML Rectangle Rectangle12;
    @FXML Rectangle Rectangle13;
    @FXML Rectangle Rectangle14;
    @FXML Rectangle Rectangle15;
    @FXML Rectangle Rectangle16;
    @FXML Rectangle Rectangle17;

    @FXML CheckBox Checbx1;
    @FXML CheckBox Checbx2;
    @FXML CheckBox Checbx3;
    @FXML CheckBox Checbx4;
    @FXML CheckBox Checbx5;
    @FXML CheckBox Checbx6;
    @FXML CheckBox Checbx7;
    @FXML CheckBox Checbx8;
    @FXML CheckBox Checbx9;
    @FXML CheckBox Checbx10;
    @FXML CheckBox Checbx11;
    @FXML CheckBox Checbx12;
    @FXML CheckBox Checbx13;
    @FXML CheckBox Checbx14;
    @FXML CheckBox Checbx15;
    @FXML CheckBox Checbx16;
    @FXML CheckBox Checbx17;
    @FXML CheckBox Checbx18;
    @FXML CheckBox ckbtopic1;
    @FXML CheckBox ckbtopic2;
    @FXML CheckBox ckbtopic3;
    @FXML CheckBox ckbtopic4;
    @FXML CheckBox ckbtopic5;
    @FXML CheckBox ckbtopic6;
    @FXML CheckBox ckbtopic7;
    @FXML CheckBox ckbtopic8;
    @FXML CheckBox ckbtopic9;
    @FXML CheckBox ckbxshape1;
    @FXML CheckBox ckbxshape2;
    @FXML CheckBox ckbxshape3;
    @FXML CheckBox ckbxshape4;
    @FXML CheckBox ckbxshape5;
    @FXML CheckBox ckbxshape6;
    @FXML CheckBox ckbxshape7;

    @FXML ImageView image1;
    @FXML ImageView image2;
    @FXML ImageView image3;
    @FXML ImageView image4;
    @FXML ImageView image5;
    @FXML ImageView image6;
    @FXML ImageView image7;
    @FXML ImageView image8;
    @FXML ImageView image9;
    @FXML ImageView image10;
    @FXML ImageView image11;
    @FXML ImageView image12;
    @FXML ImageView image13;
    @FXML ImageView image14;
    @FXML ImageView image15;
    @FXML ImageView image16;
    @FXML ImageView image17;
    @FXML ImageView line1;
    @FXML ImageView line2;
    @FXML ImageView line3;
    @FXML ImageView line4;
    @FXML ImageView line5;
    @FXML ImageView line6;
    @FXML ImageView line7;
    @FXML ImageView line8;
    @FXML ImageView line9;
    @FXML ImageView line10;
    @FXML ImageView line11;
    @FXML ImageView line12;
    @FXML ImageView line13;
    @FXML ImageView line14;
    @FXML ImageView line15;
    @FXML ImageView line16;

    @FXML Button closeButton;
    @FXML Button validateConnect;
    @FXML Button validateBusAcd;
    @FXML Button validateAnaStd;
    @FXML Button validatexpert;
    @FXML Button validatesqlite;
    @FXML Button validatefile;
    @FXML Button validateshape;
    @FXML Button validattopic;

    @FXML RadioButton BARbtn3;
    @FXML RadioButton BARbtn2;
    @FXML RadioButton BARbtn1;
    @FXML RadioButton RadioFile;
    @FXML RadioButton RadioDirectory;
    @FXML RadioButton Anabtn1;
    @FXML RadioButton Anabtn2;
    @FXML RadioButton Anabtn3;
    @FXML RadioButton RBtExp2;
    @FXML RadioButton RBtExp1;
    @FXML RadioButton RBtExp3;
    @FXML RadioButton RBtExp4;

    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis ;
    @FXML private LineChart<String, Number> linechart1 ;

    @FXML TableView tablestat;
    @FXML TableColumn<String[], String> cl1;
    @FXML TableColumn<String[], String> cl2;
    @FXML TableColumn<String[], String> cl3;

    /*Other variables*/
    private static ILexicalDatabase db = new NictWordNet(); //wordnet lexical DB
    private static int nbExtractedQueries=139766;


/*************** The FrontEnd of our Trust ETL solution *****************************/
    /*Windows displaying on checkbox click*/
    public void autoConfigTOCONNECT(ActionEvent event) throws IOException {//Window Connect To
        String s= "Connect to";
        Parent root = FXMLLoader.load(getClass().getResource("Connect_to.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 456, 204));
        secondStage.show();
    }

    public void autoConfigBUSINESS(ActionEvent event) throws IOException {//Window Business Or Academic
        String s= "Business OR Academic";
        Parent root = FXMLLoader.load(getClass().getResource("Business OR Academic.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 472, 149));
        secondStage.show();
    }

    public void autoConfigSHAPE(ActionEvent event) throws IOException {//Window complexity: Shape & Depth
        String s= "Shape & Depth";
        Parent root = FXMLLoader.load(getClass().getResource("Shape & Depth.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 472, 254));
        secondStage.show();
    }

    public void autoConfigANALYTIC(ActionEvent event) throws IOException { //Window Analytic OR Standard
        String s= "Analytic OR Standard";
        Parent root = FXMLLoader.load(getClass().getResource("Analytic OR Standard.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 472, 149));
        secondStage.show();
    }

    public void autoConfigEXPERT(ActionEvent event) throws IOException {//Window Expert Filter
        String s= "Expert Filter";
        Parent root = FXMLLoader.load(getClass().getResource("Expert Filter.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 472, 149));
        secondStage.show();
    }

    public void autoConfigTOPIC(ActionEvent event) throws IOException {//Window Topic Selector
        String s= "Topic Selector";
        Parent root = FXMLLoader.load(getClass().getResource("Topic Selector.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 419, 211));
        secondStage.show();
    }

    public void autoConfigTOFILE(ActionEvent event) throws IOException {//Window Save to file
        String s= "Save to File";
        Parent root = FXMLLoader.load(getClass().getResource("Save to File.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 482, 168));
        secondStage.show();
    }

    public void autoConfigTOSQLITE(ActionEvent event) throws IOException {//Window Save to sqlite DB
        String s= "Save to SQLIte";
        Parent root = FXMLLoader.load(getClass().getResource("Save to SQLIte.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        secondStage.getIcons().add(icon);
        secondStage.setScene(new Scene(root, 482, 168));
        secondStage.show();
    }


    /* Windows Buttons actions */
    public void connect(ActionEvent event) throws IOException { //File or directory chooser when connecting
        File file=null;
        if(RadioDirectory.isSelected()){
            DirectoryChooser directoryChooser = new DirectoryChooser();
            file = directoryChooser.showDialog(null);
        }
        else{
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(null);}
        if (file != null) {
            textfieldconect.setText(file.getPath());
        }
    }

    public void connect2(ActionEvent event) throws IOException {//directory chooser when savinf data to file
        File file=null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        file = directoryChooser.showDialog(null);
        textfieldconect.setText(file.getPath());
    }

    public void connect22(ActionEvent event) throws IOException { //save the path into File when clicking OK => connection window
        Collection<String> CTest = new ArrayList<String>();
        CTest.add("Connection: "+textfieldconect.getText());
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        Stage stage = (Stage) validateConnect.getScene().getWindow();
        stage.close();
    }

    public void connect33(ActionEvent event) throws IOException { //save the path into File when clicking OK => save to file window
        Collection<String> CTest = new ArrayList<String>();
        CTest.add("save to file: " + textfieldconect.getText());
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        Stage stage = (Stage) validatefile.getScene().getWindow();
        stage.close();
    }

    public void BusinessOrACed(ActionEvent event) throws IOException { //extract selected data => Business Or Acdemic window
        Collection<String> CTest = new ArrayList<String>();
        RadioButton[] radio = {BARbtn1, BARbtn2, BARbtn3};
        for(int i=0;i<3;i++) {
            if (radio[i].isSelected()) {
                CTest.add("Business or Academic: "+radio[i].getText());
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        Stage stage = (Stage) validateBusAcd.getScene().getWindow();
        stage.close();
    }

    public void AnaORStdACTIOn(ActionEvent event) throws IOException { //extract selected data => Analytic OR Standard window
        Collection<String> CTest = new ArrayList<String>();
        RadioButton[] radio = {Anabtn1, Anabtn2, Anabtn3};
        for(int i=0;i<3;i++) {
            if (radio[i].isSelected()) {
                CTest.add("Business or Academic: "+radio[i].getText());
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        Stage stage = (Stage) validateAnaStd.getScene().getWindow();
        stage.close();
    }

    public void EXPERTACTION(ActionEvent event) throws IOException {//extract selected data => Expert Filter window
        Collection<String> CTest = new ArrayList<String>();
        RadioButton[] radio = {RBtExp2, RBtExp1, RBtExp3, RBtExp4};
        String s=null;
        for(int i=0;i<4;i++) {
            if (radio[i].isSelected()) {
                s=s+";"+radio[i].getText();
            }
        }
        CTest.add("Expert Filter: "+s);
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        Stage stage = (Stage) validatexpert.getScene().getWindow();
        stage.close();
    }

    public void Topicselectact(ActionEvent event) throws IOException {//extract selected data => Topic selector window
        Collection<String> CTest = new ArrayList<String>();
        CheckBox[] check = {ckbtopic1, ckbtopic2, ckbtopic3, ckbtopic4, ckbtopic5, ckbtopic6, ckbtopic7, ckbtopic8, ckbtopic9};
        String s=null;
        for(int i=0;i<9;i++) {
            if (check[i].isSelected()) {
                s=s+";"+check[i].getText();
            }
        }
        CTest.add("Topic selector: "+s);
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        Stage stage = (Stage) validattopic.getScene().getWindow();
        stage.close();
    }

    public void shapeaction(ActionEvent event) throws IOException {//extract selected data => Shape & Depth window
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> CTest2 = new ArrayList<String>();
        CheckBox[] check = {ckbxshape1, ckbxshape2, ckbxshape3, ckbxshape4, ckbxshape5, ckbxshape6, ckbxshape7};
        String s="";
        for(int i=0;i<7;i++) {
            if (check[i].isSelected()) {
                s=s+";"+check[i].getText();
            }
        }
        CTest.add("Shape: "+s);
        CTest2.add("Depth: "+fielddepth1.getText()+";"+fielddepth2.getText());
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest);
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt", CTest2);
        Stage stage = (Stage) validateshape.getScene().getWindow();
        stage.close();
    }


    /*Mouse Events*/
    public void efface1(MouseEvent event ) throws IOException {//delete text field1 path => depth window
        fielddepth1.setText("");
    }

    public void efface2(MouseEvent event) throws IOException {//delete text field2 path => depth window
        fielddepth2.setText("");
    }

    public void DisplayTable( Collection<String> C){//Dislay Table content 20 rows.
        int i=0;
        tablestat.getItems().clear();
        for (String line:C ) {
            if(i<20){
                String c="";
                String a=line.split("dihia")[0];
                String b=line.split("dihia")[1];
                if(line.split("dihia").length>2) c=line.split("dihia")[2];

                String [][] list={{a,b,c}};
                i++;
                cl1.setCellValueFactory((p)->{
                    String[] x = p.getValue();
                    return new SimpleStringProperty(x != null && x.length>0 ? x[0] : "<no name>");
                });
                cl2.setCellValueFactory((p)->{
                    String[] x = p.getValue();
                    return new SimpleStringProperty(x != null && x.length>1 ? x[1] : "<no value>");
                });
                cl3.setCellValueFactory((p)->{
                    String[] x = p.getValue();
                    return new SimpleStringProperty(x != null && x.length>1 ? x[2] : "<no value>");
                });
                tablestat.getItems().addAll(Arrays.asList(list));
            }}
    }

    public void DisplayTable2( Collection<String> C){//Dislay Table content 20 rows.
        int i=0;
        tablestat.getItems().clear();
        for (String line:C ) {
            if(i<20){
                String a=""+i;
                String b=line;
                String c="";
                String [][] list={{a,b,c}};
                i++;
                cl1.setCellValueFactory((p)->{
                    String[] x = p.getValue();
                    return new SimpleStringProperty(x != null && x.length>0 ? x[0] : "<no name>");
                });
                cl2.setCellValueFactory((p)->{
                    String[] x = p.getValue();
                    return new SimpleStringProperty(x != null && x.length>1 ? x[1] : "<no value>");
                });
                cl3.setCellValueFactory((p)->{
                    String[] x = p.getValue();
                    return new SimpleStringProperty(x != null && x.length>1 ? x[2] : "<no value>");
                });
                tablestat.getItems().addAll(Arrays.asList(list));
            }}
    }

    public void afficher1(MouseEvent event ) throws IOException {//display info about connectTo
        title.setText("Connect To");
        int trusted=SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\PFE\\LOG GEO\\SWDF.log").size();
        int total=SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\PFE\\LOG GEO\\SWDF.log").size();
        info1.setText(""+trusted);
        info2.setText(""+(total));
        info3.setText(""+((total-total)/total));
        info4.setText(""+((total-total)/total));
        Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\PFE\\LOG GEO\\SWDF.log");
        DisplayTable2(C);

    }

    public void afficher3(MouseEvent event ) throws IOException {//display info about connectTo
        title.setText("Query Extractor");
        int trusted=SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Query Extractor.txt").size();
        int total=SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\PFE\\LOG GEO\\SWDF.log").size();
        info1.setText(""+trusted);
        info2.setText(""+(total-trusted));
        info3.setText(""+((total-total)/total));
        info4.setText(""+((total-total)/total));
        Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Query Extractor.txt");
        DisplayTable(C);

    }

    public void afficher2(MouseEvent event ) throws IOException{//display info about other operations
        Text[] text = {text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17};
        String source2 = event.getPickResult().getIntersectedNode().getId();
        int indice=0;
        if (source2.contains("Rectangle"))  indice=Integer.parseInt(source2.substring(9));
        else if (source2.contains("text"))  indice=Integer.parseInt(source2.substring(4));

        int trusted=SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\"+text[indice-1].getText()+".txt").size();
        int total=SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\"+text[indice-2].getText()+".txt").size();
        title.setText(text[indice-1].getText());
        info1.setText(""+trusted);
        info2.setText(""+(total-trusted));
        info4.setText(""+ new DecimalFormat("##.##").format(((((float)nbExtractedQueries-(float)trusted)/(float)nbExtractedQueries)  *100)) +"%");
        info3.setText(""+ new DecimalFormat("##.##").format(((((float)nbExtractedQueries-(float)total)  /(float)nbExtractedQueries)  *100))     +"%");

        Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\"+text[indice-1].getText()+".txt");
        DisplayTable(C);
    }


    /* Close & Refresh the central window => Clear Button*/
    public void closeopen(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        String s= "Trust ETL tool";
        Parent root = FXMLLoader.load(getClass().getResource("GUI_TETL.fxml"));
        Stage pStage=new Stage();;
        pStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        pStage.getIcons().add(icon);
        pStage.setScene(new Scene(root, 1380, 700));
        pStage.show();
    }


    /* Display the trust ETL pipeline*/
    public void validateBTNF(ActionEvent event) throws IOException {
        CheckBox[] check = {Checbx1, Checbx2, Checbx3, Checbx4, Checbx5, Checbx6, Checbx7, Checbx8, Checbx9, Checbx10, Checbx11, Checbx12, Checbx13, Checbx14, Checbx15, Checbx16, Checbx17, Checbx18};
        String[] paths = {"link-vector-icon-png_262140.jpg","téléchargement.png","logo.png","bot.jpg","images.jpg","images.png","dedup.png","syntaxic.png","semantic.png","topic.jpg","schema.jpg","complexity.png","expert.png","analytic.jpg","join.png","enrich.png","file.jpg","sqlite.jpg"};
        Text[] text = {text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17};
        Rectangle[] Rect = {Rectangle1, Rectangle2, Rectangle3, Rectangle4, Rectangle5, Rectangle6, Rectangle7, Rectangle8, Rectangle9, Rectangle10, Rectangle11, Rectangle12, Rectangle13, Rectangle14, Rectangle15, Rectangle16, Rectangle17};
        ImageView[] image = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16, image17};
        ImageView[] line = {line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15, line16};

        int j = 0;
        Collection<String> CTest = new ArrayList<String>();
        String [] img=new String[17];
        for (int i = 0; i < 18; i++) {
            if (check[i].isSelected() == true) {
                CTest.add(check[i].getText());
                img[j]=paths[i];
                j++;
            }
        }
        int l=0;
        for(String s:CTest){
            text[l].setText(s);
            System.out.println(l+": "+s);
            Image image2 = new Image(getClass().getResourceAsStream("images/"+img[l]));
            image[l].setImage(image2);
            l++;
        }
        if(j<17) {
            for (int val = j; val < 17; val++){
                Rect[val].setVisible(false);
                text[val].setVisible(false);
                image[val].setVisible(false);
                line[val-1].setVisible(false);
        }
        }


    }


/*************** The Backend of our Trust ETL solution *****************************/

    /* compute the semantic similarity*/
    public static double compute(String word1, String word2) {
        WS4JConfiguration.getInstance().setMFS(true);
        double s = new WuPalmer(db).calcRelatednessOfWords(word1, word2);
        return s;
    }

    public static String corrige(String SelectQ) {
        SelectQ = QueryFixer.get().fix(SelectQ);
        SelectQ = SelectQ.replace('[', ' ');
        SelectQ = SelectQ.replace(']', ' ');
        SelectQ = SelectQ.replaceAll("}\"", "}");
        SelectQ = QueryFixer.get().fix(SelectQ);
        return SelectQ;
    }

    public static String viderQuery(String SelectQ) {
        String queryVide = SelectQ;
        while ((queryVide.contains("\t")) || (queryVide.contains("\n")) || (queryVide.contains(" "))) {
            queryVide = queryVide.replaceAll(" ", "");
            queryVide = queryVide.replaceAll("\t", "");
            queryVide = queryVide.replaceAll("\n", "");
        }
        return queryVide;
    }

    public static String FixSynt(String line) {
        String Query = "";
        int j;
        j = line.lastIndexOf("query=");
        if (j != -1) Query = line.substring(j + 6).trim();
        j = Query.lastIndexOf(" \"Mozilla");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("HTTP/1.1");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("format");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("HTTP/1.0");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("should");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("results=");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("output=");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("Accept=");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("\"");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("\"'");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("format=");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("callback=");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("maxrows=");
        if (j != -1) Query = Query.substring(0, j);
        j = Query.lastIndexOf("timeout=");
        if (j != -1) Query = Query.substring(0, j);
        if (Query.endsWith("\'")) {
            j = Query.lastIndexOf("\'");
            if (j != -1) Query = Query.substring(0, j - 1);
        }
        Query = Query.replace("(rdf:type)*", "rdf:type");
        Query = Query.replace("^foaf", "foaf");
        Query = Query.replace("foaf:member/foaf:page", "foaf:member");
        Query = Query.replace("foaf:publication/skos:subject", "foaf:publication");
        Query = Query.replace("foaf:member/foaf:name", "foaf:member");
        Query = Query.replace("dc:creator|foaf:maker", "dc:creator");
        Query = Query.replace("dc:creator/foad:Person", "dc:creator");
        Query = Query.replace('|', ' ');
        return Query;
    }

    public static String dateExtractor(String line) {
        String date = line.substring(line.indexOf('[') + 1, line.indexOf(']') - 6);
        String[] aray = date.split("/");
        String day = aray[0];
        String month = aray[1];
        switch (month) {
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
        }
        String year = aray[2];
        String[] n = year.split(":", 2);
        String yy = n[0];
        String time = n[1];
        String dateV = yy + "-" + month + "-" + day + " " + time;
        return dateV;
    }

    public static String QueriesExtractor(String path) {
        int ap = 0;
        String hit = "";
        Collection<String> CTest = new ArrayList<String>();
        String IP = "";
        Collection<String> C = SharedFunctions.ReadFile(path);/*Read log file*/
        System.out.println("******************************Début query extractor*********************************************");
        for (String line : C) {
            if ((line.toLowerCase().contains("select")) && (line.toLowerCase().contains("query")) && (line.toLowerCase().contains("describe") == false) && (line.toLowerCase().contains("ask") == false)) {

                /*le ID de la requete*/
                ap++;
                System.out.println(ap);
                if (line.indexOf('-') != -1) {
                    /*l'@ IP de la requete*/
                    IP = line.substring(0, line.indexOf('-') - 1);
                }

                /*la date d'execution de la requete*/
                String dateV = dateExtractor(line);

                /* l'état d'execution de la requte: Hit ou fail */
                if (line.contains("HTTP/1.1"))
                    hit = line.substring(line.indexOf("HTTP/1.1") + 10, line.indexOf("HTTP/1.1") + 13);
                if (line.contains("HTTP/1.0"))
                    hit = line.substring(line.indexOf("HTTP/1.0") + 10, line.indexOf("HTTP/1.0") + 13);

                String NewQuery = (ap + "dihia" + line+ "dihia"+""+"dihia" + IP + "dihia" + dateV + "dihia" + hit );
                CTest.add(NewQuery);
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Query Extractor.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Query Extractor.txt";
        nbExtractedQueries=CTest.size();
        System.out.println("******************************Fin query extractor*********************************************");
        return (pathReturned);
    }

    public static String formatConverter(String path) {
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        System.out.println("******************************Début Format Convertor*********************************************");
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String SelectQ = (URLEncodedUtils.parse(splitted[1], Charset.forName("UTF-8"))).toString();
            String parsedQuery = (splitted[0] + "dihia"+ SelectQ+ "dihia" + splitted[2] + "dihia" + splitted[3] + "dihia" + splitted[4]);
            CTest.add(parsedQuery);
            System.out.println(splitted[0]);

        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Format Converter.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Format Converter.txt";
        System.out.println("******************************Fin Format Convertor*********************************************");
        return (pathReturned);

    }

    public static String RobotQueryCleaner() {
        Collection<String> CTest = new ArrayList<String>();
        AppJava app = new AppJava();
        System.out.println("******************************Début Robot Cleaner*********************************************");
        /**** Insert query with topics  into DB*/
        /*Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Format Converter.txt");
        for (String line : C) {
            String[] splitted = line.split("dihia");
            int id= Integer.parseInt(splitted[0]);
            String query=splitted[1];
            String ip=splitted[3];
            String time=splitted[4];
            String topic="";
            app.insertqueryrobot(id,ip,time,topic,query);
            System.out.println(id);
        }*/

        /*String[] a = app.Selectbotqueries(app.COUNTselectbotqueries());
        System.out.println(a.length);
        for (int o = 0; o < a.length; o = o + 3) {
            app.delete(a[o], a[o + 1], a[o + 2]);
        }*/

        String[] aa = app.Selectbotqueries2(app.COUNTselectbotqueries2());
        for (int o = 0; o < aa.length; o = o + 3) {
            app.delete(aa[o], aa[o + 1], aa[o + 2]);
        }

        Collection<String[]> Colect =app.unbotedQueries();
        for(String[] val:Colect){ //id, query, ip
            String parsedQuery = (val[0] + "dihia"+ val[1]+ "dihia" + "" + "dihia" +val[2]);
            CTest.add(parsedQuery);
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Robot Query Cleaner.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Robot Query Cleaner.txt";
        System.out.println("******************************Fin Robot Cleaner*********************************************");
        return (pathReturned);

    }

    public static String BusinessAcademicQueryExtractor(String path, String type) {
        AppJava app = new AppJava();
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        System.out.println("******************************Début Busines/Academic cleaner*********************************************");
        String[] a = app.selectIPType();
        String business = "none";
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String ip = splitted[3];

            for (int o = 0; o < 1846; o = o + 2) {
                String val1 = a[o];

                if (val1.equals(ip)) {

                    if (a[o + 1].equals("university")) business = "Academic";
                    else business = "Business";
                }
            }

            if(!type.contains("All")) {
                if(type.contains(business)){
                String SyntaxQuery = (splitted[0] + "dihia" + splitted[1] + "dihia" + business+ "dihia" + splitted[3] );
                CTest.add(SyntaxQuery);
            }}
            else{
                String SyntaxQuery = (splitted[0] + "dihia" + splitted[1] + "dihia" + business+ "dihia" + splitted[3] );
                CTest.add(SyntaxQuery);
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Business-Academic Query Extractor.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Business-Academic Query Extractor.txt";
        System.out.println("******************************Fin Busines/Academic cleaner*********************************************");
        return (pathReturned);

    }

    public static String VulnerableQueryEliminator(String path) {
        AppJava app = new AppJava();
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        Collection<String> IPs = app.selectBlacklist();
        System.out.println("******************************Début Vulnerable queries*********************************************");
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String ip = splitted[3];
            String Vul;

            if (IPs.contains(ip)) {
                Vul = "Vulnerable";
            } else Vul = "UNVulnerable";



            if(Vul.equals("UNVulnerable"))
            {String SyntaxQuery = (splitted[0] + "dihia" + splitted[1] + "dihia" + "");
            CTest.add(SyntaxQuery);}
            else System.out.println(splitted[0]);
        }

        //SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Vulnerable Query Eliminator.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Vulnerable Query Eliminator.txt";
        System.out.println("******************************Fin Vulnerable queries*********************************************");
        return (pathReturned);

    }

    public static String Deduplicator(String path) {
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> dedup = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String query = splitted[1];
            String vide = viderQuery(query);
            if (!dedup.contains(vide)) {
                dedup.add(vide);
                String SyntaxQuery = (splitted[0] + "dihia" + query + "dihia" + "");
                CTest.add(SyntaxQuery);
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Deduplicator2.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Deduplicator2.txt";
        return (pathReturned);

    }

    public static String SyntacticCorrector(String path) {
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        String SyntValid = "";
        String corrigé = "";
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String Query = FixSynt(splitted[1]);
            Query q = QueryFixer.toQuery(Query);
            if (q == null) {
                SyntValid = "Non";
            } else {
                SyntValid = "Oui";
            }

            /* Correction syntaxique */
            Query = QueryFixer.get().fix(Query);
            if (Query.toLowerCase().contains("select") == false && Query.toLowerCase().contains("where") == false) {

                int z = Query.indexOf("{");
                if (z == -1) {
                    Query = "{" + Query;
                    z = Query.indexOf("{");
                }
                if (z != -1) {
                    String s1 = Query.substring(0, z);
                    String s2 = Query.substring(z);
                    Query = s1 + " select * Where " + s2;
                }
            }

            Query = corrige(Query);
            q = QueryFixer.toQuery(Query);

            /* vérifier si c bien corrigé*/
            if (q != null) {corrigé = "Oui";
                String SyntaxQuery = (splitted[0] + "dihia" + Query + "dihia" + "");
                CTest.add(SyntaxQuery);}
            else {corrigé = "Non";}


        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Syntactic Corrector.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Syntactic Corrector.txt";
        return (pathReturned);

    }

    public static String SemanticCorrector(String path) {
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String SelectQ = splitted[1];
            OntModel ontologie = ModelFactory.createOntologyModel();
            OntologyFactory.readOntology("C:\\Users\\HP\\Desktop\\conference-ontology-alignments.owl", ontologie);
            StmtIterator it1 = ontologie.listStatements();
            String semntic = "Non";
            while (it1.hasNext()) {
                Statement st = it1.next();
                if (SelectQ.contains(st.getSubject().toString()) && (st.getPredicate().toString().contains("equivalent"))) {
                    SelectQ = SelectQ.replaceAll(st.getSubject().toString(), st.getObject().toString());
                    semntic = "Corrected";
                }
            }
            String SyntaxQuery = (splitted[0] + "dihia" + SelectQ + "dihia" + "");
            CTest.add(SyntaxQuery);
        }

        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Semantic Corrector.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Semantic Corrector.txt";
        return (pathReturned);

    }

    public static String TopicClustering(String path,Collection<String> topics) {
        AppJava app = new AppJava();
        Collection<String> CTest = new ArrayList<String>();

        Collection<String> C = SharedFunctions.ReadFile(path);
        String[] a = app.selectRef();
        String topic;
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String query = splitted[1];
            topic = "None";
            for (int o = 0; o < 236; o = o + 2) {
                String val1 = a[o];
                if (query.toLowerCase().contains(val1.toLowerCase())) {
                    topic = a[o + 1];
                }
            }
            if(topics.contains("All Topics")){
                String SyntaxQuery = (splitted[0] + "dihia" + query + "dihia" + topic);
                CTest.add(SyntaxQuery);
            }
            else {
            if(topics.contains(topic)){
            String SyntaxQuery = (splitted[0] + "dihia" + query + "dihia" + topic);
            CTest.add(SyntaxQuery);}
        }}
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Topic Clustering.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Topic Clustering.txt";
        return (pathReturned);
    }

    public static String SchemaRanking(String path,Collection<String> topics) throws SQLException {
        AppJava app = new AppJava();

        /*insert topics into DB*/
       Collection<String> C = SharedFunctions.ReadFile(path);
        /* for(String line:C){
            app.insertqueryrobot(Integer.parseInt(line.split("dihia")[0]),line.split("dihia")[1],line.split("dihia")[2]);
        }
        /*insert shapes into DB*/
        /*HashMap<Integer, String> cole = app.correctquerie();
        for (int ii:cole.keySet()) {
            String query = cole.get(ii);
            Query maybeQuery = QueryFixer.toQuery(query);
            if (maybeQuery != null) {
                Element ee = maybeQuery.getQueryPattern();
                if (ee instanceof ElementGroup) {
                    if (((ElementGroup) ee).getElements().size() > 0) {
                        Element e = ((ElementGroup) ee).getElements().get(0);
                        if (e instanceof ElementPathBlock) {
                            ElementPathBlock e1 = (ElementPathBlock) e;
                            PathBlock pBlk = e1.getPattern();
                            for (TriplePath tp : pBlk) {
                                String sub = "-";
                                String pred = "-";
                                String obj = "-";
                                if (tp.getSubject() != null) {
                                    if (tp.getSubject().isVariable() == false) sub = tp.getSubject().toString();
                                }
                                if (tp.getPredicate() != null) {
                                    if (tp.getPredicate().isVariable() == false) pred = tp.getPredicate().toString();
                                }
                                if (tp.getObject() != null) {
                                    if (tp.getObject().isVariable() == false) obj =tp.getObject().toString();
                                }
                                app.insertshape(ii, sub, pred, obj);
                                }
                        }
                    }
                }
            }
        }*/
        System.out.println("*****************************Début************************************");
        Collection<String> Ctest = new ArrayList<>();
        for(String topic:topics) {
            System.out.println("-----------------------"+topic);
            ResultSet rs = null;
            rs=app.selectshape(topic);
            Collection<String> colect = new ArrayList<>();
            Collection<Integer> col = new ArrayList<>();
            Collection<String> colleID = new ArrayList<>();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String SUB = rs.getString("sub");
                String PRED = rs.getString("pred");
                String OBJ = rs.getString("obj");
                if (col.size() == 0) {
                    colect.add(SUB + PRED + OBJ);
                    col.add(ID);
                } else {
                    if (col.contains(ID) == false) {
                        if (colect.contains(SUB + PRED + OBJ) == false) {
                            colect.add(SUB + PRED + OBJ);
                            col.add(ID);
                        }
                    } else {
                        colect.add(SUB + PRED + OBJ);
                    }
                }
            }
            for (int aaa : col) {
                colleID.add(Integer.toString(aaa));
            }

            //Collection<String> CC = SharedFunctions.ReadFile(path);
            for (String line : C) {
                if (colleID.contains(line.split("dihia")[0])){
                    System.out.println("Yes contains: "+line.split("dihia")[0]);
                    Ctest.add(line.split("dihia")[0]+"dihia"+line.split("dihia")[1]+"dihia"+line.split("dihia")[2]);
            }}
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Schema Ranking.txt", Ctest);
        System.out.println("*****************************FIN************************************");
        return ("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Schema Ranking.txt");
    }

    public static String ComplexityFilter(String path, Collection<String> shapes, int depthmin, int depthmax) {

        Collection<String> C = SharedFunctions.ReadFile(path);
        Collection<String> CTest = new ArrayList<String>();

        for (String line:C) {
            String query = line.split("dihia")[1];
            Query maybeQuery = QueryFixer.toQuery(query);
            String shape = "star";
            String expertise="";
            int depth =0;
            if (maybeQuery != null) {
                Element ee = maybeQuery.getQueryPattern();
                if (ee instanceof ElementGroup) {
                    if (((ElementGroup) ee).getElements().size() > 0) {
                        Element e = ((ElementGroup) ee).getElements().get(0);
                        if (e instanceof ElementPathBlock) {
                            ElementPathBlock e1 = (ElementPathBlock) e;
                            PathBlock pBlk = e1.getPattern();
                            depth = pBlk.size();
                            System.out.println(depth);
                            if (depth == 1) {
                                shape = "simple";
                                expertise="beginner";
                            }
                            else if (depth == 2) {
                                expertise="beginner";
                                String triples[]=new String[6];
                                int i=0;
                                for (TriplePath tp : pBlk) {
                                    triples[i]=tp.getSubject().toString();
                                    triples[i+1] = tp.getPredicate().toString();
                                    triples[i+2] = tp.getObject().toString();
                                    i=i+3;
                                }
                                if (triples[0].equals(triples[5]) || triples[2].equals(triples[3])) shape = "chain";
                                else shape = "star-----";
                            }
                            else {
                                String triples[]=new String[depth*3];
                                int i=0;
                                for (TriplePath tp : pBlk) {
                                    triples[i]=tp.getSubject().toString();
                                    triples[i+1] = tp.getPredicate().toString();
                                    triples[i+2] = tp.getObject().toString();
                                    i=i+3;
                                }
                                shape="star";
                                expertise="intermediate";
                                i=0;
                                while (i<(triples.length-3) && shape=="star"){
                                    if((triples[0].equals(triples[i+3])) || (triples[i+3]==null)) {shape="star***"; expertise="intermediate";}
                                    else {
                                        if (depth==5) {shape="tree"; expertise="intermediate";}
                                        else if (depth==6) {shape="flower"; expertise="expert";}
                                        else if (depth>6 && depth<9) {shape="forrest"; expertise="expert";}
                                        else if(depth >8) {shape="bouquet"; expertise="expert";}
                                    }
                                    i=i+3;
                                }
                            }
                        }
                    }
                }
                if(depth==0) {shape="star"; expertise="intermediate";}
                if(shapes.contains(shape) && depth>=depthmin && depth<=depthmax){
                String SyntaxQuery = (line.split("dihia")[0] + "dihia" + line.split("dihia")[1] + "dihia" + shape + "dihia" + expertise + "dihia" + depth);
                CTest.add(SyntaxQuery);
            }}
            else {
                String SyntaxQuery = (line.split("dihia")[0] + "dihia" + line.split("dihia")[1] + "dihia" + "Unkown" + "dihia" + "Unkown" + "dihia" + "Unkown");
                CTest.add(SyntaxQuery);
                System.out.println("I'm here");
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Complexity Filter.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Complexity Filter.txt";
        System.out.println("****************************************** FIN ****************************************************");
        return (pathReturned);
    }

    public static String ExpertiseFilter(String path, Collection<String> expertLevels){
        Collection<String> C = SharedFunctions.ReadFile(path);
        Collection<String> CTest = new ArrayList<String>();

        for (String line:C) {
            if(expertLevels.contains("All levels")){
                String SyntaxQuery = (line.split("dihia")[0] + "dihia" + line.split("dihia")[1] + "dihia" + line.split("dihia")[3]);
                CTest.add(SyntaxQuery);
            }
            else{
                String level=line.split("dihia")[3];
                if(expertLevels.contains(level)){
                    String SyntaxQuery = (line.split("dihia")[0] + "dihia" + line.split("dihia")[1] + "dihia" + line.split("dihia")[3]);
                    CTest.add(SyntaxQuery);
            }
        }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Expertise Filter.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Expertise Filter.txt";
        System.out.println("****************************************** FIN ****************************************************");
        return (pathReturned);
    }

    public static String AnalyticStandardQuerySelector(String path, Collection<String> types) {
        Collection<String> CTest = new ArrayList<String>();
        Collection<String> C = SharedFunctions.ReadFile(path);
        for (String line : C) {
            String[] splitted = line.split("dihia");
            String SelectQ = splitted[1];
            String querType;
            if (SelectQ.toLowerCase().contains("count(") || SelectQ.contains("sum(") || SelectQ.contains("min(") ||
                    SelectQ.contains("max(") || SelectQ.contains("avg(")) {
                querType = "Analytic";
            } else {
                querType = "Standard";
            }

            if (types.contains("All Queries")) {
                String SyntaxQuery = (splitted[0] + "dihia" + splitted[1] + "dihia" + querType);
                CTest.add(SyntaxQuery);
            } else {
                if (types.contains(querType)) {
                    String SyntaxQuery = (splitted[0] + "dihia" + splitted[1] + "dihia" + querType);
                    CTest.add(SyntaxQuery);
                }
            }
        }
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Analytic-Standard Query Selector.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Analytic-Standard Query Selector.txt";
        return (pathReturned);
    }

    public static String SaveToFile(String path) {
        Collection<String> C = SharedFunctions.ReadFile(path);
        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\File Loader.txt", C);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\File Loader.txt";
        return (pathReturned);
    }

    public static String LogsEnrichment(String path,String pathLogDB) {
        Collection<String> CTest = SharedFunctions.ReadFile(path);
        Collection<String> C = SharedFunctions.ReadFile(pathLogDB);
        String [] list = {"semanticweb","scholarly data","swrc","w3id"};
        for (String line : C) {
            for(int i=0; i<list.length;i++){
                if(line.contains(list[i])) {
                    CTest.add("x"+"dihia"+line+"dihia"+"added");
                    i=list.length;
                }
            }

        }

        SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Logs enrichment.txt", CTest);
        String pathReturned = "C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Logs enrichment.txt";
        return (pathReturned);

    }

    public static String Logsjoin(String path1,String path2) {
        AppJava app=new AppJava();

        Collection<String> topics1 = app.selectReflog1();
        Collection<String> topics2 = app.selectReflog2();
        Collection<String> C1 = new ArrayList<String>();
        Collection<String> C2 = SharedFunctions.ReadFile(path1);
        Collection<String> join = new ArrayList<String>();

        for (String topic2 : topics2) {
            for (String topic1 : topics1)

                if(compute(topic1, topic2)>0.6 || topic1.contains(topic2) || topic2.contains(topic1)) C1.add(topic2);
        }
        for (String line : C2) {
            for(String topic: C1){
                if(line.split("dihia")[2].equals(topic)) join.add(line.split("dihia")[1]);
            }

        }


        SharedFunctions.WriteInFile(path2, join);
        String pathReturned = path2;
        return (pathReturned);

    }

    public static void InsetQueriestoDB() {
        AppJava app = new AppJava();
        Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\SemanticQueries.txt");
        for (String line : C) {
            String[] splitted = line.split("dihia");
            int id= Integer.parseInt(splitted[0]);
            String ip=splitted[1];
            String time=splitted[2];
            String topic=splitted[8];
            String query=splitted[11];
            //app.insertqueryrobot(id,ip,time,topic,query);
        }

    }

    public static void insertBGPintoDB() {
        AppJava app = new AppJava();
        HashMap<Integer, String> col = app.correctquerie();
        for (int ii:col.keySet()) {
            String query = col.get(ii);
            Query maybeQuery = QueryFixer.toQuery(query);
            if (maybeQuery != null) {
                Element ee = maybeQuery.getQueryPattern();

                if (ee instanceof ElementGroup) {
                    if (((ElementGroup) ee).getElements().size() > 0) {

                        Element e = ((ElementGroup) ee).getElements().get(0);

                        if (e instanceof ElementPathBlock) {

                            ElementPathBlock e1 = (ElementPathBlock) e;
                            PathBlock pBlk = e1.getPattern();


                            for (TriplePath tp : pBlk) {

                                String sub = "-";
                                String pred = "-";
                                String obj = "-";

                                if (tp.getSubject() != null) {
                                    if (tp.getSubject().isVariable() == false) sub = tp.getSubject().toString();
                                }
                                if (tp.getPredicate() != null) {
                                    if (tp.getPredicate().isVariable() == false) pred = tp.getPredicate().toString();
                                }
                                if (tp.getObject() != null) {
                                    if (tp.getObject().isVariable() == false) obj =tp.getObject().toString();
                                }
                                app.insertshape(ii, sub, pred, obj);
                            }
                        }
                    }
                }
            }
        }

    }


/*************** The Process Execution of our Trust ETL solution *****************************/
    public void TrustETLProcess(){
        int trusted=6759;
        int total=139766;
        info1.setText(""+trusted);
        info2.setText(""+(total-trusted));
        info4.setText(""+ new DecimalFormat("##.##").format(((((float)nbExtractedQueries-(float)trusted)/(float)nbExtractedQueries)  *100)) +"%");
        info3.setText(""+ new DecimalFormat("##.##").format(((((float)nbExtractedQueries-(float)total)  /(float)nbExtractedQueries)  *100))     +"%");

        Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\File Loader.txt");
        DisplayTable(C);

        /*Put in Run Button*/
        //xAxis.setLabel("T-ETL Operations");
        yAxis.setLabel("Rate of trust (%)");
        //linechart1.setTitle("Rate of trust evolution");
        //xAxis.setTickLabelRotation(20);
        linechart1.setStyle("-fx-font-size: " + 8 + "px;");
        linechart1.setStyle("-fx-font-family: " + "'Bell MT'" + ";");
        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");
        linechart1.getData().add(new XYChart.Series(FXCollections.observableArrayList(new XYChart.Data("",0))));
        linechart1.getData().clear();
        //populating the series with data

        ObservableList<String> list = FXCollections.observableArrayList();

        if(Checbx2.isSelected()) {list.add("Query Extractor");
        series.getData().add(new XYChart.Data("Query Extractor", 0));}
        if(Checbx3.isSelected()) {list.add("Format Converter");
        series.getData().add(new XYChart.Data("Format Converter", 0));}
        if(Checbx4.isSelected()) {list.add("Robot Query Cleaner");
            series.getData().add(new XYChart.Data("Robot Query Cleaner", 46));}
        if(Checbx5.isSelected()) {list.add("Business-Academic Query Extractor");
            series.getData().add(new XYChart.Data("Business-Academic Query Extractor", 46));}
        if(Checbx6.isSelected()) {list.add("Vulnerable Query Eliminator");
            series.getData().add(new XYChart.Data("Vulnerable Query Eliminator", 47));}
        if(Checbx7.isSelected()) {list.add("Deduplicator");
            series.getData().add(new XYChart.Data("Deduplicator", 48));}
        if(Checbx8.isSelected()) {list.add("Syntactic Corrector");
            series.getData().add(new XYChart.Data("Syntactic Corrector", 58));}
        if(Checbx9.isSelected()) {list.add("Semantic Corrector");
            series.getData().add(new XYChart.Data("Semantic Corrector", 58));}
        if(Checbx10.isSelected()) {list.add("Topic Clustering");
            series.getData().add(new XYChart.Data("Topic Clustering", 58));}
        if(Checbx11.isSelected()) {list.add("Schema Ranking");
            series.getData().add(new XYChart.Data("Schema Ranking", 95));}
        if(Checbx12.isSelected()) {list.add("Complexity Filter");
            series.getData().add(new XYChart.Data("Complexity Filter", 95));}
        if(Checbx13.isSelected()) {list.add("Expertise Filter");
            series.getData().add(new XYChart.Data("Expertise Filter", 95));}
        if(Checbx14.isSelected()) {list.add("Analytic-Standard Query Selector");
            series.getData().add(new XYChart.Data("Analytic-Standard Query Selector", 95));}
        if(Checbx15.isSelected()) {list.add("Logs Join");
            series.getData().add(new XYChart.Data("Logs Join", 96));}
        if(Checbx16.isSelected()) {list.add("Logs Enrichement");
            series.getData().add(new XYChart.Data("Logs Enrichement", 96));}
        /*xAxis.setCategories(FXCollections.observableArrayList("Query Extractor","Format Converter","Robot Query Cleaner","Business-Academic Query Extractor","Vulnerable Query Eliminator","Deduplicator","Syntactic Corrector","Semantic Corrector",
                "Topic Clustering","Schema Ranking","Complexity Filter","Expertise Filter","Analytic-Standard Query Selector","Logs Join","Logs Enrichement"));
        */
        xAxis.setCategories(list);
        linechart1.getData().add(series);
       /* xAxis.setAnimated(false);
        yAxis.setAnimated(false);*/

        linechart1.getXAxis().setAnimated(false);
        linechart1.getYAxis().setAnimated(false);


        //String path=QueriesExtractor("C:\\Users\\HP\\Desktop\\PFE\\LOG GEO\\SWDF.log");
        //path=formatConverter(path);
        //RobotQueryCleaner();
        //BusinessAcademicQueryExtractor("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Robot Query Cleaner.txt","All");
        //VulnerableQueryEliminator("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Business-Academic Query Extractor.txt");
        //Deduplicator("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Analytic-Standard Query Selector.txt");
        //SyntacticCorrector("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Deduplicator.txt");
        //SemanticCorrector("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Syntactic Corrector.txt");
        //Collection<String> C1 = new ArrayList<String>();
        //C1.add("All Queries");


        //TopicClustering("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Semantic Corrector.txt",C1);
       /* try {
            SchemaRanking("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Topic Clustering.txt",C1);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
       //ComplexityFilter("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Schema Ranking.txt");

        //ExpertiseFilter("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Complexity Filter.txt",C1);
        //AnalyticStandardQuerySelector("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Expertise Filter.txt", C1);
        /*String path=null;
        String pathdest=null;
        Collection<String> C = SharedFunctions.ReadFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\ListOperators.txt");
        for (String line:C) {
            if(line.contains("Connection")) path=line.split(": ")[1];
        } //"C:\\Users\\HP\\Desktop\\PFE\\LOG GEO\\SWDF.log"
        System.out.println(path);
        pathdest=QueriesExtractor(path);
        System.out.println(pathdest);
        */
/* Le log DBpedia */
       /* String ReadingFilePath = "C:\\Users\\HP\\Desktop\\dbp351logs\\";
        File rep = new File(ReadingFilePath);
        File[] fichiersJava = rep.listFiles();
        Collection<String> CTest = new ArrayList<String>();

       for (File var : fichiersJava) {
            String path = ReadingFilePath + var.getName();
            Collection<String> C = SharedFunctions.ReadFile(path);
            for (String line:C){
                if(line.toLowerCase().contains("paper") || line.toLowerCase().contains("conference") || line.toLowerCase().contains("article") || line.toLowerCase().contains("Academic")
                        ||  line.toLowerCase().contains("demo") || line.toLowerCase().contains("talk") || line.toLowerCase().contains("social event")
                        || line.toLowerCase().contains("workshop") || line.toLowerCase().contains("proceeding") || line.toLowerCase().contains("track")
                        || line.toLowerCase().contains("panel") || line.toLowerCase().contains("session") || line.toLowerCase().contains("tutorial")){
                    String SelectQ = (URLEncodedUtils.parse(line, Charset.forName("UTF-8"))).toString();

                    SelectQ = QueryFixer.get().fix(SelectQ);

                    if(SelectQ.indexOf("query")!=-1) {SelectQ=SelectQ.substring(SelectQ.lastIndexOf("query=")+6, SelectQ.lastIndexOf("]"));
                    CTest.add(SelectQ);}
                }
            }
           }
        System.out.println("----------------------------------------- Finnished ----------------------------------------------------");
       SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\DBpedia.txt", CTest);*/

        //LogsEnrichment("C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\Analytic-Standard Query Selector.txt","C:\\Users\\HP\\Desktop\\ResultedFilesGUI\\DBpedia.txt");






    }

}
