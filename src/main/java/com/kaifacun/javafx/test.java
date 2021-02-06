package com.kaifacun.javafx;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Selector;

public class test {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel() ;
        Model a=model.read("C:\\Users\\HP\\Desktop\\data.trig") ;

        String q="prefix doc: <http://example.org/#ns>" +
                "select ?s ?p ?o where{?s ?p ?o." +
                "?s doc:trustis 0.54.}";

        String q2="prefix doc: <http://example.org/#ns>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "GRAPH <http://example.org/val1> {?s ?p ?o}.   \n" +
                "}";


        Dataset dataset = DatasetFactory.create();
        dataset.addNamedModel("model",a);
        System.out.println(dataset.listNames().next());
        ResultSet resultSet = QueryExecutionFactory
                .create(q2, dataset.getNamedModel("model"))
                .execSelect();
        while (resultSet.hasNext()) {
            System.out.println(resultSet.next());
        }
    }
}
