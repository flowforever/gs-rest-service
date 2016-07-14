package world

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

interface IController {
    var goodStr: String;

    @RequestMapping("good")
    fun good(): String {
        return goodStr
    };
}

@RestController
@RequestMapping("kotlin")
class WorldController : IController {


    override var goodStr: String = "";
        get() = field
        set(value) {
            field = value
        }


    data class TalkContent(val title: String, val date: String);

    @RequestMapping("/greeting")
    fun greeting(): TalkContent {
        return TalkContent("Hello", Date().toString())
    }
}