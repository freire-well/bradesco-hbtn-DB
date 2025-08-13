import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class UsuarioOperations {

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        MongoDatabase database = connection.getDatabase();
        MongoCollection<Document> usuarios = database.getCollection("usuarios");
        usuarios.insertMany(List.of(
                new Usuario("Alice", 25).toDocument(),
                new Usuario("Bob", 30).toDocument(),
                new Usuario("Charlie", 35).toDocument()
        ));

        System.out.println("Usuários Inseridos com sucesso!");
        System.out.println("usuários cadastrados: " + usuarios.find());
        usuarios.updateOne(new Document("nome", "Bob"), new Document("$set", new Usuario("Bob", 32).toDocument()));

        System.out.println("Bob atualizado para: " + usuarios.find(new Document("nome", "Bob")));

        System.out.println("usuários cadastrados: " + usuarios.find());

        System.out.println("apagando Charlie: "+ usuarios.deleteOne(new Document("nome", "Charlie")));

        System.out.println("usuários cadastrados: " + usuarios.find());
    }

}
