package Models;

import org.bson.Document;

public interface IMongoWorkable {

    void pushing(Document document);

    void parsing(Document document);
}
