package com.kaifacun.javafx.MDFromQueryLogs;

import com.kaifacun.javafx.Utils.SharedFunctions;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class AppJava {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Users/HP/Desktop/sparqlogs.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(int id, String ip, String date, String query, String hit,String type, String typeAna, String valid, String corrigé) {
        String sql = "INSERT INTO raw_queries (id,ip,date,query,hit,type,typeAna,valide,corrigéSynt) VALUES(?,?,?,?,?,?,?,?,?)";

        try {Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, ip);
            pstmt.setString(3, date);
            pstmt.setString(4, query);
            pstmt.setString(5, hit);
            pstmt.setString(6, type);
            pstmt.setString(7, typeAna);
            pstmt.setString(8, valid);
            pstmt.setString(9, corrigé);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertNew(int id, String topic) {
        String sql = "INSERT INTO topic_query (idquery,topic) VALUES(?,?)";

        try {Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setInt(1, id);
            pstmt.setString(2, topic);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertshape(int id, String sub, String pred, String obj) {
        String sql = "INSERT INTO query_shcemaDBP (ID, sub, pred, obj) VALUES (?,?,?,?);";

        try {Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setInt(1, id);
            pstmt.setString(2, sub);
            pstmt.setString(3, pred);
            pstmt.setString(4, obj);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertquery(String topic, int id) {
        String sql = "INSERT INTO querytest (query, Id) VALUES(?,?)";

        try {Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setString(1, topic);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIP(String ip, String hostname, String city) {
        String sql = "INSERT INTO ip_detection (ip,hostname,city) VALUES(?,?,?)";

        try {Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setString(1, ip);
            pstmt.setString(2, hostname);
            pstmt.setString(3, city);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertqueryrobot(int id, String ip,  String date, String topic) {
        String sql = "INSERT INTO cleanRobotdbp (id, ip, date, topic) VALUES (?,?,?,?);";

        try {Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setInt(1, id);
            pstmt.setString(2, ip);
            pstmt.setString(3, date);
            pstmt.setString(4, topic);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void selectAll(){
        String sql = "select distinct query\n" +
                "from raw_queries\n";/* +
                "where type='Construct'";*/

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                String SelectQ = (URLEncodedUtils.parse(rs.getString("query"), Charset.forName("UTF-8"))).toString();
                //System.out.println(SelectQ);
                if(SelectQ.contains("rdf:type")){
                    int j=SelectQ.lastIndexOf("rdf:type");
                    String val=SelectQ.substring(j+8);
                    int jj=0;
                    if(val.contains("\n")) jj=val.indexOf("\n");
                    else if(val.contains(".")) jj=val.indexOf(".");
                    String val2=val.substring(0,jj);
                    System.out.println("rdf: "+val2);
                }
                else  if(SelectQ.contains(" a ")){
                    int j=SelectQ.lastIndexOf(" a ");
                    String val=SelectQ.substring(j+3);

                    int jj=0;
                    if (val.contains("\n")) jj=val.indexOf("\n");
                    else {if(val.contains(".")) jj=val.indexOf(".");
                          if(val.contains(">.")) jj=val.indexOf(">.");
                          if(val.contains("> .")) jj=val.indexOf("> .");
                    }
                    String val2=val.substring(0,jj);
                    System.out.println("a: "+val2);
                }
                System.out.println("-----------------------------------------------------------------------------------");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public HashMap<Integer, String> selectRefALL(){

        String sql = "SELECT id, query FROM querytest;";
        ResultSet rs=null;
        HashMap<Integer, String> col = new HashMap<Integer, String>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);


            while(rs.next()){
                String val1 = rs.getString("query");
                int val2 = rs.getInt("id");
                col.put(val2, val1);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return col;
    }


    public HashMap<Integer, String> correctquerie(){

        String sql = "SELECT id, [query] as q\n" +
                "FROM cleanRobotnew;\n";
        ResultSet rs=null;
        HashMap<Integer, String> col = new HashMap<Integer, String>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);


            while(rs.next()){
                String val1 = rs.getString("q");
                int val2 = rs.getInt("id");
                col.put(val2, val1);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return col;
    }

    public Collection<String[]> unbotedQueries(){

        String sql = "SELECT id, [query] as q, ip\n" +
                "FROM cleanRobot;\n";

        ResultSet rs=null;
        Collection<String[]> CTest = new ArrayList<String[]>();

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                String[] valeur=new String[3];
                String val1 = rs.getString("q");
                int val2 = rs.getInt("id");
                String val3 = rs.getString("ip");
                valeur[0]=""+val2; valeur[1]=val1; valeur[2]=val3;
                CTest.add(valeur);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return CTest;
    }



    public String[] selectRef(){

        String sql = "SELECT val1, val2\n" +
                "  FROM referent;";/* +
                "where type='Construct'";*/
        ResultSet rs=null;
        String E[]=new String[270];
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            int i=0;

            while(rs.next()){
                String val1 = rs.getString("val1");
                String val2 = rs.getString("val2");
                E[i]=(val1);
                E[i+1]=val2;
                i=i+2;

            }
            conn.close();

            } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
        }


    public  Collection<String> selectReflog2(){

        String sql = "SELECT distinct val1\n" +
                "  FROM referent2;";/* +
                "where type='Construct'";*/
        ResultSet rs=null;
        Collection<String> E = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            int i=0;

            while(rs.next()){
                String val1 = rs.getString("val1");
                ((ArrayList<String>) E).add(val1);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }


    public  Collection<String> selectReflog1(){

        String sql = "SELECT distinct val1\n" +
                "  FROM referent;";/* +
                "where type='Construct'";*/
        ResultSet rs=null;
        Collection<String> E = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            int i=0;

            while(rs.next()){
                String val1 = rs.getString("val1");
                ((ArrayList<String>) E).add(val1);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }

    public int COUNTselectbotqueries(){

        String sql = "select count(*) as nb from (select count(id), ip, max(date) maxx, min(date) minn \n" +
                "from cleanRobot\n" +
                "group by ip\n" +
                "having count(id)>=30 and strftime('%s',max(date)) - strftime('%s',min(date))<1000) as fct ;";

        ResultSet rs=null;
        int ip=0;

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                ip = rs.getInt("nb");
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return ip;
    }

    public int COUNTselectbotqueries2(){

        String sql = "select count(*) as nb from " +
                "(select min(date) minn, max(date) maxx,  Substr(date,0,11) as datee, strftime('%s',max(date)) - strftime('%s',min(date)) as time, count(id) as cnt, ip\n" +
                "from cleanRobotdbp\n" +
                "group by Substr(date,0,11), ip\n" +
                "having count(id)>=1000 and strftime('%s',max(date)) - strftime('%s',min(date))<100000) as fct ;";

        ResultSet rs=null;
        int ip=0;

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                ip = rs.getInt("nb");
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return ip;
    }

    public String[] Selectbotqueries(int nb){

        String sql = "select count(id), ip, max(date) maxx, min(date) minn \n" +
                "from cleanRobot\n" +
                "group by ip\n" +
                "having count(id)>=30 and strftime('%s',max(date)) - strftime('%s',min(date))<1000 ;";

        ResultSet rs=null;
        String[] E=null;
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();

            rs    = stmt.executeQuery(sql);
            E= new String[nb*3];
            int i=0;
            while(rs.next()){
                String ip = rs.getString("ip");
                String min = rs.getString("minn");
                String max = rs.getString("maxx");
                E[i]=(ip);
                E[i+1]=min;
                E[i+2]=max;
                i=i+3;

            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }


    public String[] Selectbotqueries2(int nb){

        String sql = "select min(date) minn, max(date) maxx,  Substr(date,0,11) as datee, strftime('%s',max(date)) - strftime('%s',min(date)) as time, count(id) as cnt, ip\n" +
                "from cleanRobotdbp\n" +
                "group by Substr(date,0,11), ip\n" +
                "having count(id)>=1000 and strftime('%s',max(date)) - strftime('%s',min(date))<100000;";

        ResultSet rs=null;
        String[] E=null;
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();

            rs    = stmt.executeQuery(sql);
            E= new String[nb*3];
            int i=0;
            while(rs.next()){
                String ip = rs.getString("ip");
                String min = rs.getString("minn");
                String max = rs.getString("maxx");
                E[i]=(ip);
                E[i+1]=min;
                E[i+2]=max;
                i=i+3;

            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }


    public Collection<String> SelectIDbot(String ip, String min, String max){

        String sql = "SELECT id FROM cleanRobotdbp\n" +
                "WHERE ip = '"+ip+"' AND date>= '"+min+"'  AND date<='"+max+"';";

        ResultSet rs=null;
        Collection<String> E = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);

            int i=0;
            while(rs.next()){
                String ipp =rs.getString("id");

                E.add(ipp);


            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }


    public void delete(String ip, String min, String max) {
        String sql = "DELETE FROM cleanRobot\n" +
                "WHERE ip = ? AND date>= ?  AND date<=?;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, ip);
            pstmt.setString(2, min);
            pstmt.setString(3, max);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteALL() {
        String sql = "DELETE FROM cleanRobot;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] selectIPType(){

        String sql = "SELECT ip, type \n" +
                "FROM ip_detection;";

        ResultSet rs=null;
        String E[]=new String[1850];
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            int i=0;

            while(rs.next()){
                String ip = rs.getString("ip");
                String type = rs.getString("type");
                E[i]=(ip);
                E[i+1]=type;
                i=i+2;

            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }


    public Collection<String> selectBlacklist(){

        String sql = "SELECT ip\n" +
                "  FROM blacklist;";

        ResultSet rs=null;
        Collection<String> IPs = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);

            while(rs.next()){
                String val1 = rs.getString("ip");
                IPs.add(val1);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return IPs;
    }

    public ResultSet selectshape(String topic){

        String sql = "select bb.ID, bb.sub, bb.pred, bb.obj\n" +
                " from \n" +
                " (\n" +
                "  SELECT count(b.ID) as ct, b.ID as id, a.topic        \n" +
                "  FROM query_shcemaSCH as b\n" +
                "  left join cleanRobotsch as a\n" +
                "  on a.id=b.ID\n" +
                "  where (pred<>'-' or sub<>'-' or obj<>'-')\n" +
                "  and a.topic='"+topic+"'\n" +
                "  group by b.ID,  a.topic\n" +
                "  order by ct Desc\n" +
                "  ) as aa\n" +
                "  \n" +
                "left join \n" +
                "( select * from  query_shcemaSCH where (pred<>'-' or sub<>'-' or obj<>'-')) as bb\n" +
                "on aa.id=bb.id\n"
                ;

        ResultSet rs=null;

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);



        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return rs;
    }


    public String[] selectip(){

        String sql = "select distinct ip\n" +
                "FROM raw_queries";
        ResultSet rs=null;
        String E[]=new String[1000];

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            int i=0;

            while(rs.next()){
                String ip = rs.getString("ip");
                E[i]=(ip);
                i++;
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }


    public String selecPREFIX(String pref){

        String sql = "SELECT URI\n" +
                "FROM namespaces\n" +
                "WHERE PREFIX='"+pref+"';";
        //System.out.println("meeeeeeeeeeeeeeeeeeeeeeeeee: "+sql);

        ResultSet rs=null;
        String E="";
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            int i=0;

            while(rs.next()){
                String ip = rs.getString("URI");
                E=ip;
                //System.out.println("meeeeeeeeeeeeeeeeeeeeeeeeee: "+E);
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return E;
    }

    public void selectQuery(){

        String sql = "select distinct i.query as query\n" +
                "from raw_queries as i inner join \n" +
                "(select count(id), ip\n" +
                "from raw_queries\n" +
                "group by ip\n" +
                "having count(id)<40) as v\n" +
                "on v.ip=i.ip\n" +
                "where hit='200'";
        ResultSet rs=null;
        //String E[]=new String[150];
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
           int i=0;
            Collection<String> col = new ArrayList<>();
            while(rs.next()){
                i++;
                String ip = rs.getString("query");
                String SelectQ = (URLEncodedUtils.parse(ip, Charset.forName("UTF-8"))).toString();
                col.add(SelectQ);
                //System.out.println(SelectQ);
                 //System.out.println("-----------------------------------------------------------------------------------------");
            }
            SharedFunctions.WriteInFile("C:\\Users\\HP\\Desktop\\distinctqueriesValid.txt",col);

            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
       // return E;
    }

    public static void main(String args[]) {
        AppJava ap=new AppJava();
        ap.selectQuery();
    }


    }



