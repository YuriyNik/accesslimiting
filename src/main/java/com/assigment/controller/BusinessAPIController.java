package com.assigment.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/businesses")
public class BusinessAPIController {

    private final List<String> businesses = new ArrayList<>();

    @GetMapping
    @ResponseBody
    public List<String> getAllBusinesses() {
        return businesses;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getBusinessById(@PathVariable String id) {
        return findBusinessById(id);
    }

    @PostMapping
    @ResponseBody
    public String createBusiness(@RequestBody String business) {
        businesses.add(business);
        return "Business created successfully!";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String updateBusiness(@PathVariable String id, @RequestBody String updatedBusiness) {
        String business = findBusinessById(id);
        if (business != null) {
            businesses.remove(business);
            businesses.add(updatedBusiness);
            return "Business updated successfully!";
        } else {
            return "Business not found!";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteBusiness(@PathVariable String id) {
        String business = findBusinessById(id);
        if (business != null) {
            businesses.remove(business);
            return "Business deleted successfully!";
        } else {
            return "Business not found!";
        }
    }

    private String findBusinessById(String id) {
        return businesses.stream()
                .filter(b -> b.equals(id))
                .findFirst()
                .orElse(null);
    }
}
