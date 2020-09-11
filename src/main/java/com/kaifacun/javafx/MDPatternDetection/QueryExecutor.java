package com.kaifacun.javafx.MDPatternDetection;

import com.kaifacun.javafx.Utils.SharedFunctions;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.kaifacun.javafx.Declarations.DeclarationPaths.*;

public class QueryExecutor {

    public static ArrayList<Model> executeQuiersInFile(String endPoint,ArrayList<Query> constructQueriesList) {

        ArrayList<Model> results = new ArrayList<>();

        try {
            QueryExecutor queryExecutor = new QueryExecutor();

            // Execution of each CONSTRUCT query
            for (Query query : constructQueriesList) {
                System.out.println("exeution req ");
                Model model;
                if ((model = queryExecutor.executeQueryConstruct(query, endPoint)) != null) results.add(model);
                else SharedFunctions.writeQueryInLog(logFile, "Construct empty results: ", query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    public static void main(String[] args) {
        String endPoint = "https://dbpedia.org/sparql";
        //QueryExecutor.executeQuiersInFile2(DeclarationPaths.constructQueriesFile, endPoint);
    }
    public static void executeQuiersInFile2(/*String filePath, */String endPoint,ArrayList<Query> allLines) {
        ArrayList<Model> results = new ArrayList<>();
        //ArrayList<String> allLines = (ArrayList<String>) SharedFunctions.ReadFile(filePath);
        int size = allLines.size();
        //int size=40;
        List<String> lines;
        QueryExecutor queryExecutor = new QueryExecutor();
        int num = 0;
        //Query query = QueryFactory.create();
        System.out.println("\nL'execution des requetes \n");

            for (Query query : allLines) {
                num++;
                //query=QueryFactory.create(queryStr);
                System.out.println("Execution req " + num + "\n");
                try {
                    Model model = queryExecutor.executeQueryConstruct(query, endPoint);
                    //if (model != null)
                    if (!model.isEmpty()) {
                        results.add(model);
                        System.out.println("Le model :\n");
                        Iterator<Statement> listStatements = model.listStatements();
                        while (listStatements.hasNext()) {
                            listStatements.next();
                            System.out.println("\n ResultSet of the query " + num + ": " + listStatements.next().toString());
                        }

                    }
                }catch (Exception e){
                    SharedFunctions.writeQueryInLog("C:\\Users\\HP\\Desktop\\Files\\ProgramOutput\\logconstruct.txt","Constuct : ", query);
                }

        }



                /*System.out.println("\nLa consolidation \n");
                if (!results.isEmpty()) {
                    stopwatch_consolidation = Stopwatch.createStarted();
                    HashMap<String, Model> modelHashMap = Consolidation.consolidate(results);
                    stopwatch_consolidation.stop();

                    // persist before annotate
                    System.out.println("\n le persisting 1  \n");
                    stopwatch_persist1 = Stopwatch.createStarted();
                    TdbOperation.persistNonAnnotated(modelHashMap);
                    stopwatch_persist1.stop();

                    // annotation

                    System.out.println("\n L'annotation \n");
                    stopwatch_annotate = Stopwatch.createStarted();
                    MDGraphAnnotated.constructMDGraphs(modelHashMap);
                    stopwatch_annotate.stop();

                    // persisting
                    System.out.println("\n le persisting 2 \n");
                    stopwatch_persist2 = Stopwatch.createStarted();
                    TdbOperation.persistAnnotatedHashMap(modelHashMap);
                    stopwatch_persist2.stop();
                }
                lines = allLines.subList(0, cpt);
                allLines.removeAll(lines);*/
            }






    public boolean executeQueryAsk(String queryStr, String endpoint) {
        boolean results = false;

        Query query = null;
        try {
            query = QueryFactory.create(queryStr);
            QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
            results = qexec.execAsk();


        } catch (Exception e) {
            e.printStackTrace();
            SharedFunctions.writeQueryInLog(logFile, "Ask", query);
        }
        return results;
    }

    public QueryExecutor() {
    }

    public ResultSet executeQuerySelect(String queryStr, String endpoint)
    {
        ResultSet results = null;
        Query query = null;
        try{
            query = QueryFactory.create(queryStr);
            QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
            results = qexec.execSelect();

        }
        catch (Exception e){
            e.printStackTrace();
            SharedFunctions.writeQueryInLog(logFile, "Select", query);
        }
        return results;
    }


    public Model executeQueryConstruct(Query query, String endpoint)
    {
        Model results = null;
        try{

            QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(endpoint, query);
            results = qexec.execConstruct();

        }
        catch (Exception e){
            SharedFunctions.writeQueryInLog(logFile, "Construct: ", query);
        }
        return results;
    }

}

