package com.codingdojo.authentication.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.authentication.models.Book;
import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.services.BookService;
import com.codingdojo.authentication.services.UserService;

// .. don't forget to include all your imports! ..
    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BookService bookService;
   
    @GetMapping("/")
    public String index( @ModelAttribute("newUser") User newUser, 
    		@ModelAttribute("newLogin") LoginUser newLogin) {
   
              return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
            BindingResult result, Model model, HttpSession session) {

        // Call the register method in the service for extra validations and user creation
        User registeredUser = userService.register(newUser, result);

        if (result.hasErrors()) {
            // Be sure to send in the empty LoginUser before re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        // No errors!
        // Store the user's ID from the DB in the session to log them in.
        session.setAttribute("userId", registeredUser.getId());

        return "redirect:/welcome";
    }
    
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {
    	
         User user = userService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());


        return "redirect:/welcome";
    }
    
    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        // Check if the user is logged in by verifying if the userId attribute exists in the session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // User is not logged in, redirect to the login page
            return "redirect:/";
        }

        // User is logged in, retrieve the user object from the session or database
        User user = userService.findUser(userId);
        if (user == null) {
            // User not found, redirect to the login page
            return "redirect:/";
        }
        
        List<Book> books= bookService.allBooks();
        // User is authenticated, pass the user object to the view for display
        model.addAttribute("user", user);
        model.addAttribute("books", books);


        return "welcome.jsp";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // Clear the session
        session.invalidate();
        return "redirect:/";
    }
    
    
    @GetMapping("/books/new")
    public String newBook( @ModelAttribute("newBook") Book newBook, Model model, HttpSession session) {
    	
    	// Check if the user is logged in by verifying if the userId attribute exists in the session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // User is not logged in, redirect to the login page
            return "redirect:/";
        }

        // User is logged in, retrieve the user object from the session or database
        User user = userService.findUser(userId);
        if (user == null) {
            // User not found, redirect to the login page
            return "redirect:/";
        }

        // User is authenticated, pass the user object to the view for display
        model.addAttribute("user", user);

        return "newBook.jsp";
    }
    
    
    
    @PostMapping("/books/new")
    public String saveBook( @Valid @ModelAttribute("newBook") Book newBook, BindingResult result) {
    	
    	if (result.hasErrors()) {
            return "newBook.jsp";
        } 
    	
    	bookService.createBook(newBook);
		return "redirect:/books/"+ newBook.getId();

    }
    
    @GetMapping("/books/{id}")
    public String showBook( @PathVariable("id") Long bookId, Model model,  HttpSession session) {
    	// Check if the user is logged in by verifying if the userId attribute exists in the session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // User is not logged in, redirect to the login page
            return "redirect:/";
        }

        // User is logged in, retrieve the user object from the session or database
        User user = userService.findUser(userId);
        if (user == null) {
            // User not found, redirect to the login page
            return "redirect:/";
        }

        Book book = bookService.findBook(bookId);

        // User is authenticated, pass the user object to the view for display
        model.addAttribute("user", user);
        model.addAttribute("book" , book);
        return "showBook.jsp";
    }
    
    @GetMapping("/books/{id}/edit")
    public String editBook( @PathVariable("id") Long id, Model model, HttpSession session) {
    	
    	// Check if the user is logged in by verifying if the userId attribute exists in the session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // User is not logged in, redirect to the login page
            return "redirect:/";
        }

        // User is logged in, retrieve the user object from the session or database
        User user = userService.findUser(userId);
        if (user == null) {
            // User not found, redirect to the login page
            return "redirect:/";
        }
        
        
        Book book = bookService.findBook(id);
        // User is the same user who posted the book to be edited
        if (!user.equals(book.getUser())) {
            // not the user who posted the book
            return "redirect:/welcome";
        }
        
        
        model.addAttribute("book", book);
        // User is authenticated, pass the user object to the view for display
        model.addAttribute("user", user);

        return "edit.jsp";
    }
    
    
    @RequestMapping(value="/books/{id}/edit", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "edit.jsp";
        } else {
        	bookService.updateBook(book);
    		return "redirect:/books/"+ book.getId();
        }
    }
    
    @RequestMapping(value="/books/{id}/delete", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
    	
    	bookService.deleteBook(id);
        return "redirect:/welcome";
    }
    
    
    

}
    

