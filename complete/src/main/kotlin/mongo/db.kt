package mongo

/**
 * Created by trump.wang on 2016/7/15.
 */


import com.mongodb.MongoClient
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
    fun create():Any {
        val testDb = client.getDatabase("test");

        val items = testDb.getCollection("items");

        val document = Document("name","Trump "+ Date().toString()).append("date", Date());

        items.insertOne(document)

        data class Result(val count:Long, val document:Document);

        return Result(items.count(), document);
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