package mongo

/**
 * Created by trump.wang on 2016/7/15.
 */


import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import org.bson.Document
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/mongo")
class DbController {
    var client: MongoClient;

    init{
        client = MongoClient("localhost");
    }


    @RequestMapping("/connect")
    fun connect() {

    }

    @RequestMapping("/dbs")
    fun dbs(): List<String> {
        return client.listDatabaseNames().toList();
    }

    @RequestMapping("/create")
    fun create():Long {
        var testDb = client.getDatabase("test");

        var items = testDb.getCollection("items");

        items.insertOne(Document("name","Trump "+ Date().toString()).append("date", Date()))

        return items.count();
    }

    @RequestMapping("/all")
    fun getAll(): List<Any> {
        var testDb = client.getDatabase("test");
        var items = testDb.getCollection("items");
        var all = items.find();

        data class DataItem(val name:String, val date:Date);

        return all.toList().map { DataItem ( it.getString("name"), it.getDate("date")) };
    }

    @RequestMapping("/find")
    fun find(){}

}