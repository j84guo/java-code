package mysqlspringdata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mysqlspringdata.data.entity.User;
import mysqlspringdata.data.repository.UserRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@ResponseBody
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method=GET)
    public String addNewUser (@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Saved";
    }

    @RequestMapping(method=GET, path="/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}