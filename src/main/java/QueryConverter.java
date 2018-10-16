import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryConverter {
    public static final String Q1 = "WITH \"measures\" AS (SELECT \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"Opportunity_StageName\" AS \"c0\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"OpportunityStage_SortOrder\" AS \"c0_o\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"to_stage\" AS \"c1\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"to_stage_ordinal\" AS \"c1_o\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"to_stage_ordinal\" AS \"c2\", COUNT(DISTINCT \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"Opportunity_Id\") AS \"m0\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\" WHERE \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"is_stage_transition\" = 1 AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"date\" >= '2018-04-01T00:00:00Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"date\" < '2018-10-01T00:00:00Z') GROUP BY \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"Opportunity_StageName\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"OpportunityStage_SortOrder\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"to_stage\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_stagetransition\".\"to_stage_ordinal\") (SELECT \"measures\".\"c0\" AS \"c0\", \"measures\".\"c1\" AS \"c1\", \"measures\".\"c2\" AS \"c2\", \"measures\".\"m0\" AS \"m0\" FROM \"measures\" ORDER BY \"measures\".\"c0_o\", \"measures\".\"c1_o\", \"measures\".\"c2\", \"measures\".\"m0\")";
    public static final String Q2 = "WITH \"dim_c0\" AS (SELECT CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -1, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= GETDATE() THEN 'current' WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -2, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= DATEADD('MONTH', -1, GETDATE()) THEN 'previous' ELSE 'neither' END AS \"c0\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\" WHERE \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"current_record\" = 1 AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" >= '2018-07-15T18:54:18.631Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" < '2018-10-15T18:54:18.631Z') GROUP BY CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -1, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= GETDATE() THEN 'current' WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -2, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= DATEADD('MONTH', -1, GETDATE()) THEN 'previous' ELSE 'neither' END), \"dimensions\" AS (SELECT \"c0\" FROM \"dim_c0\"), \"measures\" AS (SELECT \"dimensions\".\"c0\" AS \"c0\", (SUM(SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Amount__c\" ELSE 0 END + CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Expansion' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal') THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Amount__c\" - \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Renewal_Basis__c\" ELSE 0 END - CASE WHEN (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Lost' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Suspended/Abandoned') AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal' THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_MRR_Renewal_Basis__c\" * 12 ELSE 0 END)) OVER (PARTITION BY \"dimensions\".\"c0\" ROWS BETWEEN UNBOUNDED PRECEDING AND 0 FOLLOWING)) / CAST(NULLIF(SUM(SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN 1 ELSE 0 END - CASE WHEN (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Lost' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Suspended/Abandoned') AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal' THEN 1 ELSE 0 END)) OVER (PARTITION BY \"dimensions\".\"c0\" ROWS BETWEEN UNBOUNDED PRECEDING AND 0 FOLLOWING), 0) AS FLOAT) AS \"m0\" FROM \"dimensions\" LEFT JOIN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\" ON (\"dimensions\".\"c0\" = CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -1, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= GETDATE() THEN 'current' WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -2, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= DATEADD('MONTH', -1, GETDATE()) THEN 'previous' ELSE 'neither' END OR \"dimensions\".\"c0\" IS NULL AND CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -1, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= GETDATE() THEN 'current' WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" > DATEADD('MONTH', -2, GETDATE()) AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" <= DATEADD('MONTH', -1, GETDATE()) THEN 'previous' ELSE 'neither' END IS NULL) AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"current_record\" = 1 AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" >= '2018-07-15T18:54:18.647Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" < '2018-10-15T18:54:18.647Z')) GROUP BY \"dimensions\".\"c0\") (SELECT \"dimensions\".\"c0\" AS \"c0\", \"measures\".\"m0\" AS \"m0\" FROM \"dimensions\" INNER JOIN \"measures\" ON \"dimensions\".\"c0\" = \"measures\".\"c0\" OR \"dimensions\".\"c0\" IS NULL AND \"measures\".\"c0\" IS NULL ORDER BY \"dimensions\".\"c0\", \"measures\".\"m0\")\n";

    public static final String Q3 = "WITH \"dim_window_outer_2\" AS (SELECT \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"OpportunityStage_SortOrder\" AS \"window_outer_3\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\" AS \"window_outer_2\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\" WHERE \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\" IN ('Lead', 'Discovery', 'Qualification', 'Evaluation', 'Negotiation') AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\" >= '2017-10-01T00:00:00Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\" < '2018-10-01T00:00:00Z') GROUP BY \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"OpportunityStage_SortOrder\", \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\"), \"dateinfo_0\" AS (SELECT MIN(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\") AS \"mindate\", DATEDIFF('DAY', MIN(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\"), MAX(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\")) AS \"diff\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\" WHERE \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\" IN ('Lead', 'Discovery', 'Qualification', 'Evaluation', 'Negotiation') AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\" >= '2017-10-01T00:00:00Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\" < '2018-10-01T00:00:00Z')), \"rownums_0\" AS (SELECT (ROW_NUMBER() OVER ()) - 1 AS \"rownum\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\"), \"vdates_0\" AS (SELECT DATEADD('day', \"rownum\", \"mindate\") AS \"date\" FROM \"dateinfo_0\", \"rownums_0\" WHERE \"rownum\" <= \"diff\"), \"dim_date_0\" AS (SELECT DATE_TRUNC('MONTH', \"date\") AS \"window_outer_0\", DATEADD('MONTH', 1, DATE_TRUNC('MONTH', \"date\")) AS \"window_outer_1\", EXTRACT(YEAR FROM \"date\") AS \"window_outer_4\", TO_CHAR(\"date\", 'MM-Mon') AS \"window_outer_5\", TO_CHAR(\"date\", 'YYYY') AS \"window_outer_6\", TO_CHAR(\"date\", 'MM') AS \"window_outer_7\" FROM \"vdates_0\" GROUP BY DATE_TRUNC('MONTH', \"date\"), DATEADD('MONTH', 1, DATE_TRUNC('MONTH', \"date\")), EXTRACT(YEAR FROM \"date\"), TO_CHAR(\"date\", 'MM-Mon'), TO_CHAR(\"date\", 'YYYY'), TO_CHAR(\"date\", 'MM')), \"dimensions\" AS (SELECT \"window_outer_0\", \"window_outer_1\", \"window_outer_2\", \"window_outer_3\", \"window_outer_4\", \"window_outer_5\", \"window_outer_6\", \"window_outer_7\" FROM \"dim_window_outer_2\", \"dim_date_0\"), \"window_outer\" AS (SELECT \"dimensions\".\"window_outer_0\" AS \"window_outer_0\", \"dimensions\".\"window_outer_1\" AS \"window_outer_1\", \"dimensions\".\"window_outer_2\" AS \"window_outer_2\", \"dimensions\".\"window_outer_3\" AS \"window_outer_3\", \"dimensions\".\"window_outer_4\" AS \"window_outer_4\", \"dimensions\".\"window_outer_5\" AS \"window_outer_5\", \"dimensions\".\"window_outer_6\" AS \"window_outer_6\", \"dimensions\".\"window_outer_7\" AS \"window_outer_7\" FROM \"dimensions\" LEFT JOIN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\" ON \"dimensions\".\"window_outer_0\" = DATE_TRUNC('MONTH', \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\") AND \"dimensions\".\"window_outer_1\" = DATEADD('MONTH', 1, DATE_TRUNC('MONTH', \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\")) AND (\"dimensions\".\"window_outer_2\" = \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\" OR \"dimensions\".\"window_outer_2\" IS NULL AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\" IS NULL) AND (\"dimensions\".\"window_outer_3\" = \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"OpportunityStage_SortOrder\" OR \"dimensions\".\"window_outer_3\" IS NULL AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"OpportunityStage_SortOrder\" IS NULL) AND \"dimensions\".\"window_outer_4\" = EXTRACT(YEAR FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\") AND \"dimensions\".\"window_outer_5\" = TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\", 'MM-Mon') AND \"dimensions\".\"window_outer_6\" = TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\", 'YYYY') AND \"dimensions\".\"window_outer_7\" = TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\", 'MM') AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"Opportunity_StageName\" IN ('Lead', 'Discovery', 'Qualification', 'Evaluation', 'Negotiation') AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\" >= '2017-10-01T00:00:00Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\".\"date\" < '2018-10-01T00:00:00Z')) GROUP BY \"dimensions\".\"window_outer_0\", \"dimensions\".\"window_outer_1\", \"dimensions\".\"window_outer_2\", \"dimensions\".\"window_outer_3\", \"dimensions\".\"window_outer_4\", \"dimensions\".\"window_outer_5\", \"dimensions\".\"window_outer_6\", \"dimensions\".\"window_outer_7\"), \"measures\" AS (SELECT (SELECT AVG(CAST(CASE WHEN \"windowFunction_0\".\"is_stage_transition\" = 1 THEN \"windowFunction_0\".\"days_in_stage\" ELSE NULL END AS FLOAT)) FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\" AS \"windowFunction_0\" WHERE \"windowFunction_0\".\"date\" >= \"window_outer\".\"window_outer_0\" AND \"windowFunction_0\".\"date\" <= \"window_outer\".\"window_outer_1\" AND (\"windowFunction_0\".\"Opportunity_StageName\" = \"window_outer\".\"window_outer_2\" OR \"windowFunction_0\".\"Opportunity_StageName\" IS NULL AND \"window_outer\".\"window_outer_2\" IS NULL) AND (\"windowFunction_0\".\"OpportunityStage_SortOrder\" = \"window_outer\".\"window_outer_3\" OR \"windowFunction_0\".\"OpportunityStage_SortOrder\" IS NULL AND \"window_outer\".\"window_outer_3\" IS NULL) AND \"windowFunction_0\".\"Opportunity_StageName\" IN ('Lead', 'Discovery', 'Qualification', 'Evaluation', 'Negotiation') GROUP BY \"windowFunction_0\".\"Opportunity_StageName\", \"windowFunction_0\".\"OpportunityStage_SortOrder\") AS \"window_outer_8\", \"window_outer\".\"window_outer_0\" AS \"window_outer_0\", \"window_outer\".\"window_outer_1\" AS \"window_outer_1\", \"window_outer\".\"window_outer_2\" AS \"window_outer_2\", \"window_outer\".\"window_outer_3\" AS \"window_outer_3\", \"window_outer\".\"window_outer_4\" AS \"window_outer_4\", \"window_outer\".\"window_outer_5\" AS \"window_outer_5\", \"window_outer\".\"window_outer_6\" AS \"window_outer_6\", \"window_outer\".\"window_outer_7\" AS \"window_outer_7\" FROM \"window_outer\") (SELECT \"measures\".\"window_outer_4\" AS \"c0\", SUBSTRING(\"measures\".\"window_outer_5\" FROM 4 FOR 3) AS \"c1\", \"measures\".\"window_outer_2\" AS \"c2\", \"measures\".\"window_outer_6\" AS \"c3\", \"measures\".\"window_outer_7\" AS \"c4\", \"measures\".\"window_outer_3\" AS \"c5\", \"measures\".\"window_outer_8\" AS \"m0\" FROM \"measures\" ORDER BY \"measures\".\"window_outer_4\", \"measures\".\"window_outer_5\", \"measures\".\"window_outer_3\", \"measures\".\"window_outer_6\", \"measures\".\"window_outer_7\", \"measures\".\"window_outer_3\", \"measures\".\"window_outer_8\")";
    // Q3 took 26369MS in redshift

    public static final String Q4 = "WITH \"dateinfo_0\" AS (SELECT MIN(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\") AS \"mindate\", DATEDIFF('DAY', MIN(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\"), MAX(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\")) AS \"diff\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\" WHERE \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"current_record\" = 1), \"rownums_0\" AS (SELECT (ROW_NUMBER() OVER ()) - 1 AS \"rownum\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\"), \"vdates_0\" AS (SELECT DATEADD('day', \"rownum\", \"mindate\") AS \"date\" FROM \"dateinfo_0\", \"rownums_0\" WHERE \"rownum\" <= \"diff\"), \"dim_date_0\" AS (SELECT EXTRACT(YEAR FROM \"date\") AS \"c0\", EXTRACT(QUARTER FROM \"date\") AS \"c1\", TO_CHAR(\"date\", 'YYYY') AS \"c2\", TO_CHAR(\"date\", 'Q') AS \"c3\" FROM \"vdates_0\" GROUP BY EXTRACT(YEAR FROM \"date\"), EXTRACT(QUARTER FROM \"date\"), TO_CHAR(\"date\", 'YYYY'), TO_CHAR(\"date\", 'Q')), \"outer_dateinfo_0\" AS (SELECT MIN(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\") AS \"mindate\", DATEDIFF('DAY', MIN(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\"), MAX(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\")) AS \"diff\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\" WHERE \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" >= '2017-04-01T00:00:00Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\" < '2018-10-15T18:54:17.959Z' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"current_record\" = 1), \"outer_rownums_0\" AS (SELECT (ROW_NUMBER() OVER ()) - 1 AS \"rownum\" FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c\"), \"outer_vdates_0\" AS (SELECT DATEADD('day', \"rownum\", \"mindate\") AS \"date\" FROM \"outer_dateinfo_0\", \"outer_rownums_0\" WHERE \"rownum\" <= \"diff\"), \"outer_dim_date_0\" AS (SELECT EXTRACT(YEAR FROM \"date\") AS \"c0\", EXTRACT(QUARTER FROM \"date\") AS \"c1\", TO_CHAR(\"date\", 'YYYY') AS \"c2\", TO_CHAR(\"date\", 'Q') AS \"c3\" FROM \"outer_vdates_0\" GROUP BY EXTRACT(YEAR FROM \"date\"), EXTRACT(QUARTER FROM \"date\"), TO_CHAR(\"date\", 'YYYY'), TO_CHAR(\"date\", 'Q')), \"dimensions\" AS (SELECT \"c0\", \"c1\", \"c2\", \"c3\" FROM \"dim_date_0\"), \"measures\" AS (SELECT \"dimensions\".\"c0\" AS \"c0\", \"dimensions\".\"c1\" AS \"c1\", \"dimensions\".\"c2\" AS \"c2\", \"dimensions\".\"c3\" AS \"c3\", (SUM(SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Amount__c\" ELSE 0 END + CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Expansion' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal') THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Amount__c\" - \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Renewal_Basis__c\" ELSE 0 END - CASE WHEN (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Lost' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Suspended/Abandoned') AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal' THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_MRR_Renewal_Basis__c\" * 12 ELSE 0 END)) OVER (ORDER BY \"dimensions\".\"c0\", \"dimensions\".\"c1\", \"dimensions\".\"c2\", \"dimensions\".\"c3\" ROWS BETWEEN UNBOUNDED PRECEDING AND 0 FOLLOWING)) / CAST(NULLIF(SUM(SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN 1 ELSE 0 END - CASE WHEN (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Lost' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Suspended/Abandoned') AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal' THEN 1 ELSE 0 END)) OVER (ORDER BY \"dimensions\".\"c0\", \"dimensions\".\"c1\", \"dimensions\".\"c2\", \"dimensions\".\"c3\" ROWS BETWEEN UNBOUNDED PRECEDING AND 0 FOLLOWING), 0) AS FLOAT) AS \"m0\", SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_ARR_Amount__c\" ELSE 0 END) / CAST(NULLIF(SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN 1 ELSE 0 END), 0) AS FLOAT) AS \"m1\", SUM(SUM(CASE WHEN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Won' AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'New Business' THEN 1 ELSE 0 END - CASE WHEN (\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Closed Lost' OR \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_StageName\" = 'Suspended/Abandoned') AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Type\" = 'Renewal' THEN 1 ELSE 0 END)) OVER (ORDER BY \"dimensions\".\"c0\", \"dimensions\".\"c1\", \"dimensions\".\"c2\", \"dimensions\".\"c3\" ROWS BETWEEN UNBOUNDED PRECEDING AND 0 FOLLOWING) AS \"m2\" FROM \"dimensions\" LEFT JOIN \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\" ON \"dimensions\".\"c0\" = EXTRACT(YEAR FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\") AND \"dimensions\".\"c1\" = EXTRACT(QUARTER FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\") AND \"dimensions\".\"c2\" = TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\", 'YYYY') AND \"dimensions\".\"c3\" = TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\", 'Q') AND \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"current_record\" = 1 GROUP BY \"dimensions\".\"c0\", \"dimensions\".\"c1\", \"dimensions\".\"c2\", \"dimensions\".\"c3\", EXTRACT(YEAR FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\"), EXTRACT(QUARTER FROM \"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\"), TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\", 'YYYY'), TO_CHAR(\"slapshot-c31fcac5-fb77-4703-9f2d-3fbe27b03c8c_currentrecord\".\"Opportunity_Term_Start_Date__c\", 'Q')) (SELECT \"dimensions\".\"c0\" AS \"c0\", 'Q' || \"dimensions\".\"c1\" AS \"c1\", \"dimensions\".\"c2\" AS \"c2\", \"dimensions\".\"c3\" AS \"c3\", \"measures\".\"m0\" AS \"m0\", \"measures\".\"m1\" AS \"m1\", \"measures\".\"m2\" AS \"m2\" FROM \"dimensions\" INNER JOIN \"measures\" ON \"dimensions\".\"c0\" = \"measures\".\"c0\" AND \"dimensions\".\"c1\" = \"measures\".\"c1\" AND \"dimensions\".\"c2\" = \"measures\".\"c2\" AND \"dimensions\".\"c3\" = \"measures\".\"c3\" INNER JOIN \"outer_dim_date_0\" ON \"measures\".\"c0\" = \"outer_dim_date_0\".\"c0\" AND \"measures\".\"c1\" = \"outer_dim_date_0\".\"c1\" AND \"measures\".\"c2\" = \"outer_dim_date_0\".\"c2\" AND \"measures\".\"c3\" = \"outer_dim_date_0\".\"c3\" ORDER BY \"dimensions\".\"c0\", \"dimensions\".\"c1\", \"dimensions\".\"c2\", \"dimensions\".\"c3\", \"measures\".\"m0\", \"measures\".\"m1\", \"measures\".\"m2\")\n";

    public static void main(String[] args) {
        String tableNameRegex = "(\"slapshot[^\"]*\")";

        String sql = tableNameSubstitution(tableNameRegex, Q4, "[^\"A-Za-z0-9_]", "_");

        // TODO: these are too greedy, FROM can be used in EXTRACT method too
        sql = fromClauseTableNameSubstitution(sql, "slapshot", "FROM");
        sql = fromClauseTableNameSubstitution(sql, "slapshot", "JOIN");

        sql = wrapTimestampComparisonsWithDateFunction(sql);
        sql = fixDateAdd(sql);
        sql = fixDateTrunc(sql);
        sql = fixDateToChar(sql);

        sql = sql.replaceAll("\"", "`");
        sql = sql.replaceAll("GETDATE\\(\\)", "CURRENT_DATE()");
        sql = sql.replaceAll("AS FLOAT", "AS NUMERIC");

        // --------------------------------------------------------------------------
        // TODO:
        // --------------------------------------------------------------------------
        // substring
        // FROM      SUBSTRING(`measures`.`window_outer_5` FROM 4 FOR 3)
        // TO        SUBSTR(`measures`.`window_outer_5`, 4, 3)

        // datediff
        // FROM      DATEDIFF('DAY', MIN(`slapshot_c31fcac5_fb77_4703_9f2d_3fbe27b03c8c`.`date`), MAX(`slapshot_c31fcac5_fb77_4703_9f2d_3fbe27b03c8c`.`date`))
        // TO        TIMESTAMP_DIFF(MAX(`slapshot_c31fcac5_fb77_4703_9f2d_3fbe27b03c8c_currentrecord`.`Opportunity_Term_Start_Date__c`), MIN(`slapshot_c31fcac5_fb77_4703_9f2d_3fbe27b03c8c_currentrecord`.`Opportunity_Term_Start_Date__c`), DAY)

        // another dateadd
        // FROM      DATEADD('day', `rownum`, `mindate`)
        // TO        DATE_ADD(DATE(`mindate`), INTERVAL `rownum` DAY)
        // --------------------------------------------------------------------------

        System.out.println(sql);
    }

    private static String tableNameSubstitution(String regex, String sql, String replaceThis, String withThis) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String val = m.group(1);
            String fixed = val.replaceAll(replaceThis, withThis);
            m.appendReplacement(sb, fixed);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String fromClauseTableNameSubstitution(String sql, String datasetName, String keyWord) {
        String regex = keyWord + " (\"slapshot[^\"]*\")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String val = m.group(1);
            String fixed = keyWord + " `" + datasetName + "`." + val;
            m.appendReplacement(sb, fixed);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * replaces <code>DATEADD('MONTH', -1, GETDATE())</code> with <code>DATE_ADD(CURRENT_DATE(), INTERVAL -1 MONTH)</code>
     * @param sql
     * @return
     */
    private static String fixDateAdd(String sql) {
        String regex = "DATEADD\\('([A-Z]*)',\\s*(-?\\d),\\s*GETDATE\\(\\)\\)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String datePart = m.group(1);
            int period = Integer.parseInt(m.group(2));
            String fixed = String.format("DATE_ADD(CURRENT_DATE(), INTERVAL %d %s)", period, datePart);
            m.appendReplacement(sb, fixed);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String wrapTimestampComparisonsWithDateFunction(String sql) {
        String regex = "(\\\"slapshot[^\\\"]*\\\"\\.\\\"[^\\\"]*[Dd][Aa][Tt][Ee][^\\\"]*\\\")\\s*([<=>]+)\\s*([^']*\\()";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String dateTableColumn = m.group(1);
            String operator = m.group(2);
            String dateFunc = m.group(3);
            String fixed = String.format("DATE(%s) %s %s", dateTableColumn, operator, dateFunc);
            m.appendReplacement(sb, fixed);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // FROM      DATE_TRUNC('MONTH', `date`)
    // TO        DATE_TRUNC(`date`, MONTH)
    private static String fixDateTrunc(String sql) {
        String regex = "DATE_TRUNC\\('([^']*)',\\s*\\\"([^\\\"]*)\\\"\\)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String datePart = m.group(1);
            String field = m.group(2);
            String fixed = String.format("DATE_TRUNC(`%s`, %s)", field, datePart);
            m.appendReplacement(sb, fixed);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // FROM      TO_CHAR("date", 'MM-Mon')
    // TO        TIMESTAMP_FORMAT('%m-%h', `date`)
    // FROM      TO_CHAR("date", 'Q')
    // TO        CAST(EXTRACT(QUARTER FROM `date`) AS STRING)
    private static String fixDateToChar(String sql) {

        // TODO: support table.column ref as well

        String regex = "TO_CHAR\\(\"([^\"]*)\",\\s*'([^']*)'\\)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String datePart = m.group(2);
            String field = m.group(1);
            String fixed = "";
            if (datePart.equalsIgnoreCase("YYYY")) {
                datePart = "%E4Y";
                fixed = String.format("TIMESTAMP_FORMAT('%s', `%s`)", datePart, field);
            } else if (datePart.equalsIgnoreCase("MM-Mon")) {
                datePart = "%m-%h";
                fixed = String.format("TIMESTAMP_FORMAT('%s', `%s`)", datePart, field);
            } else if (datePart.equalsIgnoreCase("Q")) {
                fixed = String.format("CAST(EXTRACT(QUARTER FROM `%s`) AS STRING)", field);
            }

            m.appendReplacement(sb, fixed);
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
