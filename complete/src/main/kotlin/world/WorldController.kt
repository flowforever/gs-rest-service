package world


import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.*


interface IController {
    var goodStr: String;

    @RequestMapping("good")
    fun good(): String {
        return goodStr
    };
}

@RestController
@RequestMapping("/kotlin")
class WorldController : IController {


    override var goodStr: String = "";
        get() {
            return field;
        }
        set(value) {
            field = value
        }

    data class TalkContent(val title: String, val date: String);

    @RequestMapping("/greeting")
    fun greeting(): TalkContent {
        return TalkContent("Hello", Date().toString())
    }

    @RequestMapping("/reflect")
    fun reflect(): MutableList<String> {
        //val cl = Thread.currentThread().contextClassLoader;
        //val packageElements = cl.getResources("world");

        //val dirFiles = packageElements.toList().map { File(it.file) };
        //val classes = dirFiles.map { findClasses(it, "world") };

        val list = mutableListOf<String>();

        return list;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    fun findClasses(directory: File, packageName: String): MutableList<Any> {

        val list = mutableListOf<Any>({})
        for (file in directory.listFiles()) {
            if (file.isDirectory()) {
                list.addAll(findClasses(file, packageName + "." + file.name));
            } else if (file.name.endsWith(".class")) {
                list.add(Class.forName(packageName + '.' + file.name.substring(0, file.name.length - 6)))
            }
        }
        return list;
    }
}