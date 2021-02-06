# TrustETL

To help data analysts curating their LOD logs, we propose an interactive tool consisting in curating LOD query logs and selecting only the trusted queries by orchestrating the trust ETL operators to form a suitable ETL pipeline. Our system adopts three tiers architecture based on theMVC framework.


This solution is developed using Java and Scala for parallel programming when extracting and transforming queries. Jena API (ARQ and Core libraries) is also used to deal with SPARQL queries.