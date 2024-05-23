package com.mindre.pensionat.security;




import jakarta.validation.Valid;
import org.hibernate.sql.model.PreparableMutationOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@@ -24,6 +24,7 @@ public class QueueController extends BaseController {
    @GetMapping(path="/queues")
    String empty(Model model)
    {

        model.addAttribute("activeFunction", "queues");
        setupVersion(model);

        @@ -56,6 +57,7 @@ String SaveNew(@Valid Queue queue, BindingResult result, Model model){


        @GetMapping(path="/queues/edit/{id}")
        @PreAuthorize("isAuthenticated()")
        String Edit(@PathVariable("id") UUID id, Model model){
            model.addAttribute("activeFunction", "queues");