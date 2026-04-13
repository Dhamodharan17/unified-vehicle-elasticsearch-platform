In Elasticsearch, an index represents a collection of documents. Each vehicle is a document, and fields like make and model are automatically indexed using inverted indexes. Text fields are analyzed for full-text search, while keyword fields support exact matches

@Document(indexName = "vehicles")
means:
👉 Store each Vehicle object as a document inside the "vehicles" index

| SQL World      | Elasticsearch |
| -------------- | ------------- |
| Database Table | Index         |
| Row            | Document      |
| Column         | Field         |

So your Vehicle class = one document schema inside the vehicles index
--------------------------
If you want to explicitly control indexing behavior:

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Field(type = FieldType.Text)
private String make;

@Field(type = FieldType.Keyword)
private String vin;
Difference:
Field Type	Behavior
Text	Tokenized → full-text search (TF-IDF applies)
Keyword	Not tokenized → exact match

👉 In Elasticsearch:

Index (big) = collection of documents (vehicles)
Inverted index (internal) = created automatically per field

vehicles (index)
   ├── Vehicle 1 (document)
   ├── Vehicle 2 (document)
   └── ...
        ├── make → inverted index
        ├── model → inverted index
        └── ...


🔹 5. Why whole Vehicle is an index?

Because:

👉 You are modeling a searchable entity

Each vehicle:

is independently searchable
has multiple attributes
needs filtering + scoring

So:
✔ One vehicle = one document
✔ Many vehicles = one index

✔ Elasticsearch automatically:

tokenizes text fields
builds inverted index
applies BM25 (modern TF-IDF)