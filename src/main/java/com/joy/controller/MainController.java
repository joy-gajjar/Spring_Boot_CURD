package com.joy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.joy.dao.UserRepository;
import com.joy.entities.User;

import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get")
    public String form(Model model) {
        Iterable<User> list= userRepository.findAll();
        List<User> list1 = new ArrayList<User>();
        list.forEach( list1::add);
        model.addAttribute("user", list1);
    	
        model.addAttribute("newUser", new User());
        return "index";
    }
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        // Retrieve the user from the database
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            // Return the user data if found
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // Return a 404 Not Found response if the user is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User updatedUser, RedirectAttributes redirectAttributes) {
        try {
            // Get the existing user from the database
            User existingUser = userRepository.findById(id).orElse(null);
            if (existingUser != null) {
                // Update user information
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setPassword(updatedUser.getPassword());
                // Save the updated user
                userRepository.save(existingUser);
                redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating the user.");
        }
        return "redirect:/"; // Redirect back to the homepage or wherever you need
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while deleting the user.");
        }
        return "redirect:/"; // Redirect back to the homepage or wherever you need
    }

    
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("newUser") User newUser, RedirectAttributes redirectAttributes) {
        try {
            userRepository.save(newUser);
            redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while adding the user.");
            return "redirect:/";
        }
    }

  
    
	}
    


