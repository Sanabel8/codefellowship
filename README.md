# codefellowship
# lab16
# overview 
* Build an app that allows users to create their profile on CodeFellowship.
The site should have :
 * splash page at the root route (/)
 * ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio
 * allow users to create an ApplicationUser on the “sign up”
 * Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password)
 * ApplicationUser, at a route like /users/{id}.
This should include a default profile picture, which is the same for every user, and their basic information.
* Add a Post entity to your app.
A Post has a body and a createdAt timestamp.

* depandecy
import com.mycode.securedemo.appuser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


# lab17
# overview
Continue working in your codefellowship repo.
* Allow users to log in to CodeFellowship and create posts.
* Upon logging in, users should be taken to a /myprofile route that displays their information
* Add a Post entity to your app.
A Post has a body and a createdAt timestamp.
A logged-in user should be able to create a Post, and a post should belong to the user that created it.