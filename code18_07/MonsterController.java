package code18_07;
import org.springframeeork.web.bind.annotation.*
@RestController
public class MonsterController {
	@GetMapping("/monster")
	public Monster monster(RequestParam(value="id")String id){
		return new Monster(id);
	}
}
