package com.mindre.pensionat.security;

import com.mindre.pensionat.Models.Queue;
import com.mindre.pensionat.Repo.QueueRepository;
import jakarta.validation.Valid;
import org.hibernate.sql.model.PreparableMutationOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.UUID;



@Controller
public class QueueController extends BaseController {
    @Autowired
    QueueRepository queueRepository;

    @GetMapping(path="/queues")
    String empty(Model model)
    {
        model.addAttribute("activeFunction", "queues");
        setupVersion(model);

        var listOfViewModels = new ModelMapper().map(queueRepository.findAll(), QueueController[].class);

        model.addAttribute("queues", listOfViewModels);
        return "queues";
    }

    @GetMapping(path="/queues/new")
    String Mew( Model model){

        model.addAttribute("queue", new Queue());
        model.addAttribute("activeFunction", "queues");
        return "newqueue";
    }

    @PostMapping(path="/queues/new")
    String SaveNew(@Valid Queue queue, BindingResult result, Model model){
        model.addAttribute("activeFunction", "queues");
        if(result.hasErrors()){
            return "newqueue";
        }
        var f = new Queue();
        f.setName(queue.getName());
        f.setRoomIdCSV(queue.getRoomIdCSV());
        f.setMessagesToSend(queue.getMessagesToSend());

        queueRepository.save(f);
        return "redirect:/queues";
    }


    @GetMapping(path="/queues/edit/{id}")
    String Edit(@PathVariable("id") UUID id, Model model){
        model.addAttribute("activeFunction", "queues");

        var queue = queueRepository.findById(id).orElseThrow();

        model.addAttribute("queue", queue);

        model.addAttribute("activeFunction", "queues");
        return "editqueue";
    }



    @PostMapping(path="/queues/edit/{id}")
    String Save(@PathVariable("id") UUID id,  @Valid Queue queue, BindingResult result, Model model){
        model.addAttribute("activeFunction", "queues");
        if(result.hasErrors()){
            return "editqueue";
        }
        var f = queueRepository.findById(id).orElseThrow();
        f.setName(queue.getName());
        f.setRoomIdCSV(queue.getRoomIdCSV());
        f.setMessagesToSend(queue.getMessagesToSend());
        queueRepository.save(f);
        return "redirect:/queues";
    }








}