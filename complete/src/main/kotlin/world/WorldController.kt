package world

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("kotlin")
class WorldController {
    data class TalkContent(val title:String, val date: String);

    @RequestMapping("/greeting")
    fun greeting(): TalkContent {
        return TalkContent("Hello", Date().toString())
    }
}